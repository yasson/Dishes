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

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.dishes.AppContext;
import com.dishes.adapter.CompoundDishesAdapter;
import com.dishes.common.Constant;
import com.dishes.model.DishInfo;
import com.dishes.model.WSResult;
import com.dishes.ui.base.BaseActivity;
import com.dishes.util.ThreadTool;
import com.dishes.util.ImageLoader.ImageLoadTask;
import com.dishes.views.staggeredgridview.StaggeredGridView;
import com.dishes.webservice.WebServiceAction;
import com.dishes.webservice.WebServiceConstant;

/**
 * 
 * @author YangSen
 * 
 */
public class CompoundDishesUi extends BaseActivity implements OnScrollListener {

	private ListView staggeredGridView1;
	private List<DishInfo> dishInfos;
	private Handler handler = new Handler() {

		public void handleMessage( android.os.Message msg ) {

			CompoundDishesAdapter adapter = new CompoundDishesAdapter( getApplicationContext(), dishInfos, staggeredGridView1 );
			staggeredGridView1.setAdapter( adapter );

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

						adapter = new CompoundDishesAdapter( getApplicationContext(), dishInfos, staggeredGridView1 );
						staggeredGridView1.setAdapter( adapter );
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
		staggeredGridView1 = ( ListView )findViewById( R.id.staggeredGridView1 );
		staggeredGridView1.setOnScrollListener( this );

	}


	@Override
	public void onScroll( AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount ) {

		this.firstVisibleItem = firstVisibleItem;
		this.visibleItemCount = visibleItemCount;
		lastItem = firstVisibleItem + visibleItemCount;

	}


	@Override
	public void onScrollStateChanged( AbsListView view, int scrollState ) {

		System.gc();
		if( staggeredGridView1.getChildCount() == 0 ) {
			return;
		}
		if( scrollState == OnScrollListener.SCROLL_STATE_IDLE ) {
			ArrayList<ImageLoadTask> list = ThreadTool.getImageLoadTasks();
			for( int i = 0; i < list.size(); i++ ) {
				REMOVE = true;
				for( int j = firstVisibleItem; j < lastItem - 1; j++ ) {
					DishInfo dishInfo = dishInfos.get( i );
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
		}

	}

}
