/**
 * 
 */
package com.dishes.util;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * 图片缓存类
 * 
 * @author SenYang
 * 
 */
public class ImageCache {

	private static ImageCache imageCache = null;
	private static Map<String, SoftReference<Map<String, Object>>> icMap;


	private ImageCache() {

		icMap = new HashMap<String, SoftReference<Map<String, Object>>>();
	}


	public synchronized static ImageCache getInstance() {

		if( imageCache == null ) {
			imageCache = new ImageCache();
		}

		return imageCache;

	}


	public synchronized void addSrCache( String imageUrl, Map<String, Object> map ) {

		icMap.put( imageUrl, new SoftReference<Map<String, Object>>( map ) );
	}


	public Map<String, Object> getBitmapMap( String imageUrl ) {

		if( icMap.containsKey( imageUrl ) ) {
			SoftReference<Map<String, Object>> softReference = icMap.get( imageUrl );
			if( softReference != null ) {
					return softReference.get();
			
			}

		}

		return null;

	}

}
