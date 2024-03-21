package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.AccountDto;

public interface AccountServer {
	
	AccountDto createAccount(AccountDto accountDto);
	
   AccountDto getAccount(Long id);
   
   AccountDto deposite(Long id,double amount);
   
   AccountDto withDrow(Long id,double amount);
   
   List<AccountDto> getAllAccount();
   
   void deleteAccount(Long id);

}
