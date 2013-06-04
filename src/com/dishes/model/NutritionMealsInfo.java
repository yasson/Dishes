/**
 * @author SenYang
 */
package com.dishes.model;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

/**
 * @author SenYang
 * 
 */
public class NutritionMealsInfo {

	private String date;
	private String EXIST;
	private List<MealsDetailInfo> mealsDetailInfos;


	public NutritionMealsInfo( SoapObject so ) {

		mealsDetailInfos = new ArrayList<MealsDetailInfo>();
		this.date = so.getProperty( 0 ).toString();
		this.EXIST = so.getProperty( 1 ).toString();
		for( int i = 2; i < so.getPropertyCount(); i++ ) {
			MealsDetailInfo mealsDetailInfo = new MealsDetailInfo( ( SoapObject )so.getProperty( i ) );
			mealsDetailInfos.add( mealsDetailInfo );
		}
	}


	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate( String date ) {

		this.date = date;
	}


	/**
	 * @param eXIST
	 *            the eXIST to set
	 */
	public void setEXIST( String eXIST ) {

		EXIST = eXIST;
	}


	/**
	 * @param mealsDetailInfos
	 *            the mealsDetailInfos to set
	 */
	public void setMealsDetailInfos( List<MealsDetailInfo> mealsDetailInfos ) {

		this.mealsDetailInfos = mealsDetailInfos;
	}


	/**
	 * @return the date
	 */
	public String getDate() {

		return date;
	}


	/**
	 * @return the eXIST
	 */
	public String getEXIST() {

		return EXIST;
	}


	/**
	 * @return the mealsDetailInfos
	 */
	public List<MealsDetailInfo> getMealsDetailInfos() {

		return mealsDetailInfos;
	}

}
