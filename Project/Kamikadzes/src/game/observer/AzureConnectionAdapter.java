/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//package dev.dbc;
package game.observer;

import java.sql.*;
import java.util.Properties;
import java.util.TimeZone;



/**
 *
 * @author nugal
 */
public class AzureConnectionAdapter {
    	private Connection connection;
	private static AzureConnectionAdapter instance;

    // Initialize connection variables. 

	protected AzureConnectionAdapter() {
		try {
			this.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static AzureConnectionAdapter getInstance() {
		if (instance == null) {
			synchronized(AzureConnectionAdapter.class) {
				if(instance == null) {
					instance = new AzureConnectionAdapter();
				}
			}
		}
		return instance;
	}
    public void connect ()  throws Exception
    {
        // Initialize connection variables. 
        String host = "";
        String database = "";
        String user = "";
        String password = "";

        System.out.println("MySQL JDBC driver detected in library path.");

        connection = null;

        // Initialize connection object
        try
        {
            String url = String.format("jdbc:mysql://%s:3306/%s", host, database);

            // Set connection properties.
            Properties properties = new Properties();
            properties.setProperty("user", user);
            properties.setProperty("password", password);
            properties.setProperty("useSSL", "true");
            properties.setProperty("verifyServerCertificate", "true");
            properties.setProperty("requireSSL", "false");
            TimeZone timeZone = TimeZone.getTimeZone("Europe/Vilnius"); // e.g. "Europe/Rome"
            TimeZone.setDefault(timeZone);

            // get connection
            connection = DriverManager.getConnection(url, properties);
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
    
    public int getHighScore() {
    	return 80;
    }
    public boolean setHighScore(int highScore, String name) {
    	return true;
    }
}
