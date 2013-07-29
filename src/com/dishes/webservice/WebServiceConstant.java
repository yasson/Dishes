package com.dishes.webservice;

/**
 * <p>
 * Functions 定义常量
 * </p>
 * <p>
 * Copyright HaierSoft 2012 All right reserved.
 * </p>
 * 
 * @author YangSen 时间 2012-11-23 上午9:21:43
 */
public class WebServiceConstant {
	public static final String wsUser = "1a1e02c02ee737b95e448a94c84c23ed";
	public static final String Server_IP = "http://60.209.248.227:8080";
//	public static final String Server_IP = "http://192.168.0.108:8080";
	// ************************命名空间************************
	public static final String SERVICENAMESPACE = "http://impl.ws.healthyrecipes.haiersoft.com/";
	// ************************wsdl常量*************************
	// 随机推荐
	public final static String SERVICE_RECOMMEND_URL = Server_IP
			+ "/RecommendWs/RecommendWsImpl?wsdl";
	// 每日推荐
	public final static String SERVICE_EVERYDAY_URL = Server_IP
			+ "/PopularWs/PopularWsImpl?wsdl";
	// 菜品收藏
	public static final String SERVICE_SETFAVOURITE_URL = Server_IP
			+ "/MenuWs/MenuWsImpl?wsdl";
	// 菜品详细信息
	public final static String SERVICE_DISHINFO_URL = Server_IP
			+ "/MenuWs/MenuWsImpl?wsdl";
	// 获取收藏菜品，删除收藏菜哄1�7
	public final static String SERVICE_FAVOURITE_URL = Server_IP
			+ "/MenuWs/MenuWsImpl?wsdl";
	// 获取营养餐套餄1�7
	public final static String SERVICE_NUTRIENTFOOD_URL = Server_IP
			+ "/NutrientFoodWs/NutrientFoodWsImpl?wsdl";
	// 获取搜索结果
	public static final String SREACH_URL = Server_IP
			+ "/SearchMenuWs/SearchMenuWsImpl?wsdl";
	// 早餐菜品添加
	public static final String SERVICE_URL_BREAKFAST = Server_IP
			+ "/NutrientDishAddOrModifyWs/NutrientDishAddOrModifyWsImpl?wsdl";
	// 午餐菜品添加
	public static final String SERVICE_URL_LUNCH = Server_IP
			+ "/NutrientDishAddOrModifyWs/NutrientDishAddOrModifyWsImpl?wsdl";
	// 晚餐菜品添加
	public static final String SERVICE_URL_DINNER = Server_IP
			+ "/NutrientDishAddOrModifyWs/NutrientDishAddOrModifyWsImpl?wsdl";
	// 获取食材
	public static final String SERVICE_INGREDIENTS_URL = Server_IP
			+ "/NutrientFoodIngredientsCountWs/NutrientFoodIngredientsCountWsImpl?wsdl";
	// 获取主食,家常菜，汤粥，饮哄1�7
	public static final String STAPLES_URL = Server_IP
			+ "/FoodClassWs/FoodClassWsImpl?wsdl";
	// 保存修改的食谱信恄1�7
	public static final String GENERATEMENU_URL = Server_IP
			+ "/HistoryMenuWs/HistoryMenuWsImpl?wsdl";
	// 获取营养餐能釄1�7
	public static final String SERVICE_GETENERGY_URL = Server_IP
			+ "/MenuWs/MenuWsImpl?wsdl";
	// 获取之前的营养餐信息
	public final static String SERVICE_GETHISTORYMENU_URL = Server_IP
			+ "/HistoryMenuWs/HistoryMenuWsImpl?wsdl";
	// 获取今天之前的7天的营养餐信息的url
	public final static String SERVICE_GETHISTORYNUTRIMENU_URL = Server_IP
			+ "/HistoryMenuWs/HistoryMenuWsImpl?wsdl";
	public final static String SERVIEC_GETADVICE_URL = Server_IP
			+ "/SuggestWs/SuggestWsImpl?wsdl";
	// 通过menuId判断是否为两菜相克
	public final static String SERVICE_GETMENUINCOM_URL = Server_IP
			+ "/FoodIncomWs/FoodIncomWsImpl?wsdl";
	// 根据搜索条件获取菜品
	public final static String SERVICE_SEARCHDISHES_URL = Server_IP
			+ "/SearchMenuWs/SearchMenuWsImpl?wsdl";
	// 获取搜索率高的菜
	public final static String SERVIEC_GETHOTDISHS_URL = Server_IP
			+ "/HotDishesAndIngredientsWs/HotDishesAndIngredientsWsImpl?wsdl";
	// 获取最近7天的食材
		public final static String SERVIEC_GETSEVENDAYINGRE_URL = Server_IP
				+ "/NutrientFoodIngredientsCountWs/NutrientFoodIngredientsCountWsImpl?wsdl";
	// ************************webservice方法*********************
	// 获取随机推荐营养餐或菜品
	public final static String GETONEDISHORNUTRIENTFOOD = "getOneDishOrNutrientFood";
	// 获取每日推荐菜品
	public final static String GETPOPULARDISH = "getPopularDish";
	// 获取规定数量的早餄1�7
	public final static String GETNUTRIENTBREAKFAST = "getNutrientBreakfast";
	// 获取规定数量的午餄1�7
	public final static String GETNUTRIENTLUNCH = "getNutrientLunch";
	// 获取规定数量的晚餄1�7
	public final static String GETNUTRIENTDINNER = "getNutrientDinner";
	// 获取菜品详细信息
	public final static String GETDISHINFO = "getDishInfoFromDatabase";
	// 获取收藏夹菜哄1�7
	public final static String GETFAVOURITEDISH = "getFavouriteDish";
	// 添加收藏菜品
	public static final String SETFAVOURITE = "addFavouriteDish";
	// 获取营养餐套餄1�7
	public static final String GETNUTRIENTFOOD = "getNutrientFood";
	// 获取套餐1�7
	public static final String getRandomNutrientFood = "getRandomNutrientFood";
	// 获取搜索结果
	public static final String GETDISHBYNAMEANDAUXILIARY = "getDishByNameAndAuxiliary";
	// 删除收藏菜品
	public final static String REMOVEFAVOURITEDISH = "removeFavouriteDish";
	// 获取营养餐食杄1�7
	public final static String GETNUTRIENTINGRE = "getNutrientFoodIngredients";
	// 获取主食
	public final static String GETSTAPLES = "getStaples";
	// 获取家常菄1�7
	public final static String GETHOMEDISHES = "getHomeDishes";
	// 获取汤粥
	public final static String GETSOUPS = "getSoups";
	// 获取饮品
	public final static String GETDRINKS = "getDrinks";
	// 保存修改食谱
	public final static String GENERATEMENU = "generateMenu";
	// 获取营养餐能釄1�7
	public final static String GETENERGY = "getMenuNutri";

