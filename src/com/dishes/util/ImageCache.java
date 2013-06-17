/**
 * 
 */
package com.dishes.util;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import android.R.integer;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * 图片缓存类
 * 
 * @author SenYang
 * 
 */
public class ImageCache {

	private static ImageCache imageCache = null;
	// private static Map<String, SoftReference<Map<String, Object>>> icMap;
	private static LruCache<String, Map<String, Object>> icMap;
	private static int i;


	private ImageCache() {

		// icMap = new HashMap<String, SoftReference<Map<String, Object>>>();
		icMap = new LruCache<String, Map<String, Object>>( 4 * 1024 * 1024 );
	}


	public synchronized static ImageCache getInstance() {

		if( imageCache == null ) {
			imageCache = new ImageCache();
		}

		return imageCache;

	}


	public synchronized void addSrCache( String imageUrl, Map<String, Object> map ) {

		icMap.put( imageUrl, map );
	}


	public Map<String, Object> getBitmapMap( String imageUrl ) {

		if( icMap.get( imageUrl ) != null ) {
			return icMap.get( imageUrl );

		}

		return null;

	}
	public int getA(){
		return i++;
	}

}
