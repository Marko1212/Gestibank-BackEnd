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

	
	@Query(value = "select\r\n" + 
			"(select COALESCE(sum(t.amount),0) as prilivi from transaction as t where t.bank_account_id_bank_account_to = :bankAccount) -\r\n" + 
			"(select COALESCE(sum(t.amount),0) as odlivi from transaction as t where t.bank_account_id_bank_account_from = :bankAccount) as stanje_na_racunu", nativeQuery = true)
	Double getBalanceForBankAccountId(@Param("bankAccount") int bankAccount);
	
	@Query(value = "select\r\n" + 
			"(select COALESCE(sum(t.amount),0) as prilivi from transaction as t where t.bank_account_id_bank_account_to = ?1) -\r\n" + 
			"(select COALESCE(sum(t.amount),0) as odlivi from transaction as t where t.bank_account_id_bank_account_from = ?1) as stanje_na_racunu", nativeQuery = true)
	Double getBalanceForBankAccountIdTwo(int bankAccount);
}