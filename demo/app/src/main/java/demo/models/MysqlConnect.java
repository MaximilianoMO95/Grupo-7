package demo.models;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnect {
        private final String DB_URL;
        private final String DB_USER;
        private final String DB_PASSWORD;

        private Connection connection;

        public MysqlConnect() {
                Dotenv dotenv = Dotenv.configure()
                        .directory("assets/config.env")
                        .ignoreIfMalformed()
                        .ignoreIfMissing()
                        .load();

                DB_URL = dotenv.get("DB_URL");
                DB_USER = dotenv.get("DB_USER");
                DB_PASSWORD = dotenv.get("DB_PASSWORD");
        }

        public Connection connect() {
                // Create new connection
                if (connection == null) {
                        try {
                                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

                        } catch (SQLException e) { e.printStackTrace(); }
                }

                return connection;
        }

        public void disconnect() {
                if (connection == null) { return; }

                try {
                        connection.close();
                        connection = null;

                } catch (SQLException e) { e.printStackTrace(); }
        }
}
