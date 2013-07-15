/**
 *
 * @author SenYang
 */
package com.dishes.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ksoap2.serialization.SoapObject;

import android.R.integer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.dishes.AppContext;
import com.dishes.adapter.IngredientViewPagerAdapter;
import com.dishes.model.IngredientInfo;
import com.dishes.model.WSResult;
import com.dishes.ui.base.BaseActivity;
import com.dishes.util.LoadingDialog;
import com.dishes.util.ThreadTool;
import com.dishes.webservice.WebServiceAction;
import com.dishes.webservice.WebServiceConstant;

/**
 * 
 * @author SenYang
 * 
 */
@SuppressWarnings( "deprecation" )
public class WhatToEatUi extends BaseActivity implements OnClickListener, OnItemClickListener {

	private static final int REFRESH_INGREDIENT = 0;
	private Gallery gallery;
	private ViewPager vp_ingredients;
	public static HorizontalScrollView hs_chosen;
	public static LinearLayout ll_hs;
	private Button btn_todishes;
	public static List<String> listId;
	private Handler mHandler = new Handler() {

		public void handleMessage( android.os.Message msg ) {

			switch( msg.what ) {
			case REFRESH_INGREDIENT:
				IngredientViewPagerAdapter adapter = new IngredientViewPagerAdapter( getApplicationContext(), mHandler );
				vp_ingredients.setAdapter( adapter );

				break;

			default:
				break;
			}
		};
	};


	@Override
	protected void onCreate( Bundle savedInstanceState ) {

		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_whattoeat );
		initView();
		getIngredientsInfo();
	}


	/**
	 * 
	 */
	private void getIngredientsInfo() {

		for( int i = 1; i <= 7; i++ ) {
			final HashMap<String, Object> map = new HashMap<String, Object>();
			map.put( "count", 0 );
			map.put( "startIdx", 0 );
			map.put( "wsUser", WebServiceConstant.wsUser );
			map.put( "classid", i );
			ThreadTool.getInstance().addTask( new Runnable() {

				@Override
				public void run() {

					SoapObject soapObject = WebServiceAction.getSoapObject( WebServiceConstant.SERVICE_GETCOMMONINGREDIENTS,
							WebServiceConstant.GETCOMMONINGREDIENTS, map, WebServiceConstant.SERVICENAMESPACE );
					WSResult result = new WSResult( soapObject );
					switch( Integer.parseInt( result.getState() ) ) {
					case 202:
						List<IngredientInfo> list = new ArrayList<IngredientInfo>();
						for( int j = 0; j < result.getResult().size(); j++ ) {
							IngredientInfo info = new IngredientInfo( ( SoapObject )result.getResult().get( j ) );
							list.add( info );

						}
						AppContext.ingredientMaps.put( "classid" + map.get( "classid" ), list );
						if( AppContext.ingredientMaps.size() == 7 ) {
							mHandler.sendEmptyMessage( REFRESH_INGREDIENT );
						}
						break;

					default:
						break;
					}

				}
			} );
		}

	}


	/**
	 * 
	 */
	private void initView() {

		listId = new ArrayList<String>();
		gallery = ( Gallery )findViewById( R.id.gallery );
		vp_ingredients = ( ViewPager )findViewById( R.id.vp_ingredients );
		hs_chosen = ( HorizontalScrollView )findViewById( R.id.hs_chosen );
		btn_todishes = ( Button )findViewById( R.id.btn_todishes );
		ll_hs = ( LinearLayout )findViewById( R.id.ll_hs );

	}


	@Override
	public void onItemClick( AdapterView<?> arg0, View arg1, int arg2, long arg3 ) {

	}


	@Override
	public void onClick( View v ) {

	}

}
