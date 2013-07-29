/**
 *
 * @author SenYang
 */
package com.dishes.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import com.dishes.adapter.SearchAdapter;
import com.dishes.common.Constant;
import com.dishes.interfaces.startUi;
import com.dishes.model.DishInfo;
import com.dishes.model.WSResult;
import com.dishes.ui.base.BaseActivity;
import com.dishes.util.ThreadTool;
import com.dishes.util.ImageLoader.ImageLoadTask;
import com.dishes.webservice.WebServiceAction;
import com.dishes.webservice.WebServiceConstant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * 
 * @author SenYang
 * 
 */
public class CategoryDetailUi extends BaseActivity implements startUi, OnScrollListener, OnItemClickListener {

	private TextView tv_categorytitle;
	private HashMap<String, Object> dishMap;
	private int title;
	private ListView lv_dishes;
	private static final int SHOWRESULT = 1;
	private static final int ADDMORE = 2;
	private SearchAdapter adapter;
	private List<SoapObject> lists;
	private List<SoapObject> listA;
	// private int startP = 0;
	// private int countP = 10;
	private Handler mHandler = new Handler() {

		public void handleMessage( Message msg ) {

			switch( msg.what ) {
			case SHOWRESULT:
				@SuppressWarnings( "unchecked" )
				List<SoapObject> list = ( List<SoapObject> )msg.obj;
				listA = list;
				// for( int i = startP; i < countP; i++ ) {
				// lists.add( list.get( i ) );
				// }
				// startP += countP;

				adapter = new SearchAdapter( getApplicationContext(), listA, lv_dishes );
				if( lv_dishes.getFooterViewsCount() != 0 ) {
					lv_dishes.removeFooterView( view );
				}
				if( lv_dishes.getFooterViewsCount() == 0 && ( list.size() > Constant.UtilConstant.LISTVIEW_MINCOUNT ) ) {
					lv_dishes.addFooterView( view );
				}
				lv_dishes.setAdapter( adapter );
				done();
				break;
			case ADDMORE:
				break;

			default:
				break;
			}
		};
	};
	private View view;
	private ProgressBar pro_loading;
	private int lastVisibleItem;
	private int count;
	private boolean REMOVE;
	private int firstVisibleItem;


	@Override
	protected void onCreate( Bundle savedInstanceState ) {

		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_categorydetail );
		initMap();
		initView();

	}


	@Override
	protected void onResume() {

		super.onResume();
		Intent intent = getIntent();
		int type = intent.getIntExtra( "categoryType", 0 );
		if( title != type ) {
			title = type;
			getCategoryDishInfo( type );
		}

	}


	/**
	 * @param type
	 */
	private void getCategoryDishInfo( final int type ) {

		start();
		dishMap.put( "searchType", type );

		ThreadTool.getInstance().addTask( new Runnable() {

			@Override
			public void run() {

				String wsMethod = null;

				switch( type ) {
				case 0:
					wsMethod = "getStaples";
					break;
				case 1:
					wsMethod = "getHomeDishes";
					break;
				case 2:
					wsMethod = "getSoups";
					break;
				case 3:
					wsMethod = "getDrinks";
					break;

				default:
					break;
				}
				SoapObject soapObject = WebServiceAction.getSoapObject( WebServiceConstant.STAPLES_URL, wsMethod, dishMap, WebServiceConstant.SERVICENAMESPACE );
				WSResult wsResult = new WSResult( soapObject );
				Message msg = new Message();
				msg.what = SHOWRESULT;
				msg.obj = wsResult.getResult();
				mHandler.sendMessage( msg );
			}
		} );

	}


	/**
	 * 
	 */
	private void initMap() {

		dishMap = new HashMap<String, Object>();
		dishMap.put( "startIdx", 0 );
		dishMap.put( "count", Constant.CategoryDishesConstant.DISH_NUM );
		dishMap.put( "wsUser", WebServiceConstant.wsUser );

	}


	/**
	 * 
	 */
	private void initView() {

		listA = new ArrayList<SoapObject>();
		lists = new ArrayList<SoapObject>();
		Intent intent = getIntent();
		title = intent.getIntExtra( "categoryType", 0 );
		lv_dishes = ( ListView )findViewById( R.id.lv_dish );
		lv_dishes.setOnScrollListener( this );
		lv_dishes.setOnItemClickListener( this );
		view = LayoutInflater.from( getApplicationContext() ).inflate( R.layout.tool_refreshlistview, null );
		pro_loading = ( ProgressBar )findViewById( R.id.pro_loading );
		tv_categorytitle = ( TextView )findViewById( R.id.tv_categorytitle );
		tv_categorytitle.setText( Constant.CategoryDishesConstant.CATEGORY_LIST[ title ] );
		getCategoryDishInfo( title );
	}


	@Override
	public void onBackPressed() {

		super.onBackPressed();

		overridePendingTransition( R.anim.slide_in, R.anim.slide_out );
	}


	@Override
	public void start() {

		pro_loading.setVisibility( View.VISIBLE );
	}


	@Override
	public void done() {

		pro_loading.setVisibility( View.GONE );
	}


	@Override
	public void destroy() {

	}


	@Override
	public void onScroll( AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount ) {

		this.firstVisibleItem = firstVisibleItem;

		lastVisibleItem = firstVisibleItem + visibleItemCount;

	}


	@Override
	public void onScrollStateChanged( AbsListView view, int scrollState ) {

		if( lv_dishes.getChildCount() == 0 ) {
			return;
		}
		if( scrollState == OnScrollListener.SCROLL_STATE_IDLE ) {
			ArrayList<ImageLoadTask> list = ThreadTool.getImageLoadTasks();
			for( int i = 0; i < list.size(); i++ ) {
				REMOVE = true;
				for( int j = firstVisibleItem; j < lastVisibleItem - 1; j++ ) {
					DishInfo dishInfo = new DishInfo( listA.get( j ) );
					if( list.get( i ).getId().equals( dishInfo.getDishPic() ) ) {
						REMOVE = false;
						break;
					}
				}
				if( REMOVE ) {
					list.get( i ).stopTask();
					list.remove( i );
					i--;
				}

			}
			if( lastVisibleItem == adapter.getCount() + 1 ) {

				adapter.setCount( adapter.getCount() + Constant.UtilConstant.LISTVIEW_MINCOUNT );
				if( lastVisibleItem != adapter.getCount() + 1 ) {

					adapter.notifyDataSetChanged();
				} else {
					lv_dishes.removeFooterView( this.view );
				}

			}
		}

	}


	@Override
	public void onItemClick( AdapterView<?> arg0, View arg1, int arg2, long arg3 ) {

		arg0.getChildAt( arg2 );
		DishInfo dishInfo = new DishInfo( ( SoapObject )arg0.getAdapter().getItem( arg2 ) );
		String dishId = dishInfo.getDishId();
		Intent intent = new Intent();
		intent.putExtra( "dishId", dishId );
		intent.setClass( getApplicationContext(), HowToCook.class );
		startActivity( intent );
		overridePendingTransition( R.anim.slide_right_in, R.anim.slide_left_out );
	}
}
