package transferobjects;

import java.io.Serializable;

import businesslogic.UserManager;

public class User implements Serializable{
	
	private int id;
	private String username;
	private String firstname;
	private String lastname;
	private String eMail;
	private String password;

	
	//Konstruktor
	public User (int id, String username, String firstname, String lastname, String eMail, String password){
		
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.eMail = eMail;
		this.password = password;
		
	}
	
	
	public User() {
		// TODO Auto-generated constructor stub
	}


	//Getter und Setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
