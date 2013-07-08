package com.dishes.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;

import com.dishes.common.Constant;
import com.dishes.ui.base.BaseActivity;

public class StartUi extends BaseActivity {

	@Override
	public void onCreate( Bundle savedInstanceState ) {

		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );
		Intent intent = new Intent();
		if( firstLogin() ) {

			Editor editor = ( Editor )getSharedPreferences( Constant.PACKAGE_NAME, 0 ).edit();
			editor.putBoolean( Constant.FIRSTLOGIN, false );
			editor.commit();
			intent.setClass( getApplicationContext(), HelpUi.class );
			startActivity( intent );
			finish();
			return;
		}

		intent.setClass( getApplicationContext(), LoginUi.class );
		startActivity( intent );
		finish();

	}


	/**
	 * @return
	 */
	private boolean firstLogin() {

		// TODO Auto-generated method stub
		SharedPreferences sp = getSharedPreferences( Constant.PACKAGE_NAME, 0 );
		return sp.getBoolean( Constant.FIRSTLOGIN, true );
	}


	/**
	 * 
	 */

	@Override
	public boolean onCreateOptionsMenu( Menu menu ) {

		getMenuInflater().inflate( R.menu.activity_main, menu );
		return true;
	}
}
