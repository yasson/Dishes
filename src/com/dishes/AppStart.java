/**
 *
 * @author SenYang
 */
package com.dishes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;

import com.dishes.common.Constant;
import com.dishes.ui.HelpUi;
import com.dishes.ui.LoginUi;
import com.dishes.ui.R;
import com.dishes.ui.base.BaseActivity;

/**
 * 
 * @author SenYang
 * 
 */
public class AppStart extends BaseActivity implements AnimationListener {

	@Override
	public void onCreate( Bundle savedInstanceState ) {

		super.onCreate( savedInstanceState );
		View view = View.inflate( getApplicationContext(), R.layout.activity_main, null );
		setContentView( view );
		AnimationSet animationSet = new AnimationSet( true );
		AlphaAnimation alphaAnimation = new AlphaAnimation( 0.1f, 1.0f );
		// ScaleAnimation scaleAnimation = new ScaleAnimation(0.5f, 1f, 0.5f,
		// 1f,
		// Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
		// 0.5f);
		// animationSet.addAnimation( scaleAnimation );
		animationSet.addAnimation( alphaAnimation );
		animationSet.setDuration( 1000 );
		animationSet.setFillAfter( true );
		view.startAnimation( animationSet );
		animationSet.setAnimationListener( this );

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


	@Override
	public void onAnimationEnd( Animation animation ) {

		Intent intent = new Intent();
		if( firstLogin() ) {
			Editor editor =getSharedPreferences( Constant.PACKAGE_NAME, 0 ).edit();
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


	@Override
	public void onAnimationRepeat( Animation animation ) {

	}


	@Override
	public void onAnimationStart( Animation animation ) {

	}

}
