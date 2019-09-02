package br.com.hodestito.CRUD2;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long>, ContactRepositoryCustom {

	
}