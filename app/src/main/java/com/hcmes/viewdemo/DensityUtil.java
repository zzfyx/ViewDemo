package com.hcmes.viewdemo;

import android.content.Context;
import android.util.TypedValue;
import android.view.WindowManager;


public class DensityUtil {

	public static int dip2px(Context context, float dpValue) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,

				dpValue, context.getResources().getDisplayMetrics());

	}


	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
	public static int getWindowHight(Context context){
		WindowManager windowManager=(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		return 	windowManager.getDefaultDisplay().getHeight();
	}
	public static int getWindowWidth(Context context){
		WindowManager windowManager=(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		return 	windowManager.getDefaultDisplay().getWidth();
	}


	public static float px2spfloat(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return  (pxValue / fontScale + 0.5f);
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 *
	 * @param spValue
	 * @param
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int sp2px(Context context, float spValue) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,

				spValue, context.getResources().getDisplayMetrics());

	}

	public static float px2dp(Context context, float pxVal)

	{

		final float scale = context.getResources().getDisplayMetrics().density;

		return (pxVal / scale);

	}



	/**
	 104.
	 105.       * px转sp
	 106.
	 107.       *
	 108.
	 109.       * @param fontScale
	 110.
	 111.       * @param pxVal
	 112.
	 113.       * @return
	 114.
	 115.       */

	public static float px2sp(Context context, float pxVal)

	{

		return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);

	}

}