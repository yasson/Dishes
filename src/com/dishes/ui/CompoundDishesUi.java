/**
 *
 * @author YangSen
 */
package com.dishes.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ksoap2.serialization.SoapObject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.dishes.AppContext;
import com.dishes.adapter.CompoundDishesAdapter;
import com.dishes.model.DishInfo;
import com.dishes.model.WSResult;
import com.dishes.ui.base.BaseActivity;
import com.dishes.util.ImageLoader.ImageLoadTask;
import com.dishes.util.ThreadTool;
import com.dishes.webservice.WebServiceAction;
import com.dishes.webservice.WebServiceConstant;

/**
 * 
 * @author YangSen
 * 
 */
public class CompoundDishesUi extends BaseActivity implements OnScrollListener,OnItemClickListener {

	private ListView lv_dishes;
	private List<DishInfo> dishInfos;
	private Handler handler = new Handler() {

		public void handleMessage( android.os.Message msg ) {

			CompoundDishesAdapter adapter = new CompoundDishesAdapter( getApplicationContext(), dishInfos, lv_dishes );
			lv_dishes.setAdapter( adapter );

		};
	};
	private int firstVisibleItem;
	private int visibleItemCount;
	private int lastItem;
	private boolean REMOVE;
	private CompoundDishesAdapter adapter;


	@Override
	protected void onCreate( Bundle savedInstanceState ) {

		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_compounddishes );
		initView();
		getCompoundDishes();
	}


	/**
	 * 
	 */
	private void getCompoundDishes() {

		new Thread( new Runnable() {

			@Override
			public void run() {

				Map<String, Object> inputmap = new HashMap<String, Object>();
				String material = "";
				for( int i = 0; i < AppContext.list_ingredient_Ids.size(); i++ ) {
					material += " " + AppContext.list_ingredient_Ids.get( i ).getInName();
				}
				inputmap.put( "material", material );
				inputmap.put( "count", 100 );
				inputmap.put( "wsUser", WebServiceConstant.wsUser );
				SoapObject so = WebServiceAction.getSoapObject( WebServiceConstant.SERVICE_GETDISHES_URL, WebServiceConstant.GETDISHES, inputmap,
						WebServiceConstant.SERVICENAMESPACE );

				WSResult result = new WSResult( so );
				for( int i = 0; i < result.getResult().size(); i++ ) {
					DishInfo dishInfo = new DishInfo( ( SoapObject )result.getResult().get( i ) );
					dishInfos.add( dishInfo );
				}
				// Message msg = new Message();
				handler.post( new Runnable() {

					@Override
					public void run() {

						adapter = new CompoundDishesAdapter( getApplicationContext(), dishInfos, lv_dishes );
						lv_dishes.setAdapter( adapter );
					}
				} );

			}
		} ).start();

	}


	/**
	 * 
	 */
	private void initView() {

		dishInfos = new ArrayList<DishInfo>();
		lv_dishes = ( ListView )findViewById( R.id.staggeredGridView1 );
		lv_dishes.setOnScrollListener( this );
		lv_dishes.setOnItemClickListener( this );

	}

	@Override
	public void onItemClick( AdapterView<?> parent, View view, int position, long id ) {
		DishInfo dishInfo = ( DishInfo )parent.getAdapter().getItem( position ) ;
		String dishId = dishInfo.getDishId();
		Intent intent = new Intent();
		intent.putExtra( "dishId", dishId );
		intent.setClass( getApplicationContext(), HowToCook.class );
		startActivity( intent );
		overridePendingTransition( R.anim.slide_right_in, R.anim.slide_left_out );
	}

	@Override
	public void onScroll( AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount ) {

		this.firstVisibleItem = firstVisibleItem;
		this.visibleItemCount = visibleItemCount;
		lastItem = firstVisibleItem + visibleItemCount;

	}


	@Override
	public void onScrollStateChanged( AbsListView view, int scrollState ) {	

		
		
		
		AppContext.IF_LOAD = false;
		if( lv_dishes.getChildCount() == 0 ) {
			return;
		}
		View convertView = null;
		if( scrollState == SCROLL_STATE_IDLE ) {
			AppContext.IF_LOAD = true;

			ArrayList<ImageLoadTask> list = ThreadTool.getImageLoadTasks();
			for( int i = 0; i < list.size(); i++ ) {
				REMOVE = true;
				for( int j = firstVisibleItem; j < lastItem; j++ ) {
						DishInfo dishInfo = dishInfos.get( j );
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
			
			
			
			for( int i = firstVisibleItem; i <= lastItem-1; i++ ) {

				adapter.getView( i, convertView, null );
			}
		}


	}



}
