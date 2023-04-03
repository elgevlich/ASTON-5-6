package com.example.contacts;


public class Contact {

	private String name;
	private String lastname;
	private String number;
	private final String id;

	public Contact(String name, String lastname, String number, String id) {

		this.name = name;
		this.lastname = lastname;
		this.number = number;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastname;
	}

	public String getNumber() {
		return number;
	}

	public String getID() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLastName(String lastname) {
		this.lastname = lastname;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
