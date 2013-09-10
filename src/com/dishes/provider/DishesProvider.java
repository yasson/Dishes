package com.dishes.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

import com.dishes.db.DataBaseHelper;

/**
 * 对外提供收藏信息
 * 
 * @author YangSen
 * 
 */
public class DishesProvider extends ContentProvider {

	public static final String AUTHORITY = "com.dishes";

	public final static Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
			+ "/favor");

	public static final String DISHES = "dishes";
	public static final int DISHES_ITEM = 0;
	public static final int DISHES_ITEM_ID = 1;

	public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.ruixin.login";
	public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.ruixin.login";

	private SQLiteDatabase db;
	private DataBaseHelper dh;
	private static UriMatcher uriMatcher;

	public DishesProvider() {

	}

	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(AUTHORITY, DataBaseHelper.TB_NAME, DISHES_ITEM);
		uriMatcher.addURI(AUTHORITY, DataBaseHelper.TB_NAME + "/#",
				DISHES_ITEM_ID);

	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		dh = new DataBaseHelper(getContext(), DataBaseHelper.TB_NAME, null, 1);
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		db = dh.getReadableDatabase();
		Cursor cursor = null;
		switch (uriMatcher.match(uri)) {
		case DISHES_ITEM:
			cursor = db.query(DataBaseHelper.TB_NAME, null, null, null, null,
					null, null);

			break;
		case DISHES_ITEM_ID:
			String id = uri.getPathSegments().get(1);
			cursor = db.query(
					DataBaseHelper.TB_NAME,
					projection,
					DataBaseHelper.DISHID
							+ "="
							+ id
							+ (!TextUtils.isEmpty(selection) ? "AND("
									+ selection + ')' : ""), selectionArgs,
					null, null, sortOrder);
			break;

		default:
			break;
		}
		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		return cursor;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		switch (uriMatcher.match(uri)) {
		case DISHES_ITEM:
			return CONTENT_TYPE;
		case DISHES_ITEM_ID:
			return CONTENT_ITEM_TYPE;

		default:
			break;
		}
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
