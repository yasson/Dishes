/**
 * 
 */
package com.dishes.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

/**
 * @author SenYang
 * 
 */
public class HelpUi extends Activity {

	private List<String> views;
	private ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		initView();
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		viewPager.setAdapter(new HelpUiAdapter(views));
	}
	/**
	 * 
	 */
	private void initView() {
		// TODO Auto-generated method stub
		views = new ArrayList<String>();
		views.add("p1.jpg");
		views.add("p2.jpg");
		views.add("p3.jpg");

	}
	/**
	 * @author SenYang
	 * 
	 */
	public class HelpUiAdapter extends PagerAdapter {

		private List<String> views;
		/**
		 * @param views2
		 */
		public HelpUiAdapter(List<String> views2) {
			// TODO Auto-generated constructor stub
			this.views = views2;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * android.support.v4.view.PagerAdapter#destroyItem(android.view.ViewGroup
		 * , int, java.lang.Object)
		 */
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((View) object);
			System.gc();

		}
		/*
		 * (non-Javadoc)
		 * 
		 * @see android.support.v4.view.PagerAdapter#getCount()
		 */
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return views.size();
		}
		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * android.support.v4.view.PagerAdapter#instantiateItem(android.view
		 * .ViewGroup, int)
		 */
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			View groupView = getLayoutInflater().inflate(
					R.layout.activity_help, container, false);
			ImageView imageView = (ImageView) groupView.findViewById(R.id.iv);
			imageView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent();
					intent.setClass(getApplicationContext(), LoginUi.class);
					startActivity(intent);
					finish();
				}
			});
			try {
				imageView.setImageBitmap(BitmapFactory
						.decodeStream(getApplicationContext().getAssets().open(
								views.get(position))));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			container.addView(groupView, 0);
			return groupView;
		}
		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * android.support.v4.view.PagerAdapter#isViewFromObject(android.view
		 * .View, java.lang.Object)
		 */
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
}
