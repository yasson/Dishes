/**
 * @author SenYang
 */
package com.dishes.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ksoap2.serialization.SoapObject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dishes.AppContext;
import com.dishes.common.CommonMethod;
import com.dishes.common.ViewHolder;
import com.dishes.dao.Dao;
import com.dishes.model.DishInfo;
import com.dishes.model.MenuInfo;
import com.dishes.model.MenuInfo.AccessoriesInfo;
import com.dishes.model.MenuInfo.IngredientsInfo;
import com.dishes.model.MenuInfo.SeasoningInfo;
import com.dishes.model.MenuInfo.StepsInfo;
import com.dishes.model.WSResult;
import com.dishes.ui.base.BaseActivity;
import com.dishes.util.ImageCallback;
import com.dishes.util.ImageLoader;
import com.dishes.util.ThreadTool;
import com.dishes.webservice.WebServiceAction;
import com.dishes.webservice.WebServiceConstant;

/**
 * @author SenYang
 * 
 */
public class HowToCook extends BaseActivity implements OnClickListener {

	private Handler handler = new Handler() {

		@SuppressWarnings("unused")
		public void handleMessage(android.os.Message msg) {

			switch (msg.what) {
			case LOADINGDATA:
				removeViewCache();
				menuInfo = (MenuInfo) msg.obj;
				tv_dishName.setText(menuInfo.getMenuName());
				tv_hdesc.setText(menuInfo.getDescription());
				List<IngredientsInfo> listI = menuInfo.getListI();
				if (listI.size() == 0)
					tv_ingredient.setVisibility(View.GONE);
				for (int i = 0; i < listI.size(); i++) {
					tv_ingredient.setVisibility(View.VISIBLE);
					LinearLayout layout = new LinearLayout(
							getApplicationContext());
					for (int j = 0; j < 2; j++) {
						if (i >= listI.size()) {
							break;
						}
						IngredientsInfo info = listI.get(i);
						TextView textView = new TextView(
								getApplicationContext());
						textView.setText(info.getName() + info.getVolume()
								+ info.getUnits());
						textView.setTextColor(Color.GRAY);
						layout.addView(textView);
						i++;
					}
					ll_ingredient.addView(layout);

				}

				List<AccessoriesInfo> listA = menuInfo.getListA();
				if (listA.size() == 0)
					tv_accessory.setVisibility(View.GONE);
				for (int i = 0; i < listA.size(); i++) {
					tv_accessory.setVisibility(View.VISIBLE);
					LinearLayout layout = new LinearLayout(
							getApplicationContext());
					for (int j = 0; j < 2; j++) {
						if (i >= listA.size()) {
							break;
						}
						AccessoriesInfo info = listA.get(i);
						TextView textView = new TextView(
								getApplicationContext());
						textView.setText(info.getName() + info.getVolume()
								+ info.getUnits());
						textView.setTextColor(Color.GRAY);
						layout.addView(textView);
						i++;
					}
					ll_accessory.addView(layout);

				}

				List<SeasoningInfo> listS = menuInfo.getListS();
				if (listS.size() == 0)
					tv_seasoning.setVisibility(View.GONE);
				for (int i = 0; i < listS.size(); i++) {
					tv_seasoning.setVisibility(View.VISIBLE);
					LinearLayout layout = new LinearLayout(
							getApplicationContext());
					for (int j = 0; j < 2; j++) {
						if (i >= listS.size()) {
							break;
						}
						SeasoningInfo info = listS.get(i);
						TextView textView = new TextView(
								getApplicationContext());
						textView.setTextColor(Color.GRAY);
						textView.setText(info.getName() + info.getVolume()
								+ info.getUnits());
						layout.addView(textView);
						i++;
					}
					ll_seasoning.addView(layout);

				}

				List<StepsInfo> stepsInfos = menuInfo.getListStep();

				// CookStepsAdapter cookStepsAdapter=new
				// CookStepsAdapter(getApplicationContext(),stepsInfos);
				// ll_steps.setAdapter( cookStepsAdapter );
				for (int j = 0; j < stepsInfos.size(); j++) {
					View view = null;
					final ViewHolder viewHolder;

					if (view == null) {
						view = LayoutInflater.from(getApplicationContext())
								.inflate(R.layout.adapter_cooksteps, null);
						viewHolder = new ViewHolder();
						viewHolder.textView1 = (TextView) view
								.findViewById(R.id.tv_steps);
						viewHolder.textView2 = (TextView) view
								.findViewById(R.id.tv_cookstepsdesc);
						viewHolder.imageView = (ImageView) view
								.findViewById(R.id.iv_cooksteps);
						view.setTag(viewHolder);
					} else {
						viewHolder = (ViewHolder) view.getTag();
					}

					viewHolder.textView1.setText(stepsInfos.get(j).getSn()
							+ ".");
					viewHolder.textView2.setText(stepsInfos.get(j).getNote());
					viewHolder.imageView
							.setImageResource(R.drawable.ic_launcher);
					String imageUrl = stepsInfos.get(j).getSteppic();
					ll_steps.addView(view);

				}

				new ImageLoader().loadImage(getApplicationContext(),
						iv_dishPic, menuInfo.getPic(), menuInfo.getMenuName(),
						400, new ImageCallback() {

							@Override
							public void imageLoadOver() {

							}

							@Override
							public void imageLoadFailed() {

							}

							@Override
							public void imageLoadBefore() {

							}

							@Override
							public void imageLoading(Bitmap bitmap, String url,
									float ratio, int width, int height) {

								iv_dishPic.setImageBitmap(bitmap);

							}
						});

				break;

			default:
				break;
			}

		};

	};

