// package com.wecp.progressive.dao;

// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.List;

// import com.wecp.progressive.config.DatabaseConnectionManager;
// import com.wecp.progressive.entity.Accounts;

// public class AccountDAOImpl implements AccountDAO {

//     @Override
//     public int addAccount(Accounts accounts) throws SQLException {
        
//         int generatedKey = -1;

//         Connection connection = DatabaseConnectionManager.getConnection();
//         String sql = "INSERT INTO accounts (customer_id, balance) VALUES (?, ?)";
//         try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
//             statement.setInt(1, accounts.getCustomerId());
//             statement.setDouble(2, accounts.getBalance());
            
//             statement.executeUpdate();

//             ResultSet resultSet = statement.getGeneratedKeys();

//             if(resultSet.next()){
//                 generatedKey = resultSet.getInt(1);
//                 accounts.setAccountId(generatedKey);
//             }
//         } finally{
//             if(connection != null) connection.close();
//         }

//         return generatedKey;
//     }

//     @Override
//     public Accounts getAccountById(int accountId) throws SQLException {

//         Accounts account = null;

//         Connection connection = DatabaseConnectionManager.getConnection();
//         String sql = "SELECT * FROM accounts WHERE account_id = ?";
//         try (PreparedStatement statement = connection.prepareStatement(sql)) {
//             statement.setInt(1, accountId);
            
//             ResultSet resultSet = statement.executeQuery();

//             if(resultSet.next()){
//                 int customerId = resultSet.getInt("customer_id");
//                 double balance = resultSet.getDouble("balance");
//                 account = new Accounts(accountId, customerId, balance);
//             }
//         } finally{
//             if(connection != null) connection.close();
//         }

//         return account;
//     }

//     @Override
//     public void updateAccount(Accounts accounts) throws SQLException {
//         Connection connection = DatabaseConnectionManager.getConnection();
//         String sql = "UPDATE accounts SET customer_id = ?, balance = ? WHERE account_id = ?";
//         try (PreparedStatement statement = connection.prepareStatement(sql)) {
//             statement.setInt(1, accounts.getCustomerId());            
//             statement.setDouble(2, accounts.getBalance());            
//             statement.setInt(3, accounts.getAccountId());

//             statement.executeUpdate();

//         } finally{
//             if(connection != null) connection.close();
//         }
//     }

//     @Override
//     public void deleteAccount(int accountId) throws SQLException {
//         Connection connection = DatabaseConnectionManager.getConnection();
//         String sql = "DELETE FROM accounts WHERE account_id = ?";
//         try (PreparedStatement statement = connection.prepareStatement(sql)) {
//             statement.setInt(1, accountId);
//             statement.executeUpdate();
//         } finally{
//             if(connection != null) connection.close();
//         }
//     }

//     @Override
//     public List<Accounts> getAllAccounts() throws SQLException {
//         List<Accounts> accountsList = new ArrayList<Accounts>();

//         Connection connection = DatabaseConnectionManager.getConnection();
//         String sql = "SELECT * FROM accounts";
//         try (PreparedStatement statement = connection.prepareStatement(sql)) {
//             ResultSet resultSet = statement.executeQuery();

//             while(resultSet.next()){
//                 int accountId = resultSet.getInt("account_id");
//                 int customerId = resultSet.getInt("customer_id");
//                 double balance = resultSet.getDouble("balance");

//                 Accounts accounts = new Accounts(accountId, customerId, balance);
//                 accountsList.add(accounts);
//             }
//         } finally{
//             if(connection != null) connection.close();
//         }

//         return accountsList;
//     }

// }
