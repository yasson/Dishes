/**
 * 
 */
package com.dishes.ui;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.GridView;

import com.dishes.AppContext;
import com.dishes.adapter.MyfavouriteDishesAdapter;
import com.dishes.adapter.MyfavouriteDishesAdapter.ViewHolder;
import com.dishes.dao.Dao;
import com.dishes.model.DishInfo;
import com.dishes.ui.base.BaseActivity;

/**
 * @author YangSen
 * 
 */
public class MyfavouriteDishesUi extends BaseActivity implements
		OnClickListener, OnScrollListener {
	private Button btn_delete;
	private GridView gv_favor;
	private MyfavouriteDishesAdapter adapter;
	private List<DishInfo> infos;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dishes.ui.base.BaseActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myfavouritedishes);
		initView();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dishes.ui.base.BaseActivity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
		Dao dao = new Dao(getApplicationContext());
		if (AppContext.favorlist != dao.getFavors()) {
			infos = AppContext.favorlist = dao.getFavors();
			adapter.notifyDataSetChanged();
		}
	}

	/**
	 * 
	 */
	private void initView() {
		btn_delete = (Button) findViewById(R.id.btn_deletefavor);
		btn_delete.setOnClickListener(this);
		gv_favor = (GridView) findViewById(R.id.gv_myfavor);
		infos = new ArrayList<DishInfo>();
		Dao dao = new Dao(getApplicationContext());
		infos = dao.getFavors();
		AppContext.favorlist = infos;
		adapter = new MyfavouriteDishesAdapter(getApplicationContext(), infos);
		gv_favor.setAdapter(adapter);
		gv_favor.setOnScrollListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_deletefavor:
			if (adapter.getMODIFY()) {
				adapter.setMODIFY(false);
				for (int i = 0; i < gv_favor.getChildCount(); i++) {
					MyfavouriteDishesAdapter.ViewHolder viewHolder = (ViewHolder) gv_favor
							.getChildAt(i).getTag();
					viewHolder.checkBox.setVisibility(View.GONE);

				}

			} else {
				adapter.setMODIFY(true);
				for (int i = 0; i < gv_favor.getChildCount(); i++) {
					MyfavouriteDishesAdapter.ViewHolder viewHolder = (ViewHolder) gv_favor
							.getChildAt(i).getTag();
					viewHolder.checkBox.setVisibility(View.VISIBLE);

				}

			}

			break;

		default:
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.AbsListView.OnScrollListener#onScroll(android.widget.
	 * AbsListView, int, int, int)
	 */
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		if (adapter.getMODIFY()) {
			for (int i = 0; i < gv_favor.getChildCount(); i++) {
				MyfavouriteDishesAdapter.ViewHolder viewHolder = (ViewHolder) gv_favor
						.getChildAt(i).getTag();
				viewHolder.checkBox.setVisibility(View.VISIBLE);

			}

		} else {
			for (int i = 0; i < gv_favor.getChildCount(); i++) {
				MyfavouriteDishesAdapter.ViewHolder viewHolder = (ViewHolder) gv_favor
						.getChildAt(i).getTag();
				viewHolder.checkBox.setVisibility(View.GONE);

			}

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.widget.AbsListView.OnScrollListener#onScrollStateChanged(android
	 * .widget.AbsListView, int)
	 */
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
	}

}
