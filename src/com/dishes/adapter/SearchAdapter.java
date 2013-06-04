/**
 * @author SenYang
 */
package com.dishes.adapter;

import java.util.List;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dishes.common.ViewHolder;
import com.dishes.model.DishInfo;
import com.dishes.ui.HowToCook;
import com.dishes.ui.R;
import com.dishes.util.ImageCallback;
import com.dishes.util.ImageLoader;

/**
 * @author SenYang
 * 
 */
public class SearchAdapter extends BaseAdapter {

	private int count = 10;
	private Context context;
	private List<SoapObject> list;


	/**
	 * @param applicationContext
	 * @param list
	 */
	public SearchAdapter( Context applicationContext, List<SoapObject> list ) {

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
		ImageLoader imageLoader = new ImageLoader();
		imageLoader.loadImage( dishInfo.getDishPic(), 100, new ImageCallback() {

			@Override
			public void imageLoading( Bitmap bitmap, float ratio, int width, int height ) {

				viewHolder.imageView.setImageBitmap( bitmap );

			}


			@Override
			public void imageLoadOver() {

				viewHolder.imageView.setVisibility( View.VISIBLE );
				viewHolder.progressBar.setVisibility( View.GONE );
			}


			@Override
			public void imageLoadFailed() {

				viewHolder.imageView.setImageResource( R.drawable.nopic );

			}


			@Override
			public void imageLoadBefore() {

				viewHolder.imageView.setVisibility( View.INVISIBLE );
				viewHolder.progressBar.setVisibility( View.VISIBLE );

			}
		} );
		return convertView;
	}

}
