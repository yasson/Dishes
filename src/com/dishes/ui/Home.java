/**
 *
 * @author SenYang
 */
package com.dishes.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.dishes.ui.base.BaseActivity;
import com.dishes.views.HomeMenuView;
import com.dishes.views.SlidingMenuView;
import com.dishes.views.View1;
import com.dishes.views.View1.OnIntent;

/**
 * 
 * @author SenYang
 * 
 */
public class Home extends BaseActivity implements OnIntent {

	private SlidingMenuView slidingMenuView;
	private HomeMenuView homeMenuView;
	public Home home;
//	private View view;


	@Override
	protected void onCreate( Bundle savedInstanceState ) {

		super.onCreate( savedInstanceState );
		
		home=new Home();
		homeMenuView = new HomeMenuView( getApplicationContext() );
		slidingMenuView = new SlidingMenuView( this, homeMenuView );
		View1 view1 = new View1( getApplicationContext() ,home);
		view1.setHomeMenuView( homeMenuView );
		view1.initView();
		view1.getEveryDayDishInfo();
		homeMenuView.setMainView( slidingMenuView, view1, 1 );
		setContentView( homeMenuView );

	}


	@Override
	public void turn(Context context) {

		Intent intent = new Intent();
		intent.setClass( context, CategoryDishesUi.class );
		 startActivity( intent );
		overridePendingTransition( R.anim.slide_right_in, R.anim.slide_left_out );
	}

}
