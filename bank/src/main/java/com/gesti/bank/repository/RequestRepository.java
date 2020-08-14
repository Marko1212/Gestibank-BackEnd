package com.gesti.bank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gesti.bank.model.Request;
import com.gesti.bank.model.UserAccount;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer>{
	List<Request> findAllByUserAccountToAndRequestStatus(UserAccount agent, byte requestStatus);
	
	List<Request> findAllByUserAccountToAndRequestStatusAndTitle(UserAccount agent, byte requestStatus, String title);
	
	List<Request> findAllByUserAccountFromAndRequestStatusAndTitle(UserAccount client, byte requestStatus, String title);
	
	List<Request> findAllByUserAccountToAndRequestStatusAndTitleNot(UserAccount agent, byte requestStatus, String title);
	
	List<Request> findAllByUserAccountFromAndRequestStatus(UserAccount client, byte requestStatus);
	
	Optional<Request> findFirstByUserAccountFromAndRequestStatusOrderByIdRequestDesc(UserAccount client, byte requestStatus);
	
	@Query("SELECT userAccountTo FROM Request as r where r.userAccountTo.endDate is null and r.userAccountFrom = :userAccountFrom and r.requestStatus = :requestStatus")
	List<UserAccount> fetchAgentForClient(Pageable pageable, @Param("userAccountFrom") UserAccount client, @Param("requestStatus") byte requestStatus);

	
	
}
