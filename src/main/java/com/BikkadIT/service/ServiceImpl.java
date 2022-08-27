package com.BikkadIT.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BikkadIT.Entity.Contact;
import com.BikkadIT.repository.ContactRepository;

@Service
public class ServiceImpl implements ContactServiceI {

	@Autowired
	private ContactRepository contactRepository;

	@Override
	public boolean saveContact(Contact contact) {

		Contact save = contactRepository.save(contact);

		if (save != null) {

			return true;

		} else {
			return false;
		}
	}

	@Override
	public List<Contact> getAllContact() {
		List<Contact> findAll = contactRepository.findAll();

//		List<Contact> l=new ArrayList();
//		
//		for(Contact c:findAll) {
//			if(c.getActiveSwitch()=='Y') {
//				l.add(c);
//			}
//		}
//		
//		
//		return l;

		Stream<Contact> filter = findAll.stream().filter(contact -> contact.getActiveSwitch() == 'Y');

		List<Contact> collect = filter.collect(Collectors.toList());

		return collect;

	}

	@Override
	public Optional<Contact> getContact(Integer cId) {
		Optional<Contact> findById = contactRepository.findById(cId);

		return findById;

	}

	@Override
	public boolean updateContact(Contact contact) {
		Contact save = contactRepository.save(contact);

		if (save != null) {

			return true;

		} else {
			return false;
		}
	}

	@Override
	public String softDelete(Integer cId) {
		boolean existsById = contactRepository.existsById(cId);

		if (existsById == true) {
			Contact findById = contactRepository.findById(cId).get();
			findById.setActiveSwitch('N');
			Contact save = contactRepository.save(findById);

			String msg = "Contact Deleted successfully";
			return msg;
		} else {
			String msg = null;
			return msg;

		}

	}

	@Override
	public boolean hardDelete(Integer cId) {

		boolean existsById = contactRepository.existsById(cId);

		if (existsById) {

			contactRepository.deleteById(cId);

			return true;
		} else
			return false;

	}

}
