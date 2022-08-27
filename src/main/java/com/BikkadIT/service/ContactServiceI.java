package com.BikkadIT.service;

import java.util.List;
import java.util.Optional;

import com.BikkadIT.Entity.Contact;

public interface ContactServiceI {

	boolean saveContact(Contact contact);

	List<Contact> getAllContact();

	Optional<Contact> getContact(Integer cId);

	boolean updateContact(Contact contact);

	String softDelete(Integer cId);

	boolean hardDelete(Integer cId);
}
