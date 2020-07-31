package com.gesti.bank.service;

import java.util.List;

import com.gesti.bank.dto.TransactionRequestDTO;
import com.gesti.bank.dto.TransactionResponseDTO;
import com.gesti.bank.dto.TransactionTypeResponseDTO;

public interface TransactionService {

	List<TransactionTypeResponseDTO> getTransactionTypes() throws Exception;

	String makeTransaction(TransactionRequestDTO request) throws Exception;

	List<TransactionResponseDTO> getTransactions(int idBankAccount) throws Exception;

	String getBalanceForBankAccountId(int bankAccountId);

}
