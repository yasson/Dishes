/**
 *
 * @author SenYang
 */
package com.dishes.model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.ksoap2.serialization.SoapObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

/**
 * 
 * @author SenYang
 * 
 */
public class IngredientInfo {

	private String inId;
	private String inName;
	private String inPic; // add by sjy
	private int height;
	private int width;


	/**
	 * Descriptions
	 * 
	 * @param object
	 */
	public IngredientInfo( SoapObject object ) {

		this.inId = object.getPropertySafelyAsString( "inId" );
		this.inName = object.getPropertySafelyAsString( "inName" );
		this.inPic = object.getPropertySafelyAsString( "inPic" );
	}


	public IngredientInfo( SoapObject object, int RATIO ) {

		this.inId = object.getPropertySafelyAsString( "inId" );
		this.inName = object.getPropertySafelyAsString( "inName" );
		this.inPic = object.getPropertySafelyAsString( "inPic" );
		Options opts = new Options();
		opts.inJustDecodeBounds = true;
		try {
			BitmapFactory.decodeStream( new URL( inPic ).openStream(), null, opts );
			this.width = opts.outWidth;
			this.height = opts.outHeight;
		} catch( MalformedURLException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch( IOException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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


	public int getHeight() {

		return height;
	}


	public int getWidth() {

		return width;
	}
}
