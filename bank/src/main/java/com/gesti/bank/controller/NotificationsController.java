package com.gesti.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gesti.bank.dto.NotificationResponseDTO;
import com.gesti.bank.dto.TransactionResponseDTO;
import com.gesti.bank.service.NotificationService;

@CrossOrigin(origins = {"http://localhost:4200", "http://192.168.1.80:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/notifications")
public class NotificationsController {

	@Autowired
	NotificationService notificationService;
	
	
	@GetMapping("/getNotifications/{idUserAccount}")
	public ResponseEntity<?> getNotifications(@PathVariable int idUserAccount) {
		List<NotificationResponseDTO> response = null;
		try {
			response = notificationService.getNotifications(idUserAccount);
			return new ResponseEntity<List<NotificationResponseDTO>>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
