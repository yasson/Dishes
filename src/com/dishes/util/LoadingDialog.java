/**
 * Program  : LoadingDialog.java
 * Author   : yangsen
 * Create   : 2012-12-25 上午10:39:10
 *
 * Copyright 2010 by Embedded Internet Solutions Inc.,
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Embedded Internet Solutions Inc.("Confidential Information").  
 * You shall not disclose such Confidential Information and shall 
 * use it only in accordance with the terms of the license agreement 
 * you entered into with Embedded Internet Solutions Inc.
 *
 */

package com.dishes.util;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.dishes.ui.R;

/**
 * 加载数据时显示进度对话框
 * 
 * @author yangsen
 * @create 2012-12-25 上午11:00:09
 */
public class LoadingDialog extends Dialog {
	private ImageView mImageView;
	private Animation mAnimation;
	private Context mContext;
	public LoadingDialog(Context context, int theme) {
		super(context, theme);
		mContext=context;
	}
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mImageView=new ImageView(mContext);
		mImageView.setImageResource(R.drawable.ram_loading_fg);
		mImageView.setBackgroundResource(R.drawable.ram_loading_bg);
		setContentView(mImageView);
		mAnimation=AnimationUtils.loadAnimation(mContext, R.anim.rotate);
		mImageView.setAnimation(mAnimation);
		mAnimation.startNow();
	}
}