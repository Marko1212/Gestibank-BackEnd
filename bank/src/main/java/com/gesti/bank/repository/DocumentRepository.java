package com.gesti.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gesti.bank.model.Document;
import com.gesti.bank.model.UserAccount;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {
	List<Document> findAllByUserAccount(UserAccount client);
}
