/**
 *
 * @author YangSen
 */
package com.dishes.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dishes.model.DishInfo;
import com.dishes.ui.HowToCook;
import com.dishes.ui.R;
import com.dishes.util.bitmapfun.util.ImageFetcher;

/**
 * 
 * @author YangSen
 * 
 */
public class HomeEveryDayDishAdapter extends PagerAdapter {

	private LayoutInflater layoutinflater;
	private List<DishInfo> infos;
	private Context context;
	private ImageFetcher imageFetcher;


	/**
	 * @param applicationContext
	 * @param infos
	 */
	public HomeEveryDayDishAdapter( Context applicationContext, List<DishInfo> infos ) {

		this.context = applicationContext;
		this.infos = infos;
		this.layoutinflater = LayoutInflater.from( applicationContext );
		imageFetcher = new ImageFetcher( applicationContext, 300 );
	}


	@Override
	public void destroyItem( ViewGroup container, int position, Object object ) {

		container.removeView( ( View )object );
	}


	@Override
	public int getCount() {

		return infos.size();
	}


	@Override
	public boolean isViewFromObject( View arg0, Object arg1 ) {

		return arg0 == arg1;
	}


	@Override
	public Object instantiateItem( ViewGroup container, final int position ) {

		View view = layoutinflater.inflate( R.layout.adapter_homeeveryday, null );
		final ImageView imageView = ( ImageView )view.findViewById( R.id.iv_everydish );
		imageView.setOnClickListener( new OnClickListener() {

			@Override
			public void onClick( View v ) {

				Intent intent = new Intent();
				intent.setClass( context, HowToCook.class );
				intent.putExtra( "dishId", infos.get( position ).getDishId() );
			}
		} );
		TextView tv_name = ( TextView )view.findViewById( R.id.tv_everydaydishname );
		TextView tv_desc = ( TextView )view.findViewById( R.id.tv_everydaydesc );
		
		tv_name.setText( infos.get( position ).getDishName() );
		tv_desc.setText( infos.get( position ).getDishDesc() );
		imageFetcher.loadImage( infos.get( position ).getDishPic(), imageView );
		container.addView( view );

		return view;
	}

}
