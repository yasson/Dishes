/**
 * 
 */
package com.dishes.common;

import android.R.integer;

/**
 * @author SenYang
 * 
 */
public class Constant {

	public static final String PACKAGE_NAME = "com.dishes";
	public static final String FIRSTLOGIN = "firstlogin";
	public static final String AUTOLOGINCHECKED = "autologinchecked";
	public static final String REMEMBERPASSWORD = "rememberpasword";
	public static final String USERACCOUNT = "useraccount";
	public static final String USERPASSWORD = "userpassword";
	public static final int MEALSPAGERSIZE = 3;


	// 定义主页的一些常量
	public static class HomeConstant {

		public static final String[] FUNCTION_LIST = { "每日套餐", "看看能吃啥", "食材能量查询", "食材相克查询","分类食谱" };
		public static final String[] DESCRIBTION_LIST = { "提供每日合理能量早中晚三餐推荐，也可根据自己喜好进行增删", "看看家里有什么食材，这里能帮你推荐出这些食材能做的菜品", "查询想要查看的食材所具有的能量", "查看所查询食材与什么食材相克","按照主食，汤煲，饮品，家常菜进行的分类快速定位" };
		public static final String[] IMAGE_LIST = { "h1.png", "h2.png", "h3.png", "h4.png","h5.png" };
		public static final int IMAGE_LENGTH = 300;
		public static int EVERYDAYDISHCOUNTS = 8;
	}

	public static class UtilConstant {

		public static int THREADCOUNT = 5;
	}
}
