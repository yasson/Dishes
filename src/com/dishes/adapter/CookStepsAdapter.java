/**
 * @author SenYang
 */
package com.dishes.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dishes.common.ViewHolder;
import com.dishes.model.MenuInfo.StepsInfo;
import com.dishes.ui.R;
import com.dishes.util.ImageLoader;

/**
 * @author SenYang
 * 
 */
public class CookStepsAdapter extends BaseAdapter {

	private Context context;
	private List<StepsInfo> infos;


	/**
	 * @param applicationContext
	 * @param stepsInfos
	 */
	public CookStepsAdapter( Context applicationContext, List<StepsInfo> stepsInfos ) {

		context = applicationContext;
		infos = stepsInfos;

	}


	@Override
	public int getCount() {

		return infos.size();
	}


	@Override
	public Object getItem( int position ) {

		return infos.get( position );
	}


	@Override
	public long getItemId( int position ) {

		return position;
	}


	@Override
	public View getView( int position, View convertView, ViewGroup parent ) {

		final ViewHolder viewHolder;

		if( convertView == null ) {
			convertView = LayoutInflater.from( context ).inflate( R.layout.adapter_cooksteps, null );
			viewHolder = new ViewHolder();
			viewHolder.textView1 = ( TextView )convertView.findViewById( R.id.tv_steps );
			viewHolder.textView2 = ( TextView )convertView.findViewById( R.id.tv_cookstepsdesc );
			viewHolder.imageView = ( ImageView )convertView.findViewById( R.id.iv_cooksteps );
			convertView.setTag( viewHolder );
		} else {
			viewHolder = ( ViewHolder )convertView.getTag();
		}

		viewHolder.textView1.setText( position+1 + "." );
		viewHolder.textView2.setText( infos.get( position ).getNote() );
		viewHolder.imageView.setImageResource( R.drawable.ic_launcher  );
		ImageLoader imageLoader = new ImageLoader();
		String imageUrl =infos.get( position ).getSteppic();
//		if(imageUrl != null ) {
//			if(infos.get( position ).getSteppic().contains( "http://s3" )  ) {
//				String url = infos.get( position ).getSteppic();
//				String url1 = url.substring( 0, url.indexOf( "http://s3" ) );
//				String url2 = url.substring( url.indexOf( "http://s3" ), url.length() );
//				String url3 = url2.replace( "http://s3.", "" );
//				String url4 = url3.replace( "/", "." );
//				 imageUrl = url1 + url4;
//			}
//		
//			imageLoader.loadImage( imageUrl, 100, new ImageCallback() {
//
//				@Override
//				public void imageLoading( Bitmap bitmap, float ratio, int width, int height ) {
//
//					viewHolder.imageView.setImageBitmap( bitmap );
//
//				}
//
//
//				@Override
//				public void imageLoadOver() {
//
//				}
//
//
//				@Override
//				public void imageLoadFailed() {
//
//				}
//
//
//				@Override
//				public void imageLoadBefore() {
//
//				}
//			} );
//		}
		return convertView;
	}

}
