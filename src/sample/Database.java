package sample;

import java.sql.*;

public class Database extends Config{
    Connection dbConnection;
    public Connection getDbConnection()
        throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:postgresql://"+dbHost +":"
                + dbPort + "/" + dbName;
        Class.forName("org.postgresql.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser,dbPass);

        return dbConnection;
    }
}
