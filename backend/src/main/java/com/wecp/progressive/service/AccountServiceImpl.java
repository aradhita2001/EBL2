package com.wecp.progressive.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.wecp.progressive.dao.AccountDAO;
import com.wecp.progressive.entity.Accounts;

public class AccountServiceImpl  implements AccountService {

    private static List<Accounts> accountsList = new ArrayList<Accounts>();
    private AccountDAO accountDao;


    public AccountServiceImpl(AccountDAO accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public List<Accounts> getAllAccounts() throws SQLException {
        return accountDao.getAllAccounts();
    }

    @Override
    public List<Accounts> getAccountsByUser(int userId) throws SQLException {
        return null;
    }

    @Override
    public Accounts getAccountById(int accountId) throws SQLException {
        return accountDao.getAccountById(accountId);
    }

    @Override
    public int addAccount(Accounts accounts) throws SQLException {
        return accountDao.addAccount(accounts);
    }

    @Override
    public void updateAccount(Accounts accounts) throws SQLException {
        accountDao.updateAccount(accounts);
    }

    @Override
    public void deleteAccount(int accountId) throws SQLException {
        accountDao.deleteAccount(accountId);
    }

    @Override
    public List<Accounts> getAllAccountsSortedByBalance() throws SQLException {
        List<Accounts> accountsList = accountDao.getAllAccounts();
        Collections.sort(accountsList);
        return accountsList;
    }

    @Override
    public List<Accounts> getAllAccountsFromArrayList() {
        return accountsList;
    }

    @Override
    public List<Accounts> addAccountToArrayList(Accounts accounts) {
        accountsList.add(accounts);
        return accountsList;
    }

    @Override
    public List<Accounts> getAllAccountsSortedByBalanceFromArrayList() {
        Collections.sort(accountsList);
        return accountsList;
    }

    @Override
    public void emptyArrayList() {
        accountsList.clear();
    }

}