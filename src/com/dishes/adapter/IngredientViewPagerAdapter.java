/**
 *
 * @author SenYang
 */
package com.dishes.adapter;

import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.dishes.AppContext;
import com.dishes.model.IngredientInfo;

/**
 * 
 * @author SenYang
 * 
 */
public class IngredientViewPagerAdapter extends PagerAdapter {

	private Context context;
	private LayoutInflater layoutInflater;
	private Handler handler;


	public IngredientViewPagerAdapter( Context context,Handler handler ) {

		this.context = context;
		this.layoutInflater = LayoutInflater.from( context );
		this.handler = handler;

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
		RelativeLayout relativeLayout = new RelativeLayout( context );
		GridView gridView = new GridView( context );
		relativeLayout.addView( gridView );
		gridView.setNumColumns( 4 );
		IngredientGridViewAdapter adapter = new IngredientGridViewAdapter( handler, relativeLayout, gridView, context, layoutInflater, infos );
		gridView.setAdapter( adapter );
		container.addView( relativeLayout );
		return relativeLayout;
	}

}
