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

        public boolean register(Client client) {
                String query =
                        "INSERT INTO cliente (run, dv, nombre, ap_paterno, ap_materno, tel, domicilio, comuna, saldo_cuenta, numero_cuenta, tipo_cuenta)"
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
                ;
                boolean created = false;
                Account account = client.getAccount();

                try {
                        Connection conn = connect();
                        PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                        ps.setInt(1, Integer.parseInt(client.run));
                        ps.setString(2, client.dv);
                        ps.setString(3, client.name);
                        ps.setString(4, client.ap_paterno);
                        ps.setString(5, client.ap_materno);
                        ps.setString(6, client.tel);
                        ps.setString(7, client.address);
                        ps.setString(8, client.comuna);
                        ps.setInt(9, 0);
                        ps.setInt(10, account.accountNumber);
                        ps.setString(11, account.getDescription());
                        ps.executeUpdate();

                        created = true;

                } catch (SQLException e) {
                        Logger.getLogger(SqlClients.class.getName()).log(Level.SEVERE, null, e);
                        created = false;
                }
                
                return created;
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

                                String tipoCuenta = result.getString("tipo_cuenta");
                                int saldo = result.getInt("saldo");
                                int numeroCuenta = result.getInt("numero_cuenta");

                                Account account = new Account(numeroCuenta, saldo, tipoCuenta);

                                client = new Client(id, run, dv, name, ap_paterno, ap_materno, tel, address, comuna, account);
                        }

                } catch (SQLException e) {
                        Logger.getLogger(SqlClients.class.getName()).log(Level.SEVERE, null, e);
                }

                return client;
        }

        @Nullable
        public Client searchByAccountNumber(String number) {
                String query = "SELECT * FROM cliente WHERE numero_cuenta = ?";
                Client client = null;

                try {
                        Connection conn = connect();
                        PreparedStatement ps = conn.prepareStatement(query);

                        ps.setInt(1, Integer.parseInt(number));
                        ResultSet result = ps.executeQuery();

                        if (result.next()) {
                                String run = Integer.toString(result.getInt("run"));
                                int id = result.getInt("id");
                                String dv = result.getString("dv");
                                String name = result.getString("nombre");
                                String ap_paterno = result.getString("ap_paterno");
                                String ap_materno = result.getString("ap_materno");
                                String tel = result.getString("tel");
                                String address = result.getString("domicilio");
                                String comuna = result.getString("comuna");

                                String tipoCuenta = result.getString("tipo_cuenta");
                                int saldo = result.getInt("saldo");
                                int numeroCuenta = result.getInt("numero_cuenta");

                                Account account = new Account(numeroCuenta, saldo, tipoCuenta);

                                client = new Client(id, run, dv, name, ap_paterno, ap_materno, tel, address, comuna, account);
                        }

                } catch (SQLException e) {
                        Logger.getLogger(SqlClients.class.getName()).log(Level.SEVERE, null, e);
                }

                return client;
        }
}
