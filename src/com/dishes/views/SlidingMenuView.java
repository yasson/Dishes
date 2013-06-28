/**
 *
 * @author SenYang
 */
package com.dishes.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.dishes.ui.Home;
import com.dishes.ui.R;

/**
 * 
 * @author SenYang
 * 
 */
public class SlidingMenuView {

	private HomeMenuView homeMenuView;
	private View view;


	/**
	 * @param home
	 * @param homeMenuView
	 */
	public SlidingMenuView( Home home, HomeMenuView homeMenuView ) {

		this.homeMenuView = homeMenuView;
		view = LayoutInflater.from( home ).inflate( R.layout.menu_home, null );
	}


	/**
	 * @return the view
	 */
	public View getView() {

		return view;
	}
	public void setWidth(int w){
		
		LayoutParams lp=view.getLayoutParams();
		lp.width=w;
		view.setLayoutParams( lp );
		
		
		
	}

}
