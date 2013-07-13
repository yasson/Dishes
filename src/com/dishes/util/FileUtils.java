/**
 *
 * @author YangSen
 */
package com.dishes.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.dishes.AppContext;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

/**
 * 
 * @author YangSen
 * 
 */
public class FileUtils {

	/**
	 * 判断是否存在sd卡
	 * 
	 * @return
	 */
	public static boolean hasSDCard() {

		return Environment.MEDIA_MOUNTED.equals( Environment.getExternalStorageState() );
	}


	/**
	 * 得到sd卡根目录
	 * 
	 * @return
	 */
	public static String getSDCardPath() {

		if( hasSDCard() ) {
			return Environment.getExternalStorageDirectory().getPath();
		}
		return null;

	}


	/**
	 * 获取应用路径
	 * 
	 * @param activity
	 * @return
	 */
	public static String getPackagePath( Context context ) {

		return context.getFilesDir().toString();

	}


	/**
	 * 根据url得到图片名
	 * 
	 * @param url
	 * @return
	 */
	public static String getImageName( String url ) {

		if( url != null ) {
			return url.substring( url.lastIndexOf( "/" )+1, url.length() );

		}
		return null;
	}


	public static Bitmap getBitmapFrSDCard( String url ) {

		Bitmap bitmap = null;
		String name = getImageName( url );
		String locationPath = "";
		if( url != null ) {

			if( hasSDCard() ) {
				locationPath = getSDCardPath() + "/" + AppContext.appName + "/imagecache";
			} else {
				locationPath = AppContext.appPath + "/imagecache";
			}
			File file = new File( locationPath, name );
			if( file.exists() ) {
				try {
					bitmap = BitmapFactory.decodeStream( new FileInputStream( file ) );
				} catch( FileNotFoundException e ) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					bitmap = null;
				}
			}
		}
		return bitmap;
	}


	public static boolean putBitmapToSDCard( String url, Context context, Bitmap bitmap ) {

		String locationPath = "";
		String name = getImageName( url );
		if( hasSDCard() ) {
			locationPath = getSDCardPath() + "/" + AppContext.appName + "/imagecache/";
		} else {
			locationPath = AppContext.appPath + "/imagecache/";
		}
		File dire=new File( locationPath );
		if( !dire.exists() ) {
			dire.mkdirs();
		}
		String absolutePath = locationPath + name;
		File file = new File( absolutePath );
		if( !file.exists() ) {
			try {
				file.createNewFile();
				FileOutputStream fo = null;
				if( hasSDCard() ) {
					fo = new FileOutputStream( file );
				} else {
					fo = context.openFileOutput( name, Context.MODE_PRIVATE );
				}

				if( name.contains( ".png" ) || name.contains( ".PNG" ) ) {
					bitmap.compress( Bitmap.CompressFormat.PNG, 90, fo );
				} else {
					bitmap.compress( Bitmap.CompressFormat.JPEG, 90, fo );
				}
				return true;
			} catch( IOException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return false;

	}


	/**
	 * @param context
	 * @param imageUrl
	 * 
	 */
	public static void removeBitmapFrSDCard( String imageUrl, Context context ) {

		String localPath = "";
		String name = getImageName( imageUrl );
		if( hasSDCard() ) {

			localPath = getSDCardPath();
		} else {
			localPath = getPackagePath( context );
		}

		File file = new File( localPath, name );
		file.delete();

	}
}
