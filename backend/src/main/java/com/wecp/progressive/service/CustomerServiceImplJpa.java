package com.wecp.progressive.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wecp.progressive.dao.CustomerDAO;
import com.wecp.progressive.entity.Customers;

@Service
public class CustomerServiceImplJpa implements CustomerService {

    private static List<Customers> customerList = new ArrayList<Customers>();
    private CustomerDAO customerDAO;

    public CustomerServiceImplJpa(CustomerDAO customerDAO){
        this.customerDAO = customerDAO;
    }
    

    public CustomerServiceImplJpa() {
    }


    @Override
    public List<Customers> getAllCustomers() throws SQLException {
        return customerDAO.getAllCustomers();
    }

    @Override
    public Customers getCustomerById(int customerId) throws SQLException {
        return customerDAO.getCustomerById(customerId);
    }

    @Override
    public int addCustomer(Customers customers) throws SQLException {
        return customerDAO.addCustomer(customers);
    }

    @Override
    public void updateCustomer(Customers customers) throws SQLException {
        customerDAO.updateCustomer(customers);
    }

    @Override
    public void deleteCustomer(int customerId) throws SQLException {
        customerDAO.deleteCustomer(customerId);
    }

    @Override
    public List<Customers> getAllCustomersSortedByName() throws SQLException {
        List<Customers> customersList = getAllCustomers();
        Collections.sort(customersList);
        return customersList;
    }

    @Override
    public List<Customers> getAllCustomersFromArrayList() {
        return customerList;
    }

    @Override
    public List<Customers> addCustomersToArrayList(Customers customers) {
        customerList.add(customers);
        return customerList;
    }

    @Override
    public List<Customers> getAllCustomersSortedByNameFromArrayList() {
        Collections.sort(customerList);
        return customerList;
    }

    @Override
    public void emptyArrayList() {
        customerList.clear();
    }

}