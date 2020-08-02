package com.gesti.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gesti.bank.model.Notification;
import com.gesti.bank.model.UserAccount;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer>{
	List<Notification> findAllByUserAccount(UserAccount userAccount);
}
