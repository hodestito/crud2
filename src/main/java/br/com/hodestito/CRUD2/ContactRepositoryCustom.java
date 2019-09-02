package br.com.hodestito.CRUD2;

import java.util.List;

public interface ContactRepositoryCustom {

	
	List<Contact> findAllContactsByUser(User user);

	
	
}
