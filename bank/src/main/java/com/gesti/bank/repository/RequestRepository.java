package com.gesti.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gesti.bank.model.Request;
import com.gesti.bank.model.UserAccount;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer>{
	List<Request> findAllByUserAccountToAndRequestStatus(UserAccount agent, byte requestStatus);
	List<Request> findAllByUserAccountFromAndRequestStatus(UserAccount client, byte requestStatus);
}
