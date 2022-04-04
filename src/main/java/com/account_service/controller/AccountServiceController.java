package com.account_service.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.account_service.models.AccountServiceModel;
import com.account_service.repository.AccountServiceRepository;

@RestController
@RequestMapping("/accountservice")
public class AccountServiceController {
	    @Autowired
	    AccountServiceRepository accountServiceRepository;

	    @GetMapping("/")
	    public List<AccountServiceModel> getAllAccounts(){
	        return accountServiceRepository.findAll();
	    }

	    @GetMapping("/{id}")
	    public AccountServiceModel getOneAccount(@PathVariable String id){
	        int blogId = Integer.parseInt(id);
	        return accountServiceRepository.findById(blogId).get();
	    }

	    @GetMapping("/allunlocked")
	    public List <AccountServiceModel> getAllUnlockedAccounts(){
	        List <AccountServiceModel> allAccounts = accountServiceRepository.findAll();
	        List <AccountServiceModel> allFoundUnlocked = new ArrayList<>();
	        for (AccountServiceModel account : allAccounts) {
	        	if (account.getLocked() == false) {
	        		allFoundUnlocked.add(account);
	        	}
			}
	        return allFoundUnlocked;
	    }

	    @GetMapping("/alllocked")
	    public List <AccountServiceModel> getAllLockedAccounts(){
	        List <AccountServiceModel> allAccounts = accountServiceRepository.findAll();
	        List <AccountServiceModel> allFoundLocked = new ArrayList<>();
	        for (AccountServiceModel account : allAccounts) {
	        	if (account.getLocked() == true) {
	        		allFoundLocked.add(account);
	        	}
			}
	        return allFoundLocked;
	    }

	    @PutMapping("/unlockall")
	    public List <AccountServiceModel> unlockAllAccounts(){
	        List <AccountServiceModel> allAccounts= accountServiceRepository.findAll();
	        List <AccountServiceModel> allFoundLocked = new ArrayList<>();
	        for (AccountServiceModel account : allAccounts) {
	        	if (account.getLocked() == true) {
		        	account.setLocked(false);
			        accountServiceRepository.save(account);
	        		allFoundLocked.add(account);
	        	}
			}
	        return allFoundLocked;
	    }

	    @PostMapping("/addaccount")
	    public ResponseEntity<?> addAccount(@RequestBody Map<String, String> body){
	        String name = body.get("name");
	        String email = body.get("email");
	        String password = body.get("password");
	        AccountServiceModel res = accountServiceRepository.save(new AccountServiceModel(name, email, password));
	        return new ResponseEntity<>(res, HttpStatus.OK);
	    }

	    @PostMapping("/addaccounts")
	    public List <AccountServiceModel> addAccounts(@RequestBody List <AccountServiceModel> accounts){
	    	for (AccountServiceModel account : accounts) {
	    		accountServiceRepository.save(new AccountServiceModel(account.getName(), account.getEmail(), account.getPassword()));
	    	}
	        return accounts;
	    }

	    @PutMapping("/lockaccount/{email}")
	    public AccountServiceModel lockAccount(@PathVariable String email){
	        AccountServiceModel accountServiceModel = accountServiceRepository.findByEmail(email);
	        if (accountServiceModel.getLocked() != true) {
	        	accountServiceModel.setLocked(true);
		        accountServiceRepository.save(accountServiceModel);
		        return accountServiceModel;
	        }
	        return null;
	    }

	    @PutMapping("/unlockaccount/{email}")
	    public AccountServiceModel unlockAccount(@PathVariable String email){
	        AccountServiceModel accountServiceModel = accountServiceRepository.findByEmail(email);
	        accountServiceModel.setLocked(false);
	        return accountServiceRepository.save(accountServiceModel);
	    }

	    @PutMapping("/updateaccount/{email}")
	    public AccountServiceModel updateOneAccount(@PathVariable String email, @RequestBody Map<String, String> body){
	        AccountServiceModel accountServiceModel = accountServiceRepository.findByEmail(email);
	        accountServiceModel.setName(body.get("name"));
	        accountServiceModel.setEmail(body.get("email"));
	        accountServiceModel.setPassword(body.get("password"));
	        return accountServiceRepository.save(accountServiceModel);
	    }

	    @DeleteMapping("/deleteaccount/{email}")
	    public AccountServiceModel deleteOneAccount(@PathVariable String email){
	        AccountServiceModel accountServiceModel = accountServiceRepository.findByEmail(email);
	        accountServiceRepository.delete(accountServiceModel);
	        return accountServiceModel;
	    }
}
