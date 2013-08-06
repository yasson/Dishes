/**
 *
 * @author SenYang
 */
package com.dishes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

import com.dishes.model.DishInfo;
import com.dishes.model.IngredientInfo;

/**
 * 
 * @author SenYang
 * 
 */
public class AppContext extends Application {

	/**
	 * 存放分类食材信息
	 */
	public static Map<String, List<IngredientInfo>> ingredientMaps = new HashMap<String, List<IngredientInfo>>();
	public static String appName = "";
	public static String appPath="";
	public static int i=0;
	public static List<IngredientInfo> list_ingredient_Ids = new ArrayList<IngredientInfo>();
	public static boolean IF_LOAD=true;
	public static List<IngredientInfo> list_ingredient_energy=new ArrayList<IngredientInfo>();
	public static List<DishInfo> favorlist=new ArrayList<DishInfo>();


	public AppContext( Context context ) {

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
