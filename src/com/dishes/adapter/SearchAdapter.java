/**
 * @author SenYang
 */
package com.dishes.adapter;

import java.util.List;

import org.ksoap2.serialization.SoapObject;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dishes.common.Constant;
import com.dishes.common.ViewHolder;
import com.dishes.model.DishInfo;
import com.dishes.ui.R;
import com.dishes.ui.SearchUi;
import com.dishes.util.ImageCallback;
import com.dishes.util.ImageLoader;

/**
 * @author SenYang
 * 
 */
public class SearchAdapter extends BaseAdapter {

	private int count = Constant.UtilConstant.LISTVIEW_MINCOUNT;
	private Context context;
	private List<SoapObject> list;
	SearchUi searchUi = new SearchUi();
	private ListView listView;


	/**
	 * @param applicationContext
	 * @param list
	 */
	public SearchAdapter( Context applicationContext, List<SoapObject> list, ListView listView ) {

		this.listView = listView;
		this.context = applicationContext;
		this.list = list;

	}


	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount( int count ) {

		this.count = count;
	}


	@Override
	public int getCount() {

		return count <= list.size() ? count : list.size();
		// return list.size();
	}


	@Override
	public Object getItem( int arg0 ) {

		return list.get( arg0 );
	}


	@Override
	public long getItemId( int arg0 ) {

		return arg0;
	}


	public String getItemDishId( int arg0 ) {

		final DishInfo dishInfo = new DishInfo( list.get( arg0 ) );

		return dishInfo.getDishId();

	}


	@Override
	public View getView( int arg0, View convertView, ViewGroup parent ) {

		final ViewHolder viewHolder;
		if( convertView == null ) {

			convertView = ( ViewGroup )LayoutInflater.from( context ).inflate( R.layout.adapter_search, null );
			viewHolder = new ViewHolder();
			viewHolder.imageView = ( ImageView )convertView.findViewById( R.id.iv_searchresult );
			viewHolder.textView1 = ( TextView )convertView.findViewById( R.id.tv_searchresultname );
			viewHolder.textView2 = ( TextView )convertView.findViewById( R.id.tv_searchresuldesc );
			viewHolder.progressBar = ( ProgressBar )convertView.findViewById( R.id.pr_searchresult );
			convertView.setTag( viewHolder );

		} else {
			viewHolder = ( ViewHolder )convertView.getTag();
		}

		final DishInfo dishInfo = new DishInfo( list.get( arg0 ) );
		viewHolder.textView1.setText( dishInfo.getDishName() );
		viewHolder.imageView.setTag( dishInfo.getDishPic() );
		// 这句代码的作用是为了解决convertView被重用的时候，图片预设的问题
		viewHolder.imageView.setImageResource( R.drawable.loadingpic );
		new ImageLoader().loadImage( viewHolder.imageView, dishInfo.getDishPic(), dishInfo.getDishName(), 100, new ImageCallback() {

			@Override
			public void imageLoadOver() {

			}


			@Override
			public void imageLoadFailed() {

				viewHolder.imageView.setImageResource( R.drawable.loadingpic );

			}


			@Override
			public void imageLoadBefore() {

			}


			@Override
			public void imageLoading( Bitmap bitmap, String url, float ratio, int width, int height ) {

				ImageView imageView = ( ImageView )listView.findViewWithTag( url );
				if( imageView != null && url.equals( imageView.getTag() ) ) {
					imageView.setImageBitmap( bitmap );
					imageView.setTag( "" );
				} else if( imageView != null ) {
					imageView.setBackgroundResource( R.drawable.loadingpic );
				}

			}
		} );
		return convertView;
	}

}
