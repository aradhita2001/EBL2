package com.wecp.progressive.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecp.progressive.entity.Accounts;
import com.wecp.progressive.entity.Transactions;
import com.wecp.progressive.exception.AccountNotFoundException;
import com.wecp.progressive.exception.OutOfBalanceException;
import com.wecp.progressive.exception.WithdrawalLimitException;
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
        String transactionType = transaction.getTransactionType();
        double transactionAmount = transaction.getAmount();
        
        if(transactionType.equals("debit") && transactionAmount > 30000) throw new WithdrawalLimitException("withdrawal limit exceeded");

        Accounts accounts = transaction.getAccounts();
        double accountBalance = accounts.getBalance();

        if(transactionType.equals("debit") && transactionAmount > accountBalance) throw new OutOfBalanceException("Not enough balance");

        if(transactionType.equals("debit")){
            accountBalance = accountBalance - transactionAmount;
        }
        else{
            accountBalance = accountBalance + transactionAmount;
        }

        accounts.setBalance(accountBalance);
        accountRepository.save(accounts);

        return transactionRepository.save(transaction).getTransactionId();
    }

    @Override
    public void updateTransaction(Transactions transaction) throws SQLException {
        
        String transactionType = transaction.getTransactionType();
        double transactionAmount = transaction.getAmount();
        
        if(transactionType.equals("debit") && transactionAmount > 30000) throw new WithdrawalLimitException("withdrawal limit exceeded");

        Accounts accounts = transaction.getAccounts();
        double accountBalance = accounts.getBalance();

        if(transactionType.equals("debit") && transactionAmount > accountBalance) throw new OutOfBalanceException("Not enough balance");

        
        Transactions oldTransaction = transactionRepository.findById(transaction.getTransactionId()).get();
        String oldTransactionType = oldTransaction.getTransactionType();
        double oldTransactionAmount = oldTransaction.getAmount();

        if(oldTransactionType.equals("debit")){
            accountBalance = accountBalance + oldTransactionAmount;
        }
        else{
            accountBalance = accountBalance -oldTransactionAmount;
        }

        if(transactionType.equals("debit")){
            accountBalance = accountBalance - transactionAmount;
        }
        else{
            accountBalance = accountBalance + transactionAmount;
        }

        accountRepository.save(accounts);
        transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransaction(int transactionId) throws SQLException {
        Transactions transactions = transactionRepository.findById(transactionId).get();
        String transactionType = transactions.getTransactionType();
        double transactionAmount = transactions.getAmount();
        Accounts accounts = transactions.getAccounts();
        double accountBalance = accounts.getBalance();

        if(transactionType.equals("debit")){
            accountBalance = accountBalance + transactionAmount;
        }
        else{
            accountBalance = accountBalance -transactionAmount;
        }

        accountRepository.save(accounts);
        transactionRepository.deleteById(transactionId);
    }

    @Override
    public List<Transactions> getTransactionsByCustomerId(int customerId) throws SQLException {
        List<Accounts> accounts = accountRepository.findByCustomerCustomerId(customerId);

        if(accounts.isEmpty()) throw new AccountNotFoundException("No accounts found");

        return transactionRepository.findByAccountsAccountId(accounts.iterator());
    }

}
