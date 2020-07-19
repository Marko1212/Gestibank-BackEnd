package com.gesti.bank.service;

import java.util.List;

import com.gesti.bank.dto.BankAccountResponseDTO;
import com.gesti.bank.dto.BankAccountTypeResponseDTO;
import com.gesti.bank.dto.BankRuleResponseDTO;
import com.gesti.bank.dto.GetAccountResponseDTO;
import com.gesti.bank.dto.ModifyBankAccountRequestDTO;
import com.gesti.bank.model.UserAccount;

public interface BankAccountService {

	void createInitialBankAccount(UserAccount client) throws Exception ;

	List<BankAccountResponseDTO> getBankAccounts(int id) throws Exception;

	BankAccountResponseDTO getBankAccount(int id, int userID) throws Exception;

	List<BankAccountTypeResponseDTO> getBankAccountTypes() throws Exception;

	List<BankRuleResponseDTO> getBankRules() throws Exception;

	String modifyBankAccount(ModifyBankAccountRequestDTO request, int userID) throws Exception;

}
