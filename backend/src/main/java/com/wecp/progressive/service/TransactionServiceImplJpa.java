package com.wecp.progressive.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecp.progressive.entity.Transactions;
import com.wecp.progressive.repository.AccountRepository;
import com.wecp.progressive.repository.TransactionRepository;

@Service
public class TransactionServiceImplJpa implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    public TransactionServiceImplJpa() {

    }

    public TransactionServiceImplJpa(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Transactions> getAllTransactions() throws SQLException {
        return transactionRepository.findAll();
    }

    @Override
    public Transactions getTransactionById(int transactionId) throws SQLException {
        return transactionRepository.findById(transactionId).get();
    }

    @Override
    public int addTransaction(Transactions transaction) throws SQLException {
        return transactionRepository.save(transaction).getTransactionId();
    }

    @Override
    public void updateTransaction(Transactions transaction) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTransaction'");
    }

    @Override
    public void deleteTransaction(int transactionId) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteTransaction'");
    }

    @Override
    public List<Transactions> getTransactionsByCustomerId(int customerId) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTransactionsByCustomerId'");
    }

}
