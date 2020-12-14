package com.demo.atmAPI.processing;

import com.demo.atmAPI.account.repository.AccountRepositoryImpl;
import com.demo.atmAPI.model.Account;

public class AddAccountRequest {

    private AccountRepositoryImpl accountRepository = null;

	public Boolean addAccountReq(Account account) {
		accountRepository = new AccountRepositoryImpl();
		Boolean result = accountRepository.addAccount(account);
		return result;		
	}
}
