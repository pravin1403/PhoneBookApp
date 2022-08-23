package com.BikkadIT.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.BikkadIT.Entity.Contact;

import com.BikkadIT.service.ContactServiceI;

@RestController
public class ContactController {

	@Autowired
	private ContactServiceI contactServiceI;

	@PostMapping(value = "/saveContact", consumes = "application/json")
	public ResponseEntity<String> saveContact(@Valid @RequestBody Contact contact) {

		boolean saveContact = contactServiceI.saveContact(contact);

		if (saveContact == true) {

			String msg = "Contact save successfully";

			return new ResponseEntity<String>(msg, HttpStatus.CREATED);
		} else {
			String msg = "Contact not save";

			return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);

		}

	}

	@GetMapping(value = "/getAllContact", produces = "application/json")
	public ResponseEntity<String> getAllContact() {

		List<Contact> allContact = contactServiceI.getAllContact();

		if (allContact!=null) {

			String msg = "All contacts Details \n"+allContact;
			return new ResponseEntity<String>(msg, HttpStatus.FOUND);

			
		} else {
			

			String msg = "Contact not found";
			return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);

		
		}
		

		

	}

}
