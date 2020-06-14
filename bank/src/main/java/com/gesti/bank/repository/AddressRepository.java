package com.gesti.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gesti.bank.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
