/**
 *
 * @author SenYang
 */
package com.dishes.ui;

import java.io.IOException;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dishes.common.Constant;
import com.dishes.common.ViewHolder;
import com.dishes.ui.base.BaseActivity;

/**
 * 
 * @author SenYang
 * 
 */
public class CategoryDishesUi extends BaseActivity implements OnItemClickListener {

	private ListView lv_categorydishes;


	@Override
	protected void onCreate( Bundle savedInstanceState ) {

		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_categorydishes );
		initView();
	}


	/**
	 * 
	 */
	private void initView() {

		lv_categorydishes = ( ListView )findViewById( R.id.lv_categorydishes );
		lv_categorydishes.setOnItemClickListener( this );
		lv_categorydishes.setAdapter( new Adapter() );

	}


	@Override
	public void onItemClick( AdapterView<?> arg0, View arg1, int arg2, long arg3 ) {

		Intent intent = new Intent();
		intent.putExtra( "categoryType", arg2 );
		intent.setClass( getApplicationContext(), CategoryDetailUi.class );
		startActivity( intent );
		overridePendingTransition( R.anim.slide_right_in, R.anim.slide_out_donothing );

	}


	public class Adapter extends BaseAdapter {

		@Override
		public int getCount() {

			return Constant.CategoryDishesConstant.CATEGORY_LIST.length;
		}


		@Override
		public Object getItem( int position ) {

			return Constant.CategoryDishesConstant.CATEGORY_LIST[ position ];
		}


		@Override
		public long getItemId( int position ) {

			return position;
		}


		@Override
		public View getView( int position, View convertView, ViewGroup parent ) {

			ViewHolder viewHolder = null;
			if( convertView == null ) {
				viewHolder = new ViewHolder();
				convertView = LayoutInflater.from( getApplicationContext() ).inflate( R.layout.adapter_categorylistview, null );
				viewHolder.imageView = ( ImageView )convertView.findViewById( R.id.iv_categorylist );
				viewHolder.textView1 = ( TextView )convertView.findViewById( R.id.tv_categoryf );
				viewHolder.textView2 = ( TextView )convertView.findViewById( R.id.tv_categoryd );
				convertView.setTag( viewHolder );
			} else {
				viewHolder = ( ViewHolder )convertView.getTag();
			}
			try {
				viewHolder.imageView.setImageBitmap( BitmapFactory.decodeStream( ( getResources().getAssets()
						.open( Constant.CategoryDishesConstant.IMAGE_LIST[ position ] ) ) ) );
			} catch( IOException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			viewHolder.textView1.setText( Constant.CategoryDishesConstant.CATEGORY_LIST[ position ].toString() );
			viewHolder.textView2.setText( Constant.CategoryDishesConstant.DESCRIBTION_LIST[ position ].toString() );

			return convertView;
		}

	}


	@Override
	public void onBackPressed() {

		super.onBackPressed();

		overridePendingTransition( R.anim.slide_in, R.anim.slide_out );
	}
}
