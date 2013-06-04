/**
 * @author SenYang
 */
package com.dishes.model;

import org.ksoap2.serialization.SoapObject;

/**
 * 套餐每个菜品信息
 * 
 * @author SenYang
 * 
 */
public class MealsDetailInfo {

	private String date;
	private String dishId;
	private String dishName;
	private String dishPic;
	private String groupId;
	private String type;


	public MealsDetailInfo( SoapObject so ) {

		this.date = so.getPropertySafelyAsString( "date" );
		this.dishId = so.getPropertySafelyAsString( "dishId" );
		this.dishName = so.getPropertySafelyAsString( "dishName" );
		this.dishPic = so.getPropertySafelyAsString( "dishPic" );
		this.groupId = so.getPropertySafelyAsString( "groupId" );
		this.type = so.getPropertySafelyAsString( "type" );

	}


	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate( String date ) {

		this.date = date;
	}


	/**
	 * @param dishId
	 *            the dishId to set
	 */
	public void setDishId( String dishId ) {

		this.dishId = dishId;
	}


	/**
	 * @param dishName
	 *            the dishName to set
	 */
	public void setDishName( String dishName ) {

		this.dishName = dishName;
	}


	/**
	 * @param dishPic
	 *            the dishPic to set
	 */
	public void setDishPic( String dishPic ) {

		this.dishPic = dishPic;
	}


	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId( String groupId ) {

		this.groupId = groupId;
	}


	/**
	 * @param type
	 *            the type to set
	 */
	public void setType( String type ) {

		this.type = type;
	}


	/**
	 * @return the date
	 */
	public String getDate() {

		return date;
	}


	/**
	 * @return the dishId
	 */
	public String getDishId() {

		return dishId;
	}


	/**
	 * @return the dishName
	 */
	public String getDishName() {

		return dishName;
	}


	/**
	 * @return the dishPic
	 */
	public String getDishPic() {

		return dishPic;
	}


	/**
	 * @return the groupId
	 */
	public String getGroupId() {

		return groupId;
	}


	/**
	 * @return the type
	 */
	public String getType() {

		return type;
	}

}
