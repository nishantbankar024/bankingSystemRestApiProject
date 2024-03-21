package com.example.demo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AccountDto;
import com.example.demo.entity.Account;
import com.example.demo.mapper.AccountMapper;
import com.example.demo.repository.AccountRepository;
import com.example.demo.service.AccountServer;
@Service
public class AccountServiceImpl implements AccountServer{
	
	@Autowired
	AccountRepository accountRepository;

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		// TODO Auto-generated method stub
	Account account=AccountMapper.mapToAccount(accountDto);
	Account saveAccount=accountRepository.save(account);
	return AccountMapper.mapToAccountDto(saveAccount);
		
	}

	@Override
	public AccountDto getAccount(Long id) {
		// TODO Auto-generated method stub
		Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does not exit"));
		
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposite(Long id, double amount) {
		// TODO Auto-generated method stub
		Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does not exit"));
		double totalBalance= account.getBalance()+amount;
		account.setBalance(totalBalance);
		Account saveAccount=accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(saveAccount);
	}

	@Override
	public AccountDto withDrow(Long id, double amount) {
		Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does not exit"));
		if(account.getBalance()<amount) {
			throw new RuntimeException("insufficent balance");
		}
		double totalBalance=account.getBalance()-amount;
		account.setBalance(totalBalance);
		  Account saveaccount= accountRepository.save(account);
		  return AccountMapper.mapToAccountDto(saveaccount);
		
		
	
	}

	@Override
	public List<AccountDto> getAllAccount() {
		// TODO Auto-generated method stub
		return accountRepository.findAll().stream().map((account)->AccountMapper.mapToAccountDto(account))
				.collect(Collectors.toList());
		
		
	}

	@Override
	public void deleteAccount(Long id) {
		// TODO Auto-generated method stub
		Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does not exit"));
		accountRepository.delete(account);
		
	}

}
