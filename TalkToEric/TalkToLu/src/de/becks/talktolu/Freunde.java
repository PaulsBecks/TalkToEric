package de.becks.talktolu;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class Freunde extends Activity {

	
	private Person user;
	private ArrayList<Person> freunde;
	private Datenbank db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_freunde);
		Intent intent = getIntent();
		db = (Datenbank) intent.getSerializableExtra("datenbank");
		user = (Person) intent.getSerializableExtra("user");
		Toast.makeText(getApplicationContext(), user.getEmail(), Toast.LENGTH_SHORT).show();
		findViewById(R.id.newfriend).setOnClickListener(ocl);
		findViewById(R.id.chatverlaeufe).setOnClickListener(ocl);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.freunde, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void addFriend(){
		LayoutInflater li = LayoutInflater.from(getApplicationContext());
		View newfriend = li.inflate(R.layout.newfriend, null);
		if(newfriend != null){
			Toast.makeText(getApplicationContext(), ""+newfriend.getId(), Toast.LENGTH_SHORT).show();
		}
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setView(newfriend);
		final EditText userInput = (EditText) newfriend.findViewById(R.id.edittextfriendrequest);
		
		alertDialogBuilder
		.setCancelable(false)
		.setPositiveButton("OK",
		  new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog,int id) {
			// get user input and set it to result
			// edit text
		    	
		    }
		  })
		.setNegativeButton("Cancel",
		  new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog,int id) {
			dialog.cancel();
		    }
		  });
		
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		if(alertDialog!=null){
			alertDialog.show();
		}
	}
	
	OnClickListener ocl =new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.newfriend:{
				addFriend();
			};
			break;
			case R.id.chatverlaeufe :{
				Intent intent = new Intent(getApplicationContext(), Chatverlauf.class);
				intent.putExtra("user", user);
				intent.putExtra("datenbank", db);
				startActivity(intent);
			};
			break;
			}
		}
	};
}
