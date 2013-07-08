/**
 *
 * @author SenYang
 */
package com.dishes.adapter;

import java.util.List;

import com.dishes.AppContext;
import com.dishes.model.IngredientInfo;
import com.dishes.ui.R;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

/**
 * 
 * @author SenYang
 * 
 */
public class IngredientViewPagerAdapter extends PagerAdapter {

	private Context context;
	private LayoutInflater layoutInflater;


	public IngredientViewPagerAdapter( Context context ) {

		this.context = context;
		this.layoutInflater = LayoutInflater.from( context );

	}


	@Override
	public void destroyItem( ViewGroup container, int position, Object object ) {

		container.removeView( ( View )object );
	}


	@Override
	public int getCount() {

		return AppContext.ingredientMaps.size();
	}


	@Override
	public boolean isViewFromObject( View arg0, Object arg1 ) {

		return arg0 == arg1;
	}


	@Override
	public Object instantiateItem( ViewGroup container, int position ) {

		List<IngredientInfo> infos = AppContext.ingredientMaps.get( "classid" + ( position + 1 ) );
		GridView gridView = new GridView( context );
		
		gridView.setNumColumns( 4 );
		IngredientGridViewAdapter adapter = new IngredientGridViewAdapter( context, layoutInflater, infos );
		gridView.setAdapter( adapter );
		container.addView( gridView );
		return gridView;
	}

}
