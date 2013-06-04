/**
 * 
 */
package com.dishes.common;

/**
 * @author SenYang
 * 
 */
public class SharedPr {

	private static SharedPr sharedPr;


	private SharedPr() {

	}


	public static SharedPr getInstance() {

		if( sharedPr != null ) {
			return sharedPr;
		} else {
			sharedPr = new SharedPr();
		}

		return sharedPr;

	}

}
