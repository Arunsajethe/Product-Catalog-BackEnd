package project.training.productcatalog.register.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import project.training.productcatalog.register.model.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {

	List<Registration> findByEmail(String email);
	
	@Modifying
	@Transactional
	@Query("update Registration set checkstatus=?1 where email=?2")
	void updateCheckStatus(String checkstatus, String email);
	
	@Modifying
	@Transactional
	@Query("delete from Registration where email=?1")
	void deleteRegistration(String email);
	
	@Modifying
	@Transactional
	@Query("update Registration set password=?1 where email=?2")
	void forgetPassword(String password, String email);
	
}
