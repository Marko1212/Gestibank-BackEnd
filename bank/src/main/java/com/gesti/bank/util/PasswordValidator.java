package com.gesti.bank.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {

	private Pattern pattern;
	private Matcher matcher;

	private static final String PASSWORD_PATTERN = "((?=.*[A-Z])(?=.*[@#$%]).{6,8})";   
	//special characters: @#$% (zapravo specijalan karakter je bilo koji karakter koji je razlicit od cifre 0-9, od slova ili od space

	public PasswordValidator() {
		pattern = Pattern.compile(PASSWORD_PATTERN);
	}

//		Password policy:
//		1) Password mora da bude duzine izmedju 6 i 8 karaktera - ok
//		2) Password mora da sadrzi bar jedno veliko slovo - ok
//		3) Password mora da sadrzi bar dve cifre - ok
//		4) Password mora da sadrzi bar jedan specijalni karakter - ok
//		5) Password ne sme da sadrzi username - ok

	public boolean validate(final String username, final String password) {
		if (password.indexOf(username) > -1) {
			System.out.println("Password should not contain user name");
			return false;
		}
		/* Declare an int variable to hold the count of each digit */
	    int digit = 0; 
		//char element;
	    /* Check if the password has 2 or more digits */
	   // for(int index = 0; index < password.length(); index++ ){

	        /* Check each char in the String */
	     //   element = password.charAt( index );
	        
	        for(char element:password.toCharArray()) {

	        /* Check if it is a digit or not */
	        if( Character.isDigit(element) ){
	            /* It is a digit, so increment digit */
	            digit++;
	        } // End if block

	    } // End for loop 

	    /* Now check for the count of digits in the password */
	    if( digit < 2 ){
	        /* There are fewer than 2 digits in the password, return false */
	        return false;
	    }

		matcher = pattern.matcher(password);
		return matcher.matches();

	}
	
}