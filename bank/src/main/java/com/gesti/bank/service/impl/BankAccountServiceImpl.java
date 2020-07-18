package com.gesti.bank.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gesti.bank.dto.BankAccountResponseDTO;
import com.gesti.bank.model.BankAccount;
import com.gesti.bank.model.BankAccountType;
import com.gesti.bank.model.BankRule;
import com.gesti.bank.model.Request;
import com.gesti.bank.model.UserAccount;
import com.gesti.bank.repository.BankAccountRepository;
import com.gesti.bank.repository.BankAccountTypeRepository;
import com.gesti.bank.repository.BankRuleRepository;
import com.gesti.bank.repository.RequestRepository;
import com.gesti.bank.repository.UserAccountRepository;
import com.gesti.bank.service.BankAccountService;

@Service
public class BankAccountServiceImpl implements BankAccountService{
	
	private final static int INITIAL_BANK_ACCOUNT_ID = 1;
	private final static int INITIAL_RULE_ID = 1;
	
	private final static String ROLE_ADMIN = "admin";
	private final static String ROLE_CLIENT = "client";
	private final static String ROLE_AGENT = "agent";

	@Autowired
	BankAccountRepository bankAccountRepository;
	
	@Autowired
	BankAccountTypeRepository bankAccountTypeRepository;
	
	@Autowired
	BankRuleRepository bankRuleRepository;
	
	@Autowired
	UserAccountRepository userAccountRepository;
	
	@Autowired
	RequestRepository requestRepository;
	
	@Override
	public void createInitialBankAccount(UserAccount client) throws Exception {
		// TODO Auto-generated method stub
		BankAccount bankAccount = new BankAccount();		
		bankAccount.setBankAccountNumber(generateBankAccountNumber());
		Optional<BankAccountType> initialTypeOpt = bankAccountTypeRepository.findById(INITIAL_BANK_ACCOUNT_ID);
		if(!initialTypeOpt.isPresent()) {
			throw new Exception("Bank account type with provided id does not exist");
		}
		BankAccountType initialType = initialTypeOpt.get();
		bankAccount.setBankAccountStatus((byte) 1);
		bankAccount.setBankAccountType(initialType);
		bankAccount.setUserAccount(client);
		Optional<BankRule> initialBankRuleOpt = bankRuleRepository.findById(INITIAL_RULE_ID);
		if(!initialBankRuleOpt.isPresent()) {
			throw new Exception("Bank rule with provided id does not exist");
		}
		BankRule initialBankRule = initialBankRuleOpt.get();
		bankAccount.setBankRule(initialBankRule);
		bankAccountRepository.save(bankAccount);
	}
	
	private String generateBankAccountNumber() {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for(int i=0; i<10; i++) {
			sb.append(random.nextInt(10));
		}
		String generatedBankAccountNumber = sb.toString();
		List<BankAccount> checkNumberList = bankAccountRepository.findAllByBankAccountNumber(generatedBankAccountNumber);
		if(checkNumberList!=null && !checkNumberList.isEmpty()) {
			return generateBankAccountNumber();
		}
		return generatedBankAccountNumber;
	}

	@Override
	public List<BankAccountResponseDTO> getBankAccounts(int id) throws Exception {
		Optional<UserAccount> loggedInUserOpt = userAccountRepository.findById(id);
		if (!loggedInUserOpt.isPresent()) {
			throw new Exception("User with provided ID does not exist!");
		}
		UserAccount loggedInUser = loggedInUserOpt.get();
		if (loggedInUser.getValid() == 0) {
			throw new Exception("User with provided credentials is not valid!");
		}
		List<BankAccountResponseDTO> response = new ArrayList<BankAccountResponseDTO>();
		if (loggedInUser.getRole().getName().equals(ROLE_AGENT)) {
			List<Request> requests = requestRepository.findAllByUserAccountToAndRequestStatus(loggedInUser, (byte) 1);
			for(Request r:requests) {
				UserAccount client = r.getUserAccountFrom();
				for(BankAccount bankAcc:client.getBankAccounts()) {
					BankAccountResponseDTO tmpObj = new BankAccountResponseDTO(bankAcc.getIdBankAccount(), bankAcc.getBankAccountNumber(), bankAcc.getBankAccountType().getIdBankAccountType(), bankAcc.getBankAccountType().getName(), bankAcc.getUserAccount().getIdUserAccount(), bankAcc.getUserAccount().getFirstname() + " " + bankAcc.getUserAccount().getLastname(), bankAcc.getBankRule().getIdBankRules(), bankAcc.getBankRule().getPercent(), bankAcc.getBankRule().getRuleName());
					response.add(tmpObj);
				}
			}
		}else if (loggedInUser.getRole().getName().equals(ROLE_CLIENT)) {
			for(BankAccount bankAcc:loggedInUser.getBankAccounts()) {
				BankAccountResponseDTO tmpObj = new BankAccountResponseDTO(bankAcc.getIdBankAccount(), bankAcc.getBankAccountNumber(), bankAcc.getBankAccountType().getIdBankAccountType(), bankAcc.getBankAccountType().getName(), bankAcc.getUserAccount().getIdUserAccount(), bankAcc.getUserAccount().getFirstname() + " " + bankAcc.getUserAccount().getLastname(), bankAcc.getBankRule().getIdBankRules(), bankAcc.getBankRule().getPercent(), bankAcc.getBankRule().getRuleName());
				response.add(tmpObj);
			}
		}else {
			throw new Exception("You don't have a permission for this action!");
		}
		if(response.isEmpty()) {
			throw new Exception("There is no bank account");
		}
		return response;
	}

}
