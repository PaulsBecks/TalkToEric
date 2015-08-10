package de.becks.talktolu;

import java.io.Serializable;
import java.util.HashMap;

public class Datenbank implements Serializable {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private HashMap<String, Person> user;
		private int id_count;
		
		public Datenbank(){
			user = new HashMap<String, Person>();
			id_count = 0;
		}
		
		
		public void addPerson(String email, String passwort){
			user.put(email, new Person(email, passwort));
		}
		
		public Person getPerson(String email){
			return user.get(email);
		}
		
}
