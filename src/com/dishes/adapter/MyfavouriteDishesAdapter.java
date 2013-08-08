/**
 * 
 */
package com.dishes.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.dishes.AppContext;
import com.dishes.model.DishInfo;
import com.dishes.ui.R;
import com.dishes.util.bitmapfun.util.ImageFetcher;

/**
 * @author YangSen
 * 
 */
public class MyfavouriteDishesAdapter extends BaseAdapter {

	public class ViewHolder {

		public ImageView imageView;
		public TextView textView;
		public CheckBox checkBox;

	}

	private List<DishInfo> infos;
	private Context context;
	private LayoutInflater layoutInflater;
	private ImageFetcher imageFetcher;
	private boolean MODIFY = false;

	/**
	 * @param applicationContext
	 * @param infos
	 */
	public MyfavouriteDishesAdapter(Context applicationContext,
			List<DishInfo> infos) {
		this.context = applicationContext;
		this.infos = infos;
		this.layoutInflater = LayoutInflater.from(applicationContext);
		imageFetcher = new ImageFetcher(applicationContext, 300);
	}

	/**
	 * @param mODIFY
	 *            the mODIFY to set
	 */
	public void setMODIFY(boolean mODIFY) {
		MODIFY = mODIFY;
	}

	/**
	 * @param infos
	 *            the infos to set
	 */
	public void setInfos(List<DishInfo> infos) {
		this.infos = infos;
	}

	/**
	 * @return
	 */
	public boolean getMODIFY() {
		return MODIFY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		return infos.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		return infos.get(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = layoutInflater.inflate(
					R.layout.adapter_myfavordishes, null);
			viewHolder = new ViewHolder();
			viewHolder.imageView = (ImageView) convertView
					.findViewById(R.id.ivfavorpic);
			viewHolder.textView = (TextView) convertView
					.findViewById(R.id.tvfavorname);
			viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.cb);
			convertView.setTag(viewHolder);
		}
		viewHolder = (ViewHolder) convertView.getTag();
		viewHolder.checkBox.setChecked(false);
		if (AppContext.remove_favorlist.contains(infos.get(position)
				.getDishId())) {
			viewHolder.checkBox.setChecked(true);
		}
		System.out.println(AppContext.remove_favorlist + "==========");
		viewHolder.textView.setText(infos.get(position).getDishName());
		imageFetcher.loadImage(infos.get(position).getDishPic(),
				viewHolder.imageView);
		viewHolder.checkBox.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (AppContext.remove_favorlist.contains(infos.get(position)
						.getDishId())) {
					AppContext.remove_favorlist.remove(infos.get(position)
							.getDishId());
				} else {
					AppContext.remove_favorlist.add(infos.get(position)
							.getDishId());

				}
			}
		});
		return convertView;
	}

}
