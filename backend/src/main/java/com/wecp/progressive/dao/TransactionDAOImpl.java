// package com.wecp.progressive.dao;

// import com.wecp.progressive.config.DatabaseConnectionManager;
// import com.wecp.progressive.entity.Transactions;

// import java.sql.*;
// import java.util.ArrayList;
// import java.util.Date;
// import java.util.List;

// public class TransactionDAOImpl implements TransactionDAO {

//     @Override
//     public int addTransaction(Transactions transaction) throws SQLException {
        
//         int generatedKey = -1;
//         Connection connection = DatabaseConnectionManager.getConnection();
        
//         String sql = "INSERT INTO transactions (account_id, amount, transaction_date, transaction_type) VALUES (?, ?, ?, ?)";
//         try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS) ) {
//             statement.setInt(1, transaction.getAccountId());
//             statement.setDouble(2, transaction.getAmount());
//             statement.setTimestamp(3,new Timestamp( transaction.getTransactionDate().getTime()));
//             statement.setString(4, transaction.getTransactionType());

//             statement.executeUpdate();
//             ResultSet resultSet = statement.getGeneratedKeys();

//             if(resultSet.next()){
//                 generatedKey = resultSet.getInt(1);
//                 transaction.setTransactionId(generatedKey);
//             }
//         }finally {
//             if(connection != null) connection.close();
//         }

//         return generatedKey;
//     }

//     @Override
//     public Transactions getTransactionById(int transactionId) throws SQLException {
//         Transactions transactions = null;
        
//         Connection connection = DatabaseConnectionManager.getConnection();
        
//         String sql = "SELECT * FROM transactions WHERE transaction_id = ?";
//         try (PreparedStatement statement = connection.prepareStatement(sql) ) {
//             statement.setInt(1, transactionId);

//             ResultSet resultSet = statement.executeQuery();

//             if (resultSet.next()) {
//                 int accountId = resultSet.getInt("account_id");
//                 double amount = resultSet.getDouble("amount");
//                 Date transactionDate = resultSet.getTimestamp("transaction_date");
//                 String transactionType = resultSet.getString("transaction_type");

//                 transactions = new Transactions(transactionId, accountId, amount, transactionDate, transactionType);
//             }
//         }finally {
//             if(connection != null) connection.close();
//         }
//         return transactions;
//     }

//     @Override
//     public void updateTransaction(Transactions transaction) throws SQLException {
//         Connection connection = DatabaseConnectionManager.getConnection();
//         String sql = "UPDATE transactions SET account_id = ?, amount = ?, transaction_date = ?, transaction_type = ? WHERE transaction_id = ?";

//         try(PreparedStatement statement =connection.prepareStatement(sql) ) {
//             statement.setInt(1, transaction.getAccountId());
//             statement.setDouble(2, transaction.getAmount());
//             statement.setTimestamp(3,new Timestamp( transaction.getTransactionDate().getTime()));
//             statement.setString(4, transaction.getTransactionType());
//             statement.setInt(5, transaction.getTransactionId());
//             statement.executeUpdate();
//         }  finally {
//             if (connection != null) {
//                 connection.close();
//             }
//         }
//     }

//     @Override
//     public void deleteTransaction(int transactionId) throws SQLException {
//         Connection connection = DatabaseConnectionManager.getConnection();
        
//         String sql = "DELETE FROM transactions WHERE transaction_id = ?";
//         try (PreparedStatement statement = connection.prepareStatement(sql) ) {
//             statement.setInt(1, transactionId);
//             statement.executeUpdate();
//         }finally {
//             if(connection != null) connection.close();
//         }
//     }

//     @Override
//     public List<Transactions> getAllTransactions() throws SQLException {
//         List<Transactions> transactionsList = new ArrayList<Transactions>();
//         Connection connection = DatabaseConnectionManager.getConnection();
        
//         String sql = "SELECT * FROM transactions";
//         try (PreparedStatement statement = connection.prepareStatement(sql) ) {
//             ResultSet resultSet = statement.executeQuery();

//             while (resultSet.next()) {
//                 int transactionId = resultSet.getInt("transaction_id");
//                 int accountId = resultSet.getInt("account_id");
//                 double amount = resultSet.getDouble("amount");
//                 Date transactionDate = resultSet.getTimestamp("transaction_date");
//                 String transactionType = resultSet.getString("transaction_type");

//                 Transactions transactions = new Transactions(transactionId, accountId, amount, transactionDate, transactionType);
//                 transactionsList.add(transactions);
//             }
//         }finally {
//             if(connection != null) connection.close();
//         }
//         return transactionsList;
//     }
// }
