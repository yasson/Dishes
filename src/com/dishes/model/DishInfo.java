/**
 * 
 */
package com.dishes.model;

import java.io.Serializable;

import org.ksoap2.serialization.SoapObject;

/**
 * @author SenYang
 * 
 */
public class DishInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1578028418680488269L;
	private String dishId;
	private String dishName;
	private String dishPic;
	private String dishDesc;
	private String dishType;


	public DishInfo() {

	}


	public DishInfo( SoapObject soapObject ) {

		this.dishId = soapObject.getPropertySafelyAsString( "dishId" );
		this.dishName = soapObject.getPropertySafelyAsString( "dishName" );
		this.dishPic = soapObject.getPropertySafelyAsString( "dishPic" );
		this.dishDesc = soapObject.getPropertySafelyAsString( "dishDesc" );

	}


	/**
	 * @param dishDesc
	 *            the dishDesc to set
	 */
	public void setDishDesc( String dishDesc ) {

		this.dishDesc = dishDesc;
	}


	/**
	 * @param dishName
	 *            the dishName to set
	 */
	public void setDishName( String dishName ) {

		this.dishName = dishName;
	}


	/**
	 * @param dishId
	 *            the dishId to set
	 */
	public void setDishId( String dishId ) {

		this.dishId = dishId;
	}


	/**
	 * @param dishPic
	 *            the dishPic to set
	 */
	public void setDishPic( String dishPic ) {

		this.dishPic = dishPic;
	}


	/**
	 * @param dishType
	 *            the dishType to set
	 */
	public void setDishType( String dishType ) {

		this.dishType = dishType;
	}


	/**
	 * @return the dishDesc
	 */
	public String getDishDesc() {

		return dishDesc;
	}


	/**
	 * @return the dishId
	 */
	public String getDishId() {

		return dishId;
	}


	/**
	 * @return the dishPic
	 */
	public String getDishPic() {

		return dishPic;
	}


	/**
	 * @return the dishType
	 */
	public String getDishType() {

		return dishType;
	}


	/**
	 * @return the dishName
	 */
	public String getDishName() {

		return dishName;
	}
}
