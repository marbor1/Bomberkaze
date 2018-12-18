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


public class AzureConnection implements MySQLConnection {
	
	private Connection connection;
	//private static AzureConnection instance;

    // Initialize connection variables.

	public AzureConnection() {
            try {
			this.connect(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
    @Override
    public void connect(String usr)  throws Exception
    {
        // Initialize connection variables. 
        String host = "bomberman-mysqldbserver.mysql.database.azure.com";
        String database = "bombermandatabase";
        String user = "adminbomberman@bomberman-mysqldbserver";
        String password = "Admin123";

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            throw new ClassNotFoundException("MySQL JDBC driver NOT detected in library path.", e);
        }
        
        System.out.println("MySQL JDBC driver detected in library path.");


        connection = null;

        // Initialize connection object
        try
        {
             String url ="jdbc:mysql://bomberman-mysqldbserver.mysql.database.azure.com:3306/bombermandatabase?useSSL=true&requireSSL=false";

            // get connection
            connection = DriverManager.getConnection(url, "adminbomberman@bomberman-mysqldbserver", password);

        }
        catch (SQLException e)
        {
            throw new SQLException("Failed to create connection to database.", e);
        }
        if (connection != null) 
        { 
            System.out.println("Successfully created connection to database.");
        }
        else {
            System.out.println("Failed to create connection to database.");
        }
        System.out.println("Execution finished.");
    }
}
