/**
 * 
 */
package com.dishes.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.dishes.adapter.HomeEveryDayDishAdapter;
import com.dishes.adapter.HomeEveryDishAdapter;
import com.dishes.adapter.HomeListViewAdapter;
import com.dishes.common.CommonMethod;
import com.dishes.common.Constant;
import com.dishes.model.DishInfo;
import com.dishes.model.WSResult;
import com.dishes.ui.base.BaseActivity;
import com.dishes.util.SlideHolder;
import com.dishes.util.SlideHolder.OnSlideListener;
import com.dishes.util.ThreadTool;
import com.dishes.views.flipview.FlipViewController;
//import com.dishes.views.flipview.FlipViewController;
import com.dishes.webservice.WebServiceAction;
import com.dishes.webservice.WebServiceConstant;

/**
 * @author SenYang
 * 
 */
public class HomeUi extends BaseActivity implements OnClickListener,
		OnItemClickListener, OnTouchListener {

	private HomeListViewAdapter adapter;
	private Button btn_menu, btn_search;
	private ViewPager vp_everyday;
	private final int EVERYDAYVIEW = 1;
	private boolean TODAY;
	private FlipViewController flipView;
	private Handler handler = new Handler() {

		@SuppressWarnings("unchecked")
		public void handleMessage(android.os.Message msg) {

			switch (msg.what) {
			case EVERYDAYVIEW:
				List<DishInfo> infos = new ArrayList<DishInfo>();
				for (Object object : (List<Object>) msg.obj) {
					final DishInfo dishInfo = new DishInfo((SoapObject) object);
					infos.add(dishInfo);
				}
				// HomeEveryDayDishAdapter adapter = new
				// HomeEveryDayDishAdapter( getApplicationContext(), infos );
				// vp_everyday.setAdapter( adapter );
				HomeEveryDishAdapter adapter = new HomeEveryDishAdapter(
						getApplicationContext(), infos, flipView);
				flipView.setAdapter(adapter);
				break;

			default:
				break;
			}

		};
	};
	private SlideHolder slideHolder;
	private ListView lv_home;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		initView();
		System.gc();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {

		super.onResume();
		if (!TODAY) {
			getEveryDayDishInfo();
		}

	}

	/**
	 * 
	 */
	private void getEveryDayDishInfo() {

		final HashMap<String, Object> everyDishMap = new HashMap<String, Object>();
		everyDishMap.put("diseaseStr", "");
		everyDishMap.put("num", Constant.HomeConstant.EVERYDAYDISHCOUNTS);
		everyDishMap.put("wsUser", WebServiceConstant.wsUser);

		Runnable runnable = new Runnable() {

			@Override
			public void run() {

				SoapObject soapObject = WebServiceAction.getSoapObject(
						WebServiceConstant.SERVICE_EVERYDAY_URL,
						WebServiceConstant.GETPOPULARDISH, everyDishMap,
						WebServiceConstant.SERVICENAMESPACE);
				if (soapObject == null) {
					handler.post(new Runnable() {

						@Override
						public void run() {

							CommonMethod.netException(getApplicationContext());
							return;
						}
					});
				} else {

					WSResult wsResult = new WSResult(soapObject);
					switch (Integer.parseInt(wsResult.getState())) {
					case 201:

						break;
					case 202:
						Message msg = new Message();
						msg.obj = wsResult.getResult();
						msg.what = EVERYDAYVIEW;
						handler.sendMessage(msg);
						TODAY = true;

						break;
					default:
						break;
					}
				}
			}
		};
		ThreadTool threadTool = ThreadTool.getInstance();
		threadTool.addTask(runnable);
	}

	/**
	 * 
	 */
	private void initView() {
		flipView = (FlipViewController) findViewById(R.id.flipview);
		btn_menu = (Button) findViewById(R.id.btn_menu);
		btn_search = (Button) findViewById(R.id.btn_search);
		slideHolder = (SlideHolder) findViewById(R.id.slideHolder);
		// hScrollView = ( ScrollView )findViewById( R.id.hs_everyday );
		// hScrollView.setOnTouchListener( this );
		btn_search.setOnClickListener(this);
		btn_menu.setOnClickListener(this);
		vp_everyday = (ViewPager) findViewById(R.id.vp_everyday);
		vp_everyday.setOnTouchListener(this);
		lv_home = (ListView) findViewById(R.id.lv_home);
		adapter = new HomeListViewAdapter(getApplicationContext());
		lv_home.setAdapter(adapter);
		lv_home.setOnItemClickListener(this);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_menu:

			slideHolder.toggle();

			break;
		case R.id.btn_search:
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), SearchUi.class);
			startActivity(intent);
			overridePendingTransition(R.anim.slide_top_in,
					R.anim.slide_out_donothing);
			break;

		default:

			break;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		switch (arg2) {

		case 0:
			openActivity(EachdayMealsUi.class);

			break;
		case 1:
			openActivity(WhatToEatUi.class);
			break;
		case 2:
			openActivity(IngredientEnergyUi.class);
			break;
		case 3:
			openActivity(IngredientInterRestrictionUi.class);
			break;
		case 4:
			openActivity(CategoryDishesUi.class);
			break;

		default:
			break;
		}

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

}
