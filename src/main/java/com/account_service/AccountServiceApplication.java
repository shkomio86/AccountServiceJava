package com.account_service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.account_service.models.AccountServiceModel;
import com.account_service.repository.AccountServiceRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class AccountServiceApplication {
	@Autowired
	private AccountServiceRepository accountServiceRepository;

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}
	
	@Bean
	InitializingBean sendDatabase(){
		return () -> {
			// read JSON and load json
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<AccountServiceModel>> typeReference = new TypeReference<List<AccountServiceModel>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/data.json");
			try {
				List<AccountServiceModel> accountServiceModels = mapper.readValue(inputStream,typeReference);
				for (AccountServiceModel account : accountServiceModels) {
		    		accountServiceRepository.save(new AccountServiceModel(account.getName(), account.getEmail(), account.getPassword()));
		    	}
				System.out.println("Users Saved!");
			} catch (IOException e){
				System.out.println("Unable to save users: " + e.getMessage());
			}
	    };
	}
}
