package br.com.hodestito.CRUD2;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactListRepository extends JpaRepository<ContactList, Long> {
}