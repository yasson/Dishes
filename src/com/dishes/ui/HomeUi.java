/**
 * 
 */
package com.dishes.ui;

import java.util.HashMap;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dishes.adapter.HomeListViewAdapter;
import com.dishes.common.Constant;
import com.dishes.model.DishInfo;
import com.dishes.model.WSResult;
import com.dishes.ui.base.BaseActivity;
import com.dishes.util.ImageCallback;
import com.dishes.util.ImageLoader;
import com.dishes.util.SlideHolder;
import com.dishes.util.ThreadTool;
import com.dishes.webservice.WebServiceAction;
import com.dishes.webservice.WebServiceConstant;

/**
 * @author SenYang
 * 
 */
public class HomeUi extends BaseActivity implements OnClickListener, OnItemClickListener, OnTouchListener {

	private ListView lv_home;
	private HomeListViewAdapter adapter;
	private Button btn_menu, btn_search;
	private HorizontalScrollView hScrollView;
	private LinearLayout ll_everyday;
	private final int EVERYDAYVIEW = 1;
	private Handler handler = new Handler() {

		@SuppressWarnings( "unchecked" )
		public void handleMessage( android.os.Message msg ) {

			switch( msg.what ) {
			case EVERYDAYVIEW:
				for( Object object : ( List<Object> )msg.obj ) {
					final DishInfo dishInfo = new DishInfo( ( SoapObject )object );
					View view = LayoutInflater.from( getApplicationContext() ).inflate( R.layout.adapter_homeeveryday, null );
					final ImageView imageView = ( ImageView )view.findViewById( R.id.iv_everydish );
					imageView.setOnClickListener( new OnClickListener() {

						@Override
						public void onClick( View v ) {

							Intent intent = new Intent();
							intent.setClass( getApplicationContext(), HowToCook.class );
							intent.putExtra( "dishId", dishInfo.getDishId() );
							startActivity( intent );
							overridePendingTransition( R.anim.slide_right_in, R.anim.slide_left_out );
						}
					} );
					TextView tv_name = ( TextView )view.findViewById( R.id.tv_everydaydishname );
					TextView tv_desc = ( TextView )view.findViewById( R.id.tv_everydaydesc );
					final ProgressBar pr = ( ProgressBar )view.findViewById( R.id.pro );
					tv_name.setText( dishInfo.getDishName() );
					tv_desc.setText( dishInfo.getDishDesc() );
					ll_everyday.addView( view );
					ImageLoader imageLoader = new ImageLoader();
					imageLoader.loadImage(getApplicationContext(), imageView, dishInfo.getDishPic(), dishInfo.getDishName(), Constant.HomeConstant.IMAGE_LENGTH, new ImageCallback() {



						@Override
						public void imageLoadOver() {

							// TODO Auto-generated method stub
							pr.setVisibility( View.GONE );
						}


						@Override
						public void imageLoadFailed() {

						}


						@Override
						public void imageLoadBefore() {

							pr.setVisibility( View.VISIBLE );
						}


						@Override
						public void imageLoading( Bitmap bitmap, String url, float ratio, int width, int height ) {


							imageView.setImageBitmap( bitmap );
						
						}

					} );
				}
				break;

			default:
				break;
			}

		};
	};
	private SlideHolder slideHolder;


	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate( Bundle savedInstanceState ) {

		// TODO Auto-generated method stub
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_home );
		initView();
		System.gc();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {

		// TODO Auto-generated method stub
		super.onResume();
		getEveryDayDishInfo();

	}


	/**
	 * 
	 */
	private void getEveryDayDishInfo() {

		final HashMap<String, Object> everyDishMap = new HashMap<String, Object>();
		everyDishMap.put( "diseaseStr", "" );
		everyDishMap.put( "num", Constant.HomeConstant.EVERYDAYDISHCOUNTS );
		everyDishMap.put( "wsUser", WebServiceConstant.wsUser );

		// TODO Auto-generated method stub
		Runnable runnable = new Runnable() {

			@Override
			public void run() {

				// TODO Auto-generated method stub
				SoapObject soapObject = WebServiceAction.getSoapObject( WebServiceConstant.SERVICE_EVERYDAY_URL, WebServiceConstant.GETPOPULARDISH,
						everyDishMap, WebServiceConstant.SERVICENAMESPACE );
				WSResult wsResult = new WSResult( soapObject );
				switch( Integer.parseInt( wsResult.getState() ) ) {
				case 201:

					break;
				case 202:
					Message msg = new Message();
					msg.obj = wsResult.getResult();
					msg.what = EVERYDAYVIEW;
					handler.sendMessage( msg );

					break;
				default:
					break;
				}
			}
		};
		ThreadTool threadTool = ThreadTool.getInstance();
		threadTool.addTask( runnable );
	}


	/**
	 * 
	 */
	private void initView() {

		// TODO Auto-generated method stub
		btn_menu = ( Button )findViewById( R.id.btn_menu );
		btn_search = ( Button )findViewById( R.id.btn_search );
		slideHolder = ( SlideHolder )findViewById( R.id.slideHolder );
		hScrollView = ( HorizontalScrollView )findViewById( R.id.hs_everyday );
		hScrollView.setOnTouchListener( this );
		btn_search.setOnClickListener( this );
		btn_menu.setOnClickListener( this );
		ll_everyday = ( LinearLayout )findViewById( R.id.ll_everyday );
		hScrollView = ( HorizontalScrollView )findViewById( R.id.hs_everyday );
		lv_home = ( ListView )findViewById( R.id.lv_home );
		adapter = new HomeListViewAdapter( getApplicationContext() );
		lv_home.setAdapter( adapter );
		lv_home.setOnItemClickListener( this );

	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick( View v ) {

		// TODO Auto-generated method stub
		switch( v.getId() ) {
		case R.id.btn_menu:

			slideHolder.toggle();

			break;
		case R.id.btn_search:
			Intent intent = new Intent();
			intent.setClass( getApplicationContext(), SearchUi.class );
			startActivity( intent );
			overridePendingTransition( R.anim.slide_top_in, R.anim.slide_out_donothing );
			break;

		default:
			break;
		}

	}


	@Override
	public void onItemClick( AdapterView<?> arg0, View arg1, int arg2, long arg3 ) {

		switch( arg2 ) {

		case 0:
			openActivity( EachdayMealsUi.class );

			break;
		case 1:
			openActivity( WhatToEatUi.class );
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			openActivity( CategoryDishesUi.class );
			break;

		default:
			break;
		}

	}





	@Override
	public boolean onTouch( View v, MotionEvent event ) {

		switch( v.getId() ) {
		case R.id.hs_everyday:

			switch( event.getAction() ) {

			case MotionEvent.ACTION_DOWN:

				slideHolder.setEnabled( false );
				return super.onTouchEvent( event );

			case MotionEvent.ACTION_MOVE:
				if( v.getScrollX() != 0 ) {
					slideHolder.setEnabled( false );
				} else {
					slideHolder.setEnabled( true );
				}
				return super.onTouchEvent( event );
			case MotionEvent.ACTION_UP:
				slideHolder.setEnabled( true );
				return super.onTouchEvent( event );
			default:
				break;
			}

			break;

		default:
			break;
		}
		return super.onTouchEvent( event );
	}
}
