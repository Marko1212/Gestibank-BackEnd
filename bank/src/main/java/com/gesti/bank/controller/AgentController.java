package com.gesti.bank.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gesti.bank.dto.AssignClientRequestDTO;
import com.gesti.bank.dto.BankAccountResponseDTO;
import com.gesti.bank.dto.GetUnresolvedRequestsForAgentResponseDTO;
import com.gesti.bank.dto.RequestsForAgentResolutionDTO;
import com.gesti.bank.dto.SimpleMessageResponseDTO;
import com.gesti.bank.dto.VerifiedClientsRequestDTO;
import com.gesti.bank.model.BankAccount;
import com.gesti.bank.service.BankAccountService;
import com.gesti.bank.service.UserAccountService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/agent")
public class AgentController {

	@Autowired
	UserAccountService userAccountService;
	
	@Autowired
	BankAccountService bankAccountService;
	/*
	 * @Param - requestTypeFlag - 0 CREATE_ACCOUNT, 1 OTHER REQUESTS
	 */
	
	@GetMapping("/getUnresolvedRequests/{id}")
	public ResponseEntity<List<GetUnresolvedRequestsForAgentResponseDTO>> getUnresolvedRequests(@PathVariable int id, @RequestParam(value="requestTypeFlag", defaultValue = "0") String requestTypeFlag) {
		List<GetUnresolvedRequestsForAgentResponseDTO> response = new ArrayList<GetUnresolvedRequestsForAgentResponseDTO>();
		try {
			response = userAccountService.getUnresolvedRequests(id, Integer.parseInt(requestTypeFlag));
			return new ResponseEntity<List<GetUnresolvedRequestsForAgentResponseDTO>>(response, HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<GetUnresolvedRequestsForAgentResponseDTO>>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/validation")
	public ResponseEntity<String> validation(@RequestBody VerifiedClientsRequestDTO request){
		String response = "";
		try {
			response = userAccountService.validation(request);
			return new ResponseEntity<String>(response, HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/markRequestsAsResolved")
	public ResponseEntity<?> markRequestsAsResolved(@RequestBody RequestsForAgentResolutionDTO request) {
		
		SimpleMessageResponseDTO response = null;
		try {
			response = bankAccountService.markRequestsAsResolved(request);
			return new ResponseEntity<SimpleMessageResponseDTO>(response, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
}
