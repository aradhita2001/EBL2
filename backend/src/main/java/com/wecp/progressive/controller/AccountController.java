package com.wecp.progressive.controller;

import com.wecp.progressive.entity.Accounts;
import com.wecp.progressive.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;


@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Accounts>> getAllAccounts() throws SQLException {
        return new ResponseEntity<List<Accounts>>(accountService.getAllAccounts(), HttpStatus.OK);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Accounts> getAccountById(@PathVariable int accountId) throws SQLException {
        return new ResponseEntity<Accounts>(accountService.getAccountById(accountId), HttpStatus.OK);
    }
    @GetMapping("/user/{param}")
    public ResponseEntity<List<Accounts>> getAccountsByUser(@PathVariable String param) throws NumberFormatException, SQLException {
        return new ResponseEntity<List<Accounts>>(accountService.getAccountsByUser(Integer.parseInt(param)), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Integer> addAccount(@RequestBody Accounts accounts) throws SQLException {
        return new ResponseEntity<Integer>(accountService.addAccount(accounts), HttpStatus.CREATED);
    }
    
    public ResponseEntity<Void> updateAccount(int accountId, Accounts accounts) {
        return null;
    }

    public ResponseEntity<Void> deleteAccount(int accountId) {
        return null;
    }
}