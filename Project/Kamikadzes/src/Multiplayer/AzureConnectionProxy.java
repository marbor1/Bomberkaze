/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Multiplayer;

/**
 *
 * @author a.pocius
 */

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class AzureConnectionProxy implements MySQLConnection {
    private Connection connection;
    private AzureConnection azureconnection;

    private static List<String> bannedUsers;

    static
    {
        bannedUsers = new ArrayList<String>();
        bannedUsers.add("simpledare");
    }


    public AzureConnectionProxy(String username) {
        try {
            this.connect(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connect(String username) throws Exception {

        if (bannedUsers.contains(username)) {
            try {

                String host = "bomberman-mysqldbserver.mysql.database.azure.com";
                String database = "bombermandatabase";
                String user = "adminbomberman@bomberman-mysqldbserver";
                String password = "Admin123";

                String url = String.format("jdbc:mysql://%s/%s", host, database);

                Connection connection = DBconnection(username);

                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO logs (username, description, date) VALUES (?, ?, ?);");
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, "This user is blocked");

                preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                preparedStatement.executeUpdate();



            } catch (SQLException e) {
                throw new SQLException("Encountered an error when executing given sql statement.", e);
            }

        }
        azureconnection = new AzureConnection();
    }

    public Connection DBconnection(String username) throws Exception {

            String host = "bomberman-mysqldbserver.mysql.database.azure.com";
            String database = "bombermandatabase";
            String user = "adminbomberman@bomberman-mysqldbserver";
            String password = "Admin123";

            String url = String.format("jdbc:mysql://%s/%s", host, database);

            Connection connection = DriverManager.getConnection(url, "adminbomberman@bomberman-mysqldbserver", password);

        if (connection != null) {
            System.out.println("Successfully created connection to database.");

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO logs (username, description, date) VALUES (?, ?, ?);");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, "Successfully created connection to database.");

            preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            preparedStatement.executeUpdate();
        }
        return connection;
    }

}
