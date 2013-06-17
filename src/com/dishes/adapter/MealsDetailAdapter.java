/**
 * @author SenYang
 */
package com.dishes.adapter;

import java.util.ArrayList;
import java.util.List;

import com.dishes.common.CommonMethod;
import com.dishes.common.ViewHolder;
import com.dishes.model.MealsDetailInfo;
import com.dishes.model.NutritionMealsInfo;
import com.dishes.ui.HowToCook;
import com.dishes.ui.R;
import com.dishes.util.ImageCallback;
import com.dishes.util.ImageLoader;

import android.R.integer;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

/**
 * @author SenYang
 * 
 */
public class MealsDetailAdapter extends BaseAdapter {

	private Context context;
	private List<MealsDetailInfo> mealsDetailInfos;
	private int flag;
	private List<MealsDetailInfo> usedInfos;


	/**
	 * @param context
	 * @param nutritionMealsInfo
	 * @param position
	 */
	public MealsDetailAdapter( Context context, NutritionMealsInfo nutritionMealsInfo, int position ) {

		this.context = context;
		this.mealsDetailInfos = nutritionMealsInfo.getMealsDetailInfos();
		this.flag = position;
	}


	@Override
	public int getCount() {

		int size = 0;
		usedInfos = new ArrayList<MealsDetailInfo>();
		for( MealsDetailInfo info : mealsDetailInfos ) {
			if( info.getType().equals( flag + "" ) ) {
				usedInfos.add( info );
				size++;
			}

		}

		return size;
	}


	@Override
	public Object getItem( int position ) {

		return usedInfos.get( position );
	}


	@Override
	public long getItemId( int position ) {

		return position;
	}


	@Override
	public View getView( final int position, View convertView, ViewGroup parent ) {

		final ViewHolder viewHolder;

		if( convertView == null ) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from( context ).inflate( R.layout.adapter_mealsdetailadapter, null );
			viewHolder.imageView = ( ImageView )convertView.findViewById( R.id.iv_homelist );
			viewHolder.textView1 = ( TextView )convertView.findViewById( R.id.tv_homef );
			convertView.setTag( viewHolder );
		} else {
			viewHolder = ( ViewHolder )convertView.getTag();
		}
		viewHolder.textView1.setText( usedInfos.get( position ).getDishName() );
		ImageLoader imageLoader = new ImageLoader();
		imageLoader.loadImage( null, usedInfos.get( position ).getDishPic(), usedInfos.get( position ).getDishName(),400, new ImageCallback() {

			@Override
			public void imageLoading( Bitmap bitmap, float ratio, int width, int height ) {

				int screenWidth = CommonMethod.getWindowSizeW( context );
				int screenHeight = CommonMethod.getWindowSizeH( context );
				viewHolder.imageView.setLayoutParams( new RelativeLayout.LayoutParams( screenWidth - 20, (screenHeight/2) ) );
				viewHolder.imageView.setX( 10 );
				viewHolder.imageView.setImageBitmap( bitmap );
				viewHolder.imageView.setOnClickListener( new OnClickListener() {

					@Override
					public void onClick( View v ) {

						Intent intent = new Intent();
						intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK);
						intent.setClass( context, HowToCook.class );
						intent.putExtra( "dishId", usedInfos.get( position ).getDishId() );
						context.startActivity( intent );
					}
				} );
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

		} );
		return convertView;
	}

}
