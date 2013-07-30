/**
 *
 * @author YangSen
 */
package com.dishes.ui;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.ksoap2.serialization.SoapObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dishes.adapter.IngreEnergyAdapter;
import com.dishes.model.IngredientNutritionInfo;
import com.dishes.model.WSResult;
import com.dishes.ui.base.BaseActivity;
import com.dishes.webservice.WebServiceAction;
import com.dishes.webservice.WebServiceConstant;

/**
 * 
 * @author YangSen
 * 
 */
public class IngredientEnergyDetailUi extends BaseActivity {

	private String inName;
	private Button searchButton;
	private EditText editText;
	private ListView listView;
	private ImageView imageView;
	private TextView textView;

	private IngredientNutritionInfo info;
	private Handler handler = new Handler() {

		public void handleMessage( android.os.Message msg ) {

			new Thread( new Runnable() {

				@Override
				public void run() {

					try {
						final Bitmap bitmap = BitmapFactory.decodeStream( new URL( info.getPicUrl() ).openStream() );
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
			IngreEnergyAdapter energyAdapter = new IngreEnergyAdapter( getApplicationContext(), info );

			listView.setAdapter( energyAdapter );

		};
	};


	@Override
	protected void onCreate( Bundle savedInstanceState ) {

		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_ingredient_energy_detail );
		initView();
	}


	@Override
	protected void onResume() {

		super.onResume();
		getIngredientEnergyInfo();
		textView.setText( inName );
	}


	/**
	 * 
	 */
	private void getIngredientEnergyInfo() {

		new Thread( new Runnable() {

			@Override
			public void run() {

				Map<String, Object> hashmap = new HashMap<String, Object>();
				hashmap.put( "ingreName", inName );
				hashmap.put( "wsUser", WebServiceConstant.wsUser );
				SoapObject nutritionInfo = WebServiceAction.getSoapObject( WebServiceConstant.SERVICE_URL_POPUWSs,
						WebServiceConstant.GETINGREDIENTNUTRITIONBYNAME, hashmap, WebServiceConstant.SERVICENAMESPACE );
				if( nutritionInfo != null ) {
					WSResult result = new WSResult( nutritionInfo );
					for( int i = 0; i < result.getResult().size(); i++ ) {
						info = new IngredientNutritionInfo( ( SoapObject )result.getResult().get( i ) );
					}
					handler.sendEmptyMessage( 1 );
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
		editText = ( EditText )findViewById( R.id.et_search );
		listView = ( ListView )findViewById( R.id.lv_ingredient_energy_detail );
		inName = getIntent().getBundleExtra( "bundle" ).getString( "inId" );

	}
}
