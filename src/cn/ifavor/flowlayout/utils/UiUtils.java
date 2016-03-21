package cn.ifavor.flowlayout.utils;

import android.content.Context;
import cn.ifavor.flowlayout.BaseApplication;

public class UiUtils {
	
	public static Context getContext() {
		return BaseApplication.getApplication();
	}
	
	/**
	 * �����ֻ��ķֱ��ʴ� dip �ĵ�λ ת��Ϊ px(����)
	 */
	public static int dip2px(float dpValue) {
		final float scale = getContext().getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * �����ֻ��ķֱ��ʴ� px(����) �ĵ�λ ת��Ϊ dp
	 */
	public static int px2dip(float pxValue) {
		final float scale = getContext().getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
}
