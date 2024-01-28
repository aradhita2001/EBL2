package com.wecp.progressive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wecp.progressive.config.DatabaseConnectionManager;
import com.wecp.progressive.dto.CustomerAccountInfo;
import com.wecp.progressive.entity.Customers;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public int addCustomer(Customers customers) throws SQLException {

        int generatedKey = -1;

        Connection connection = DatabaseConnectionManager.getConnection();

        String sql = "INSERT INTO customers (name, email,username, password) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, customers.getName());
            statement.setString(2, customers.getEmail());
            statement.setString(3, customers.getUsername());
            statement.setString(4, customers.getPassword());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                generatedKey = resultSet.getInt(1);
                customers.setCustomerId(generatedKey);
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return generatedKey;
    }

    @Override
    public Customers getCustomerById(int customerId) throws SQLException {

        Customers customer = null;
        Connection connection = DatabaseConnectionManager.getConnection();

        String sql = "SELECT * FROM customers WHERE customer_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                String name = resultSet.getString("name");
                 String email = resultSet.getString("email");
                 String username = resultSet.getString("username");
                 String password = resultSet.getString("password");

                 customer = new Customers(customerId, name, email, username, password);
            }
        } finally {
            if(connection != null){
                connection.close();
            }
        }

        return customer;
    }

    @Override
    public void updateCustomer(Customers customers) throws SQLException {

        Connection connection = DatabaseConnectionManager.getConnection();

        String sql = "UPDATE customers SET name = ?, email = ?, username= ?, password =? WHERE customer_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, customers.getName());
            statement.setString(2, customers.getEmail());
            statement.setString(3, customers.getUsername());
            statement.setString(4, customers.getPassword());
            statement.setInt(5, customers.getCustomerId());

            statement.executeUpdate();

        } finally {
            if(connection != null){
                connection.close();
            }
        }
    }

    @Override
    public void deleteCustomer(int customerId) throws SQLException {
        Connection connection = DatabaseConnectionManager.getConnection();

        String sql = "DELETE FROM customers WHERE customer_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);

            statement.executeUpdate();
            
        } finally {
            if(connection != null){
                connection.close();
            }
        }
    }

    @Override
    public List<Customers> getAllCustomers() throws SQLException {
        List<Customers> customersList = new ArrayList<Customers>();

        Connection connection = DatabaseConnectionManager.getConnection();

        String sql = "SELECT * FROM customers";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int customerId = resultSet.getInt("customer_id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                Customers customers = new Customers(customerId, name, email, username, password);
                customersList.add(customers);
            }
        } finally {
            if(connection != null){
                connection.close();
            }
        }

        return customersList;
    }

    @Override
    public CustomerAccountInfo getCustomerAccountInfo(int customerId) throws SQLException {
        CustomerAccountInfo customerAccountInfo = null;
        Connection connection = DatabaseConnectionManager.getConnection();

        //CustomerAccountInfo details are collected from both customers and accounts table
        String sql = "SELECT c.customer_id, c.name AS customer_name, c.email, a.account_id, a.balance " +
                     "FROM customers c " +
                     "INNER JOIN accounts a ON c.customer_id = a.customer_id " +
                     "WHERE c.customer_id = ?";
         
        try (PreparedStatement statement =  connection.prepareStatement(sql)) {
             
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String customerName = resultSet.getString("customer_name");
                String email = resultSet.getString("email");
                int accountId = resultSet.getInt("account_id");
                double balance = resultSet.getDouble("balance");

                customerAccountInfo = new CustomerAccountInfo(customerId, customerName, email, accountId, balance);
            }

            if (resultSet != null) {
                resultSet.close();
            }
        }  finally {
            if (connection != null) {
                connection.close();
            }
        }
        return customerAccountInfo;
    }


}
