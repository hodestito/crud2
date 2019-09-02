package br.com.hodestito.CRUD2;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUid(String uid);
	
	Long deleteByUid(String uid);
	
}
