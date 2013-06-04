/**
 * 
 */
package com.dishes.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dishes.common.Constant;
import com.dishes.model.NutritionMealsInfo;

/**
 * @author SenYang
 * 
 */
public class EachdayMealsAdapter extends PagerAdapter {

	private Context context;
	private int datePage;
	private ArrayList<NutritionMealsInfo> nutritionMealsInfos;


	/**
	 * @param applicationContext
	 * @param list
	 * @param datePage
	 */
	public EachdayMealsAdapter( Context applicationContext, ArrayList<NutritionMealsInfo> list, int datePage ) {

		this.context = applicationContext;
		this.datePage = datePage;
		this.nutritionMealsInfos = list;
	}


	@Override
	public int getCount() {

		return Constant.MEALSPAGERSIZE;
	}


	@Override
	public void destroyItem( ViewGroup container, int position, Object object ) {

		container.removeView( ( View )object );
	}


	@Override
	public Object instantiateItem( ViewGroup container, int position ) {

		ListView listView = new ListView( context );

		MealsDetailAdapter detailAdapter = new MealsDetailAdapter( context, nutritionMealsInfos.get( datePage ), position + 1 );
		listView.setAdapter( detailAdapter );
		container.addView( listView );
		return listView;
	}


	@Override
	public boolean isViewFromObject( View arg0, Object arg1 ) {

		return arg0 == arg1;
	}


	public void setDatePage( int datePage ) {

		this.datePage = datePage;

	}
}
