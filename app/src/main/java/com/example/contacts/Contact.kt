package com.example.contacts


data class Contact(
	var name: String, var lastname: String, var number: String, val id: String, val picture: String
) : Comparable<Contact> {

	override fun compareTo(o: Contact): Int {
		return id.toInt() - o.id.toInt()
	}

}