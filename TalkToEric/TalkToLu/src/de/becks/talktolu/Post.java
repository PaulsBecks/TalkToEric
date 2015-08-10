package de.becks.talktolu;


public class Post {
	
	String message;
	Person absender;
	Person empfaenger;
	Long date;
	
	public Post(String message, Person absender, Person empfaenger){
		this.message = message;
		this.absender = absender;
		this.empfaenger = empfaenger;
		this.date = System.currentTimeMillis();
	}
}
