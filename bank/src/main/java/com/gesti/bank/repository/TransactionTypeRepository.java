package com.gesti.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gesti.bank.model.TransactionType;

@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionType, Integer>{

}
