/**
 *
 * @author YangSen
 */
package com.dishes.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ksoap2.serialization.SoapObject;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.dishes.AppContext;
import com.dishes.adapter.StaggeredGridViewAdapter;
import com.dishes.common.CommonMethod;
import com.dishes.common.Constant;
import com.dishes.model.IngredientInfo;
import com.dishes.model.WSResult;
import com.dishes.ui.base.BaseActivity;
import com.dishes.views.stageredggridview.StaggeredGridView;
import com.dishes.views.stageredggridview.StaggeredGridView.OnItemClickListener;
import com.dishes.webservice.WebServiceAction;
import com.dishes.webservice.WebServiceConstant;

/**
 * 
 * @author YangSen
 * 
 */
public class IngredientInterRestrictionUi extends BaseActivity implements
		OnClickListener, OnEditorActionListener {

	private StaggeredGridView sGridView;
	private List<IngredientInfo> infos;
	private Button button;
	private EditText editText;

	private Handler mHandler = new Handler() {

		public void handleMessage(android.os.Message msg) {

			switch (msg.what) {
			case 1:

				StaggeredGridViewAdapter sAdapter = new StaggeredGridViewAdapter(
						getApplicationContext(), infos, sGridView);
				sGridView.setAdapter(sAdapter);
				pr.setVisibility(View.GONE);
				sGridView.setVisibility(View.VISIBLE);
				sAdapter.notifyDataSetChanged();
				break;

			default:
				break;
			}

		};
	};
	private RelativeLayout pr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_ingredient_interrestriction);
		initView();
	}

	@Override
	protected void onResume() {

		super.onResume();
		if (AppContext.list_ingredient_energy.size() == 0) {
			getIngredients();
		} else if (sGridView.getAdapter() == null) {
			infos = AppContext.list_ingredient_energy;
			mHandler.sendEmptyMessage(1);
		}
	}

	/**
	 * 
	 */
	private void initView() {

		button = (Button) findViewById(R.id.btn_searching);
		button.setOnClickListener(this);
		editText = (EditText) findViewById(R.id.et_search);
		editText.setOnEditorActionListener(this);
		pr = (RelativeLayout) findViewById(R.id.pr);
		sGridView = (StaggeredGridView) findViewById(R.id.staggeredGridView);
		sGridView.setVisibility(View.GONE);
		sGridView.setItemMargin(1, 1, 1, 1); // set the GridView margin
		sGridView.setFastScrollEnabled(true);
		sGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(StaggeredGridView parent, View view,
					int position, long id) {

				Bundle bundle = new Bundle();
				bundle.putSerializable("ingreName", infos.get(position)
						.getInName());
				bundle.putSerializable("pic", infos.get(position).getInPic());
				openActivity(IngredientInterRestrictionDetailUi.class, bundle);
			}
		});
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_searching:
			String ingreName = editText.getText().toString();
			if (ingreName != null && !ingreName.equals("")) {
				Bundle bundle = new Bundle();
				bundle.putSerializable("ingreName", ingreName);
				openActivity(IngredientInterRestrictionDetailUi.class, bundle);
			} else {
				Toast.makeText(getApplicationContext(), "请输入要查看的食材名称",
						Toast.LENGTH_SHORT).show();
			}

			break;

		default:
			break;
		}
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

		switch (actionId) {
		case EditorInfo.IME_ACTION_SEARCH:
			onClick(button);

			break;

		default:
			break;
		}
		return false;
	}

	/**
	 * 获取食材列表
	 */
	private void getIngredients() {

		new Thread(new Runnable() {

			@Override
			public void run() {

				infos = new ArrayList<IngredientInfo>();
				Map<String, Object> hashmap = new HashMap<String, Object>();
				hashmap.put("count", Constant.Ingredient.HOTCOUNT);
				hashmap.put("wsUser", WebServiceConstant.wsUser);
				SoapObject comIngredients = WebServiceAction.getSoapObject(
						WebServiceConstant.SERVICE_URL_HOTDISHESWS,
						WebServiceConstant.GETHOTINGREDIENTS, hashmap,
						WebServiceConstant.SERVICENAMESPACE);
				if (comIngredients != null) {
					WSResult result = new WSResult(comIngredients);
					for (int i = 0; i < result.getResult().size(); i++) {
						IngredientInfo info = new IngredientInfo(
								(SoapObject) result.getResult().get(i), 1);
						infos.add(info);
					}
					AppContext.list_ingredient_energy = infos;
					mHandler.sendEmptyMessage(1);

				} else {
					CommonMethod.netException(getApplicationContext());
				}

			}
		}).start();

	}

}
