/**
 *
 * @author SenYang
 */
package com.dishes.model;

import org.ksoap2.serialization.SoapObject;

/**
 * 
 * @author SenYang
 * 
 */
public class IngredientInfo {

	private String inId;
	private String inName;
	private String inPic; // add by sjy


	/**
	 * Descriptions
	 * 
	 * @param object
	 */
	public IngredientInfo( SoapObject object ) {

		this.inId = object.getPropertySafelyAsString( "inld" );
		this.inName = object.getPropertySafelyAsString( "inName" );
		this.inPic = object.getPropertySafelyAsString( "inPic" );

	}


	public String getInId() {

		return inId;
	}


	public void setInId( String inId ) {

		this.inId = inId;
	}


	public String getInName() {

		return inName;
	}


	public void setInName( String inName ) {

		this.inName = inName;
	}


	public String getInPic() {

		return inPic;
	}


	public void setInPic( String inPic ) {

		this.inPic = inPic;
	}

}
