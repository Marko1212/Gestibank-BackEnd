package com.gesti.bank.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gesti.bank.dto.BankAccountResponseDTO;
import com.gesti.bank.dto.BankAccountTypeResponseDTO;
import com.gesti.bank.dto.BankRuleResponseDTO;
import com.gesti.bank.dto.CreateCustomRequestForAgentDTO;
import com.gesti.bank.dto.GetAccountResponseDTO;
import com.gesti.bank.dto.ModifyBankAccountRequestDTO;
import com.gesti.bank.dto.SimpleMessageResponseDTO;
import com.gesti.bank.service.BankAccountService;
import com.gesti.bank.util.RequestTitlesUtil;

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
	
	
	@DeleteMapping("/deactivateBankAccount/{id}/{userID}")
	public ResponseEntity<?> deactivateBankAccount(@PathVariable int id, @PathVariable int userID) {
		String response;
		try {
			response = bankAccountService.deactivateBankAccount(id, userID);
			return new ResponseEntity<String>(response, HttpStatus.OK);
		} catch (Exception e) {
			response = e.getMessage();
			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/createChequeBookForBankAccount/{id}/{userID}")
	public ResponseEntity<?> createChequeBookForBankAccount(@PathVariable int id, @PathVariable int userID) {
		String response = null;
		try {
			response = bankAccountService.createChequeBookForBankAccount(id, userID);
			return new ResponseEntity<SimpleMessageResponseDTO>(new SimpleMessageResponseDTO(response), HttpStatus.OK);
		} catch (Exception e) {
			response = e.getMessage();
			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	/*
	 * @Param - bankAccountFlag - 0 (current), 1 (saving), 2 (cheque)
	 */
	@GetMapping("/getBankAccountTypes")
	public ResponseEntity<?> getBankAccountTypes(@RequestParam(value="bankAccountFlag", defaultValue = "0") String bankAccountFlag) {
		List<BankAccountTypeResponseDTO> response = new ArrayList<BankAccountTypeResponseDTO>();
		try {
			response = bankAccountService.getBankAccountTypes(Integer.parseInt(bankAccountFlag));
			return new ResponseEntity<List<BankAccountTypeResponseDTO>>(response, HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/*
	 * @Param - bankAccountFlag - 0 (current), 1 (saving), 2 (cheque)
	 */
	@GetMapping("/getBankRules")
	public ResponseEntity<?> getBankRules(@RequestParam(value="bankAccountFlag", defaultValue = "0") String bankAccountFlag) {
		List<BankRuleResponseDTO> response = new ArrayList<BankRuleResponseDTO>();
		try {
			response = bankAccountService.getBankRules(Integer.parseInt(bankAccountFlag));
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
	
	@PostMapping("/createCustomRequestForAgent")
	public ResponseEntity<?> createCustomRequestForAgent(@RequestBody CreateCustomRequestForAgentDTO request){
		String response = null;
		try {
			response=bankAccountService.createCustomRequestForAgent(request);
			return new ResponseEntity<SimpleMessageResponseDTO>(new SimpleMessageResponseDTO(response), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	@GetMapping("/requestTitlesForClients")
	public ResponseEntity<List<String>> requestTitlesForClients(){
		return new ResponseEntity<List<String>>(RequestTitlesUtil.requestTitlesForClients, HttpStatus.OK);
	}
		
}
