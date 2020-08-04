package com.gesti.bank.util;

import java.util.Arrays;
import java.util.List;

public class RequestTitlesUtil {

	public static final String CREATE_SAVING_ACCOUNT = "CREATE_SAVING_ACCOUNT";
	public static final String CREATE_ACCOUNT = "CREATE_ACCOUNT";
	public static final String CHANGE_RULE_FOR_CURRENT_ACCOUNT = "CHANGE_RULE_FOR_CURRENT_ACCOUNT";
	public static final String CHANGE_TYPE_FOR_CURRENT_ACCOUNT = "CHANGE_TYPE_FOR_CURRENT_ACCOUNT";
	
	public static List<String> requestTitlesForClients = Arrays.asList(CREATE_SAVING_ACCOUNT,CHANGE_RULE_FOR_CURRENT_ACCOUNT,CHANGE_TYPE_FOR_CURRENT_ACCOUNT);
}
