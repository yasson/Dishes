/**
 *
 * @author SenYang
 */
package com.dishes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dishes.model.IngredientInfo;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 * 
 * @author SenYang
 * 
 */
public class AppContext extends Application {

	/**
	 * 存放分类食材信息
	 */
	public static Map<String, List<IngredientInfo>> ingredientMaps=new HashMap<String, List<IngredientInfo>>();
	private Context context;


	public AppContext( Context context ) {

		this.context = context;
	}


	@Override
	public void onCreate() {

		super.onCreate();
		init();

	}


	/**
	 * 
	 */
	private static void init() {


	}


	public static void addIngredientMaps() {

	}


	/**
	 * @return the ingredientMap
	 */
	public static Map<String, List<IngredientInfo>> getIngredientMap() {

		return ingredientMaps;
	}


	/**
	 * 获取App安装包信息
	 * 
	 * @return
	 */
	public PackageInfo getPackageInfo() {

		PackageInfo info = null;
		try {
			info = getPackageManager().getPackageInfo( getPackageName(), 0 );
		} catch( NameNotFoundException e ) {
			e.printStackTrace( System.err );
		}
		if( info == null )
			info = new PackageInfo();
		return info;
	}
}
