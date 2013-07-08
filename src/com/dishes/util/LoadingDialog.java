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

import com.dishes.ui.R;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * 加载数据时显示进度对话框
 * 
 * @author yangsen
 * @create 2012-12-25 上午11:00:09
 */
public class LoadingDialog {
	private static ProgressDialog progressDialog;

	public void showDlg(Context context) {
		progressDialog = new ProgressDialog(context,R.style.dialog);
		// 设置进度条风格，风格为圆形，旋转的
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		// 设置ProgressDialog 提示信息
//		progressDialog.setMessage("loading...");
		// 设置ProgressDialog 的进度条是否不明确
		progressDialog.setIndeterminate(false);
		// 设置是否可以取消
		progressDialog.setCancelable(false);
		progressDialog.show();

	}

	public void cancleDlg() {

		if (null != progressDialog && progressDialog.isShowing()) {
			progressDialog.dismiss();
		}
	}
}