/**
 *
 * @author SenYang
 */
package com.dishes.views;

import android.R.bool;
import android.app.Activity;
import android.text.GetChars;
import android.view.View;

/**
 * 
 * @author SenYang
 * 
 */
public class BaseView {

	private boolean isScroll;
	public View view;
	public HomeMenuView homeMenuView;


	/**
	 * @param homeMenuView
	 *            the homeMenuView to set
	 */
	public void setHomeMenuView( HomeMenuView homeMenuView ) {

		this.homeMenuView = homeMenuView;
	}


	/**
	 * @return the homeMenuView
	 */
	public HomeMenuView getHomeMenuView() {

		return homeMenuView;
	}


	/**
	 * @param isScroll
	 *            the isScroll to set
	 */
	public void setScroll( boolean isScroll ) {

		this.isScroll = isScroll;
	}


	public boolean isScroll() {

		return isScroll;
	}


	/**
	 * @return the view
	 */
	public View getView() {

		return view;
	}


	/**
	 * @param view
	 *            the view to set
	 */
	public void setView( View view ) {

		this.view = view;
	}

}
