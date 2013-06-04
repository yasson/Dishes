/**
 * 
 */
package com.dishes.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import android.R.integer;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * 图片加载类
 * 
 * @author SenYang
 */
public class ImageLoader {

	private ImageCache imageCache;
	private ThreadTool threadTool;


	public ImageLoader() {

		imageCache = ImageCache.getInstance();
		threadTool = ThreadTool.getInstance();
	}


	public void loadImage( final String imageUrl, final int length, final ImageCallback imageCallback ) {

		imageCallback.imageLoadBefore();
		final Handler mHandler = new Handler() {

			public void handleMessage( Message msg ) {

				@SuppressWarnings( "unchecked" )
				Map<String, Object> map = ( Map<String, Object> )msg.obj;
				Bitmap bitmap = ( Bitmap )map.get( "bitmap" );
				float ratio = ( Float )map.get( "ratio" );
				int width = ( Integer )map.get( "width" );
				int height = ( Integer )map.get( "height" );
				imageCallback.imageLoading( bitmap, ratio, width, height );
				imageCallback.imageLoadOver();
				if( bitmap == null ) {
					imageCallback.imageLoadFailed();
				}
			}
		};
		threadTool.addTask( new Runnable() {

			@Override
			public void run() {

				Map<String, Object> map;
				if( imageCache.getBitmapMap( imageUrl ) != null ) {
					map = imageCache.getBitmapMap( imageUrl );
				} else {
					map = loadImageFromNet( imageUrl, length );

					imageCache.addSrCache( imageUrl, map );
				}

				Message msg = new Message();
				msg.obj = map;
				mHandler.sendMessage( msg );
			}
		} );

	}


	public static Map<String, Object> loadImageFromNet( String imageUrl, int length ) {

		Map<String, Object> map = new HashMap<String, Object>();
		Options options = new Options();
		options.inJustDecodeBounds = true;
		try {
			BitmapFactory.decodeStream( ( new URL( imageUrl ) ).openStream(), null, options );
		} catch( MalformedURLException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch( IOException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int width = options.outWidth;
		int height = options.outHeight;
		float ratio = width / length;
		if( width < length ) {
			ratio = 1;
		}
		options.inSampleSize = ( int )ratio;
		options.inJustDecodeBounds = false;

		try {
			Bitmap bitmap = BitmapFactory.decodeStream( ( new URL( imageUrl ) ).openStream(), null, options );
			map.put( "bitmap", bitmap );
			map.put( "ratio", ratio );
			map.put( "width", width );
			map.put( "height", height );
			return map;
		} catch( MalformedURLException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch( IOException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
