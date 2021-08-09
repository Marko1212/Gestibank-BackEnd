package com.gesti.bank;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.gesti.bank.dto.CreateAgentRequestDTO;
import com.gesti.bank.service.UserAccountService;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
class BankApplicationTests {
	
	@Autowired
	private UserAccountService userAccountService;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void testAgentCreation() throws Exception {	
		String email = generateRandomValueForTest(6, true, false) + "@mailinator.com";
		String firstname = generateRandomValueForTest(6, true, false);
		String lastname = generateRandomValueForTest(6, true, false);
		String pass = generateRandomValueForTest(6, true, false);
		String phone = generateRandomValueForTest(10, false, true);
		String username = generateRandomValueForTest(6, true, false);
		String additionalInfoAddress = generateRandomValueForTest(6, true, false);
		String city = generateRandomValueForTest(6, true, false);
		String country = generateRandomValueForTest(6, true, false);
		String homeNumber = generateRandomValueForTest(3, true, true);
		String street = generateRandomValueForTest(6, true, false);
		int zip = Integer.parseInt(generateRandomValueForTest(6, false, true));
		CreateAgentRequestDTO request = new CreateAgentRequestDTO(email, firstname, lastname, pass, phone, username, additionalInfoAddress, city, country, homeNumber, street,zip);	
		userAccountService.createAgent(request);
	}
	
	public String generateRandomValueForTest(int length, boolean useLetters, boolean useNumbers) {
		return RandomStringUtils.random(length, useLetters, useNumbers);
	}
}
