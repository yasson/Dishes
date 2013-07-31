package com.dishes.model;

import org.ksoap2.serialization.SoapObject;

public class RestrictionInfo {

	private String inName;
	private String desc;
	private String result;


	/**
	 * @param soapObject
	 */
	public RestrictionInfo( SoapObject soapObject ) {

		this.setInName( soapObject.getPropertySafelyAsString( "inName" ) );
		this.desc = soapObject.getPropertySafelyAsString( "desc" );

	}


	/**
	 * @param inName
	 *            the inName to set
	 */
	public void setInName( String inName ) {

		this.inName = inName;
	}


	/**
	 * @return the desc
	 */
	public String getDesc() {

		return desc;
	}


	/**
	 * @param desc
	 *            the desc to set
	 */
	public void setDesc( String desc ) {

		this.desc = desc;
	}


	/**
	 * @return the inName
	 */
	public String getInName() {

		return inName;
	}

}