	// 获取之前的营养餐信息
	public final static String GETHISTORYMENU = "getHistoryMenu";
	// 获取今天之前的7天的营养餐信息
	public final static String GETTHISTRYNUTRIMENU = "getHistoryNutriMenu";
	// 健康建议
	public final static String GETADVICE = "getSuggestionBySexBirthyearAndDisease";
	// 两菜相克
	public final static String GETMENUINCOM = "getMenuIncomByMenuID";
	// 获取搜索到的菜品
	public final static String GETDISHBYCONDITIONS = "getDishByConditions";
	// 获取搜索率高的菜
	public final static String GETHOTDISHS = "getHotDishes";
	
	
	// 菜品添加
	public static final String SERVICE_URL_POPUWS = Server_IP
			+ "/PopularWs/PopularWsImpl?wsdl";
	// 获取分类食材
	public static final String SERVICE_GETCOMMONINGREDIENTS = Server_IP
			+ "/HotDishesAndIngredientsWs/HotDishesAndIngredientsWsImpl?wsdl";
	// 根据食材获取菜品
	public static final String SERVICE_GETDISHES_URL = Server_IP
			+ "/SearchMenuWs/SearchMenuWsImpl?wsdl";
	// 获取常用食材
	public final static String GETCOMMONINGREDIENTS = "getCommonIngredients";
	// 根据食材获取菜品
	public final static String GETDISHES = "getDishByMaterial";
	// 获取最近7天的食材
	public final static String GETSEVENDAYINGRE = "getManyDaysFoodIngredients";

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
