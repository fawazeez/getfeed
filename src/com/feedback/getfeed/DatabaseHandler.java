package com.feedback.getfeed;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION=1;
	
	private static final String DATABASE_NAME="getFeed";
	
	private static final String table_master="master";
	
	private static final String master_name="name";
	
	private static final String master_key="key";
	private static final String master_type="type";
	private static final String master_passwordreqd="passwordreqd";
	private static final String master_listavailable="listavailable";
	
	
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
	
String Create_Master_table="CREATE TABLE "+table_master+" ("+master_name+ " TEXT," +master_key +"  TEXT,"+master_type +" INTEGER,"+master_passwordreqd+" TEXT,"+master_listavailable+" TEXT"+")";
Log.d("string",Create_Master_table);
db.execSQL(Create_Master_table);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
db.execSQL("DROP TABLE IF EXISTS "+table_master);
onCreate(db);
	}
	
	void addMaster(Master master){
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues  values = new ContentValues();
		values.put(master_name, master.getName());
		values.put(master_key, master.getKey());
		values.put(master_type, master.getType());
		values.put(master_passwordreqd, master.getPasswordreqd());
		values.put(master_listavailable, master.getListavailable());
		
		db.insert(table_master, null, values);
		db.close();
		
	}
	
	
	Master getMaster(String Key){
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cursor=db.query(table_master, new String[] {master_name,master_type,master_passwordreqd,master_listavailable}, master_key+ "=?", new String[] {Key}, null, null, null,null);
		if (cursor != null)
				cursor.moveToFirst();
		
		Master master = new Master(cursor.getString(0),Key,Integer.parseInt(cursor.getString(1)),cursor.getString(2),cursor.getString(3));
		return master;
		
	}
	
	public List<Master> getAllMaster(){
		List<Master> masterlist = new ArrayList<Master>();
		String selectquery ="SELECT * FROM "+table_master;
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cursor=db.rawQuery(selectquery, null);
		if (cursor.moveToFirst())
		{
			do{
				Master master = new Master(cursor.getString(0),cursor.getString(1),Integer.parseInt(cursor.getString(2)),cursor.getString(3),cursor.getString(4));
				masterlist.add(master);
				}while(cursor.moveToNext());		}
		return masterlist;
		
	}
	
	public int getMasterCount(){
		String selectquery ="SELECT * FROM "+table_master;
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cursor=db.rawQuery(selectquery, null);
	
		int a= cursor.getCount();
		cursor.close();
		return a;
		
	}


}
