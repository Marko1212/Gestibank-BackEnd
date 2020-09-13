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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.gesti.bank.dto.CreateClientRequestDTO;
import com.gesti.bank.dto.ForgotPasswordRequestDTO;
import com.gesti.bank.dto.GetAccountResponseDTO;
import com.gesti.bank.dto.LoginRequestDTO;
import com.gesti.bank.dto.LoginResponseDTO;
import com.gesti.bank.dto.PasswordChangeRequestDTO;
import com.gesti.bank.dto.ResetPasswordRequestDTO;
import com.gesti.bank.service.UserAccountService;

//u cross origins, treba staviti url localhost:4200 (to je za slucaj kad je klijent browser na laptopu, u ovom slucaju sa angular aplikacijom na port-u 4200) i url 192.168.1.80:4200 (sa port : 4200 za Angular front end) - to je kad
//'gadjam' aplikaciju back end, sa brauzera u mobilnom telefonu. Taj mobilni telefon mora da bude prikljucen na internet pomocu iste wi fi mreze kao
// i laptop (na kome je klient, brauzer). Ukoliko stavim origins = "*", onda dozvoljavam dostup na aplikaciju sa bilo kog klijenta (desktop, laptop, tablet, mobile device i t.d.)
//192.168.1.80 je ovde IP v4 adresa kompjutera na kome mi je brauzer klijenta (laptop). To je takodje ip v4 adresa klijenta, mobilni telefon, posto je mobilni telefon prikljucen na internet pomocu
//iste wi fi mreze kao i laptop, kao sto je receno vise. ta IP v4 adresa se dobija pomocu ipconfig komande u cmd shell-u, u Windows.
//Ponekad je jos potrebno, da bi se funkcionisalo kako treba, iskljuciti antivirusni program i firewall na kompjuteru, gde se ranuje back end.

//@CrossOrigin(origins = {"http://localhost:4200", "http://192.168.1.80:4200"}, maxAge = 3600)
@CrossOrigin(origins = "*", maxAge = 3600)
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
	
	@PostMapping("/forgotPassword")
	public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequestDTO request){
		String response;
		try {
			response = userAccountService.forgotPassword(request);
			return new ResponseEntity<String>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response = e.getMessage();
			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/resetPassword")
	public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequestDTO request){
		String response;
		try {
			response = userAccountService.resetPassword(request);
			return new ResponseEntity<String>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response = e.getMessage();
			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
		}
		
	}

}
