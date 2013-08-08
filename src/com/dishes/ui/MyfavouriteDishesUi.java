/**
 * 
 */
package com.dishes.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
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
		OnClickListener, OnScrollListener, OnItemClickListener {
	private Button btn_delete;
	private GridView gv_favor;
	private MyfavouriteDishesAdapter adapter;
	private List<DishInfo> infos;
	private Button btn_cancle;
	private Dao dao;

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
		if (AppContext.favorlist.size() != dao.getFavors().size()) {
			infos = AppContext.favorlist = dao.getFavors();
			adapter.setInfos(infos);
			adapter.notifyDataSetChanged();
		}
	}

	/**
	 * 
	 */
	private void initView() {
		btn_delete = (Button) findViewById(R.id.btn_deletefavor);
		btn_delete.setOnClickListener(this);
		btn_cancle = (Button) findViewById(R.id.btn_canclefavor);
		btn_cancle.setOnClickListener(this);
		gv_favor = (GridView) findViewById(R.id.gv_myfavor);
		infos = new ArrayList<DishInfo>();
		dao = new Dao(getApplicationContext());
		AppContext.favorlist = infos = dao.getFavors();
		adapter = new MyfavouriteDishesAdapter(getApplicationContext(), infos);
		gv_favor.setAdapter(adapter);
		gv_favor.setOnScrollListener(this);
		gv_favor.setOnItemClickListener(this);
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
				btn_delete.setText("删除");
				btn_cancle.setVisibility(View.GONE);
				for (String id : AppContext.remove_favorlist) {
					dao.removeFavor(id);
				}

				for (int i = 0; i < gv_favor.getChildCount(); i++) {
					MyfavouriteDishesAdapter.ViewHolder viewHolder = (ViewHolder) gv_favor
							.getChildAt(i).getTag();
					viewHolder.checkBox.setVisibility(View.GONE);
				}
				if (AppContext.remove_favorlist.size() == 0) {
					return;
				}
				AppContext.remove_favorlist.clear();
				AppContext.favorlist = infos = dao.getFavors();
				adapter.setInfos(infos);
				adapter.notifyDataSetChanged();

			} else {
				btn_delete.setText("完成");
				btn_cancle.setVisibility(View.VISIBLE);
				adapter.setMODIFY(true);
				for (int i = 0; i < gv_favor.getChildCount(); i++) {
					MyfavouriteDishesAdapter.ViewHolder viewHolder = (ViewHolder) gv_favor
							.getChildAt(i).getTag();
					viewHolder.checkBox.setVisibility(View.VISIBLE);

				}

			}

			break;
		case R.id.btn_canclefavor:
			btn_cancle.setVisibility(View.GONE);
			adapter.setMODIFY(false);
			AppContext.remove_favorlist.clear();
			btn_delete.setText("删除");
			for (int i = 0; i < gv_favor.getChildCount(); i++) {
				MyfavouriteDishesAdapter.ViewHolder viewHolder = (ViewHolder) gv_favor
						.getChildAt(i).getTag();
				viewHolder.checkBox.setVisibility(View.GONE);
				viewHolder.checkBox.setChecked(false);
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
				viewHolder.checkBox.setChecked(false);

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget
	 * .AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(MyfavouriteDishesUi.this, HowToCook.class);
		intent.putExtra("dishId",
				((DishInfo) adapter.getItem(position)).getDishId());
		startActivity(intent);
		overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
	}
}
