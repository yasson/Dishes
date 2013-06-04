/**
 * 
 */
package com.dishes.adapter;

import java.io.IOException;
import java.net.MalformedURLException;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dishes.common.Constant;
import com.dishes.common.ViewHolder;
import com.dishes.ui.R;

/**
 * @author SenYang
 * 
 */
public class HomeListViewAdapter extends BaseAdapter {

	private Context context;


	public HomeListViewAdapter( Context context ) {

		this.context = context;

	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {

		// TODO Auto-generated method stub
		return Constant.HomeConstant.DESCRIBTION_LIST.length;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem( int position ) {

		// TODO Auto-generated method stub
		return position;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId( int position ) {

		// TODO Auto-generated method stub
		return position;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView( int position, View convertView, ViewGroup parent ) {

		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		if( convertView == null ) {

			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from( context ).inflate( R.layout.adapter_homelistview, null );
			viewHolder.imageView = ( ImageView )convertView.findViewById( R.id.iv_homelist );
			viewHolder.textView1 = ( TextView )convertView.findViewById( R.id.tv_homef );
			viewHolder.textView2 = ( TextView )convertView.findViewById( R.id.tv_homed );
			viewHolder.textView2.setSelected( true );
			convertView.setTag( viewHolder );
		} else {
			viewHolder = ( ViewHolder )convertView.getTag();
		}
		try {
			viewHolder.imageView.setImageBitmap( BitmapFactory.decodeStream( context.getAssets().open( Constant.HomeConstant.IMAGE_LIST[ position ] ) ) );
		} catch( MalformedURLException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch( IOException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		viewHolder.textView1.setText( Constant.HomeConstant.FUNCTION_LIST[ position ] );
		viewHolder.textView2.setText( Constant.HomeConstant.DESCRIBTION_LIST[ position ] );
		return convertView;

	}
}
