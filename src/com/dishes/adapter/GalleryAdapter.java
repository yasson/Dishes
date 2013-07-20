/**
 *
 * @author YangSen
 */
package com.dishes.adapter;

import com.dishes.common.Constant;
import com.dishes.ui.R;

import android.R.integer;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 
 * @author YangSen
 * 
 */
public class GalleryAdapter extends BaseAdapter {

	private class ViewHolder {

		private TextView textView;
	}


	private String[] list;
	private Context context;
	private LayoutInflater layoutInflater;
	private int selectedItem;


	public GalleryAdapter( Context context ) {

		this.list = Constant.WhatToEatConstant.CATEGORY_LIST;
		this.context = context;
		this.layoutInflater = LayoutInflater.from( context );

	}


	@Override
	public int getCount() {

		return list.length;
	}


	@Override
	public Object getItem( int arg0 ) {

		return list[ arg0 ];
	}


	@Override
	public long getItemId( int position ) {

		return position;
	}


	public void setSelected( int position ) {

		this.selectedItem = position;
		notifyDataSetChanged();
	}


	@Override
	public View getView( int position, View convertView, ViewGroup parent ) {

		ViewHolder viewHolder;

		if( position == selectedItem ) {

			if( convertView == null ) {
				viewHolder = new ViewHolder();
				convertView = layoutInflater.inflate( R.layout.adapter_gallery_selected, null );
				viewHolder.textView = ( TextView )convertView.findViewById( R.id.tv );
				convertView.setTag( viewHolder );
			} else {
				viewHolder = ( ViewHolder )convertView.getTag();
			}
			viewHolder.textView.setText( list[ position ] );

		} else {

			if( convertView == null ) {
				viewHolder = new ViewHolder();
				convertView = layoutInflater.inflate( R.layout.adapter_gallery_notselected, null );
				viewHolder.textView = ( TextView )convertView.findViewById( R.id.tv );
				convertView.setTag( viewHolder );
			} else {
				viewHolder = ( ViewHolder )convertView.getTag();
			}
			viewHolder.textView.setText( list[ position ] );

		}

		return convertView;
	}

}
