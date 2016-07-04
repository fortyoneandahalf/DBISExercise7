package com.dbExercise7.createTables;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.dbExercise7.util.DB2ConnectionManager;

public class TEST {
	public static void main(String[] args) {
		
		System.out.println("Hello TEST!");
		try {
			// Get connection
			Connection con = DB2ConnectionManager.getInstance().getConnection();
			con.setAutoCommit(false);
		      System.out.println("**** Created a JDBC connection to the data source");

		      // Create the Statement
		      Statement stmt = con.createStatement();                                            
		      System.out.println("**** Created JDBC Statement object");

		      //URL url = ClassLoader.getSystemResource("createObjects.sql");
		      //URL url = ClassLoader.getSystemResource("createTablesSimple.sql");
		      //URL url = ClassLoader.getSystemResource("DropAllTables.sql");
		      
		     
		      // Execute a query and generate a ResultSet instance
		      ResultSet rs = stmt.executeQuery("SELECT COUNT(*) from VSISP51.SALES");                    
		      System.out.println("**** Created JDBC ResultSet object");

		      // Print all of the employee numbers to standard output device
		      while (rs.next()) {
		        int col1 = rs.getInt(1);
		        System.out.println("Table name = " + col1);
		      }
		      System.out.println("**** Fetched all rows from JDBC ResultSet");
		      // Close the ResultSet
		      rs.close();
		      System.out.println("**** Closed JDBC ResultSet");
		      
		      // Close the Statement
		      stmt.close();
		      System.out.println("**** Closed JDBC Statement");

		      // Connection must be on a unit-of-work boundary to allow close
		      con.commit();
		      System.out.println ( "**** Transaction committed" );
		    
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
