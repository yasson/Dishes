/**
 * 
 */
package com.dishes.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.dishes.common.Constant;
import com.dishes.ui.base.BaseActivity;

/**
 * @author SenYang
 * 
 */
public class LoginUi extends BaseActivity implements OnClickListener {

	private EditText et_account, et_password;
	private CheckBox cb_rp, cb_al;
	private Button btn_login, btn_regedit;
	private Editor editor;
	private SharedPreferences sp;


	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate( Bundle savedInstanceState ) {

		// TODO Auto-generated method stub
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_login );
		System.gc();
		editor = ( Editor )getSharedPreferences( Constant.PACKAGE_NAME, 0 ).edit();
		sp = getSharedPreferences( Constant.PACKAGE_NAME, 0 );
		initView();
		autoLogin();
	}


	/**
	 * 
	 */
	private void initView() {

		// TODO Auto-generated method stub

		et_account = ( EditText )findViewById( R.id.et_account );
		et_password = ( EditText )findViewById( R.id.et_password );
		btn_login = ( Button )findViewById( R.id.btn_login );
		btn_regedit = ( Button )findViewById( R.id.btn_regedit );
		btn_login.setOnClickListener( this );
		btn_regedit.setOnClickListener( this );
		cb_rp = ( CheckBox )findViewById( R.id.cb_rp );
		cb_al = ( CheckBox )findViewById( R.id.cb_al );
	}


	/**
	 * @return
	 */
	private void autoLogin() {

		// TODO Auto-generated method stub
		et_account.setText( sp.getString( Constant.USERACCOUNT, null ) );
		if( sp.getBoolean( Constant.REMEMBERPASSWORD, false ) ) {

			et_password.setText( sp.getString( Constant.USERPASSWORD, null ) );
			cb_rp.setChecked( true );
			if( sp.getBoolean( Constant.AUTOLOGINCHECKED, false ) ) {
				cb_al.setChecked( true );
				login();

			}
		}

	}


	/**
	 * 
	 */
	private void login() {

		// TODO Auto-generated method stub
		String account = et_account.getText().toString();
		String password = et_password.getText().toString();
		if( account != null && password != null ) {
			editor.putString( Constant.USERACCOUNT, account );
			editor.commit();
			if( account.equals( "111" ) && password.equals( "123" ) ) {
				if( cb_rp.isChecked() ) {
					editor.putString( Constant.USERPASSWORD, password );
					editor.putBoolean( Constant.REMEMBERPASSWORD, true );
					editor.commit();
				}
				if( cb_al.isChecked() ) {
					editor.putBoolean( Constant.AUTOLOGINCHECKED, true );
					editor.commit();
				}

				Intent intent = new Intent();
				intent.setClass( getApplicationContext(), HomeUi.class );
				startActivity( intent );
				finish();
				System.out.println( "222222" );
				System.gc();

			}
		}
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick( View v ) {

		// TODO Auto-generated method stub
		switch( v.getId() ) {
		case R.id.btn_login:
			login();

			break;
		case R.id.btn_regedit:
			break;

		default:
			break;
		}

	}
}
