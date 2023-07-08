package demo.models;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.logging.Logger;
import java.util.logging.Level;

public class SqlClients extends MysqlConnect {

        public boolean register(Client client) {
                String query =
                        "INSERT INTO cliente (run, dv, nombre, ap_paterno, ap_materno, tel, direccion, comuna)"
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
                ; 
                
                try {
                        PreparedStatement ps = connect().prepareStatement(query);

                        ps.setString(1, client.run);
                        ps.setString(2, client.dv);
                        ps.setString(3, client.name);
                        ps.setString(4, client.ap_paterno);
                        ps.setString(5, client.ap_materno);
                        ps.setString(6, client.tel);
                        ps.setString(7, client.address);
                        ps.setString(8, client.comuna);

                        return true;

                } catch (SQLException e) {
                        Logger.getLogger(SqlClients.class.getName()).log(Level.SEVERE, null, e);
                        return false;
                }
        }
}
