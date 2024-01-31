package com.wecp.progressive.controller;

import com.wecp.progressive.entity.Transactions;
import com.wecp.progressive.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<Transactions>> getAllTransactions() throws SQLException {
        return new ResponseEntity<List<Transactions>>(transactionService.getAllTransactions(), HttpStatus.OK);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transactions> getTransactionById(@PathVariable int transactionId) throws SQLException {
        return new ResponseEntity<Transactions>(transactionService.getTransactionById(transactionId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Integer> addTransaction(Transactions transaction) throws SQLException {
        return new ResponseEntity<Integer>(transactionService.addTransaction(transaction), HttpStatus.CREATED);
    }

    public ResponseEntity<Void> updateTransaction(int transactionId, Transactions transaction) {
        return null;
    }

    public ResponseEntity<Void> deleteTransaction(int transactionId) {
        return null;
    }
}
