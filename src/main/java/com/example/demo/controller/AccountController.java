package com.example.demo.controller;


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

import com.example.demo.dto.AccountDto;
import com.example.demo.service.AccountServer;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	@Autowired
	AccountServer accountService;
	
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
		return new ResponseEntity<>(accountService.createAccount(accountDto),HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccount(@PathVariable Long id){
		AccountDto accountDto=accountService.getAccount(id);
		return ResponseEntity.ok(accountDto);
	}
	
	@PutMapping("/{id}/deposite")
	public ResponseEntity<AccountDto> depositeAmount(@PathVariable Long id,@RequestBody Map<String,Double> request){
		AccountDto accountDto=accountService.deposite(id, request.get("amount"));
		return ResponseEntity.ok(accountDto);
		
	}
	
	@PutMapping("/{id}/withdrow")
	public ResponseEntity<AccountDto> withDrowAmount(@PathVariable Long id, @RequestBody Map<String,Double> request){
		AccountDto accountDto=accountService.withDrow(id, request.get("amount"));
		return ResponseEntity.ok(accountDto);
	}
	
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccount(){
		List<AccountDto> accountDto=accountService.getAllAccount();
		return ResponseEntity.ok(accountDto);
	}
	
	@DeleteMapping("/{id}")
	public String deletedAccount(@PathVariable Long id) {
		accountService.deleteAccount(id);
		return "account deleted sucessfull";
	}
	}
	

