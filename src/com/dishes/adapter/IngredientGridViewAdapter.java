/**
 *
 * @author SenYang
 */
package com.dishes.adapter;

import java.util.List;

import android.R.integer;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dishes.common.CommonMethod;
import com.dishes.model.IngredientInfo;
import com.dishes.ui.R;
import com.dishes.util.ImageCallback;
import com.dishes.util.ImageLoader;

/**
 * 
 * @author SenYang
 * 
 */
public class IngredientGridViewAdapter extends BaseAdapter implements Cloneable {

	private Context context;
	private LayoutInflater layoutInflater;
	private List<IngredientInfo> infos;


	class ViewHoder {

		private ImageView iView;
		private TextView tView;
		private ProgressBar pBar;
	}


	public IngredientGridViewAdapter( Context context, LayoutInflater layoutInflater, List<IngredientInfo> infos ) {

		this.context = context;
		this.layoutInflater = layoutInflater;
		this.infos = infos;
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
	public View getView( int position, View convertView, ViewGroup parent ) {

		final ViewHoder viewHoder;
		if( convertView == null ) {
			viewHoder = new ViewHoder();
			convertView = layoutInflater.inflate( R.layout.adapter_ingredient_gridview, null );
			viewHoder.tView = ( TextView )convertView.findViewById( R.id.tv_ingrename );
			viewHoder.iView = ( ImageView )convertView.findViewById( R.id.iv_ingre );
			viewHoder.pBar = ( ProgressBar )convertView.findViewById( R.id.pbar );
			int screenW = CommonMethod.getWindowSizeW( context );
			convertView.setLayoutParams( new AbsListView.LayoutParams( screenW / 4, screenW / 4 ) );

			convertView.setTag( viewHoder );
		} else {
			viewHoder = ( ViewHoder )convertView.getTag();
		}

		viewHoder.tView.setText( infos.get( position ).getInName() );
		viewHoder.iView.setOnClickListener( new OnClickListener() {

			@Override
			public void onClick( View v ) {

				AnimationSet animationSet = new AnimationSet( true );
				AlphaAnimation alphaAnimation = new AlphaAnimation( 1.0f, 0.0f );
				int screenW = CommonMethod.getWindowSizeW( context );
				int sceenH = CommonMethod.getWindowSizeH( context );
				TranslateAnimation translateAnimation = new TranslateAnimation( 0f, 0f, screenW * 0.1f, sceenH * 0.1f );
				animationSet.setDuration( 1000 );
				animationSet.addAnimation( translateAnimation );
				animationSet.addAnimation( alphaAnimation );
				viewHoder.iView.startAnimation( animationSet );

			}
		} );
		ImageLoader imageLoader = new ImageLoader();
		imageLoader.loadImage( viewHoder.iView, infos.get( position ).getInPic(), infos.get( position ).getInName(), 100, new ImageCallback() {

			@Override
			public void imageLoadOver() {

				viewHoder.pBar.setVisibility( View.GONE );
			}


			@Override
			public void imageLoadFailed() {

			}


			@Override
			public void imageLoadBefore() {

				viewHoder.iView.setVisibility( View.GONE );
				viewHoder.pBar.setVisibility( View.VISIBLE );

			}


			@Override
			public void imageLoading( Bitmap bitmap, String url, float ratio, int width, int height ) {

				viewHoder.iView.setVisibility( View.VISIBLE );
				viewHoder.iView.setImageBitmap( bitmap );
			}
		} );
		return convertView;
	}

}
