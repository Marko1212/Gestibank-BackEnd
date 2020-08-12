package com.gesti.bank.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.joda.time.DateTime;
import org.joda.time.Days;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gesti.bank.dto.TransactionRequestDTO;
import com.gesti.bank.dto.TransactionResponseDTO;
import com.gesti.bank.dto.TransactionTypeResponseDTO;
import com.gesti.bank.model.BankAccount;
import com.gesti.bank.model.Notification;
import com.gesti.bank.model.Transaction;
import com.gesti.bank.model.TransactionType;
import com.gesti.bank.model.UserAccount;
import com.gesti.bank.repository.BankAccountRepository;
import com.gesti.bank.repository.NotificationRepository;
import com.gesti.bank.repository.TransactionRepository;
import com.gesti.bank.repository.TransactionTypeRepository;
import com.gesti.bank.repository.UserAccountRepository;
import com.gesti.bank.service.TransactionService;
import com.gesti.bank.util.BankAccountTypesUtil;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	TransactionTypeRepository transactionTypeRepository;

	@Autowired
	BankAccountRepository bankAccountRepository;

	@Autowired
	UserAccountRepository userAccountRepository;

	@Autowired
	NotificationRepository notificationRepository;

	private static final int TRANSACTION_TYPE_CREDIT_ID = 1;
	private static final int TRANSACTION_TYPE_BANKOMAT_ID = 2;
	private static final int TRANSACTION_TYPE_TRANSFER_ID = 3;

	@Override
	public List<TransactionTypeResponseDTO> getTransactionTypes() throws Exception {
		List<TransactionType> listTransactionTypes = transactionTypeRepository.findAll();
		List<TransactionTypeResponseDTO> response = new ArrayList<TransactionTypeResponseDTO>();
		for (TransactionType transactionType : listTransactionTypes) {
			TransactionTypeResponseDTO tempObj = new TransactionTypeResponseDTO(transactionType.getIdTransactionType(),
					transactionType.getName());
			response.add(tempObj);
		}
		return response;
	}

	@Override
	@Transactional
	public String makeTransaction(TransactionRequestDTO request) throws Exception {
		String response = "Something went wrong!";
		if (request.getTransactionTypeId() == TRANSACTION_TYPE_CREDIT_ID) {
			response = makeTransactionTypeCredit(request);
		} else if (request.getTransactionTypeId() == TRANSACTION_TYPE_BANKOMAT_ID) {
			response = makeTransactionTypeBankomat(request);
		} else if (request.getTransactionTypeId() == TRANSACTION_TYPE_TRANSFER_ID) {
			response = makeTransactionTypeTransfer(request);
		} else {
			throw new Exception("You must provide valid transaction type!");
		}
		return response;
	}

	private String makeTransactionTypeBankomat(TransactionRequestDTO request) throws Exception {
		if (request.getAmount() <= 0) {
			throw new Exception("You must provide amount!");
		}
		if (request.getLoggedInUserId() < 1) {
			throw new Exception("You must be logged in!");
		}
		Optional<UserAccount> withdrawUserAccountOpt = userAccountRepository.findById(request.getLoggedInUserId());
		if (!withdrawUserAccountOpt.isPresent()) {
			throw new Exception("User account with provided ID does not exist!");
		}
		UserAccount withdrawUserAccount = withdrawUserAccountOpt.get();
		if (request.getBankAccountFromId() < 1) {
			throw new Exception("You must provide withdrawal bank account!");
		}
		Optional<BankAccount> bankAccountFromOpt = bankAccountRepository.findById(request.getBankAccountFromId());
		if (!bankAccountFromOpt.isPresent()) {
			throw new Exception("Provided sender's bank account doesn't exist");
		}
		BankAccount bankAccountFrom = bankAccountFromOpt.get();
		if (bankAccountFrom.getBankAccountStatus() == (byte) 0) {
			throw new Exception("Sender's bank account is not active!");
		}
		if (bankAccountFrom.getUserAccount().getIdUserAccount() != withdrawUserAccount.getIdUserAccount()) {
			throw new Exception("Illegal sender's bank account provided!");
		}
		Optional<TransactionType> transactionTypeOpt = transactionTypeRepository
				.findById(request.getTransactionTypeId());
		if (!transactionTypeOpt.isPresent()) {
			throw new Exception("You provided invalid transaction type!");
		}
		TransactionType transactionType = transactionTypeOpt.get();
		// handling amount limits start
		Double balanceForAccount = Double.parseDouble(getBalanceForBankAccountId(bankAccountFrom.getIdBankAccount()));
		float amount = request.getAmount();
		float newBalance = balanceForAccount.floatValue() - amount;
		if (newBalance < 0) {
			Integer limitForBankAccountType = (-1) * BankAccountTypesUtil
					.getMinimumAmountForBankAccountType(bankAccountFrom.getBankAccountType().getName());
			if (newBalance < limitForBankAccountType) {
				throw new Exception("You don't have enough money");
			}
		}
		// handling amount limits end
		Transaction bankomatTransaction = new Transaction();
		bankomatTransaction.setAmount(amount);
		bankomatTransaction.setBankAccountFrom(bankAccountFrom);
		bankomatTransaction.setDescription(request.getDescription());
		bankomatTransaction.setTime(new Date());
		bankomatTransaction.setTransactionType(transactionType);
		transactionRepository.save(bankomatTransaction);

		return "Success";
	}

	private String makeTransactionTypeTransfer(TransactionRequestDTO request) throws Exception {
		if (request.getAmount() <= 0) {
			throw new Exception("You must provide amount!");
		}
		if (request.getLoggedInUserId() < 1) {
			throw new Exception("You must be logged in!");
		}
		Optional<UserAccount> sendersUserAccountOpt = userAccountRepository.findById(request.getLoggedInUserId());
		if (!sendersUserAccountOpt.isPresent()) {
			throw new Exception("User account with provided ID does not exist!");
		}
		UserAccount sendersUserAccount = sendersUserAccountOpt.get();
		if (request.getBankAccountFromId() < 1) {
			throw new Exception("You must provide sender's bank account!");
		}
		Optional<BankAccount> bankAccountFromOpt = bankAccountRepository.findById(request.getBankAccountFromId());
		if (!bankAccountFromOpt.isPresent()) {
			throw new Exception("Provided sender's bank account does not exist!");
		}
		BankAccount bankAccountFrom = bankAccountFromOpt.get();
		if (bankAccountFrom.getBankAccountStatus() == (byte) 0) {
			throw new Exception("Sender's bank account is not active!");
		}
		if (bankAccountFrom.getUserAccount().getIdUserAccount() != sendersUserAccount.getIdUserAccount()) {
			throw new Exception("Illegal sender's bank account provided!");
		}
		if (request.getBankAccountNumberTo() == null || request.getBankAccountNumberTo().isEmpty()) {
			throw new Exception("Please enter number of recipient bank account!");
		}
		Optional<BankAccount> bankAccountToOpt = bankAccountRepository
				.findByBankAccountNumber(request.getBankAccountNumberTo());
		if (!bankAccountToOpt.isPresent()) {
			throw new Exception("Receiver's bank account does not exist in our database!");
		}
		BankAccount bankAccountTo = bankAccountToOpt.get();
		if (bankAccountTo.getBankAccountStatus() == (byte) 0) {
			throw new Exception("Bank account with provided ID is not active!");
		}
		Optional<TransactionType> transactionTypeOpt = transactionTypeRepository
				.findById(request.getTransactionTypeId());
		if (!transactionTypeOpt.isPresent()) {
			throw new Exception("You provided invalid transaction type!");
		}
		TransactionType transactionType = transactionTypeOpt.get();
		// handling amount limits start
		Double balanceForAccount = Double.parseDouble(getBalanceForBankAccountId(bankAccountFrom.getIdBankAccount()));
		float amount = request.getAmount();
		float newBalance = balanceForAccount.floatValue() - amount;
		if (newBalance < 0) {
			Integer limitForBankAccountType = (-1) * BankAccountTypesUtil
					.getMinimumAmountForBankAccountType(bankAccountFrom.getBankAccountType().getName());
			if (newBalance < limitForBankAccountType && bankAccountTo.getIdBankAccount() != bankAccountFrom.getIdBankAccount()) {
				throw new Exception("You do not have enough money!");
			}
		}
		// handling amount limits end
		Transaction transferTransaction = new Transaction();
		transferTransaction.setAmount(amount);
		transferTransaction.setBankAccountFrom(bankAccountFrom);
		transferTransaction.setBankAccountTo(bankAccountTo);
		transferTransaction.setDescription(request.getDescription());
		transferTransaction.setTime(new Date());
		transferTransaction.setTransactionType(transactionType);
		transactionRepository.saveAndFlush(transferTransaction);
		// handling notifications start
		if (!bankAccountFrom.equals(bankAccountTo)) {
			DecimalFormat df = new DecimalFormat("0.00");
			Notification notifyReceiver = new Notification();
			notifyReceiver.setUserAccount(bankAccountTo.getUserAccount());
			String mainText = String.format("Hi there, you have received transfer of %s€ from %s.", df.format(amount),
					bankAccountFrom.getUserAccount().getFirstname() + " "
							+ bankAccountFrom.getUserAccount().getLastname());
			String optionalText = request.getDescription().isBlank() ? ""
					: String.format(" Description : %s.", request.getDescription());
			notifyReceiver.setMessage(mainText + optionalText);
			notifyReceiver.setTransaction(transferTransaction);
			notificationRepository.save(notifyReceiver);
		}
		// handling notifications end
		return "Success";
	}

	private String makeTransactionTypeCredit(TransactionRequestDTO request) throws Exception {
		if (request.getBankAccountToId() < 1) {
			throw new Exception("You must provide informations of receiver's bank account!");
		}
		if (request.getAmount() <= 0) {
			throw new Exception("You must provide amount!");
		}
		Optional<BankAccount> bankAccountToOpt = bankAccountRepository.findById(request.getBankAccountToId());
		if (!bankAccountToOpt.isPresent()) {
			throw new Exception("You provided invalid informations for receiver's bank account!");
		}
		BankAccount bankAccountTo = bankAccountToOpt.get();
		if (bankAccountTo.getBankAccountStatus() == (byte) 0) {
			throw new Exception("Bank account with provided ID is not active!");
		}
		Optional<TransactionType> transactionTypeOpt = transactionTypeRepository
				.findById(request.getTransactionTypeId());
		if (!transactionTypeOpt.isPresent()) {
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

	@Override
	public List<TransactionResponseDTO> getTransactions(int idBankAccount) throws Exception {
		Optional<BankAccount> bankAccountOpt = bankAccountRepository.findById(idBankAccount);
		if (!bankAccountOpt.isPresent()) {
			throw new Exception("You provided invalid informations for receiver's bank account!");
		}
		BankAccount bankAccount = bankAccountOpt.get();
		if (bankAccount.getBankAccountStatus() == (byte) 0) {
			throw new Exception("Bank account with provided ID is not active!");
		}
		List<Transaction> listTransactions = transactionRepository.findByBankAccountFromOrBankAccountTo(bankAccount,
				bankAccount);

		//listTransactions.removeIf(transaction -> Days.daysBetween(new DateTime(transaction.getTime()), new DateTime(new Date())).getDays() > 30);
		List<TransactionResponseDTO> response = new ArrayList<TransactionResponseDTO>();
		for (Transaction transaction : listTransactions) {
			TransactionResponseDTO tempObj = new TransactionResponseDTO(
					(transaction.getBankAccountFrom() == null) ? 0
							: transaction.getBankAccountFrom().getIdBankAccount(),
					(transaction.getBankAccountTo() == null) ? 0 : transaction.getBankAccountTo().getIdBankAccount(),
					transaction.getTransactionType().getName(), transaction.getDescription(), transaction.getAmount(),
					transaction.getTime(), transaction.getIdTransaction());
			response.add(tempObj);
		}
		return response;
	}

	@Override
	public String getBalanceForBankAccountId(int bankAccountId) {
		try {
			double balance = transactionRepository.getBalanceForBankAccountId(bankAccountId);
			DecimalFormat df = new DecimalFormat("0.00");
			return df.format(balance);
		} catch (Exception e) {
			e.printStackTrace();
			return "0.00";
		}
	}

}
