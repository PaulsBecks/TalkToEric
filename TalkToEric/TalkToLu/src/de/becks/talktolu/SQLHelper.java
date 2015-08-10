package de.becks.talktolu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHelper extends SQLiteOpenHelper {
	 public static final String TABLE_NAME = "person";
	 public static final String COLUMN_EMAIL = "email";
	 public static final String COLUMN_PASSWORT = "passwort";
	 
	private static final String DB_NAME  = "talktolu";
	private static final int DB_VERSION  = 1;
	
	private static final String DATABASE_CREATE = "create table" + TABLE_NAME +"("+ COLUMN_EMAIL + "text primary key, " + COLUMN_PASSWORT +"text);";
	public SQLHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
		onCreate(db);
	}

}
