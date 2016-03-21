package cn.ifavor.flowlayout.utils;

import android.widget.Toast;
import cn.ifavor.flowlayout.BaseApplication;

/**
 * 吐司显示工具类
 * @author Administrator
 *
 */
public class ToastUtils {

	/**
	 * 是否打开Toast显示开关
	 */
	private static boolean isShow = true;
	
	private static Toast sToast;
	
	/**
	 * 最常用的提示文本
	 * 
	 * @param ctx
	 *            上下文
	 * @param message
	 *            需要显示的文字
	 * @param duration
	 *            自定义显示时间
	 */
	public static void show( String message) {
		if (isShow) {
			if (sToast != null){
				sToast.cancel();
				sToast = null;
			}
			
			sToast = Toast.makeText(BaseApplication.getApplication(), message, Toast.LENGTH_SHORT);
			sToast.show();
		}
	}
	
	
	/**
	 * 直接显示文本
	 * 
	 * @param ctx
	 *            上下文
	 * @param message
	 *            需要显示的文字
	 */
	public static void showShort( int message) {
		if (isShow) {
			if (sToast != null){
				sToast.cancel();
				sToast = null;
			}
			
			sToast = Toast.makeText(BaseApplication.getApplication(), message, Toast.LENGTH_SHORT);
			sToast.show();
		}
	}

	/**
	 * 直接显示文本
	 * 
	 * @param ctx
	 *            上下文
	 * @param message
	 *            需要显示的文字
	 */
	public static void showShort( String message) {
		if (isShow) {
			if (sToast != null){
				sToast.cancel();
				sToast = null;
			}
			
			sToast = Toast.makeText(BaseApplication.getApplication(), message, Toast.LENGTH_SHORT);
			sToast.show();
		}
	}

	/**
	 * 直接显示文本
	 * 
	 * @param ctx
	 *            上下文
	 * @param message
	 *            需要显示的文字
	 */
	public static void showLong( int message) {
		if (isShow) {
			if (sToast != null){
				sToast.cancel();
				sToast = null;
			}
			
			sToast = Toast.makeText(BaseApplication.getApplication(), message, Toast.LENGTH_SHORT);
			sToast.show();
		}
	}

	/**
	 * 直接显示文本
	 * 
	 * @param ctx
	 *            上下文
	 * @param message
	 *            需要显示的文字
	 */
	public static void showLong( String message) {
		if (isShow) {
			if (sToast != null){
				sToast.cancel();
				sToast = null;
			}
			
			sToast = Toast.makeText(BaseApplication.getApplication(), message, Toast.LENGTH_SHORT);
			sToast.show();
		}
	}

	/**
	 * 直接显示文本
	 * 
	 * @param ctx
	 *            上下文
	 * @param message
	 *            需要显示的文字
	 * @param duration
	 *            自定义显示时间
	 */
	public static void show( int message, int duration) {
		if (isShow) {
			if (sToast != null){
				sToast.cancel();
				sToast = null;
			}
			
			sToast = Toast.makeText(BaseApplication.getApplication(), message, duration);
			sToast.show();
		}
	}

	/**
	 * 直接显示文本
	 * 
	 * @param ctx
	 *            上下文
	 * @param message
	 *            需要显示的文字
	 * @param duration
	 *            自定义显示时间
	 */
	public static void show( String message, int duration) {
		if (isShow) {
			if (sToast != null){
				sToast.cancel();
				sToast = null;
			}
			
			sToast = Toast.makeText(BaseApplication.getApplication(), message, duration);
			sToast.show();
		}
	}

}
