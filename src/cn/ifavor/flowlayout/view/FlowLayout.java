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
	
	// ��ֹ����ʱ�������ػز�����onMeasure��ε��á�
	mLines.clear();
	usedWidth = 0;
	
	for (int i = 0; i < getChildCount(); i++){
		View view = getChildAt(i);
		int createChildWidthMeasureSpec = MeasureUtils.createChildWidthMeasureSpec(widthMeasureSpec, view);
		int createChildHeightMeasureSpec = MeasureUtils.createChildHeightMeasureSpec(heightMeasureSpec, view);
		view.measure(createChildWidthMeasureSpec, createChildHeightMeasureSpec);
		
		// �����󣬿���ֱ��ʹ�� view.getMeasuredWidth() ��ȡʵ�ʿ����
		System.out.println("View MeasureWidth: " + view.getMeasuredWidth());
		System.out.println("View MeasureHeight: " + view.getMeasuredHeight());
	}
	
	// ������ measure �� View �Լ���Ҳ����ֱ���� onMeasure �У��� getMeasuredWidth() ��ò������ֵ
	// measure(widthMeasureSpec, heightMeasureSpec);
	
	// ��ȡ�� View �Ŀ��
	int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
	// ���� padding ��Ĵ���
	parentWidth = parentWidth - getPaddingLeft() - getPaddingRight();
	
	// ���� View ����Ӳ������� onLayout �Ƶ� onMeasure
	currentLine = new Line();
	for (int i = 0; i < getChildCount(); i++){
		View childView = getChildAt(i);
		
		usedWidth += childView.getMeasuredWidth();
		// �޸���һ����Ҫ�� bug
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
	
	// �ֱ����ø� View �ĳߴ�
	int parentHeight = 0;
	for (int i = 0; i < mLines.size(); i++){
		parentHeight += mLines.get(i).getHeight();
		parentHeight += VERTICAL_SPACE;
	}
	// ���߶������ϱ߾���±߾�
	parentHeight = parentHeight + getPaddingTop() + getPaddingBottom();
	
	parentWidth = parentWidth + getPaddingLeft() + getPaddingRight();
	setMeasuredDimension(parentWidth, parentHeight);
}


	List<Line> mLines = new ArrayList<FlowLayout.Line>();
	int usedWidth = 0;
	Line currentLine = null;
	
@Override
protected void onLayout(boolean changed, int l, int t, int r, int b) {
	// ����ʣ����
	calcSpaceWidth();
	
	int left = getPaddingLeft();
	int top = getPaddingTop();
	for (int i = 0; i < mLines.size(); i++){
		Line line = mLines.get(i);
		line.layout(left, top);
		top += line.getHeight() + VERTICAL_SPACE;
	}
}
	
/* ����ÿһ�е�ʣ��ռ��С */
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
	 * childView �� view
	 * isSpaceNewLine �Ƿ���Ϊ HORZENTAL_SPACE �Ų��¶�����
	 */
	private void newLine(View childView, boolean isSpaceNewLine) {
		mLines.add(currentLine);
		usedWidth = 0;
		currentLine = new Line();		
		// ���������ΪHORZENTAL_SPACE �Ų��¶����У��ͽ��� view ��ӵ���һ�У�����Ͳ����
		if (!isSpaceNewLine){
			currentLine.addChild(childView);
		}
		usedWidth += childView.getMeasuredWidth();
	}

	/**
	 * ÿһ�е��ڲ���
	 * @author Administrator
	 *
	 */
	private class Line{
		public List<View> children = new ArrayList<View>();
		/* ���и߶ȣ�== ��������� TextView �߶ȣ� */
		private int height = 0;
		/* ʣ��Ŀ�� */
		public int spaceWidth; 

		public void addChild(View childView) {
			children.add(childView);
			// height Ϊ���������� view �����߶� 
			int childHeight = childView.getMeasuredHeight();
			if (childHeight > height){
				height = childHeight;
			}
		}

		public int getHeight() {
			return height;
		}

/**
 * ÿһ�еĲ��ַ���
 * @param i �ڼ���
 * @param j �ڼ��� -> ��Ӧ children �е� index
 */
public void layout(int left, int top) {
	int eachExtraSpace = 0;
	if (spaceWidth / children.size() > 0){
		eachExtraSpace = spaceWidth / children.size();
	}
	
	for (int i = 0; i < children.size(); i++){
		View view = children.get(i);
		// ��ͨЧ��
		// view.layout(left, top, left + view.getMeasuredWidth(), top + view.getMeasuredHeight()); 
		// �ٲ���Ч��
		// view.layout(left, top, left + getMeasuredWidth(), top + getMeasuredHeight()); 
		// ��ͨЧ�� + ����ʣ��ռ�
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
