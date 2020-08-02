package com.gesti.bank.service;

import java.util.List;

import com.gesti.bank.dto.NotificationResponseDTO;

public interface NotificationService {

	List<NotificationResponseDTO> getNotifications(int idUserAccount) throws Exception;

}
