/**
 * 
 */
package com.dishes.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ksoap2.serialization.SoapObject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dishes.adapter.EachdayMealsAdapter;
import com.dishes.common.CommonMethod;
import com.dishes.common.Constant;
import com.dishes.model.NutritionMealsInfo;
import com.dishes.model.WSResult;
import com.dishes.ui.base.BaseActivity;
import com.dishes.util.ThreadTool;
import com.dishes.webservice.WebServiceAction;
import com.dishes.webservice.WebServiceConstant;

/**
 * @author SenYang
 * 
 */
public class EachdayMealsUi extends BaseActivity implements OnClickListener {

	private final int GETNEWVALUE = 1;
	private final int REFRESH = 2;
	private ViewPager vp;
	private Map<String, Object> inputMap;
	private int DATEPAGE = 0;
	private Button btn_next, btn_last, btn_list;
	private ArrayList<NutritionMealsInfo> list;
	private ImageView viewflag;
	private int currentPage = 0;
	private EachdayMealsAdapter eachdayMealsAdapter;
	private Handler handler = new Handler() {

		@SuppressWarnings( "unchecked" )
		public void handleMessage( android.os.Message msg ) {

			switch( msg.what ) {
			case GETNEWVALUE:

				for( SoapObject so : ( List<SoapObject> )msg.obj ) {
					NutritionMealsInfo nutritionMealsInfo = new NutritionMealsInfo( so );
					list.add( nutritionMealsInfo );
				}
				eachdayMealsAdapter = new EachdayMealsAdapter( getApplicationContext(), list, DATEPAGE );
				vp.setAdapter( eachdayMealsAdapter );
				break;
			case REFRESH:

				break;

			default:
				break;
			}

		};
	};
	private int screenWidth;
	private int viewflagWidth;
	private int offSet;
	private int transeWidth;
	private TextView tv_breakfast;
	private TextView tv_lunch;
	private TextView tv_supper;


	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate( Bundle savedInstanceState ) {

		super.onCreate( savedInstanceState );
		System.gc();
		setContentView( R.layout.activity_eachdaymeals );
		initView();
		getUserInfos();
		getEachdayMealsInfo();
	}


	@Override
	protected void onResume() {

		super.onResume();

		vp.setOnPageChangeListener( new ViewPagerChange() );
	}


	/**
	 * 
	 */
	private void getUserInfos() {

		SharedPreferences sp = getSharedPreferences( Constant.PACKAGE_NAME, 0 );
		String userId = sp.getString( "userId", "0" );
		String userNum = sp.getString( "userNum", "3" );
		String groupNum = sp.getString( "groupNum", "7" );
		String userTaste = sp.getString( "userTaste", "" );
		String userRegion = sp.getString( "userRegion", "0" );
		inputMap.put( "userId", userId );
		inputMap.put( "userNum", userNum );
		inputMap.put( "groupNum", groupNum );
		inputMap.put( "userTaste", userTaste );
		inputMap.put( "userRegion", userRegion );
		inputMap.put( "wsUser", WebServiceConstant.wsUser );

	}


	/**
	 * 
	 */
	private void getEachdayMealsInfo() {

		ThreadTool.getInstance().addTask( new Runnable() {

			@Override
			public void run() {

				SoapObject soapObject = WebServiceAction.getSoapObject( WebServiceConstant.SERVICE_NUTRIENTFOOD_URL, WebServiceConstant.getRandomNutrientFood,
						inputMap, WebServiceConstant.SERVICENAMESPACE );
				if( soapObject != null ) {
					WSResult wsResult = new WSResult( soapObject );
					switch( Integer.parseInt( wsResult.getState() ) ) {
					case 200:
						CommonMethod.netException( getApplicationContext() );
						break;
					case 201:
						Message msg = new Message();
						msg.what = GETNEWVALUE;
						msg.obj = wsResult.getResult();
						handler.sendMessage( msg );
						break;
					case 202:
						Message msg2 = new Message();
						msg2.what = GETNEWVALUE;
						msg2.obj = wsResult.getResult();
						handler.sendMessage( msg2 );
						break;
					default:
						break;
					}

				} else {
					CommonMethod.netException( getApplicationContext() );
				}

			}
		} );

	}


