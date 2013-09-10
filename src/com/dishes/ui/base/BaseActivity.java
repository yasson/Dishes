/**
 *
 * @author SenYang
 */
package com.dishes.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.dishes.AppManager;
import com.dishes.ui.R;
import com.dishes.util.LoadingDialog;
import com.dishes.util.ThreadTool;

/**
 * activity基类
 * 
 * @author SenYang
 * 
 */
public class BaseActivity extends Activity {

	@Override
	protected void onCreate( Bundle savedInstanceState ) {

		super.onCreate( savedInstanceState );
		AppManager.getAppManager().addActivity( this );

	}


	@Override
	protected void onStart() {

		super.onStart();
	}


	@Override
	protected void onRestart() {

		super.onRestart();
	}


	@Override
	protected void onResume() {

		super.onResume();
	}


	@Override
	protected void onPause() {

		super.onPause();
	}


	@Override
	protected void onStop() {

		ThreadTool.getInstance().endTask();
		super.onStop();
	}


	@Override
	protected void onDestroy() {

		super.onDestroy();
		AppManager.getAppManager().finishActivity( this );

	}


	protected void openActivity( Class<?> activity ) {

		openActivity( activity, null );
	}


	/**
	 * @param activity
	 * @param bundle
	 */
	protected void openActivity( Class<?> activity, Bundle bundle ) {

		Intent intent = new Intent();
		if( bundle != null ) {
			intent.putExtra( "bundle", bundle );
		}
		intent.setClass( getApplicationContext(), activity );
		startActivity( intent );
		overridePendingTransition( R.anim.slide_right_in, R.anim.slide_left_out );

	}

}
