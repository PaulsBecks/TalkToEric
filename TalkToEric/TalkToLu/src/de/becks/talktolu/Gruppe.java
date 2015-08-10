package de.becks.talktolu;

import java.util.LinkedList;

public class Gruppe {

	LinkedList<Person> gruppe;
	
	public Gruppe(LinkedList<Person> gruppe){
		this.gruppe = gruppe;
	}
	
	public void addPerson(Datenbank db, String email){
		Person person = db.getPerson(email);
		if(person != null){
			gruppe.add(person);	
		}
	}
}
