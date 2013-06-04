/**
 * @author SenYang
 */
package com.dishes.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.dishes.adapter.SearchAdapter;
import com.dishes.model.DishInfo;
import com.dishes.model.WSResult;
import com.dishes.util.ThreadTool;
import com.dishes.webservice.WebServiceAction;
import com.dishes.webservice.WebServiceConstant;

/**
 * @author SenYang
 * 
 */
public class SearchUi extends Activity implements OnClickListener, OnScrollListener {

	private Button btn_searching;
	private EditText et_search;
	private ListView lv_searchresult;
	private Map<String, Object> sreachDishMap;
	private final int SHOWSEARCHRESULT = 1;
	private int lastItem = 0;

	private SearchAdapter adapter;
	private Handler handler = new Handler() {

		public void handleMessage( Message msg ) {

			switch( msg.what ) {
			case SHOWSEARCHRESULT:
				@SuppressWarnings( "unchecked" )
				List<SoapObject> list = ( List<SoapObject> )msg.obj;
				adapter = new SearchAdapter( getApplicationContext(), list );
				lv_searchresult.setAdapter( adapter );
				break;

			default:
				break;
			}
		};
	};


	@Override
	protected void onCreate( Bundle savedInstanceState ) {

		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_search );
		initView();
	}


	/**
	 * 
	 */
	private void initView() {

		btn_searching = ( Button )findViewById( R.id.btn_searching );
		btn_searching.setOnClickListener( this );
		et_search = ( EditText )findViewById( R.id.et_search );
		lv_searchresult = ( ListView )findViewById( R.id.lv_searchresult );
		lv_searchresult.setOnScrollListener( this );
		lv_searchresult.setOnItemClickListener( new ListViewItemClickL() );

	}


	@Override
	public void onClick( View v ) {

		switch( v.getId() ) {
		case R.id.btn_searching:
			String string = et_search.getText().toString();
			Intent intent = getIntent();
			if( intent.getStringExtra( "dishName" ) != null ) {
				string = intent.getStringExtra( "dishName" );
			}
			sreachDishMap = new HashMap<String, Object>();
			sreachDishMap.put( "name", string );
			sreachDishMap.put( "ingredients", null );
			sreachDishMap.put( "process", null );
			sreachDishMap.put( "taste", null );
			sreachDishMap.put( "caixi", null );
			sreachDishMap.put( "searchType", "0" );
			sreachDishMap.put( "wsUser", WebServiceConstant.wsUser );
			getSreachDishInfo( sreachDishMap );

			break;

		default:
			break;
		}

	}


	/**
	 * @param sreachDishMap2
	 */
	private void getSreachDishInfo( Map<String, Object> sreachDishMap2 ) {

		ThreadTool.getInstance().addTask( new Runnable() {

			@Override
			public void run() {

				SoapObject soapObject = WebServiceAction.getSoapObject( WebServiceConstant.SERVICE_SEARCHDISHES_URL, WebServiceConstant.GETDISHBYCONDITIONS,
						sreachDishMap, WebServiceConstant.SERVICENAMESPACE );
				WSResult wsResult = new WSResult( soapObject );
				Message msg = new Message();
				msg.what = SHOWSEARCHRESULT;
				msg.obj = wsResult.getResult();

				handler.sendMessage( msg );
			}
		} );

	}


	@Override
	public void onScroll( AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount ) {

		lastItem = firstVisibleItem + visibleItemCount;
	}


	@Override
	public void onScrollStateChanged( AbsListView view, int scrollState ) {

		if( lastItem == adapter.getCount() && scrollState == OnScrollListener.SCROLL_STATE_IDLE ) {
			adapter.setCount( adapter.getCount() + 10 );
			if( lastItem != adapter.getCount() ) {
				adapter.notifyDataSetChanged();
			}

		}

	}


	@Override
	protected void onPause() {

		super.onPause();
		overridePendingTransition( R.anim.slide_right_in, R.anim.slide_left_out );
	}


	public class ListViewItemClickL implements OnItemClickListener {

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

}
