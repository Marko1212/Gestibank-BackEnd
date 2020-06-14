package com.gesti.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gesti.bank.model.Role;
import com.gesti.bank.model.UserAccount;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {
	List<UserAccount> findAllByRole(Role role);
	UserAccount findByUsernameAndPass(String username, String password);
	
	@Query("SELECT ua FROM UserAccount as ua where ua.role = :role AND " + 
			"(ua.idUserAccount = :filterId or ua.lastname like :filter)")
	List<UserAccount> filterPerIdOrLastNameAndRole(@Param("role") Role role, @Param("filterId") int filterId, @Param("filter") String filter);
}
