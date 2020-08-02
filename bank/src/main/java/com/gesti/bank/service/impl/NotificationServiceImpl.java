package com.gesti.bank.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gesti.bank.dto.NotificationResponseDTO;
import com.gesti.bank.model.Notification;
import com.gesti.bank.model.UserAccount;
import com.gesti.bank.repository.NotificationRepository;
import com.gesti.bank.repository.UserAccountRepository;
import com.gesti.bank.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService{

	@Autowired
	NotificationRepository notificationRepository;
	
	@Autowired
	UserAccountRepository userAccountRepository;

	@Override
	public List<NotificationResponseDTO> getNotifications(int idUserAccount) throws Exception{
		Optional<UserAccount> userAccountOpt = userAccountRepository.findById(idUserAccount);
		if(!userAccountOpt.isPresent()) {
			throw new Exception("User does not exist!");
		}
		UserAccount userAccount = userAccountOpt.get();
		List<Notification> fetchAllNotificationsForUser = notificationRepository.findAllByUserAccount(userAccount);
		List<NotificationResponseDTO> response = new ArrayList<NotificationResponseDTO>();
		for(Notification not:fetchAllNotificationsForUser) {
			NotificationResponseDTO tmpNot = new NotificationResponseDTO(not.getIdNotification(), not.getMessage(), not.getNotificationDate());
			response.add(tmpNot);
		}
		return response;
	}
}
