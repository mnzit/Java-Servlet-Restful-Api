package com.sudreeshya.day19.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Manjit Shakya <manjit.shakya@f1soft.com>
 */
public class DatabaseHelper {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(DbConfig.URL, DbConfig.USERNAME, DbConfig.PASSWORD);
    }

    public PreparedStatement init(String sql) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        return preparedStatement;
    }

    public int update() throws SQLException {
        return preparedStatement.executeUpdate();
    }

    public ResultSet execute() throws SQLException {
        return preparedStatement.executeQuery();
    }

    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            preparedStatement.close();
            connection.close();
        }
        connection = null;
        preparedStatement = null;
    }

}
