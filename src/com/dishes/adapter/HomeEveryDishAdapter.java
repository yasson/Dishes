/**
 *
 * @author YangSen
 */
package com.dishes.adapter;

import java.util.List;

import com.dishes.model.DishInfo;
import com.dishes.ui.HowToCook;
import com.dishes.ui.R;
import com.dishes.util.ImageCallback;
import com.dishes.util.ImageLoader;
import com.dishes.util.bitmapfun.util.ImageFetcher;
import com.dishes.views.flipview.FlipViewController;
import com.dishes.views.flipview.UI;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * @author YangSen
 * 
 */
public class HomeEveryDishAdapter extends BaseAdapter {

	private ImageFetcher imageFetcher;
	private LayoutInflater layoutinflater;
	private List<DishInfo> infos;
	private Context context;
	private FlipViewController flipView;


	/**
	 * @param applicationContext
	 * @param infos
	 * @param flipView 
	 */
	public HomeEveryDishAdapter( Context applicationContext, List<DishInfo> infos, FlipViewController flipView ) {

		this.context = applicationContext;
		this.infos = infos;
		this.flipView=flipView;
		this.layoutinflater = LayoutInflater.from( applicationContext );
		imageFetcher = new ImageFetcher( applicationContext, 300 );
	}


	@Override
	public int getCount() {

		return infos.size();
	}


	@Override
	public Object getItem( int position ) {

		return infos.get( position );
	}


	@Override
	public long getItemId( int position ) {

		return position;
	}


	@Override
	public View getView( final int position, View convertView, ViewGroup parent ) {

		convertView = layoutinflater.inflate( R.layout.adapter_homeeveryday, null );
		final ImageView imageView = UI.findViewById(convertView, R.id.iv_everydish);
		imageView.setOnClickListener( new OnClickListener() {

			@Override
			public void onClick( View v ) {

				Intent intent = new Intent();
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.setClass( context, HowToCook.class );
				intent.putExtra( "dishId", infos.get( position ).getDishId() );
				context.startActivity(intent);
			}
		} );
		TextView tv_name = UI.findViewById(convertView, R.id.tv_everydaydishname );
		TextView tv_desc = UI.findViewById(convertView, R.id.tv_everydaydesc );

		tv_name.setText( infos.get( position ).getDishName() );
		tv_desc.setText( infos.get( position ).getDishDesc() );
//		imageFetcher.loadImage( infos.get( position ).getDishPic(), imageView );
		ImageLoader imageLoader=new ImageLoader();
		imageView.setImageResource(R.drawable.empty_photo);
		imageLoader.loadImage(context, imageView,  infos.get( position ).getDishPic(),  infos.get( position ).getDishName(), 200, new ImageCallback() {
			
			@Override
			public void imageLoading(Bitmap bitmap, String url, float ratio, int width,
					int height) {
				// TODO Auto-generated method stub
				imageView.setImageBitmap(bitmap);
			}
			
			@Override
			public void imageLoadOver() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void imageLoadFailed() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void imageLoadBefore() {
				// TODO Auto-generated method stub
				
			}
		});
		return convertView;
	}

}
