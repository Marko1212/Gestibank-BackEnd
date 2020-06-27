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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gesti.bank.dto.GetUnresolvedRequestsForAgentResponseDTO;
import com.gesti.bank.service.UserAccountService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/agent")
public class AgentController {

	@Autowired
	UserAccountService userAccountService;
	
	@GetMapping("/getUnresolvedRequests/{id}")
	public ResponseEntity<List<GetUnresolvedRequestsForAgentResponseDTO>> getUnresolvedRequests(@PathVariable int id) {
		List<GetUnresolvedRequestsForAgentResponseDTO> response = new ArrayList<GetUnresolvedRequestsForAgentResponseDTO>();
		try {
			response = userAccountService.getUnresolvedRequests(id);
			return new ResponseEntity<List<GetUnresolvedRequestsForAgentResponseDTO>>(response, HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<GetUnresolvedRequestsForAgentResponseDTO>>(response, HttpStatus.BAD_REQUEST);
		}
	}
}
