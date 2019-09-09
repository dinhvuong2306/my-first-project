/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv30_project_final.service;

import com.mycompany.jv30_project_final.entities.AccountEntity;
import com.mycompany.jv30_project_final.repositories.AccountRepository;
import com.mycompany.jv30_project_final.repositories.ShippingRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private ShippingRepository shippingRepository;

    @Autowired
    private AccountRepository accountRepository;

    public AccountEntity
            findAccountByEmailAndPassword(String email, String password) {
        return accountRepository.findAccountByEmailAndPassword(email, password);
    }

    public List<AccountEntity> getAccount() {
        return (List<AccountEntity>) accountRepository.findAll();
    }
    
    public AccountEntity saveAccount(AccountEntity account){
        return  accountRepository.save(account);
    }

    public AccountEntity findAccountByEmail(String email) {
        AccountEntity account = accountRepository.findByEmailLike(email);
        if (account != null) {
            account.setShipping(shippingRepository.findByAccount(account));
        }
        return account;
    }
}
