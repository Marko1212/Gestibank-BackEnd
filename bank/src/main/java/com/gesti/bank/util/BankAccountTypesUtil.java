package com.gesti.bank.util;

import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

public class BankAccountTypesUtil {
	public static final String CURRENT_BANK_ACCOUNT_TYPE_NAME = "Current";
	public static final String CURRENT_LIMIT_1000_BANK_ACCOUNT_TYPE_NAME = "Current-LIMIT-1000";
	public static final String CURRENT_LIMIT_2000_BANK_ACCOUNT_TYPE_NAME = "Current-LIMIT-2000";
	public static final String CURRENT_LIMIT_3000_BANK_ACCOUNT_TYPE_NAME = "Current-LIMIT-3000";
	public static final String CURRENT_LIMIT_5000_BANK_ACCOUNT_TYPE_NAME = "Current-LIMIT-5000";
	public static final String CURRENT_LIMIT_10000_BANK_ACCOUNT_TYPE_NAME = "Current-LIMIT-10000";
	public static final String CURRENT_LIMIT_20000_BANK_ACCOUNT_TYPE_NAME = "Current-LIMIT-20000";
	public static final String CURRENT_LIMIT_50000_BANK_ACCOUNT_TYPE_NAME = "Current-LIMIT-50000";
	public static final String SAVING_BANK_ACCOUNT_TYPE_NAME = "Saving";
	
	private static final Integer CURRENT_BANK_ACCOUNT_TYPE_AMOUNT = 0;
	private static final Integer CURRENT_LIMIT_1000_BANK_ACCOUNT_TYPE_AMOUNT = 1000;
	private static final Integer CURRENT_LIMIT_2000_BANK_ACCOUNT_TYPE_AMOUNT = 2000;
	private static final Integer CURRENT_LIMIT_3000_BANK_ACCOUNT_TYPE_AMOUNT = 3000;
	private static final Integer CURRENT_LIMIT_5000_BANK_ACCOUNT_TYPE_AMOUNT = 5000;
	private static final Integer CURRENT_LIMIT_10000_BANK_ACCOUNT_TYPE_AMOUNT = 10000;
	private static final Integer CURRENT_LIMIT_20000_BANK_ACCOUNT_TYPE_AMOUNT = 20000;
	private static final Integer CURRENT_LIMIT_50000_BANK_ACCOUNT_TYPE_AMOUNT = 50000;
	private static final Integer SAVING_BANK_ACCOUNT_TYPE_AMOUNT = 0;
	
	private static Map<String, Integer> getBankAccountTypes(){
		Map<String, Integer> bankAccountTypesMap = new HashedMap();
		bankAccountTypesMap.put(CURRENT_BANK_ACCOUNT_TYPE_NAME, CURRENT_BANK_ACCOUNT_TYPE_AMOUNT);
		bankAccountTypesMap.put(CURRENT_LIMIT_1000_BANK_ACCOUNT_TYPE_NAME, CURRENT_LIMIT_1000_BANK_ACCOUNT_TYPE_AMOUNT);
		bankAccountTypesMap.put(CURRENT_LIMIT_2000_BANK_ACCOUNT_TYPE_NAME, CURRENT_LIMIT_2000_BANK_ACCOUNT_TYPE_AMOUNT);
		bankAccountTypesMap.put(CURRENT_LIMIT_3000_BANK_ACCOUNT_TYPE_NAME, CURRENT_LIMIT_3000_BANK_ACCOUNT_TYPE_AMOUNT);
		bankAccountTypesMap.put(CURRENT_LIMIT_5000_BANK_ACCOUNT_TYPE_NAME, CURRENT_LIMIT_5000_BANK_ACCOUNT_TYPE_AMOUNT);
		bankAccountTypesMap.put(CURRENT_LIMIT_10000_BANK_ACCOUNT_TYPE_NAME, CURRENT_LIMIT_10000_BANK_ACCOUNT_TYPE_AMOUNT);
		bankAccountTypesMap.put(CURRENT_LIMIT_20000_BANK_ACCOUNT_TYPE_NAME, CURRENT_LIMIT_20000_BANK_ACCOUNT_TYPE_AMOUNT);
		bankAccountTypesMap.put(CURRENT_LIMIT_50000_BANK_ACCOUNT_TYPE_NAME, CURRENT_LIMIT_50000_BANK_ACCOUNT_TYPE_AMOUNT);
		bankAccountTypesMap.put(SAVING_BANK_ACCOUNT_TYPE_NAME, SAVING_BANK_ACCOUNT_TYPE_AMOUNT);
		return bankAccountTypesMap;
	}
	
	public static Integer getMinimumAmountForBankAccountType(String bankAccountType) {
		return getBankAccountTypes().get(bankAccountType);
	}
}
