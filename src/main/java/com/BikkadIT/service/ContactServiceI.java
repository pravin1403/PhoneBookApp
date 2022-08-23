package com.BikkadIT.service;

import java.util.List;

import com.BikkadIT.Entity.Contact;

public interface ContactServiceI {

	boolean saveContact(Contact contact);
	
	List<Contact> getAllContact();
}