	/**
	 * 
	 */
	private void initView() {

		tv_breakfast = ( TextView )findViewById( R.id.tv_breakfast );
		tv_lunch = ( TextView )findViewById( R.id.tv_lunch );
		tv_supper = ( TextView )findViewById( R.id.tv_supper );
		tv_breakfast.setOnClickListener( this );
		tv_lunch.setOnClickListener( this );
		tv_supper.setOnClickListener( this );
		viewflag = ( ImageView )findViewById( R.id.iv_flag );
		list = new ArrayList<NutritionMealsInfo>();
		btn_last = ( Button )findViewById( R.id.btn_last );
		btn_list = ( Button )findViewById( R.id.btn_list );
		btn_next = ( Button )findViewById( R.id.btn_next );
		btn_last.setOnClickListener( this );
		btn_list.setOnClickListener( this );
		btn_next.setOnClickListener( this );
		inputMap = new HashMap<String, Object>();
		vp = ( ViewPager )findViewById( R.id.vp_eachdaymeals );
		screenWidth = CommonMethod.getWindowSizeW( getApplicationContext() );
		viewflagWidth = ( screenWidth / Constant.MEALSPAGERSIZE ) * 3 / 4;
		offSet = ( screenWidth / Constant.MEALSPAGERSIZE - viewflagWidth ) / 2;
		viewflag.setMaxWidth( viewflagWidth );
		transeWidth = screenWidth / Constant.MEALSPAGERSIZE - viewflagWidth;
		// Matrix matrix = new Matrix();
		// System.out.println( matrix.postTranslate( offSet, 0 ) );
		// viewflag.setImageMatrix( matrix );
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick( View v ) {

		switch( v.getId() ) {
		case R.id.btn_last:
			if( DATEPAGE == 0 ) {
				Toast.makeText( getApplicationContext(), R.string.firstday, Toast.LENGTH_SHORT ).show();
				return;
			}
			Animation animation = new TranslateAnimation( currentPage * ( offSet + viewflagWidth + transeWidth ), 0, 0, 0 );
			animation.setDuration( 100 );
			animation.setFillAfter( true );
			viewflag.startAnimation( animation );
			currentPage = 0;
			DATEPAGE--;
			eachdayMealsAdapter = new EachdayMealsAdapter( getApplicationContext(), list, DATEPAGE );
			vp.setAdapter( eachdayMealsAdapter );
			break;
		case R.id.btn_next:
			if( DATEPAGE == 6 ) {
				Toast.makeText( getApplicationContext(), R.string.lastday, Toast.LENGTH_SHORT ).show();
				return;
			}
			Animation animation2 = new TranslateAnimation( currentPage * ( offSet + viewflagWidth + transeWidth ), 0, 0, 0 );
			animation2.setDuration( 100 );
			animation2.setFillAfter( true );
			viewflag.startAnimation( animation2 );
			currentPage = 0;
			DATEPAGE++;
			eachdayMealsAdapter = new EachdayMealsAdapter( getApplicationContext(), list, DATEPAGE );
			vp.setAdapter( eachdayMealsAdapter );
			break;
		case R.id.btn_list:
			break;

		case R.id.tv_breakfast:
			if( vp.getCurrentItem() == 1 ) {
				vp.setCurrentItem( 0 );
			}
			if( vp.getCurrentItem() == 2 ) {
				vp.setCurrentItem( 1 );
				vp.setCurrentItem( 0 );
			}
			break;
		case R.id.tv_lunch:
			vp.setCurrentItem( 1 );

			break;
		case R.id.tv_supper:
			if( vp.getCurrentItem() == 1 ) {
				vp.setCurrentItem( 2 );
			}
			if( vp.getCurrentItem() == 0 ) {
				vp.setCurrentItem( 1 );
				vp.setCurrentItem( 2 );
			}
			break;
		default:
			break;
		}

	}


	/**
	 * @author SenYang
	 * 
	 */
	public class ViewPagerChange implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged( int arg0 ) {

		}


		@Override
		public void onPageScrolled( int arg0, float arg1, int arg2 ) {

		}


		@Override
		public void onPageSelected( int arg0 ) {

			Animation animation = null;
			switch( arg0 ) {
			case 0:
				if( currentPage == 1 ) {
					animation = new TranslateAnimation( viewflagWidth + transeWidth + offSet, 0, 0, 0 );
				}
				currentPage = arg0;
				break;
			case 1:
				if( currentPage == 0 ) {
					animation = new TranslateAnimation( 0, viewflagWidth + transeWidth + offSet, 0, 0 );
				}
				if( currentPage == 2 ) {
					animation = new TranslateAnimation( 2 * offSet + 2 * viewflagWidth + 2 * transeWidth, viewflagWidth + transeWidth + offSet, 0, 0 );
				}
				currentPage = arg0;
				break;
			case 2:
				if( currentPage == 1 ) {
					animation = new TranslateAnimation( offSet + viewflagWidth + transeWidth, 2 * viewflagWidth + 2 * transeWidth + 2 * offSet, 0, 0 );
				}
				currentPage = arg0;
				break;
			default:
				break;
			}
			animation.setFillAfter( true );
			animation.setDuration( 200 );
			viewflag.startAnimation( animation );
		}

	}

}
