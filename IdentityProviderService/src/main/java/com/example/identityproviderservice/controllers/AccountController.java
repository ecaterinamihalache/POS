package com.example.identityproviderservice.controllers;

import com.example.identityproviderservice.interfaces.AccountServiceInterface;
import com.example.identityproviderservice.interfaces.HashingServiceInterface;
import com.example.identityproviderservice.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//SWAGGER: localhost:8080/swagger-ui.html
@CrossOrigin
@RestController
@RequestMapping(path = "uac/bookcollection/accounts")
public class AccountController {

    @Autowired
    private AccountServiceInterface accountService;

    @Autowired
    private HashingServiceInterface hashingService;

    @GetMapping(path="")
    public ResponseEntity<List<Account>> getAccounts() {
        List<Account> accounts = this.accountService.getAccounts();

        if(accounts.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Integer id) {
        Account account = this.accountService.getAccountById(id);

        if(account != null)
            return new ResponseEntity<Account>(account, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(path="")
    public ResponseEntity<Account> addAccount(@RequestBody Account account) {
        String hashedPassword = hashingService.encode(account.getPassword());
        account.setPassword(hashedPassword);

        boolean isSuccessful = this.accountService.addAccount(account);

        if(isSuccessful)
            return new ResponseEntity<Account>(account, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity deleteAccount(@PathVariable Integer id) {
        boolean isSuccessful = this.accountService.deleteAccountById(id);

        if(isSuccessful)
            return new ResponseEntity<Account>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable int id, @RequestBody Account account) {
        account.setId(id);
        String hashedPassword = hashingService.encode(account.getPassword());
        account.setPassword(hashedPassword);

        Account updatedAccount = this.accountService.updateAccount(account);

        if(updatedAccount != null)
            return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
