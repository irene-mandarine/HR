package com.webApp.HRdatabase.repository;

import com.webApp.HRdatabase.connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseRepository {

    DatabaseConnection databaseConnection = new DatabaseConnection();

    protected ResultSet executeQuery(String sqlQuery) {
        try {
            Connection connection = databaseConnection.establishConnection();
            Statement statement = connection.createStatement();
            return statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected void execute(String sqlQuery) {
        try {
            Connection connection = databaseConnection.establishConnection();
            Statement statement = connection.createStatement();
            statement.execute(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
