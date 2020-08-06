package com.gesti.bank.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.gesti.bank.dto.CreateClientRequestDTO;
import com.gesti.bank.dto.GetAccountResponseDTO;
import com.gesti.bank.dto.LoginRequestDTO;
import com.gesti.bank.dto.LoginResponseDTO;
import com.gesti.bank.dto.PasswordChangeRequestDTO;
import com.gesti.bank.service.UserAccountService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/userAccount")
public class UserAccountController {
	
	@Autowired
	UserAccountService userAccountService;
	
	@PostMapping("/createClient")
//	public ResponseEntity<String> createClient(@RequestBody CreateClientRequestDTO request){
//		String response;
//		try {
//			response = userAccountService.createClient(request);
//			return new ResponseEntity<String>(response, HttpStatus.OK);
//		} catch (Exception e) {
//			response = e.getMessage();
//			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
//		}
//		
//	}
	public ResponseEntity<String> createClient(@RequestParam("idDocument") MultipartFile idDocument, @RequestParam("proofHome") MultipartFile proofHome, @RequestParam("proofSalary") MultipartFile proofSalary, @RequestParam("demande") String demande){
		String response;
		try {
			CreateClientRequestDTO request = new CreateClientRequestDTO(demande);
			response = userAccountService.createClient(request, idDocument, proofHome, proofSalary);
			return new ResponseEntity<String>(response, HttpStatus.OK);
			//return null;
		} catch (Exception e) {
			e.printStackTrace();
			response = e.getMessage();
			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
		LoginResponseDTO response = null;
		try {
			response = userAccountService.login(request);
			return new ResponseEntity<LoginResponseDTO>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<LoginResponseDTO>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping("/getAccount/{id}")
	public ResponseEntity<GetAccountResponseDTO> getAccount(@PathVariable int id) {
		GetAccountResponseDTO response = null;
		try {
			response = userAccountService.getAccount(id);
			return new ResponseEntity<GetAccountResponseDTO>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<GetAccountResponseDTO>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/changePassword")
	public ResponseEntity<String> changePassword(@RequestBody PasswordChangeRequestDTO request){
		String response;
		try {
			response = userAccountService.changePassword(request);
			return new ResponseEntity<String>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response = e.getMessage();
			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
		}
		
	}

}
