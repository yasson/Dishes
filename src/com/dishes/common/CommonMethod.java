/**
 * 
 */
package com.dishes.common;

import android.content.Context;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.dishes.ui.R;

/**
 * @author SenYang
 * 
 */
public class CommonMethod {

	public static void netException( Context context ) {

		Toast.makeText( context, R.string.netexception, Toast.LENGTH_SHORT ).show();
	}


	public static int getWindowSizeW( Context context ) {

		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getResources().getDisplayMetrics();
		return dm.widthPixels;
	}


	public static int getWindowSizeH( Context context ) {

		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getResources().getDisplayMetrics();
		return dm.heightPixels;
	}
}