	private Button btn_ddback;
	private ImageView iv_dishPic;
	private LinearLayout ll_ingredient, ll_accessory, ll_seasoning, ll_steps;
	private TextView tv_dishName, tv_hdesc, tv_ingredient, tv_accessory,
			tv_seasoning;
	private ImageButton ib_favor;
	private MenuInfo menuInfo;

	private final int LOADINGDATA = 1;
	private boolean ISFAVOR;

	private String dishId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_howtocook);
		initView();
	}

	@Override
	protected void onResume() {

		super.onResume();

		getDishDetailInfo(dishId, WebServiceConstant.wsUser);

	}

	@Override
	protected void onPause() {

		super.onPause();

		overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);

	}

	/**
	 * 
	 */
	private void removeViewCache() {

		ll_accessory.removeAllViews();
		ll_ingredient.removeAllViews();
		ll_seasoning.removeAllViews();
		ll_steps.removeAllViews();

	}

	/**
	 * @param dishId
	 * @param wsuser
	 */
	private void getDishDetailInfo(final String dishId, final String wsuser) {

		ThreadTool.getInstance().addTask(new Runnable() {

			@Override
			public void run() {

				Map<String, Object> dishMap = new HashMap<String, Object>();
				dishMap.put("menuId", dishId);
				dishMap.put("wsUser", wsuser);

				SoapObject soapObject = WebServiceAction.getSoapObject(
						WebServiceConstant.SERVICE_DISHINFO_URL,
						WebServiceConstant.GETDISHINFO, dishMap,
						WebServiceConstant.SERVICENAMESPACE);

				if (soapObject != null) {
					WSResult wsResult = new WSResult(soapObject);
					switch (Integer.parseInt(wsResult.getState())) {
					case 200:
						CommonMethod.netException(getApplicationContext());
						break;
					case 201:
						SoapObject sObject = (SoapObject) wsResult.getResult()
								.get(0);
						MenuInfo menuInfo = new MenuInfo(sObject);
						Message message = new Message();
						message.what = LOADINGDATA;
						message.obj = menuInfo;
						handler.sendMessage(message);
						break;
					case 202:
						wsResult.getResult();
						break;
					default:
						break;
					}
				}
			}
		});

	}

	/**
	 * 
	 */
	private void initView() {

		int screenHeight = CommonMethod.getWindowSizeH(getApplicationContext());
		int screenWidth = CommonMethod.getWindowSizeW(getApplicationContext());
		btn_ddback = (Button) findViewById(R.id.btn_ddback);
		btn_ddback.setOnClickListener(this);
		ib_favor = (ImageButton) findViewById(R.id.ib_favor);
		ib_favor.setOnClickListener(this);
		iv_dishPic = (ImageView) findViewById(R.id.iv_dishPic);
		tv_dishName = (TextView) findViewById(R.id.tv_dishName);
		tv_hdesc = (TextView) findViewById(R.id.tv_hdesc);
		tv_ingredient = (TextView) findViewById(R.id.tv_ingredient);
		tv_accessory = (TextView) findViewById(R.id.tv_accessory);
		tv_seasoning = (TextView) findViewById(R.id.tv_seasoning);
		ll_ingredient = (LinearLayout) findViewById(R.id.ll_ingredient);
		ll_accessory = (LinearLayout) findViewById(R.id.ll_accessory);
		ll_seasoning = (LinearLayout) findViewById(R.id.ll_seasoning);
		ll_steps = (LinearLayout) findViewById(R.id.ll_steps);
		iv_dishPic.setLayoutParams(new LinearLayout.LayoutParams(screenWidth,
				(int) (screenHeight / 2.5)));
		Intent intent = getIntent();
		dishId = intent.getStringExtra("dishId");
		if (AppContext.favorlist.size() == 0) {
			Dao dao = new Dao(getApplicationContext());
			AppContext.favorlist = dao.getFavors();
		}

		for (DishInfo iterable_element : AppContext.favorlist) {
			if (iterable_element.getDishId().equals(dishId)) {
				ISFAVOR = true;
				ib_favor.setImageResource(R.drawable.favor);
				break;
			}
			ib_favor.setImageResource(R.drawable.nfavor);
		}
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_ddback:
			finish();
			break;
		case R.id.ib_favor:
			Dao dao = new Dao(getApplicationContext());
			DishInfo dishInfo = new DishInfo(menuInfo.getMenuId(),
					menuInfo.getMenuName(), menuInfo.getPic(), null);
			if (!ISFAVOR) {
				if (dao.addFavor(dishInfo)) {
					ISFAVOR = true;
					AppContext.favorlist.add(dishInfo);
					ib_favor.setImageResource(R.drawable.favor);
					Toast.makeText(getApplicationContext(), "收藏成功",
							Toast.LENGTH_SHORT).show();
				}
			} else {
				if (dao.removeFavor(dishInfo.getDishId())) {
					ISFAVOR = false;
					AppContext.favorlist.remove(dishInfo);
					ib_favor.setImageResource(R.drawable.nfavor);
					Toast.makeText(getApplicationContext(), "取消收藏成功",
							Toast.LENGTH_SHORT).show();
				}
			}

			break;
		default:
			break;
		}

	}
}
