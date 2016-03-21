package cn.ifavor.flowlayout.utils;

import android.widget.Toast;
import cn.ifavor.flowlayout.BaseApplication;

/**
 * ��˾��ʾ������
 * @author Administrator
 *
 */
public class ToastUtils {

	/**
	 * �Ƿ��Toast��ʾ����
	 */
	private static boolean isShow = true;
	
	private static Toast sToast;
	
	/**
	 * ��õ���ʾ�ı�
	 * 
	 * @param ctx
	 *            ������
	 * @param message
	 *            ��Ҫ��ʾ������
	 * @param duration
	 *            �Զ�����ʾʱ��
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
	 * ֱ����ʾ�ı�
	 * 
	 * @param ctx
	 *            ������
	 * @param message
	 *            ��Ҫ��ʾ������
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
	 * ֱ����ʾ�ı�
	 * 
	 * @param ctx
	 *            ������
	 * @param message
	 *            ��Ҫ��ʾ������
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
	 * ֱ����ʾ�ı�
	 * 
	 * @param ctx
	 *            ������
	 * @param message
	 *            ��Ҫ��ʾ������
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
	 * ֱ����ʾ�ı�
	 * 
	 * @param ctx
	 *            ������
	 * @param message
	 *            ��Ҫ��ʾ������
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
	 * ֱ����ʾ�ı�
	 * 
	 * @param ctx
	 *            ������
	 * @param message
	 *            ��Ҫ��ʾ������
	 * @param duration
	 *            �Զ�����ʾʱ��
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
	 * ֱ����ʾ�ı�
	 * 
	 * @param ctx
	 *            ������
	 * @param message
	 *            ��Ҫ��ʾ������
	 * @param duration
	 *            �Զ�����ʾʱ��
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
