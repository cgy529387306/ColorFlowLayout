package cn.ifavor.flowlayout.utils;

import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout.LayoutParams;

public class MeasureUtils {
	/**
	 * 根据父 View 规则和子 View 的 LayoutParams，计算子类的宽度(width)测量规则
	 * 
	 * @param widthMeasureSpec
	 * @param view
	 */
	public static int createChildWidthMeasureSpec(int parentWidthMeasureSpec,
			View view) {
		// 获取父 View 的测量模式
		int parentWidthMode = MeasureSpec.getMode(parentWidthMeasureSpec);
		// 获取父 View 的测量尺寸
		int parentWidthSize = MeasureSpec.getSize(parentWidthMeasureSpec);

		// 定义子 View 的测量规则
		int childWidthMeasureSpec = 0;

		// 获取子 View 的 LayoutParams
		LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();

		if (parentWidthMode == MeasureSpec.EXACTLY) {
			/* 这是当父类的模式是 dp 的情况 */
			if (layoutParams.width > 0) {
				childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(
						layoutParams.width, MeasureSpec.EXACTLY);
			} else if (layoutParams.width == LayoutParams.WRAP_CONTENT) {
				childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(
						parentWidthSize, MeasureSpec.AT_MOST);
			} else if (layoutParams.width == LayoutParams.MATCH_PARENT) {
				childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(
						parentWidthSize, MeasureSpec.EXACTLY);
			}
		} else if (parentWidthMode == MeasureSpec.AT_MOST) {
			/* 这是当父类的模式是 WRAP_CONTENT 的情况 */
			if (layoutParams.width > 0) {
				childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(
						layoutParams.width, MeasureSpec.EXACTLY);
			} else if (layoutParams.width == LayoutParams.WRAP_CONTENT) {
				childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(
						parentWidthSize, MeasureSpec.AT_MOST);
			} else if (layoutParams.width == LayoutParams.MATCH_PARENT) {
				childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(
						parentWidthSize, MeasureSpec.EXACTLY);
			}
		} else if (parentWidthMode == MeasureSpec.UNSPECIFIED) {
			/* 这是当父类的模式是 MATCH_PARENT 的情况 */
			if (layoutParams.width > 0) {
				childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(
						layoutParams.width, MeasureSpec.EXACTLY);
			} else if (layoutParams.width == LayoutParams.WRAP_CONTENT) {
				childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(0,
						MeasureSpec.UNSPECIFIED);
			} else if (layoutParams.width == LayoutParams.MATCH_PARENT) {
				childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(0,
						MeasureSpec.UNSPECIFIED);
			}
		}

		// 返回子 View 的测量规则
		return childWidthMeasureSpec;
	}

	/**
	 * 根据父 View 规则和子 View 的 LayoutParams，计算子类的宽度(width)测量规则
	 * 
	 * @param widthMeasureSpec
	 * @param view
	 */
	public static int createChildHeightMeasureSpec(
			int parentHeightMeasureSpec, View view) {
		int parentHeightMode = MeasureSpec.getMode(parentHeightMeasureSpec);
		int parentHeightSize = MeasureSpec.getSize(parentHeightMeasureSpec);

		int childHeightMeasureSpec = 0;

		LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();

		if (parentHeightMode == MeasureSpec.EXACTLY) {
			if (layoutParams.height > 0) {
				childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
						layoutParams.height, MeasureSpec.EXACTLY);
			} else if (layoutParams.height == LayoutParams.WRAP_CONTENT) {
				childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
						parentHeightSize, MeasureSpec.AT_MOST);
			} else if (layoutParams.height == LayoutParams.MATCH_PARENT) {
				childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
						parentHeightSize, MeasureSpec.EXACTLY);
			}
		} else if (parentHeightMode == MeasureSpec.AT_MOST) {
			if (layoutParams.height > 0) {
				childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
						layoutParams.height, MeasureSpec.EXACTLY);
			} else if (layoutParams.height == LayoutParams.WRAP_CONTENT) {
				childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
						parentHeightSize, MeasureSpec.AT_MOST);
			} else if (layoutParams.height == LayoutParams.MATCH_PARENT) {
				childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
						parentHeightSize, MeasureSpec.EXACTLY);
			}
		} else if (parentHeightMode == MeasureSpec.UNSPECIFIED) {
			if (layoutParams.height > 0) {
				childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
						layoutParams.height, MeasureSpec.EXACTLY);
			} else if (layoutParams.height == LayoutParams.WRAP_CONTENT) {
				childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(0,
						MeasureSpec.UNSPECIFIED);
			} else if (layoutParams.height == LayoutParams.MATCH_PARENT) {
				childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(0,
						MeasureSpec.UNSPECIFIED);
			}
		}

		return childHeightMeasureSpec;
	}

}
