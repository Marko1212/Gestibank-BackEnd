package com.gesti.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gesti.bank.model.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {

}
