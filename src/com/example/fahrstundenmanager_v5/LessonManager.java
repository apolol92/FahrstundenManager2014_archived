package com.example.fahrstundenmanager_v5;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class LessonManager extends SQLiteOpenHelper {
	Context MyContext;
	LessonInfo Current_Lesson;
	static final String DATABASE_NAME = "testData14.db";
	static final int DATABASE_VERSION = 1;

	static final String TABLE_LESSONS = "table_lessons";
	static final String LESSON_ID = "id";
	static final String LESSON_TITLE = "title";
	static final String LESSON_DATE = "date";
	static final String LESSON_LICENSE = "license";
	static final String LESSON_NORMAL_COST = "normal_cost";
	static final String LESSON_ROAD_COST = "road_cost";
	static final String LESSON_HIGHWAY_COST = "highway_cost";
	static final String LESSON_NIGHT_COST = "night_cost";

	static final String CREATE_TABLE_LESSONS = "CREATE TABLE " + TABLE_LESSONS
			+ " (" + LESSON_ID + " INTEGER PRIMARY KEY autoincrement,"
			+ LESSON_TITLE + " TEXT," + LESSON_DATE + " TEXT," + LESSON_LICENSE
			+ " TEXT," + LESSON_NORMAL_COST + " FLOAT," + LESSON_ROAD_COST
			+ " FLOAT," + LESSON_HIGHWAY_COST + " FLOAT," + LESSON_NIGHT_COST
			+ " FLOAT)";

	static final String TABLE_HOURS = "table_hours";
	static final String HOUR_ID = "id";
	static final String HOUR_LESSONID = "lessonid";
	static final String HOUR_DATE = "date";
	static final String HOUR_TYP = "typ";
	static final String HOUR_LENGTH = "length";
	static final String HOUR_EMO = "emo";
	static final String CREATE_TABLE_HOURS = "CREATE TABLE " + TABLE_HOURS
			+ " (" + HOUR_ID + " INTEGER PRIMARY KEY autoincrement,"
			+ HOUR_LESSONID + " INTEGER," + HOUR_DATE + " TEXT," + HOUR_TYP
			+ " TEXT," + HOUR_LENGTH + " INTEGER," + HOUR_EMO + " INTEGER)";

	public LessonManager(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
		this.MyContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_TABLE_LESSONS);
		db.execSQL(CREATE_TABLE_HOURS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	public void insert_lessonInfo(LessonInfo lInfo) {
		SQLiteDatabase db = this.getWritableDatabase();
		/*
		 * Daten verpacken ContentValues values = new ContentValues();
		 * values.put(KEY_NAME, contact.getName()); values.put(KEY_PH_NO,
		 * contact.getPhoneNumber()); //Reihe einfügen db.insert(TABLE_CONTACTS,
		 * null, values); db.close(); //Closing database connection
		 */
		ContentValues values = new ContentValues();
		values.put(LESSON_TITLE, lInfo.title);
		values.put(LESSON_DATE, lInfo.date);
		values.put(LESSON_LICENSE, lInfo.license);
		values.put(LESSON_NORMAL_COST, lInfo.normal_cost);
		values.put(LESSON_ROAD_COST, lInfo.road_cost);
		values.put(LESSON_HIGHWAY_COST, lInfo.highway_cost);
		values.put(LESSON_NIGHT_COST, lInfo.night_cost);
		db.insert(TABLE_LESSONS, null, values);
	}

	public void insert_hour(HourInfo hInfo) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(HOUR_LESSONID, hInfo.lessonid);
		values.put(HOUR_DATE, hInfo.date);
		values.put(HOUR_TYP, hInfo.typ);
		values.put(HOUR_LENGTH, hInfo.length);
		values.put(HOUR_EMO, hInfo.emo);
		db.insert(TABLE_HOURS, null, values);
	}

	public LessonInfo read_lessonInfo(int id) {
		try {
			/*
			 * SQLiteDatabase db = this.getReadableDatabase();
			 * 
			 * Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID,
			 * KEY_NAME,KEY_PH_NO},KEY_ID+"=?",new String[]
			 * {String.valueOf(id)}, null,null,null); if(cursor != null) {
			 * cursor.moveToFirst(); Contact contact = new
			 * Contact(Integer.parseInt
			 * (cursor.getString(0)),cursor.getString(1),cursor.getString(2));
			 * this.tv.setText(contact.getName()); return contact; }
			 */
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.query(TABLE_LESSONS, new String[] { LESSON_ID,
					LESSON_TITLE, LESSON_DATE, LESSON_LICENSE,
					LESSON_NORMAL_COST, LESSON_ROAD_COST, LESSON_HIGHWAY_COST,
					LESSON_NIGHT_COST }, LESSON_ID + "=?",
					new String[] { String.valueOf(id) }, null, null, null);
			if (cursor != null) {
				cursor.moveToFirst();
				this.Current_Lesson = new LessonInfo();
				Current_Lesson.id = cursor.getInt(0);
				Current_Lesson.title = cursor.getString(1);
				Current_Lesson.date = cursor.getString(2);
				Current_Lesson.license = cursor.getString(3);
				Current_Lesson.normal_cost = cursor.getFloat(4);
				Current_Lesson.road_cost = cursor.getFloat(5);
				Current_Lesson.highway_cost = cursor.getFloat(6);
				Current_Lesson.night_cost = cursor.getFloat(7);

				return this.Current_Lesson;
			} else {
				return null;
			}
		} catch (Exception ex) {
			Log.e("FEHLER", ex.getMessage());
		}
		return null;
	}

	public List<LessonInfo> read_allLessonInfos() {
		List<LessonInfo> listLessonInfo = new ArrayList<LessonInfo>();
		String selectQuery = "SELECT * FROM " + TABLE_LESSONS;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				LessonInfo lInfo = new LessonInfo();
				lInfo.id = cursor.getInt(0);
				lInfo.title = cursor.getString(1);
				lInfo.date = cursor.getString(2);
				lInfo.license = cursor.getString(3);
				lInfo.normal_cost = cursor.getFloat(4);
				lInfo.road_cost = cursor.getFloat(5);
				lInfo.highway_cost = cursor.getFloat(6);
				lInfo.night_cost = cursor.getFloat(7);
				listLessonInfo.add(lInfo);

			} while (cursor.moveToNext());
			cursor.close();
			return listLessonInfo;
		}

		return null;
	}

	public HourInfo read_hourinfo(int id) {
		try {
			SQLiteDatabase db = getWritableDatabase();
			Cursor cursor = db.query(TABLE_HOURS,
					new String[] { HOUR_ID, HOUR_LESSONID, HOUR_DATE, HOUR_TYP,
							HOUR_LENGTH, HOUR_EMO }, LESSON_ID + "=?",
					new String[] { String.valueOf(id) }, null, null, null);
			if (cursor != null) {
				HourInfo MyHourInfo = new HourInfo();
				cursor.moveToFirst();
				MyHourInfo.id = cursor.getInt(0);
				MyHourInfo.lessonid = cursor.getInt(1);
				MyHourInfo.date = cursor.getString(2);
				MyHourInfo.typ = cursor.getString(3);
				MyHourInfo.length = cursor.getInt(4);
				MyHourInfo.emo = cursor.getInt(5);
				cursor.close();
				return MyHourInfo;
			}
			return null;
		} catch (Exception ex) {
			Log.e("FEHLER HOUR", ex.getMessage());
		}

		return null;
	}

	public List<HourInfo> read_all_hours() {
		List<HourInfo> hourList = new ArrayList<HourInfo>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_HOURS;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				HourInfo hour = new HourInfo();
				hour.id = cursor.getInt(0);
				hour.lessonid = cursor.getInt(1);
				hour.date = cursor.getString(2);
				hour.typ = cursor.getString(3);
				hour.length = cursor.getInt(4);
				hour.emo = cursor.getInt(5);
				// Adding contact to list
				hourList.add(hour);
			} while (cursor.moveToNext());
		}

		// return contact list
		return hourList;
	}

	public void update_lessoninfo(int id, LessonInfo lInfo) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(LESSON_TITLE, lInfo.title);
		values.put(LESSON_DATE, lInfo.date);
		values.put(LESSON_LICENSE, lInfo.license);
		values.put(LESSON_NORMAL_COST, lInfo.normal_cost);
		values.put(LESSON_ROAD_COST, lInfo.road_cost);
		values.put(LESSON_HIGHWAY_COST, lInfo.highway_cost);
		values.put(LESSON_NIGHT_COST, lInfo.night_cost);
		db.update(TABLE_LESSONS, values, LESSON_ID + " = ?",
				new String[] { String.valueOf(id) });
		// TODO: puts and update

		/*
		 * db.update(TABLE_CONTACTS, values, KEY_ID + " = ?", new String[] {
		 * String.valueOf(contact.getID()) });
		 */

	}

	public void update_hourinfo(int id, HourInfo hInfo) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(HOUR_LESSONID, hInfo.lessonid);
		values.put(HOUR_DATE, hInfo.date);
		values.put(HOUR_TYP, hInfo.typ);
		values.put(HOUR_LENGTH, hInfo.length);
		values.put(HOUR_EMO, hInfo.emo);
		db.update(TABLE_HOURS, values, HOUR_ID + " = ?",
				new String[] { String.valueOf(id) });
	}

	/*
	 * // Deleting single contact public void deleteContact(Contact contact) {
	 * SQLiteDatabase db = this.getWritableDatabase(); db.delete(TABLE_CONTACTS,
	 * KEY_ID + " = ?", new String[] { String.valueOf(contact.getID()) });
	 * db.close(); }
	 */
	void delete_lessonInfo(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_LESSONS, LESSON_ID + " = ?",
				new String[] { String.valueOf(id) });
	}

	void delete_hourinfo(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_HOURS, HOUR_ID + " = ?",
				new String[] { String.valueOf(id) });
	}

}
