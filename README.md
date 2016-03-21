具体请参考：http://blog.csdn.net/anydrew/article/details/50945256

## 前言

前面已经了解了 [Android 测量（onMeasure） 的原理](http://blog.csdn.net/anydrew/article/details/50935362)，今天我们就用 onMeasure 和 onLayout 实现一个流动标签布局的案例，效果就是对 ViewGrop 中的 TextView 横向排列显示，一行放不下自动换行，并且将剩余的空间平均分配给该行的其他 TextView。效果图如下：

![这里写图片描述](http://img.blog.csdn.net/20160321112225380)


## 实现流程

1. 创建 FlowLayout，继承自 LinearLayout。
2. 重写 onMeasure，完成对每个子 View 的测量，确定自己的宽高。
3. 重写 onLayout，根据算法实现子 View 的布局。


## 具体实现

### onMeasure

在自定义 ViewGroup 过程中，父 View 其实并不知道子 View 的宽高，必须重写父 View 的 onMeasure，对子 View 进行遍历测量后，子 View  的宽高才能被确定。有时为了方便，我们不直接重写的 ViewGroup 的    onMeasure 方法，而是通过继承既有的布局（LinearLayout、RelativeLayout、FramLayout 等）巧妙的避开测量。

但是在这个案例中，我们不得不自己测量，因为：

1. 父 View 需要根据子 TextView 的排列的行数确定自己的高度。
2. 在测量的同时需要确定 TextView 的分布情况，即每行有几个 TextView。

下面是 onMeasure 的具体实现

**第一步：定义内部类 Line，存储每行标签**

成员变量：
`List<View> children`：存储每一行的子 TextView。
`int spaceWidth`：每一行的剩余宽度

方法：
`addChild(View childView)` ：添加子 View，并计算最大高度
`layout(int left, int top)`：布局这一行的子 TextView
`getHeight()`：获取高度
`getSize()`：获取子 TextView 个数


```
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
```

**第二步：实现 onMeasure 方法**

这一步主要完成以下操作：
1. 测量子 TextView 的宽高
2. 将子 TextView 分配到每一行
3. 设置父 View 的最终宽高

其中：`createChildWidthMeasureSpec(int, int)` 和`createChildHeightMeasureSpec(int, int)` 方法的介绍，请看我之前的文章：
[《自定义控件：onMeasure 方法和测量原理的理解》](http://blog.csdn.net/anydrew/article/details/50935362)

```
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

	/*********** 以上为测量过程，以下为 TextView 的分配过程 ****************/	

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
		// 注意：这里必须使用 >=（惨痛的教训啊）
        //if (usedWidth > parentWidth){
		if (usedWidth >= parentWidth){
			// 必须每行有一个标签，强制放下第一个
			if (currentLine.size() < 1){
				currentLine.addChild(childView);
			}
			newLine(childView, false);
		} else {
			// 添加子 TextView 后，重新计算剩余空间
			currentLine.addChild(childView);
			usedWidth += HORIZONTOL_SPACE;
			// 当最后一个 TextView 的空间不足时，换行
			if (usedWidth > parentWidth){
				newLine(childView, true);
			}
		}
	}
	mLines.add(currentLine);
	
	// 分别设置父 View 的尺寸
	int parentHeight = 0;
	for (int i = 0; i < mLines.size(); i++){
		parentHeight += mLines.get(i).getHeight();
		parentHeight += VERTICAL_SPACE;
	}
	// 给高度增加上边距和下边距
	parentHeight = parentHeight + getPaddingTop() + getPaddingBottom();
	
	parentWidth = parentWidth + getPaddingLeft() + getPaddingRight();
	// 最终设置父 View 自己的高度
	setMeasuredDimension(parentWidth, parentHeight);
}

```

### onLayout 代码：

**计算每行剩余空间**

有了每一行 TextView 的分布情况，我们就可以根据 TextView 和宽度和间距，计算出每一行剩余（没用完）的宽度，并将此值告诉每一行的 Line 对象。

```
/* 计算每一行的剩余空间大小 */
private void calcSpaceWidth() {
	for (int i = 0; i < mLines.size(); i++){
		Line line = mLines.get(i);
		int used = 0;
		for (int j = 0; j < line.children.size(); j++){
			used += line.children.get( j ).getMeasuredWidth();
		}
		used += (line.children.size() - 1) * HORIZONTOL_SPACE;
		
		// 加上了 padding 后的处理
		line.spaceWidth = getMeasuredWidth() - used - getPaddingLeft() - getPaddingRight();
	}
}

```

**确定每一行的位置**

```
@Override
protected void onLayout(boolean changed, int l, int t, int r, int b) {
	// 计算剩余宽度
	calcSpaceWidth();
	
	int left = getPaddingLeft();
	int top = getPaddingTop();
	// 主要是循环确定每一行的位置，行内每个 TextView, 交给行的 layout 方法
	for (int i = 0; i < mLines.size(); i++){
		Line line = mLines.get(i);
		line.layout(left, top);
		top += line.getHeight() + VERTICAL_SPACE;
	}
}
```

**确定每一行的子 TextView 的位置**

```
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
```
 可以看到，实现剩余空间的分配其实只要给 `view.layout(l,t,r,b)` 的第三个参数加上分配后的值。



遇到的坑：

1. 不能在自定义控件中直接使用 `getWidth()` 获取高度。
    区别：`getWidth()` 返回的是显示在屏幕上的，`getMeasureWidth()` 是实际大小，onMeasure、onLayout 执行时，控件还未显示，所以 `getWidth()` 始终为 0。注意：显示的大小不一定是实际大小。

2. 不能在 onMeasure 中使用 `getMeasureWidth()` 获取宽度，否则会造成无限递归调用，报 `java.lang.StackOverFlowError` 

3. 换行逻辑非常关键，因为放不下最后一个子 View 而换行则必须将它添加到下一行，如果是因为间距放不下，而最后一个子 View 已经添加到该行，则不必重复添加到下一行。

4. 换行时，最后一行默认是不会被添加的，必须手动添加

5. 不能使用 >，而要使用 >= 判断剩余空间是否足够，否则当宽度刚好相等，最后一个元素明明可以放得下，也被强制换行了。

## 附录

附上几个优秀的“流动标签布局”开源框架，供大家参考学习

1. [BGAFlowLayout-Android](https://github.com/bingoogolapple/BGAFlowLayout-Android)
2. [FlowLayout](https://github.com/hongyangAndroid/FlowLayout)
3. [tagcloud](https://github.com/fyales/tagcloud)
4. [AndroidFlowLayout](https://github.com/LyndonChin/AndroidFlowLayout)
