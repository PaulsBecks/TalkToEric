package de.becks.talktolu;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Comment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class PersonDataSource {

	private SQLiteDatabase database;
	private SQLHelper dbHelper;
	
	private String[] allColumns = { SQLHelper.COLUMN_EMAIL,  SQLHelper.COLUMN_PASSWORT};
	
	public PersonDataSource(Context context){
		dbHelper = new SQLHelper(context);
		
	}
	
	public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }

	  public void close() {
	    dbHelper.close();
	  }

	  public Person createComment(String email, String passwort) {
	    ContentValues values = new ContentValues();
	    values.put(SQLHelper.COLUMN_EMAIL, email);
	    values.put(SQLHelper.COLUMN_PASSWORT, passwort);
	    
	    long insertPerson = database.insert(SQLHelper.TABLE_NAME, null,
	        values);
	    Cursor cursor = database.query(SQLHelper.TABLE_NAME,
	        allColumns, SQLHelper.COLUMN_EMAIL + " = " + insertPerson, null,
	        null, null, null);
	    cursor.moveToFirst();
	    Person newPerson = cursorToPerson(cursor);
	    cursor.close();
	    return null;
	  }

	  /*public void deleteComment(Comment comment) {
	    long id = comment.getId();
	    System.out.println("Comment deleted with id: " + id);
	    database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
	        + " = " + id, null);
	  }
*/
	  public List<Person> getAllPersons() {
	    List<Person> persons = new ArrayList<Person>();

	    Cursor cursor = database.query(SQLHelper.TABLE_NAME,
	        allColumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Person person = cursorToPerson(cursor);
	      persons.add(person);
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    return persons;
	  }

	  private Person cursorToPerson(Cursor cursor) {
	    Person person = new Person(cursor.getString(1), cursor.getString(2));
	    return person;
	  }
}
