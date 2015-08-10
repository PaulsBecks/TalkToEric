package de.becks.talktolu;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Chatverlauf extends Activity  {

	private ArrayList<String> listItems;
	private ArrayAdapter<String> adapter;
	private ListView lv;
	private Person user;
	private Datenbank db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chatverlauf);
		
		lv = (ListView) findViewById(R.id.chatverlauf);
		Intent i = getIntent();
		user = (Person) i.getSerializableExtra("user");
		Toast.makeText(getApplicationContext(), user.getEmail(), Toast.LENGTH_SHORT).show();
		db = (Datenbank) i.getSerializableExtra("datenbank");
		findViewById(R.id.freunde).setOnClickListener(ocl);
		for(Person person :user.getFreunde() ){
			listItems.add(person.getEmail());
		}
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
		if(adapter != null){
			
			Toast.makeText(getApplicationContext(), "adapter not null", Toast.LENGTH_SHORT ).show();
			//lv.setAdapter(adapter);
		}
		else{
			Toast.makeText(getApplicationContext(), "Something fucked up with adapter", Toast.LENGTH_SHORT ).show();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chatverlauf, menu);
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
	
	
	OnClickListener ocl =new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.freunde:{
				Intent intent = new Intent(getApplicationContext(), Freunde.class);
				intent.putExtra("user", user);
				intent.putExtra("datenbank", db);
				startActivity(intent);
			};
			break;
			case R.id.newchat :{
				//Neuen Chat anlegen -> vielleicht liste der freunde und gruppen einer muss ausgewählt werden
			};
			break;
			}
			
		}
	};
	
		
	
}
