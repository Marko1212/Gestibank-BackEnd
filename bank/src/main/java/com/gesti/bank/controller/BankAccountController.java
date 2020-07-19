package com.gesti.bank.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gesti.bank.dto.BankAccountResponseDTO;
import com.gesti.bank.dto.BankAccountTypeResponseDTO;
import com.gesti.bank.dto.BankRuleResponseDTO;
import com.gesti.bank.dto.GetAccountResponseDTO;
import com.gesti.bank.dto.ModifyBankAccountRequestDTO;
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
	
	@GetMapping("/getBankAccount/{id}/{userID}")
	public ResponseEntity<?> getAccount(@PathVariable int id, @PathVariable int userID) {
		BankAccountResponseDTO response = null;
		try {
			response = bankAccountService.getBankAccount(id, userID);
			return new ResponseEntity<BankAccountResponseDTO>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getBankAccountTypes")
	public ResponseEntity<?> getBankAccountTypes() {
		List<BankAccountTypeResponseDTO> response = new ArrayList<BankAccountTypeResponseDTO>();
		try {
			response = bankAccountService.getBankAccountTypes();
			return new ResponseEntity<List<BankAccountTypeResponseDTO>>(response, HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getBankRules")
	public ResponseEntity<?> getBankRules() {
		List<BankRuleResponseDTO> response = new ArrayList<BankRuleResponseDTO>();
		try {
			response = bankAccountService.getBankRules();
			return new ResponseEntity<List<BankRuleResponseDTO>>(response, HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/modifyBankAccount/{userID}")
	public ResponseEntity<String> modifyBankAccount(@RequestBody ModifyBankAccountRequestDTO request, @PathVariable int userID) {
		String response = null;
		try {
			response = bankAccountService.modifyBankAccount(request, userID);
			return new ResponseEntity<String>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
		
}
