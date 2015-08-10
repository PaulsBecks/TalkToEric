package de.becks.talktolu;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EinloggActivity extends Activity {

	private Datenbank db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_einlogg);
		db = new Datenbank();
		Button einloggen = (Button) findViewById(R.id.anmelden);
		Button register = (Button) findViewById(R.id.register);
		
		einloggen.setOnClickListener(ocl);
		register.setOnClickListener(ocl);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.einlogg, menu);
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
		// TODO Auto-generated method stub
		EditText email =(EditText) findViewById(R.id.email);
		EditText passwort =(EditText) findViewById(R.id.passwort);
		String emailText = email.getText().toString();
		String passwortText = passwort.getText().toString();
		//Toast.makeText(getApplicationContext(), "im click", Toast.LENGTH_SHORT).show();
		switch(v.getId()){
		case R.id.anmelden: {
			
			if(emailText != null || passwortText != null){
				try{
					Person person = db.getPerson(emailText);
					if(person != null){
						if(person.checkPasswort(passwortText)){
							Toast.makeText(getApplicationContext(), "e-mail und passwort richtig", Toast.LENGTH_SHORT).show();
							Intent chatverlauf = new Intent(getApplicationContext(), Freunde.class);
							chatverlauf.putExtra("user", person);
							chatverlauf.putExtra("datenbank", db);
							startActivity(chatverlauf);
							//Hier müssen wir die Klasse wechseln und in den Bereich des Users gehen 
						}else{
							Toast.makeText(getApplicationContext(), "e-mail oder passwort falsch", Toast.LENGTH_SHORT).show();
						}
						
					}else{
						Toast.makeText(getApplicationContext(), "bitte registrieren", Toast.LENGTH_SHORT).show();
					}
					
				}catch(Exception e){
					Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_SHORT).show();

				}
			}else{
				Toast.makeText(getApplicationContext(), "Wtf", Toast.LENGTH_SHORT).show();
			}
		};
		break;
		case R.id.register: {
			if(emailText != null || passwortText != null){
				try{
					Person person = db.getPerson(emailText);
					if(person != null){
						Toast.makeText(getApplicationContext(), "sie sind bereits registriert!", Toast.LENGTH_SHORT).show();
						
					}else{
						db.addPerson(emailText, passwortText);
						Toast.makeText(getApplicationContext(), emailText +" Sie sind registriert", Toast.LENGTH_SHORT).show();
					}
					
				}catch(Exception e){
					Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_SHORT).show();

				}
			}else{
				Toast.makeText(getApplicationContext(), "Wtf", Toast.LENGTH_SHORT).show();
			}
		};
		}
	}
};
	
	
}
