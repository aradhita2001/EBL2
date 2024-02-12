package com.wecp.progressive.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecp.progressive.entity.Customers;
import com.wecp.progressive.exception.CustomerAlreadyExistsException;
import com.wecp.progressive.repository.CustomerRepository;

@Service
public class CustomerServiceImplJpa implements CustomerService {

    private static List<Customers> customerList = new ArrayList<Customers>();
    
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerServiceImplJpa() {
    }

    public CustomerServiceImplJpa(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customers> getAllCustomers() throws SQLException {
        return customerRepository.findAll();
    }

    @Override
    public Customers getCustomerById(int customerId) throws SQLException {
        return customerRepository.findById(customerId).get();
    }

    @Override
    public int addCustomer(Customers customers) throws SQLException {
        String name = customers.getName();
        String email = customers.getEmail();

        Optional<Customers> existingCustomer = customerRepository.findByNameAndEmail(name, email);
        if(existingCustomer.isPresent()) throw new CustomerAlreadyExistsException("customer already exists");
        
        //customers must have a role
        if(customers.getRole().isBlank()) return -1;
        
        return customerRepository.save(customers).getCustomerId();
    }

    @Override
    public void updateCustomer(Customers customers) throws SQLException {
        if(customerRepository.existsById(customers.getCustomerId()) && !customers.getRole().isBlank()){
            customerRepository.save(customers);
        }
    }

    @Override
    public void deleteCustomer(int customerId) throws SQLException {
        customerRepository.deleteById(customerId);
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