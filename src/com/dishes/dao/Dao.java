package com.dishes.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dishes.db.DataBaseHelper;
import com.dishes.model.DishInfo;

public class Dao {
	private String TB_NAME = DataBaseHelper.TB_NAME;
	private String DISHID = DataBaseHelper.DISHID;
	private String DISHNAME = DataBaseHelper.DISHNAME;
	private String DISHPIC = DataBaseHelper.DISHPIC;
	private DataBaseHelper dataBaseHelper;

	public Dao(Context context) {
		dataBaseHelper = new DataBaseHelper(context, TB_NAME, null, 1);
	}

	/**
	 * 添加收藏
	 * 
	 * @param dishInfo
	 * @return
	 */
	public boolean addFavor(DishInfo dishInfo) {

		SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DISHID, dishInfo.getDishId());
		values.put(DISHNAME, dishInfo.getDishName());
		values.put(DISHPIC, dishInfo.getDishPic());
		boolean succ = db.insert(TB_NAME, null, values) != -1;
		closeDb();
		return succ;

	}

	/**
	 * 移除收藏
	 * 
	 * @param dishId
	 * @return
	 */
	public boolean removeFavor(String dishId) {

		SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
		boolean succ = db.delete(TB_NAME, DISHID + "=?",
				new String[] { dishId }) != 0;
		closeDb();
		return succ;

	}

	/**
	 * 查询数据库中收藏
	 * 
	 * @return
	 */
	public List<DishInfo> getFavors() {

		SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
		List<DishInfo> infos = new ArrayList<DishInfo>();
		Cursor cursor = db.query(TB_NAME, null, null, null, null, null, null);
//		Cursor cursor=db.rawQuery(sql, selectionArgs)
		if (cursor.moveToFirst()) {
			while (cursor.moveToNext()) {
				DishInfo dishInfo = new DishInfo(cursor.getString(0),
						cursor.getString(1), cursor.getString(2), null);
				infos.add(dishInfo);
			}

		}
		return infos;
	}

	public void closeDb() {
		dataBaseHelper.close();
	}
}
