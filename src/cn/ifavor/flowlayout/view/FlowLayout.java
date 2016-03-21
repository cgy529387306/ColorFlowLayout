package cn.ifavor.flowlayout.view;

import java.util.ArrayList;
import java.util.List;

import cn.ifavor.flowlayout.utils.MeasureUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class FlowLayout extends LinearLayout{

	private static final int HORIZONTOL_SPACE = 26;
	private static final int VERTICAL_SPACE = 26;

	public FlowLayout(Context context) {
		super(context);
	}
	
	public FlowLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}
	
	@Override
protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	
	// 防止因复杂时，来来回回测量，onMeasure多次调用。
	mLines.clear();
	usedWidth = 0;
	
	for (int i = 0; i < getChildCount(); i++){
		View view = getChildAt(i);
		int createChildWidthMeasureSpec = MeasureUtils.createChildWidthMeasureSpec(widthMeasureSpec, view);
		int createChildHeightMeasureSpec = MeasureUtils.createChildHeightMeasureSpec(heightMeasureSpec, view);
		view.measure(createChildWidthMeasureSpec, createChildHeightMeasureSpec);
		
		// 测量后，可以直接使用 view.getMeasuredWidth() 获取实际宽高了
		System.out.println("View MeasureWidth: " + view.getMeasuredWidth());
		System.out.println("View MeasureHeight: " + view.getMeasuredHeight());
	}
	
	// 不能用 measure 父 View 自己，也不能直接在 onMeasure 中，用 getMeasuredWidth() 获得测量后的值
	// measure(widthMeasureSpec, heightMeasureSpec);
	
	// 获取父 View 的宽度
	int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
	// 加上 padding 后的处理
	parentWidth = parentWidth - getPaddingLeft() - getPaddingRight();
	
	// 将子 View 的添加操作，从 onLayout 移到 onMeasure
	currentLine = new Line();
	for (int i = 0; i < getChildCount(); i++){
		View childView = getChildAt(i);
		
		usedWidth += childView.getMeasuredWidth();
		// 修改了一个重要的 bug
//			if (usedWidth > parentWidth){
		if (usedWidth >= parentWidth){
			if (currentLine.size() < 1){
				currentLine.addChild(childView);
			}
			newLine(childView, false);
		} else {
			currentLine.addChild(childView);
			usedWidth += HORIZONTOL_SPACE;
			if (usedWidth > parentWidth){
				newLine(childView, true);
			}
		}
	}
	mLines.add(currentLine);
	System.out.println("lines: " + mLines.size());
	
	// 分别设置父 View 的尺寸
	int parentHeight = 0;
	for (int i = 0; i < mLines.size(); i++){
		parentHeight += mLines.get(i).getHeight();
		parentHeight += VERTICAL_SPACE;
	}
	// 给高度增加上边距和下边距
	parentHeight = parentHeight + getPaddingTop() + getPaddingBottom();
	
	parentWidth = parentWidth + getPaddingLeft() + getPaddingRight();
	setMeasuredDimension(parentWidth, parentHeight);
}


	List<Line> mLines = new ArrayList<FlowLayout.Line>();
	int usedWidth = 0;
	Line currentLine = null;
	
@Override
protected void onLayout(boolean changed, int l, int t, int r, int b) {
	// 计算剩余宽度
	calcSpaceWidth();
	
	int left = getPaddingLeft();
	int top = getPaddingTop();
	for (int i = 0; i < mLines.size(); i++){
		Line line = mLines.get(i);
		line.layout(left, top);
		top += line.getHeight() + VERTICAL_SPACE;
	}
}
	
/* 计算每一行的剩余空间大小 */
private void calcSpaceWidth() {
	for (int i = 0; i < mLines.size(); i++){
		Line line = mLines.get(i);
		int used = 0;
		for (int j = 0; j < line.children.size(); j++){
			used += line.children.get( j ).getMeasuredWidth();
		}
		used += (line.children.size() - 1) * HORIZONTOL_SPACE;
		line.spaceWidth = getMeasuredWidth() - used - getPaddingLeft() - getPaddingRight();
	}
}

	/*
	 * childView 子 view
	 * isSpaceNewLine 是否因为 HORZENTAL_SPACE 放不下而换行
	 */
	private void newLine(View childView, boolean isSpaceNewLine) {
		mLines.add(currentLine);
		usedWidth = 0;
		currentLine = new Line();		
		// 如果不是因为HORZENTAL_SPACE 放不下而换行，就将子 view 添加到下一行，否则就不添加
		if (!isSpaceNewLine){
			currentLine.addChild(childView);
		}
		usedWidth += childView.getMeasuredWidth();
	}

	/**
	 * 每一行的内部类
	 * @author Administrator
	 *
	 */
	private class Line{
		public List<View> children = new ArrayList<View>();
		/* 该行高度（== 该行最大子 TextView 高度） */
		private int height = 0;
		/* 剩余的宽度 */
		public int spaceWidth; 

		public void addChild(View childView) {
			children.add(childView);
			// height 为该行所有子 view 的最大高度 
			int childHeight = childView.getMeasuredHeight();
			if (childHeight > height){
				height = childHeight;
			}
		}

		public int getHeight() {
			return height;
		}

/**
 * 每一行的布局方法
 * @param i 第几行
 * @param j 第几个 -> 对应 children 中的 index
 */
public void layout(int left, int top) {
	int eachExtraSpace = 0;
	if (spaceWidth / children.size() > 0){
		eachExtraSpace = spaceWidth / children.size();
	}
	
	for (int i = 0; i < children.size(); i++){
		View view = children.get(i);
		// 普通效果
		// view.layout(left, top, left + view.getMeasuredWidth(), top + view.getMeasuredHeight()); 
		// 瀑布流效果
		// view.layout(left, top, left + getMeasuredWidth(), top + getMeasuredHeight()); 
		// 普通效果 + 分配剩余空间
		int realWidth = view.getMeasuredWidth() + eachExtraSpace;
		view.layout(left, top, left + realWidth, top + view.getMeasuredHeight()); 
		left += realWidth;
		left +=  + HORIZONTOL_SPACE;
	}
}
		public int size(){
			return children.size();
		}
	}
	
}
