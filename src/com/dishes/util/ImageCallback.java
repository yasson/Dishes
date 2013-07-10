/**
 * 
 */
package com.dishes.util;

import android.graphics.Bitmap;

/**
 * 图片加载回调接口
 * 
 * @author SenYang
 * 
 */
public interface ImageCallback {

	/**
	 * 图片加载前
	 */
	public void imageLoadBefore();


	/**
	 * 图片加载中
	 */
	public void imageLoading(Bitmap bitmap,String url,float ratio,int width,int height);


	/**
	 * 图片加载后
	 */
	public void imageLoadOver();


	/**
	 * 图片加载失败
	 */
	public void imageLoadFailed();
}
