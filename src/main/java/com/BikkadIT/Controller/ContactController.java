package com.BikkadIT.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@GetMapping(value = "/getAllContact")
	public ResponseEntity<String> getAllContact() {

		List<Contact> allContact = contactServiceI.getAllContact();

		if (allContact != null) {

		
			String msg = "All contacts Details \n" + allContact;
			
			return new ResponseEntity<String>(msg, HttpStatus.FOUND);

		} else {

			String msg = "Contact not found";
			
			return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);

		}
	}
	
	
	@GetMapping(value = "/getContactById/{cId}")
	public ResponseEntity<String> getContactById(@PathVariable Integer cId) {

		Optional<Contact> contact = contactServiceI.getContact(cId);
		
		if(contact.isPresent()) {
			
			String msg="Contact Details"+contact;
			
			return new ResponseEntity<String>(msg, HttpStatus.FOUND);
		}else {

		String msg="Contact not found";
		
		return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
	}
	}
	
	
	@PutMapping(value = "/updateContact", consumes = "application/json")
	public ResponseEntity<String> updateContact(@Valid @RequestBody Contact contact) {

		boolean saveContact = contactServiceI.saveContact(contact);

		if (saveContact == true) {

			String msg = "Contact updated successfully";

			return new ResponseEntity<String>(msg, HttpStatus.CREATED);
		} else {
			String msg = "Contact not save";

			return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);

		}

	}
	
	
	@DeleteMapping(value = "/softDelete/{cId}")
	public ResponseEntity<String> softDelete(@PathVariable Integer cId) {

		 String softDelete = contactServiceI.softDelete(cId);
		 
		 if(softDelete==null) {
		
			return new ResponseEntity<String>(softDelete, HttpStatus.BAD_REQUEST);

		}
		 else {
			 
			 return new ResponseEntity<String>(softDelete, HttpStatus.OK);
			 
		 }

	}
	
	@DeleteMapping(value = "/hardDelete/{cId}")
	public ResponseEntity<String> hardDelete(@PathVariable Integer cId) {

		 boolean hardDelete = contactServiceI.hardDelete(cId);
		 
		 
		 if(hardDelete ) {
		
			return new ResponseEntity<String>("contact delete successfully", HttpStatus.BAD_REQUEST);

		}
		 else {
			 
			 return new ResponseEntity<String>("contact not delete", HttpStatus.OK);
			 
		 }

	}

}
