package com.gesti.bank.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gesti.bank.model.BankAccount;
import com.gesti.bank.model.Request;
import com.gesti.bank.model.Role;
import com.gesti.bank.model.Transaction;
import com.gesti.bank.model.UserAccount;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
	
	@Query("SELECT tr FROM Transaction AS tr WHERE tr.bankAccountFrom = :bankAccountFrom OR tr.bankAccountTo = :bankAccountTo")
	List<Transaction> findByBankAccountFromOrBankAccountTo(@Param("bankAccountFrom") BankAccount bankAccountFrom, @Param("bankAccountTo") BankAccount bankAccountTo);

}