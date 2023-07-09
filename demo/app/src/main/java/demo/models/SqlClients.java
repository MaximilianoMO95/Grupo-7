package demo.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import java.util.logging.Level;

import javax.annotation.Nullable;

public class SqlClients extends MysqlConnect {

        public boolean register(Client client, Account account) {
                String query = "INSERT INTO cliente (run, dv, nombre, ap_paterno, ap_materno, tel, domicilio, comuna) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                String accountQuery = "INSERT INTO cuenta (descripcion, numero, saldo, cliente_id) VALUES (?, ?, ?, ?)";

                try (Connection conn = connect();
                                PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                                PreparedStatement accountPs = conn.prepareStatement(accountQuery)) {

                        conn.setAutoCommit(false);

                        // Insert client
                        ps.setString(1, client.run);
                        ps.setString(2, client.dv);
                        ps.setString(3, client.name);
                        ps.setString(4, client.ap_paterno);
                        ps.setString(5, client.ap_materno);
                        ps.setString(6, client.tel);
                        ps.setString(7, client.address);
                        ps.setString(8, client.comuna);
                        ps.executeUpdate();

                        // Get the generated client ID
                        ResultSet generatedKeys = ps.getGeneratedKeys();
                        if (generatedKeys.next()) {
                                int clientId = generatedKeys.getInt(1);

                                // Insert account
                                accountPs.setString(1, account.getDescription());
                                accountPs.setInt(2, account.getAccountNumber());
                                accountPs.setInt(3, 0);
                                accountPs.setInt(4, clientId);
                                accountPs.executeUpdate();

                                conn.commit();
                                return true;
                        } else {
                                conn.rollback();
                                return false;
                        }

                } catch (SQLException e) {
                        Logger.getLogger(SqlClients.class.getName()).log(Level.SEVERE, null, e);
                        return false;
                }
        }

        @Nullable
        public Client searchByRun(String run) {
                Client client = null;
                String query = "SELECT * FROM cliente WHERE run = ?";

                try {
                        Connection conn = connect();
                        PreparedStatement ps = conn.prepareStatement(query);

                        ps.setInt(1, Integer.parseInt(run));
                        ResultSet result = ps.executeQuery();

                        if (result.next()) {
                                int id = result.getInt("id");
                                String dv = result.getString("dv");
                                String name = result.getString("nombre");
                                String ap_paterno = result.getString("ap_paterno");
                                String ap_materno = result.getString("ap_materno");
                                String tel = result.getString("tel");
                                String address = result.getString("domicilio");
                                String comuna = result.getString("comuna");

                                client = new Client(id, run, dv, name, ap_paterno, ap_materno, tel, address, comuna);
                        }

                } catch (SQLException e) {
                        Logger.getLogger(SqlClients.class.getName()).log(Level.SEVERE, null, e);
                }

                return client;
        }
}
