package cn.ifavor.flowlayout.utils;

import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout.LayoutParams;

public class MeasureUtils {
	/**
	 * ���ݸ� View ������� View �� LayoutParams����������Ŀ��(width)��������
	 * 
	 * @param widthMeasureSpec
	 * @param view
	 */
	public static int createChildWidthMeasureSpec(int parentWidthMeasureSpec,
			View view) {
		// ��ȡ�� View �Ĳ���ģʽ
		int parentWidthMode = MeasureSpec.getMode(parentWidthMeasureSpec);
		// ��ȡ�� View �Ĳ����ߴ�
		int parentWidthSize = MeasureSpec.getSize(parentWidthMeasureSpec);

		// ������ View �Ĳ�������
		int childWidthMeasureSpec = 0;

		// ��ȡ�� View �� LayoutParams
		LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();

		if (parentWidthMode == MeasureSpec.EXACTLY) {
			/* ���ǵ������ģʽ�� dp ����� */
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
			/* ���ǵ������ģʽ�� WRAP_CONTENT ����� */
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
			/* ���ǵ������ģʽ�� MATCH_PARENT ����� */
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

		// ������ View �Ĳ�������
		return childWidthMeasureSpec;
	}

	/**
	 * ���ݸ� View ������� View �� LayoutParams����������Ŀ��(width)��������
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
