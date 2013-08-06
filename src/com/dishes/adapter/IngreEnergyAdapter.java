/**
 *
 * @author YangSen
 */
package com.dishes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dishes.model.IngredientNutritionInfo;
import com.dishes.ui.R;

/**
 * 
 * @author YangSen
 * 
 */
public class IngreEnergyAdapter extends BaseAdapter {

	private IngredientNutritionInfo info;
	private Context context;
	private LayoutInflater layoutInflater;


	class ViewHolder {

		TextView tv1;
		TextView tv2;
		TextView tv3;

	}


	/**
	 * @param applicationContext
	 * @param info
	 */
	public IngreEnergyAdapter( Context applicationContext, IngredientNutritionInfo info ) {

		this.context = applicationContext;
		this.info = info;
		this.layoutInflater = LayoutInflater.from( context );
	}


	@Override
	public int getCount() {

		return 12;
	}


	@Override
	public Object getItem( int position ) {

		return null;
	}


	@Override
	public long getItemId( int position ) {

		return 0;
	}


	@Override
	public View getView( int position, View convertView, ViewGroup parent ) {

		ViewHolder viewHolder;
		if( convertView == null ) {
			convertView = layoutInflater.inflate( R.layout.adapter_energy_detail, null );
			viewHolder = new ViewHolder();
			viewHolder.tv1 = ( TextView )convertView.findViewById( R.id.tvname );
			viewHolder.tv2 = ( TextView )convertView.findViewById( R.id.value );
			viewHolder.tv3 = ( TextView )convertView.findViewById( R.id.unit );
			convertView.setTag( viewHolder );
		} else {
			viewHolder = ( ViewHolder )convertView.getTag();
		}
		switch( position ) {
		case 0:
			viewHolder.tv1.setText( "水分" );
			viewHolder.tv2.setText( info.getWater() );
			viewHolder.tv3.setText( "g" );

			break;
		case 1:
			viewHolder.tv1.setText( "能量" );
			viewHolder.tv2.setText( info.getEnergy() );
			viewHolder.tv3.setText( "kcal" );
			break;
		case 2:
			viewHolder.tv1.setText( "蛋白质" );
			viewHolder.tv2.setText( info.getProtein() );
			viewHolder.tv3.setText( "g" );
			break;
		case 3:
			viewHolder.tv1.setText( "脂肪" );
			viewHolder.tv2.setText( info.getFat() );
			viewHolder.tv3.setText( "g" );
			break;
		case 4:
			viewHolder.tv1.setText( "碳水化合物" );
			viewHolder.tv2.setText( info.getCho() );
			viewHolder.tv3.setText( "g" );
			break;
		case 5:
			viewHolder.tv1.setText( "膳食纤维" );
			viewHolder.tv2.setText( info.getDietaryFiber() );
			viewHolder.tv3.setText( "g" );
			break;
		case 6:
			viewHolder.tv1.setText( "胆固醇" );
			viewHolder.tv2.setText( info.getCholesterol() );
			viewHolder.tv3.setText( "mg" );
			break;
		case 7:
			viewHolder.tv1.setText( "维生素A" );
			viewHolder.tv2.setText( info.getVitaminA() );
			viewHolder.tv3.setText( "µgRE" );
			break;
		case 8:
			viewHolder.tv1.setText( "维生素C" );
			viewHolder.tv2.setText( info.getVitC() );
			viewHolder.tv3.setText( "mg" );
			break;
		case 9:
			viewHolder.tv1.setText( "维生素E" );
			viewHolder.tv2.setText( info.getVitE() );
			viewHolder.tv3.setText( "mg" );
			break;
		case 10:
			viewHolder.tv1.setText( "钙" );
			viewHolder.tv2.setText( info.getEleCa() );
			viewHolder.tv3.setText( "mg" );
			break;
		case 11:
			viewHolder.tv1.setText( "钾" );
			viewHolder.tv2.setText( info.getEleK() );
			viewHolder.tv3.setText( "mg" );
			break;

		default:
			break;
		}

		return convertView;
	}

}
