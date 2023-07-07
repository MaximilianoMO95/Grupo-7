package demo.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Client {
        public int id;
        public String run;
        public String dv;
        public String name;
        public String surname;
        public String address;
        public String comuna;
        public String tel;
        private Account account;

        public Client(String run, String dv, String name, String surname, String address, String comuna, String tel, Account account) {
                this.run        = run;
                this.dv         = dv;
                this.name       = name;
                this.surname    = surname;
                this.address    = address;
                this.comuna     = comuna;
                this.tel        = tel;


                this.account = account;
        }
        
        public Account getAccount() {
                return account;
        }
}

class BuscameUnLugar {
        // Donde deberian ir esto ???
        public void create(Client client, Connection conn) {
                String query = "INSERT INTO cliente VALUES (?, ?, ?, ?, ?, ?, ?)";

                try (conn) {
                        PreparedStatement statement = conn.prepareStatement(query);

                        statement.setString(1, client.run);
                        statement.setString(2, client.dv);
                        statement.setString(3, client.name);
                        statement.setString(4, client.surname);
                        statement.setString(5, client.address);
                        statement.setString(6, client.tel);
                        statement.setString(7, client.comuna);
                        statement.setString(8, client.address);

                        statement.executeUpdate();

                } catch (SQLException e) { e.printStackTrace(); }
        }

        public void getClientByRun(String run, Connection conn) {
                String query = "SELECT * FROM cliente WHERE run = ?";

                try (conn) {
                        PreparedStatement statement = conn.prepareStatement(query);

                        // ...

                } catch (SQLException e) { e.printStackTrace(); }
        }
}
