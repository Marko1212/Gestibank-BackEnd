package com.gesti.bank.service;

import java.util.List;

import com.gesti.bank.dto.BankAccountResponseDTO;
import com.gesti.bank.dto.GetAccountResponseDTO;
import com.gesti.bank.model.UserAccount;

public interface BankAccountService {

	void createInitialBankAccount(UserAccount client) throws Exception ;

	List<BankAccountResponseDTO> getBankAccounts(int id) throws Exception;

	BankAccountResponseDTO getBankAccount(int id) throws Exception;

}
