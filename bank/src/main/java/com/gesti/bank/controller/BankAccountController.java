package com.gesti.bank.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gesti.bank.dto.BankAccountResponseDTO;
import com.gesti.bank.service.BankAccountService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/bankAccount")
public class BankAccountController {
	
	@Autowired
	BankAccountService bankAccountService;

	@GetMapping("/getBankAccounts/{id}")
	public ResponseEntity<?> getBankAccounts(@PathVariable int id) {
		List<BankAccountResponseDTO> response = new ArrayList<BankAccountResponseDTO>();
		try {
			response = bankAccountService.getBankAccounts(id);
			return new ResponseEntity<List<BankAccountResponseDTO>>(response, HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
