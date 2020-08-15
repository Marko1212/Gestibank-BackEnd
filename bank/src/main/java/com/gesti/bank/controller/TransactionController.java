package com.gesti.bank.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.gesti.bank.dto.BankAccountResponseDTO;
import com.gesti.bank.dto.TransactionRequestDTO;
import com.gesti.bank.dto.TransactionResponseDTO;
import com.gesti.bank.dto.TransactionTypeResponseDTO;
import com.gesti.bank.service.TransactionService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	TransactionService transactionService;

	@GetMapping("/getTransactionTypes")
	public ResponseEntity<?> getTransactionTypes() {
		List<TransactionTypeResponseDTO> response = null;
		try {
			response = transactionService.getTransactionTypes();
			return new ResponseEntity<List<TransactionTypeResponseDTO>>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/makeTransaction")
	public ResponseEntity<String> makeTransaction(@RequestBody TransactionRequestDTO request) {
		String response = null;
		try {
			response = transactionService.makeTransaction(request);
			return new ResponseEntity<String>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getTransactions/{idBankAccount}")
	public ResponseEntity<?> getTransactions(@PathVariable int idBankAccount) {
		List<TransactionResponseDTO> response = null;
		try {
			response = transactionService.getTransactions(idBankAccount);
			return new ResponseEntity<List<TransactionResponseDTO>>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getBalanceForBankAccountId")
	public ResponseEntity<String> getBalanceForBankAccountId(@RequestParam int bankAccountId){
		String response = "0.00";
		try {
			response = transactionService.getBalanceForBankAccountId(bankAccountId);
			//JSONObject jsonObject = new JSONObject();
			//jsonObject.put("balance", response);
			return new ResponseEntity<String> (response, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
