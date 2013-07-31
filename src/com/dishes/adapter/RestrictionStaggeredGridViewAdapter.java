/**
 *
 * @author YangSen
 */
package com.dishes.adapter;

import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dishes.model.IngredientInfo;
import com.dishes.ui.R;
import com.dishes.util.bitmapfun.util.ImageFetcher;
import com.dishes.views.stageredggridview.StaggeredGridView;

/**
 * 
 * @author YangSen
 * 
 */
public class RestrictionStaggeredGridViewAdapter extends BaseAdapter {

	private StaggeredGridView sGridView;
	private List<IngredientInfo> infos;
	private Context context;
	private ImageFetcher mImageFetcher;
	private LayoutInflater layoutInflater;


	class ViewHolder {

		ImageView imageView;
		TextView textView;
	}


	/**
	 * @param applicationContext
	 * @param infos
	 * @param sGridView
	 */
	public RestrictionStaggeredGridViewAdapter( Context applicationContext, List<IngredientInfo> infos, StaggeredGridView sGridView ) {

		mImageFetcher = new ImageFetcher( applicationContext, 240 );
		mImageFetcher.setLoadingImage( R.drawable.empty_photo );
		this.context = applicationContext;
		this.infos = infos;
		this.sGridView = sGridView;
		this.layoutInflater = LayoutInflater.from( context );
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

		ViewHolder viewHolder;
		if( convertView == null ) {
			viewHolder = new ViewHolder();
			convertView = layoutInflater.inflate( R.layout.adapter_ingredients_restriction_lists, null );
			viewHolder.imageView = ( ImageView )convertView.findViewById( R.id.news_pic );
			viewHolder.textView = ( TextView )convertView.findViewById( R.id.news_title );
			convertView.setTag( viewHolder );
		} else {
			viewHolder = ( ViewHolder )convertView.getTag();
		}
		viewHolder.textView.setText( infos.get( position ).getInName() );

		int width = infos.get( position ).getWidth();
		int height = infos.get( position ).getHeight();
		if( width != 0 ) {
			viewHolder.imageView.setLayoutParams( new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, height * 370 / width ) );
		}

		mImageFetcher.loadImage( infos.get( position ).getInPic(), viewHolder.imageView );
		return convertView;
	}

}
