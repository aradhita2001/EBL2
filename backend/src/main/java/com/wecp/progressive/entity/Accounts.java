package com.wecp.progressive.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Accounts implements Comparable<Accounts> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;

    @ManyToOne
    private Customers customer;
    private double balance;

    public Accounts() {
    }

    public Accounts(Customers customers, double balance) {
        this.customer = customers;
        this.balance = balance;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customers) {
        this.customer = customers;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public int compareTo(Accounts o) {
        return Double.compare(this.balance, o.balance);
    }

}