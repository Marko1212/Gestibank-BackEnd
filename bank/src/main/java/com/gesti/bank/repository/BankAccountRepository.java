package com.gesti.bank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gesti.bank.model.BankAccount;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer>{
	List<BankAccount> findAllByBankAccountNumber(String bankAccountNumber);
	Optional<BankAccount> findByBankAccountNumber(String bankAccountNumber);
}
