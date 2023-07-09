package demo.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.logging.Logger;
import java.util.logging.Level;

import javax.annotation.Nullable;


public class SqlAccounts extends MysqlConnect {

        public boolean newAccount(Account account) {
                String query =
                        "INSERT INTO cuenta (descripcion, numero, saldo, cliente_id)"
                        + "VALUES (?, ?, ?, ?)"
                ; 
                
                try {
                        Connection conn = connect();
                        PreparedStatement ps = conn.prepareStatement(query);

                        ps.setString(1, account.getDescription());
                        ps.setInt(2, account.getAccountNumber());
                        ps.setInt(3, 0);
                        ps.setInt(4, account.clientId);
                        ps.execute();

                        return true;

                } catch (SQLException e) {
                        Logger.getLogger(SqlAccounts.class.getName()).log(Level.SEVERE, null, e);
                        return false;
                }
        }

        @Nullable
        public Account searchByAccountNumber(String number) {
                String query = "SELECT * FROM cuenta WHERE numero = ?";
                Account account = null;
                
                try {
                        Connection conn = connect();
                        PreparedStatement ps = conn.prepareStatement(query);

                        ps.setInt(1, Integer.parseInt(number));
                        ResultSet result = ps.executeQuery();

                        if (result.next()) {
                                int id = result.getInt("id");
                                int clientId = result.getInt("cliente_id");
                                int balance = result.getInt("saldo");
                                String description = result.getString("descripcion");

                                account = new Account(id, clientId, Integer.parseInt(number), balance, description);
                        }

                } catch (SQLException e) {
                        Logger.getLogger(SqlAccounts.class.getName()).log(Level.SEVERE, null, e);
                }

                return account;
        }

        public boolean withdraw(int accountId, int amount) {
                String query = "UPDATE cuenta SET saldo = saldo + ? WHERE id = ?";
                
                try {
                        Connection conn = connect();
                        PreparedStatement ps = conn.prepareStatement(query);

                        ps.setInt(1, amount);
                        ps.setInt(2, accountId);
                        ps.execute();

                        int rowsAffected = ps.executeUpdate();
                        return (rowsAffected > 0);

                } catch (SQLException e) {
                        Logger.getLogger(SqlAccounts.class.getName()).log(Level.SEVERE, null, e);
                        return false;
                }
        }

        public boolean deposit(int fromAccountId, int toAccountId, int amount) {
                String query = "UPDATE cuenta SET saldo = saldo + ? WHERE id = ?";
                
                try {
                        Connection conn = connect();
                        PreparedStatement ps = conn.prepareStatement(query);
                        PreparedStatement ps = conn.prepareStatement(query);

                        ps.setInt(1, amount);
                        ps.setInt(2, toAccountId);
                        ps.execute();

                        int rowsAffected = ps.executeUpdate();
                        return (rowsAffected > 0);

                } catch (SQLException e) {
                        Logger.getLogger(SqlAccounts.class.getName()).log(Level.SEVERE, null, e);
                        return false;
                }
        }
}
