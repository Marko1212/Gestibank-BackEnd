package com.gesti.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.gesti.bank.model.BankRule;

@Service
public interface BankRuleRepository extends JpaRepository<BankRule, Integer>{

}
