/**
 *
 * @author YangSen
 */
package com.dishes.adapter;

import java.util.List;

import com.dishes.model.RestrictionInfo;
import com.dishes.ui.R;

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
public class IngreRestrictionAdapter extends BaseAdapter {

	class ViewHolder {

		TextView textView;
		TextView textView2;
	}


	private Context context;
	private List<RestrictionInfo> infos;
	private LayoutInflater layoutinflater;


	/**
	 * @param applicationContext
	 * @param infos
	 */
	public IngreRestrictionAdapter( Context applicationContext, List<RestrictionInfo> infos ) {

		this.layoutinflater = LayoutInflater.from( applicationContext );
		this.infos = infos;
		this.context = applicationContext;
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
			convertView = layoutinflater.inflate( R.layout.adapter_ingredients_restriction_detail, null );
			viewHolder = new ViewHolder();
			viewHolder.textView = ( TextView )convertView.findViewById( R.id.textView1 );
			viewHolder.textView2 = ( TextView )convertView.findViewById( R.id.textView2 );
			convertView.setTag( viewHolder );

		} else {
			viewHolder = ( ViewHolder )convertView.getTag();
		}
		viewHolder.textView.setText( infos.get( position ).getInName() );
		viewHolder.textView2.setText( infos.get( position ).getDesc() );

		return convertView;
	}

}
