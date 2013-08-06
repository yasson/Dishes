package com.dishes.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

	public static final String TB_NAME = "favor";
	public static final String DISHID = "id";
	public static final String DISHNAME = "dishname";
	public static final String DISHPIC = "dishpic";

	public DataBaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	public DataBaseHelper(Context context, String name, CursorFactory factory,
			int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		String sqlString = "create table " + TB_NAME + "(" + DISHID
				+ " char(20)," + DISHNAME + " char(20)," + DISHPIC
				+ " char(50))";
		db.execSQL(sqlString);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
