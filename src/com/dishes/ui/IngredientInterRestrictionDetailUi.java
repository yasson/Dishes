/**
 *
 * @author YangSen
 */
package com.dishes.ui;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ksoap2.serialization.SoapObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.dishes.adapter.IngreRestrictionAdapter;
import com.dishes.model.RestrictionInfo;
import com.dishes.model.WSResult;
import com.dishes.ui.base.BaseActivity;
import com.dishes.webservice.WebServiceAction;
import com.dishes.webservice.WebServiceConstant;

/**
 * 
 * @author YangSen
 * 
 */
public class IngredientInterRestrictionDetailUi extends BaseActivity implements OnClickListener, OnEditorActionListener {

	private Handler handler = new Handler() {

		public void handleMessage( android.os.Message msg ) {

			if( pic != null ) {
				getIngredientPic();
			}
			textView.setText( inName );
			IngreRestrictionAdapter energyAdapter = new IngreRestrictionAdapter( getApplicationContext(), infos );
			listView.setAdapter( energyAdapter );

		}

	};
	private String inName, pic;
	private Button searchButton;
	private EditText editText;
	private ListView listView;
	private ImageView imageView;
	private TextView textView;
	private List<RestrictionInfo> infos;


	@Override
	protected void onCreate( Bundle savedInstanceState ) {

		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_ingredient_restriction_detail );
		initView();
	}


	@Override
	protected void onResume() {

		super.onResume();
		getIngredientEnergyInfo( inName );
		textView.setText( inName );
	}


	/**
	 * 
	 */
	private void getIngredientEnergyInfo( final String name ) {

		new Thread( new Runnable() {

			@Override
			public void run() {

				infos = new ArrayList<RestrictionInfo>();
				Map<String, Object> hashmap = new HashMap<String, Object>();
				hashmap.put( "inName", inName );
				hashmap.put( "wsUser", WebServiceConstant.wsUser );
				SoapObject incomIngredients = WebServiceAction.getSoapObject( WebServiceConstant.SERVICE_URL_FOODINCOMWS,
						WebServiceConstant.GETINCOMINGREDIENTS, hashmap, WebServiceConstant.SERVICENAMESPACE );
				if( incomIngredients != null ) {
					WSResult result = new WSResult( incomIngredients );
					for( int i = 0; i < result.getResult().size(); i++ ) {
						inName = name;
						RestrictionInfo info = new RestrictionInfo( ( SoapObject )result.getResult().get( i ) );
						infos.add( info );

					}
					handler.sendEmptyMessage( 1 );
				} else {
					handler.post( new Runnable() {

						@Override
						public void run() {

							Toast.makeText( getApplicationContext(), "没有结果，换个名字试试", Toast.LENGTH_SHORT ).show();
						}
					} );
				}
			}
		} ).start();
	}


	/**
	 * 
	 */
	private void initView() {

		imageView = ( ImageView )findViewById( R.id.iv );
		textView = ( TextView )findViewById( R.id.tv );
		searchButton = ( Button )findViewById( R.id.btn_searching );
		searchButton.setOnClickListener( this );
		editText = ( EditText )findViewById( R.id.et_search );
		editText.setOnEditorActionListener( this );
		listView = ( ListView )findViewById( R.id.lv_ingredient_energy_detail );
		inName = getIntent().getBundleExtra( "bundle" ).getString( "ingreName" );
		pic = getIntent().getBundleExtra( "bundle" ).getString( "pic" );
	}


	@Override
	public void onClick( View v ) {

		switch( v.getId() ) {
		case R.id.btn_searching:
			String ingreName = editText.getText().toString();
			if( ingreName != null && !ingreName.equals( "" ) ) {
				getIngredientEnergyInfo( ingreName );
			} else {
				Toast.makeText( getApplicationContext(), "请输入要查看的食材名称", Toast.LENGTH_SHORT ).show();
			}

			break;

		default:
			break;
		}
	}


	@Override
	public boolean onEditorAction( TextView v, int actionId, KeyEvent event ) {

		switch( actionId ) {
		case EditorInfo.IME_ACTION_SEARCH:
			onClick( searchButton );
			break;

		default:
			break;
		}
		return false;
	}


	/**
	 * 
	 */
	private void getIngredientPic() {

		new Thread( new Runnable() {

			@Override
			public void run() {

				try {
					final Bitmap bitmap = BitmapFactory.decodeStream( new URL( pic ).openStream() );
					handler.post( new Runnable() {

						@Override
						public void run() {

							imageView.setImageBitmap( bitmap );
						}
					} );
				} catch( MalformedURLException e ) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch( IOException e ) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} ).start();
	}
}
