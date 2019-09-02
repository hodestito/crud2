package br.com.hodestito.CRUD2;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class ContactListRepositoryCustomImpl implements ContactRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> findAllContactsByUser(User user) {
		Query query = entityManager.createNativeQuery("SELECT em.* FROM contactList.contact as em " +
                "WHERE em.user = ?", Contact.class);
        query.setParameter(1, user);
        return query.getResultList();
	}

}
