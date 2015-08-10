package de.becks.talktolu;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

public class Person implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LinkedList<Person> freunde;
	private HashMap<String, Chatverlauf> chatverlauf;
	private String passwort;
	private String email;
	private LinkedList<Gruppe> gruppen;
	
	
	public Person(String email, String passwort){
		this.email =email;
		this.passwort = passwort;
		this.freunde = new LinkedList<Person>();
		this.gruppen = new LinkedList<Gruppe>();
		this.chatverlauf = new HashMap<String, Chatverlauf>();
	}
	
	public void addFreund(Datenbank db, String email){
		Person person = db.getPerson(email);
		if(person != null){
			freunde.add(person);	
		}
		
	}
	
	public boolean checkPasswort(String passwort){
		if(this.passwort.equals(passwort)){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean checkEmail(Person person){
		String email = person.email;
		if(this.email.equals(email)){
			return true;
		}
		else{
			return false;
		}
	}

	public LinkedList<Person> getFreunde(){
		return this.freunde;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	
	
}
