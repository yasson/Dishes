/**
 * 
 */
package com.dishes.util;

import java.util.Map;

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
	private static LruCache<String, Map<String, Object>> icMap;
	private static int i;


	private ImageCache() {

		icMap = new LruCache<String, Map<String, Object>>( 4 * 1024 * 1024 ) {

			@Override
			protected int sizeOf( String key, Map<String, Object> value ) {

				return ( ( Bitmap )value.get( "bitmap" ) ).getByteCount();
			}
		};
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
//		else if( FileUtils.getBitmapFrSDCard( imageUrl ) != null ) {
//			Bitmap bitmap = FileUtils.getBitmapFrSDCard( imageUrl );
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put( "bitmap", bitmap );
//			map.put( "url", imageUrl );
//			map.put( "ratio", ( bitmap.getWidth() / bitmap.getHeight() ) * 1.0f );
//			map.put( "width", bitmap.getWidth() );
//			map.put( "height", bitmap.getHeight() );
//			icMap.put( imageUrl, map );
//			return map;
//		}

		return null;

	}


	public int getA() {

		return i++;
	}

}
