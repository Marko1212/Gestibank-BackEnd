package com.gesti.bank;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gesti.bank.service.FilesStorageService;

@SpringBootApplication
public class BankApplication implements CommandLineRunner {

	@Resource
	FilesStorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

	@Override
	public void run(String... arg) throws Exception {
		//storageService.deleteAll(); //TODO COMMENT THIS LINE
		storageService.init();
	}

}
