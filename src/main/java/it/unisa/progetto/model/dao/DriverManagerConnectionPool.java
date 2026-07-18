package it.unisa.progetto.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DriverManagerConnectionPool {
    private static List<Connection> freeDbConnections;

    static {
        freeDbConnections = new LinkedList<Connection>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver non trovato: " + e.getMessage());
        }
    }

    private static synchronized Connection createDBConnection() throws SQLException {
        Connection newConnection = null;
        String ip = "localhost";
        String port = "3306";
        String dbName = "vivaio_db"; // Il nome del database creato su Workbench
        String username = "root";
        String password = "root"; // <--- INSERISCI QUI LA TUA PASSWORD DI WORKBENCH

        newConnection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", username, password);
        return newConnection;
    }

    public static synchronized Connection getConnection() throws SQLException {
        Connection connection;
        if (!freeDbConnections.isEmpty()) {
            connection = freeDbConnections.get(0);
            freeDbConnections.remove(0);
            try {
                if (connection.isClosed()) {
                    connection = getConnection();
                }
            } catch (SQLException e) {
                connection.isClosed();
                connection = getConnection();
            }
        } else {
            connection = createDBConnection();
        }
        return connection;
    }

    public static synchronized void releaseConnection(Connection connection) throws SQLException {
        if (connection != null) {
            freeDbConnections.add(connection);
        }
    }
}