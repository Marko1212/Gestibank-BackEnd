package com.gesti.bank.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gesti.bank.dto.TransactionRequestDTO;
import com.gesti.bank.dto.TransactionTypeResponseDTO;
import com.gesti.bank.model.BankAccount;
import com.gesti.bank.model.Transaction;
import com.gesti.bank.model.TransactionType;
import com.gesti.bank.repository.BankAccountRepository;
import com.gesti.bank.repository.TransactionRepository;
import com.gesti.bank.repository.TransactionTypeRepository;
import com.gesti.bank.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	TransactionTypeRepository transactionTypeRepository;	
	
	@Autowired
	BankAccountRepository bankAccountRepository;
	
	private static final int TRANSACTION_TYPE_CREDIT_ID = 1;
	private static final int TRANSACTION_TYPE_BANKOMAT_ID = 2;
	private static final int TRANSACTION_TYPE_TRANSFER_ID = 3;

	@Override
	public List<TransactionTypeResponseDTO> getTransactionTypes() throws Exception {
		List<TransactionType> listTransactionTypes = transactionTypeRepository.findAll();
		List<TransactionTypeResponseDTO> response = new ArrayList<TransactionTypeResponseDTO>();
		for (TransactionType transactionType:listTransactionTypes) {
			TransactionTypeResponseDTO tempObj = new TransactionTypeResponseDTO(transactionType.getIdTransactionType(), transactionType.getName());
			response.add(tempObj);
		}
		return response;
	}


	@Override
	@Transactional
	public String makeTransaction(TransactionRequestDTO request) throws Exception{
		String response = "Something went wrong!";
		if(request.getTransactionTypeId() == TRANSACTION_TYPE_CREDIT_ID) {
			response = makeTransactionTypeCredit(request);
		}else if(request.getTransactionTypeId() == TRANSACTION_TYPE_BANKOMAT_ID){
			//TODO
			throw new Exception("Missing implementation");
		}else if(request.getTransactionTypeId() == TRANSACTION_TYPE_TRANSFER_ID){
			//TODO
			throw new Exception("Missing implementation");
		}else {
			throw new Exception("You must provide valid transaction type!");
		}
		return response;
	}


	private String makeTransactionTypeCredit(TransactionRequestDTO request) throws Exception {
		if(request.getBankAccountToId()<1) {
			throw new Exception("You must provide informations of receiver's bank account!");
		}
		if(request.getAmount()<=0) {
			throw new Exception("You must provide amount!");
		}
		Optional<BankAccount> bankAccountToOpt = bankAccountRepository.findById(request.getBankAccountToId());
		if(!bankAccountToOpt.isPresent()) {
			throw new Exception("You provided invalid informations for receiver's bank account!");
		}
		BankAccount bankAccountTo = bankAccountToOpt.get();
		Optional<TransactionType> transactionTypeOpt = transactionTypeRepository.findById(request.getTransactionTypeId());
		if(!transactionTypeOpt.isPresent()) {
			throw new Exception("You provided invalid transaction type!");
		}
		TransactionType transactionType = transactionTypeOpt.get();
		Transaction creditTypeTransaction = new Transaction();
		creditTypeTransaction.setAmount(request.getAmount());
		creditTypeTransaction.setBankAccountTo(bankAccountTo);
		creditTypeTransaction.setTime(new Date());
		creditTypeTransaction.setDescription(request.getDescription());
		creditTypeTransaction.setTransactionType(transactionType);
		transactionRepository.save(creditTypeTransaction);
		return "Success";
	}
	
	

}
