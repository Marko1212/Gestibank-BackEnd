package com.gesti.bank.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gesti.bank.dto.AgentResponseDTO;
import com.gesti.bank.dto.AssignClientRequestDTO;
import com.gesti.bank.dto.ClientRequestForAdminDTO;
import com.gesti.bank.dto.ClientResponseForAdminDTO;
import com.gesti.bank.dto.CreateAgentRequestDTO;
import com.gesti.bank.dto.UpdateAgentRequestDTO;
import com.gesti.bank.service.UserAccountService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/admin")

public class AdminController {
	
	@Autowired
	UserAccountService userAccountService;
	
	@PostMapping("/createAgent")
	public ResponseEntity<String> createAgent(@RequestBody CreateAgentRequestDTO request){
		String response;
		try {
			response = userAccountService.createAgent(request);
			return new ResponseEntity<String>(response, HttpStatus.OK);
		} catch (Exception e) {
			response = e.getMessage();
			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
		}
		
	}

	@GetMapping("/getAllAgents")
	public ResponseEntity<List<AgentResponseDTO>> getAllAgents(@RequestParam(defaultValue = "") String filter) {
		List<AgentResponseDTO> response = new ArrayList<>();
		try {
			response = userAccountService.getAllAgents(filter);
			//response = userAccountService.getAllAgents();
			return new ResponseEntity<List<AgentResponseDTO>>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<AgentResponseDTO>>(response, HttpStatus.BAD_REQUEST);
		}
		
	}
	
//	@GetMapping("/filterAgents")
//	public ResponseEntity<List<AgentResponseDTO>> filterAgents(@RequestParam(defaultValue = "") String filter) {
//		List<AgentResponseDTO> response = new ArrayList<>();
//		try {
//			response = userAccountService.getAllAgents(filter);
//			return new ResponseEntity<List<AgentResponseDTO>>(response, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<List<AgentResponseDTO>>(response, HttpStatus.BAD_REQUEST);
//		}
//	}
	
	@PutMapping("/updateAgent/{id}")
	public ResponseEntity<String> updateAgent(@RequestBody UpdateAgentRequestDTO request, @PathVariable int id) {
		String response;
		try {
			response = userAccountService.updateAgent(request, id);
			return new ResponseEntity<String>(response, HttpStatus.OK);
		} catch (Exception e) {
			response = e.getMessage();
			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/deleteAgent/{id}")
	public ResponseEntity<String> deleteAgent(@PathVariable int id) {
		String response;
		try {
			response = userAccountService.deleteAgent(id);
			return new ResponseEntity<String>(response, HttpStatus.OK);
		} catch (Exception e) {
			response = e.getMessage();
			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getInvalidClients")
	public ResponseEntity<List<ClientResponseForAdminDTO>> getInvalidClients() {
		List <ClientResponseForAdminDTO> listInvalidClients = new ArrayList<>();
		try {
			listInvalidClients = userAccountService.getInvalidClients();
			return new ResponseEntity<List<ClientResponseForAdminDTO>>(listInvalidClients, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<ClientResponseForAdminDTO>>(listInvalidClients, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/аssignClient/{agentId}")
	public ResponseEntity<String> assignClient(@PathVariable("agentId") int agentId, @RequestBody AssignClientRequestDTO request){
		String response = "";
		try {
			//TODO SERVICE CALL
			response = userAccountService.assignClient(agentId, request);
			return new ResponseEntity<String>(response, HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
}
