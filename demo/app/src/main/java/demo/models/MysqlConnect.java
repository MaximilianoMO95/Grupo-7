package demo.models;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.logging.Logger;
import java.util.logging.Level;

public class MysqlConnect {
        private static final String ENVDIR = "src/main/java/demo/assets";
        private final String DB_URL;
        private final String DB_USER;
        private final String DB_PASSWORD;

        private Connection connection;

        // Default
        public MysqlConnect() {
                Dotenv dotenv = Dotenv.configure()
                        .directory(ENVDIR)
                        .filename("config.env")
                        .load();

                DB_URL = dotenv.get("DB_URL");
                DB_USER = dotenv.get("DB_USERNAME");
                DB_PASSWORD = dotenv.get("DB_PASSWORD");
        }

        public MysqlConnect(String fileName) {
                Dotenv dotenv = Dotenv.configure()
                        .directory(ENVDIR)
                        .filename(fileName)
                        .load();

                DB_URL = dotenv.get("DB_URL");
                DB_USER = dotenv.get("DB_USERNAME");
                DB_PASSWORD = dotenv.get("DB_PASSWORD");
        }

        public Connection connect() {
                // Create new connection
                if (connection == null) {
                        try {
                                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

                        } catch (SQLException e) {
                                Logger.getLogger(MysqlConnect.class.getName()).log(Level.SEVERE, null, e);
                        }
                }

                return connection;
        }

        public void disconnect() {
                if (connection == null) {
                        return;
                }

                try {
                        connection.close();
                        connection = null;

                } catch (SQLException e) {
                        Logger.getLogger(MysqlConnect.class.getName()).log(Level.SEVERE, null, e);
                }
        }
}
