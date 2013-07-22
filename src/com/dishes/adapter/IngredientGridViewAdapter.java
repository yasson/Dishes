/**
 *
 * @author SenYang
 */
package com.dishes.adapter;

import java.util.List;

import android.R.integer;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.dishes.AppContext;
import com.dishes.common.CommonMethod;
import com.dishes.model.IngredientInfo;
import com.dishes.ui.R;
import com.dishes.ui.WhatToEatUi;
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
	private GridView gridView;
	private RelativeLayout relativeLayout;
	private Handler handler;


	class ViewHoder {

		private ImageView iView;
		private TextView tView;
		private ProgressBar pBar;
	}


	public IngredientGridViewAdapter( Handler handler, RelativeLayout relativeLayout, GridView gridView, Context context, LayoutInflater layoutInflater,
			List<IngredientInfo> infos ) {

		this.relativeLayout = relativeLayout;
		this.gridView = gridView;
		this.context = context;
		this.layoutInflater = layoutInflater;
		this.infos = infos;
		this.handler = handler;
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
		viewHoder.iView.setTag( infos.get( position ).getInPic() );
		viewHoder.iView.setImageResource( R.drawable.loadingpic );
		viewHoder.tView.setText( infos.get( position ).getInName() );
		viewHoder.iView.setOnClickListener( new OnClickListener() {

			@Override
			public void onClick( final View v ) {

				if( AppContext.list_ingredient_Ids.size() >= 10 ) {
					Toast.makeText( context, R.string.choseningre_atmost, Toast.LENGTH_SHORT ).show();
					return;
				}

				if( AppContext.list_ingredient_Ids.contains( infos.get( position ).getInId() ) ) {
					Toast.makeText( context, R.string.choseningre_already, Toast.LENGTH_SHORT ).show();
					return;
				}
				AppContext.list_ingredient_Ids.add( infos.get( position ) );
				final ImageView iView = new ImageView( context );
				relativeLayout.addView( iView );
				int[] a = { 0, 0 };
				v.getLocationInWindow( a );// 获取v所在的位置
				iView.setX( a[ 0 ] );
				iView.setY( a[ 1 ] - v.getHeight() );
				iView.setLayoutParams( new RelativeLayout.LayoutParams( v.getWidth(), v.getHeight() ) );
				v.setDrawingCacheEnabled( true );
				iView.setImageBitmap( Bitmap.createBitmap( v.getDrawingCache() ) );
				v.setDrawingCacheEnabled( false );
				iView.bringToFront();
				AnimationSet animationSet = new AnimationSet( true );
				AlphaAnimation alphaAnimation = new AlphaAnimation( 1.0f, 0.6f );
				ScaleAnimation scaleAnimation = new ScaleAnimation( 1f, 0.3f, 1f, 0.3f );
				int screenW = CommonMethod.getWindowSizeW( context );
				int sceenH = CommonMethod.getWindowSizeH( context );
				TranslateAnimation translateAnimation = new TranslateAnimation( 0, screenW * 0.2f - a[ 0 ], 0, sceenH * 1f - iView.getY() );
				animationSet.setDuration( 1000 );
				animationSet.addAnimation( translateAnimation );
				animationSet.addAnimation( alphaAnimation );
				animationSet.addAnimation( scaleAnimation );
				animationSet.setFillAfter( true );
				iView.startAnimation( animationSet );
				new Thread( new Runnable() {

					@Override
					public void run() {

						try {
							Thread.sleep( 1000 );
						} catch( InterruptedException e ) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						handler.post( new Runnable() {

							@Override
							public void run() {

								relativeLayout.removeView( iView );
							}
						} );

					}
				} ).start();

				animationSet.setAnimationListener( new AnimationListener() {

					@Override
					public void onAnimationStart( Animation animation ) {

					}


					@Override
					public void onAnimationRepeat( Animation animation ) {

					}


					@Override
					public void onAnimationEnd( Animation animation ) {

						View view = layoutInflater.inflate( R.layout.adapter_ingredient_chosen, null );
						ImageView imageView = ( ImageView )view.findViewById( R.id.iv_ingre );
						TextView textView = ( TextView )view.findViewById( R.id.tv_ingrename );
						textView.setText( infos.get( position ).getInName() );
						view.setLayoutParams( new LinearLayout.LayoutParams( 130, LayoutParams.MATCH_PARENT ) );
						v.setDrawingCacheEnabled( true );
						imageView.setImageBitmap( Bitmap.createBitmap( v.getDrawingCache() ) );
						v.setDrawingCacheEnabled( false );
						WhatToEatUi.ll_hs.addView( view );
						view.setOnClickListener( new OnClickListener() {

							@Override
							public void onClick( final View v ) {

								AnimationSet animationSet = new AnimationSet( true );
								AlphaAnimation alphaAnimation = new AlphaAnimation( 1.0f, 0.3f );
								ScaleAnimation scaleAnimation = new ScaleAnimation( 1.2f, 0.1f, 1.2f, 0.1f, Animation.RELATIVE_TO_SELF, 0.5f,
										Animation.RELATIVE_TO_SELF, 0.5f );
								animationSet.addAnimation( alphaAnimation );
								animationSet.addAnimation( scaleAnimation );
								animationSet.setDuration( 500 );
								v.startAnimation( animationSet );

								new Thread( new Runnable() {

									@Override
									public void run() {

										try {
											Thread.sleep( 500 );
										} catch( InterruptedException e ) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										handler.post( new Runnable() {

											@Override
											public void run() {

												WhatToEatUi.ll_hs.removeView( v );
												AppContext.list_ingredient_Ids.remove( infos.get( position ));
											}
										} );

									}
								} ).start();

							}
						} );
						WhatToEatUi.hs_chosen.fullScroll( ScrollView.FOCUS_RIGHT );

					}
				} );

			}
		} );
		ImageLoader imageLoader = new ImageLoader();
		imageLoader.loadImage( context, viewHoder.iView, infos.get( position ).getInPic(), infos.get( position ).getInName(), 100, new ImageCallback() {

			@Override
			public void imageLoadOver() {

			}


			@Override
			public void imageLoadFailed() {

			}


			@Override
			public void imageLoadBefore() {

			}


			@Override
			public void imageLoading( Bitmap bitmap, String url, float ratio, int width, int height ) {

				ImageView imageView = ( ImageView )gridView.findViewWithTag( url );
				if( imageView != null && imageView.getTag().equals( url ) ) {
					imageView.setImageBitmap( bitmap );
				} else if( imageView != null ) {
					imageView.setImageResource( R.drawable.loadingpic );
				}

			}
		} );
		return convertView;
	}

}
