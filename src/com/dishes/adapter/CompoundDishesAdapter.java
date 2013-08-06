/**
 *
 * @author YangSen
 */
package com.dishes.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dishes.AppContext;
import com.dishes.model.DishInfo;
import com.dishes.ui.R;
import com.dishes.util.ImageCallback;
import com.dishes.util.ImageLoader;

/**
 * 
 * @author YangSen
 * 
 */
public class CompoundDishesAdapter extends BaseAdapter {

	private class ViewHolder {

		private ImageView iView;
		private TextView tViewName;
	}


	private Context context;
	private List<DishInfo> dishInfos;
	private LayoutInflater layoutInflater;
	private ListView staggeredGridView;


	// private StaggeredGridView staggeredGridView;

	public CompoundDishesAdapter( Context context, List<DishInfo> dishInfos, ListView staggeredGridView ) {

		this.context = context;
		this.dishInfos = dishInfos;
		this.layoutInflater = LayoutInflater.from( context );
		this.staggeredGridView = staggeredGridView;

	}


	@Override
	public int getCount() {

		return dishInfos.size();
	}


	@Override
	public Object getItem( int arg0 ) {

		return dishInfos.get( arg0 );
	}


	@Override
	public long getItemId( int position ) {

		return position;
	}


	@Override
	public View getView( int position, View convertView, ViewGroup parent ) {

		ViewHolder viewHolder;
		if( convertView == null ) {
			// LayoutParams lp;
			viewHolder = new ViewHolder();
			convertView = layoutInflater.inflate( R.layout.adapter_compounddishes, parent, false );
			// lp=new LayoutParams( convertView.getLayoutParams() );
			// lp.span=staggeredGridView.getColumnCount();
			// convertView.setLayoutParams( lp );o

			viewHolder.iView = ( ImageView )convertView.findViewById( R.id.iv_ingre );
			viewHolder.tViewName = ( TextView )convertView.findViewById( R.id.tv_ingrename );
			convertView.setTag( viewHolder );

		} else {
			viewHolder = ( ViewHolder )convertView.getTag();
		}
		String dishPic = dishInfos.get( position ).getDishPic();
		String dishName = dishInfos.get( position ).getDishName();
		viewHolder.tViewName.setText( dishName );
		viewHolder.iView.setTag( dishPic );
		viewHolder.iView.setImageResource( R.drawable.loadingpic );
		ImageLoader imageLoader = new ImageLoader();
		if( AppContext.IF_LOAD ) {
			
		
		imageLoader.loadImage( context, viewHolder.iView, dishPic, dishName, 400, new ImageCallback() {

			@Override
			public void imageLoading( Bitmap bitmap, String url, float ratio, int width, int height ) {

				ImageView imageView = ( ImageView )staggeredGridView.findViewWithTag( url );
				if( imageView != null && url.equals( imageView.getTag() ) ) {
					imageView.setImageBitmap( bitmap );
				} else if( imageView != null ) {
					imageView.setImageResource( R.drawable.loadingpic );
				}

			}


			@Override
			public void imageLoadOver() {

			}


			@Override
			public void imageLoadFailed() {

			}


			@Override
			public void imageLoadBefore() {

			}
		} );}

		return convertView;
	}

}
