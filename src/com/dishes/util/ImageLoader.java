/**
 * 
 */
package com.dishes.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.dishes.ui.R;
import com.dishes.util.ImageLoader.ImageLoadTask;

import android.R.integer;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

/**
 * 图片加载类
 * 
 * @author SenYang
 */
public class ImageLoader {

	boolean RUN = true;
	private ImageCache imageCache;
	private ThreadTool threadTool;
	private ArrayList<ImageLoadTask> taskList;
	private int i = 0;
	private ImageLoadTask imageLoadTask;


	public ImageLoader() {

		imageCache = ImageCache.getInstance();
		threadTool = ThreadTool.getInstance();
		taskList = ThreadTool.getImageLoadTasks();
	}


	public void loadImage( final ImageView imageView, final String imageUrl, final String name, final int length, final ImageCallback imageCallback ) {

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
		imageLoadTask = new ImageLoadTask( imageUrl, length, mHandler );
		imageLoadTask.setId( imageUrl );
		imageLoadTask.setName( name );
		taskList.add( imageLoadTask );
		threadTool.addTask( imageLoadTask );

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


	public class ImageLoadTask implements Runnable {

		String imageUrl;
		int length;
		Handler mHandler;
		String id;
		String name;


		public ImageLoadTask( String imageUrl, int length, Handler mHandler ) {

			this.imageUrl = imageUrl;
			this.length = length;
			this.mHandler = mHandler;

		}


		/**
		 * @param name
		 *            the name to set
		 */
		public void setName( String name ) {

			this.name = name;
		}


		/**
		 * @return the name
		 */
		public String getName() {

			return name;
		}


		/**
		 * @param id
		 *            the id to set
		 */
		public void setId( String id ) {

			this.id = id;
		}


		/**
		 * @return the id
		 */
		public String getId() {

			return id;
		}


		public void stopTask() {

			RUN = false;

		}


		@Override
		public void run() {

			Map<String, Object> map;
			if( RUN ) {
				if( imageCache.getBitmapMap( imageUrl ) != null ) {
					map = imageCache.getBitmapMap( imageUrl );

				} else {
					map = loadImageFromNet( imageUrl, length );
					imageCache.addSrCache( imageUrl, map );
				}
				if( RUN ) {
					Message msg = new Message();
					msg.obj = map;
					mHandler.sendMessage( msg );
				}

				return;

			}
		}
	}

}
