/**
 * 
 */
package com.dishes.common;


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

		public static final String[] FUNCTION_LIST = { "每日套餐", "看看能吃啥", "食材能量查询", "食材相克查询", "分类食谱" };
		public static final String[] DESCRIBTION_LIST = { "提供每日合理能量早中晚三餐推荐，也可根据自己喜好进行增删", "看看家里有什么食材，这里能帮你推荐出这些食材能做的菜品", "查询想要查看的食材所具有的能量", "查看所查询食材与什么食材相克",
				"按照主食，汤煲，饮品，家常菜进行的分类快速定位" };
		public static final String[] IMAGE_LIST = { "h1.png", "h2.png", "h3.png", "h4.png", "h5.png" };
		public static final int IMAGE_LENGTH = 300;
		public static int EVERYDAYDISHCOUNTS = 8;
	}

	public static class CategoryDishesConstant {

		public static final String[] CATEGORY_LIST = { "主食", "家常菜", "汤煲", "饮品", "减肥", "增肥", "疾病", "营养", "荤素" };
		public static final String[] DESCRIBTION_LIST = { "提供主食检索", "各种常吃家常菜", "营养健康汤煲", "美味可口，清凉解渴", "低热量食物推荐", "高热量易增肥食物推荐", "各种疾病嘴上的禁忌与调和",
				"各种营养的含量的总汇，缺什么，吃什么", "荤素菜的推荐" };
		public static final String[] IMAGE_LIST = { "c1.png", "c2.png", "c3.png", "c4.png", "c5.png" ,"c6.png", "c7.png", "c8.png", "c9.png" };
		public static final int IMAGE_LENGTH = 300;
		public static final int DISH_NUM = 100;
	}

	/**
	 * 搜索界面常量
	 * 
	 * @author SenYang
	 * 
	 */
	public static class SearchConstant {

		// listCuisines.add("鲁菜");// 42
		// listCuisines.add("川菜");// 41
		// listCuisines.add("粤菜");// 43
		// listCuisines.add("苏菜");// 47
		// listCuisines.add("闽菜");// 45
		// listCuisines.add("浙菜");// 46
		// listCuisines.add("湘菜");// 44
		// listCuisines.add("徽菜");// 48
		// listCuisines.add("全部");
		// listCuisines.add("辣");// 1
		// listCuisines.add("酸");// 2
		// listCuisines.add("咸");// 3
		// listCuisines.add("苦");// 15
		// listCuisines.add("甜");// 5
		// listCuisines.add("蒜香");// 9
		// listCuisines.add("姜香");// 17
		// listCuisines.add("葱香");// 12
		// listCuisines.add("全部");
		// listCuisines.add("蒸");// 1
		// listCuisines.add("煮");// 2
		// listCuisines.add("炖");// 3
		// listCuisines.add("炒");// 10
		// listCuisines.add("煎");// 15
		// listCuisines.add("烧");// 6
		// listCuisines.add("炸");// 23
		// listCuisines.add("煲");// 8
		public static final String[] TASTE_LIST = { "口味", "辣", "酸", "咸", "苦", "甜", "蒜香", "姜香", "葱香" };
		public static final String[] TASTE_LIST_ID = { "", "1", "2", "3", "15", "5", "9", "17", "12" };
		public static final String[] CAIXI_LIST = { "菜系", "鲁菜", "粤菜", "苏菜", "闽菜", "浙菜", "湘菜", "徽菜" };
		public static final String[] CAIXI_LIST_ID = { "", "42", "41", "47", "43", "45", "46", "44", "48" };
		public static final String[] PROCESS_LIST = { "工艺", "蒸", "煮", "炖", "炒", "煎", "烧", "炸", "煲" };
		public static final String[] PROCESS_LIST_ID = { "", "1", "2", "3", "10", "15", "6", "23", "8" };

	}

	public static class UtilConstant {

		public static int THREADCOUNT = 5;
		public static int LISTVIEW_MINCOUNT = 10;
	}
	public static class WhatToEatConstant{
		public static final String[] CATEGORY_LIST={"五谷类","蔬菜类","水果类","畜肉类","禽蛋类","水产类","其他"};
		
	}
	public static class Ingredient{
		public static final int HOTCOUNT=30;
		
		
		
		
	}
}
