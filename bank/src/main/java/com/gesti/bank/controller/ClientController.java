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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gesti.bank.dto.AgentResponseDTO;
import com.gesti.bank.service.UserAccountService;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/client")

public class ClientController {
	
	@Autowired
	UserAccountService userAccountService;
	
	@GetMapping("/{idClient}/conseiller")
	public ResponseEntity<List<AgentResponseDTO>> getAccount(@PathVariable int idClient) {
		List<AgentResponseDTO> response = null;
		try {
			response = userAccountService.getAgentOfClient(idClient);
			return new ResponseEntity<List<AgentResponseDTO>>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<AgentResponseDTO>>(response, HttpStatus.BAD_REQUEST);
		}
	}

}
