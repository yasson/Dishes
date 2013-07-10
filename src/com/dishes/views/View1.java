/**
 *
 * @author SenYang
 */
package com.dishes.views;

import java.util.HashMap;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.dishes.adapter.HomeListViewAdapter;
import com.dishes.common.Constant;
import com.dishes.model.DishInfo;
import com.dishes.model.WSResult;
import com.dishes.ui.CategoryDishesUi;
import com.dishes.ui.EachdayMealsUi;
import com.dishes.ui.Home;
import com.dishes.ui.HowToCook;
import com.dishes.ui.R;
import com.dishes.ui.SearchUi;
import com.dishes.util.ImageCallback;
import com.dishes.util.ImageLoader;
import com.dishes.util.ThreadTool;
import com.dishes.webservice.WebServiceAction;
import com.dishes.webservice.WebServiceConstant;

/**
 * 
 * @author SenYang
 * 
 */
public class View1 extends BaseView implements OnClickListener {

	private Context context;
	private Button button;
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
					View view = LayoutInflater.from( context ).inflate( R.layout.adapter_homeeveryday, null );
					final ImageView imageView = ( ImageView )view.findViewById( R.id.iv_everydish );
					imageView.setOnClickListener( new OnClickListener() {

						@Override
						public void onClick( View v ) {

							Intent intent = new Intent();
							intent.setClass( context, HowToCook.class );
							intent.putExtra( "dishId", dishInfo.getDishId() );
							// startActivity( intent );
							// overridePendingTransition( R.anim.slide_right_in,
							// R.anim.slide_left_out );
						}
					} );
					TextView tv_name = ( TextView )view.findViewById( R.id.tv_everydaydishname );
					TextView tv_desc = ( TextView )view.findViewById( R.id.tv_everydaydesc );
					final ProgressBar pr = ( ProgressBar )view.findViewById( R.id.pro );
					tv_name.setText( dishInfo.getDishName() );
					tv_desc.setText( dishInfo.getDishDesc() );
					ll_everyday.addView( view );
					ImageLoader imageLoader = new ImageLoader();
					imageLoader.loadImage( imageView, dishInfo.getDishPic(), dishInfo.getDishName(), Constant.HomeConstant.IMAGE_LENGTH, new ImageCallback() {

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
	private Home home;


	public View1( Context context, Home home ) {

		this.context = context;
		this.home = home;
	}


	public void initView() {

		view = LayoutInflater.from( context ).inflate( R.layout.activity_home, null );
		view.setBackgroundResource( R.drawable.backg );
		// TODO Auto-generated method stub
		btn_menu = ( Button )view.findViewById( R.id.btn_menu );
		btn_search = ( Button )view.findViewById( R.id.btn_search );
		btn_search.setOnClickListener( this );
		btn_menu.setOnClickListener( this );
		ll_everyday = ( LinearLayout )view.findViewById( R.id.ll_everyday );
		hScrollView = ( HorizontalScrollView )view.findViewById( R.id.hs_everyday );
		lv_home = ( ListView )view.findViewById( R.id.lv_home );
		adapter = new HomeListViewAdapter( context );
		lv_home.setAdapter( adapter );
		lv_home.setOnItemClickListener( new HomeListViewClick() );

	}


	@Override
	public void onClick( View v ) {

		// TODO Auto-generated method stub
		switch( v.getId() ) {
		case R.id.btn_menu:
			homeMenuView.showHideLeftMenu();
			break;
		case R.id.btn_search:
			Intent intent = new Intent();
			intent.setClass( context, SearchUi.class );
			home.turn( context );
			// startActivity( intent );
			// overridePendingTransition( android.R.anim.slide_in_left,
			// android.R.anim.slide_out_right );
			break;

		default:
			break;
		}

	}


	public void getEveryDayDishInfo() {

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


	public class HomeListViewClick implements OnItemClickListener {

		@Override
		public void onItemClick( AdapterView<?> arg0, View arg1, int arg2, long arg3 ) {

			Intent intent = new Intent();
			switch( arg2 ) {

			case 0:
				intent.setClass( context, EachdayMealsUi.class );
				// startActivity( intent );
				// overridePendingTransition( R.anim.slide_right_in,
				// R.anim.slide_left_out );

				break;
			case 1:

				break;
			case 2:

				break;
			case 3:
				intent.setClass( context, Home.class );
				// startActivity( intent );
				// overridePendingTransition( R.anim.slide_right_in,
				// R.anim.slide_left_out );
				break;
			case 4:
				intent.setClass( context, CategoryDishesUi.class );
				// startActivity( intent );
				// overridePendingTransition( R.anim.slide_right_in,
				// R.anim.slide_left_out );
				break;

			default:
				break;
			}

		}

	}

	public interface OnIntent {

		public void turn( Context context );

	}
}
