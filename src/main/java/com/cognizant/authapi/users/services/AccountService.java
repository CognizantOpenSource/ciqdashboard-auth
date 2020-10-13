package com.cognizant.authapi.users.services;

import com.cognizant.authapi.users.beans.Account;
import com.cognizant.authapi.users.repos.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by 784420 on 7/22/2019 4:47 PM
 */
@Service
@AllArgsConstructor
public class AccountService {
    private AccountRepository accountRepository;

    /**
     * Getting all Account list
     **/
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    /**
     * Getting a Account by Account ID
     **/
    public Optional<Account> getAccount(String userId) {
        return accountRepository.findById(userId);
    }

    /**
     * Adding a new Account
     **/
    public Account addNewAccount(@RequestBody Account account) {
        return accountRepository.insert(account);
    }

    /**
     * Getting a Account by Account UserId
     **/
    public List<Account> getAccountByUserId(String userId) {
        return accountRepository.findByUserId(userId);
    }

    /**
     * Updating a Account
     *
     * @param account
     * @return
     */
    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }

    /**
     * Deleting Accounts based on list of id's
     *
     * @param accountIds
     */
    public void removeIds(List<String> accountIds) {
        accountRepository.deleteByIdIn(accountIds);
    }

    /**
     * Deleting Accounts based on id
     *
     * @param id
     */
    public void removeById(String id) {
        accountRepository.deleteById(id);
    }

    /**
     * Deleting Account based on the UserId
     *
     * @param userId
     */
    public void removeByUserId(String userId) {
        accountRepository.deleteByUserId(userId);
    }

    /**
     * Generating Account with default values
     *
     * @param userId
     * @return
     */
    public Account generateAccount(String userId) {
        Account account = new Account();
        account.setUserId(userId);
        account.setRoles(Collections.emptyList());
        account.setProjectIds(Collections.emptyList());
        account.setOwnProjectIds(Collections.emptyList());
        return account;
    }
}
