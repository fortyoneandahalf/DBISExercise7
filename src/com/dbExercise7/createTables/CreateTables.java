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

public class CreateTables {
	public static void main(String[] args) {
		dropTables();
		createTables();
		//OTHERS MIGHT BE USEFUL LATER - So they are still somewhere in this file.
		//createInitialObjects();
		//showAllData();
	}
	
	
	
	public static void createTables(){
		System.out.println("Hello");
		try {
			// Get connection
			Connection con = DB2ConnectionManager.getInstance().getConnection();
			con.setAutoCommit(false);
		      System.out.println("**** Created a JDBC connection to the data source");

		      // Create the Statement
		      Statement stmt = con.createStatement();                                            
		      System.out.println("**** Created JDBC Statement object");

		      //URL url = ClassLoader.getSystemResource("createObjects.sql");
		      URL url = ClassLoader.getSystemResource("createTablesSimple.sql");
		      //URL url = ClassLoader.getSystemResource("DropAllTables.sql");
		      
		      String sql= "";
		      Scanner scanIn = null;
		      try {
		    	  scanIn = new Scanner(new File(url.toURI()));
		    	  while(scanIn.hasNext()){
		    		  sql = scanIn.nextLine();
		    		  sql = sql.replace(";", "");
		    		  if(sql.length()>5){
		    			  System.out.println(sql);
		    			  stmt.executeUpdate(sql);
		    			  //con.commit();
		    		  }
		    	  }
		    	  System.out.println(sql);
		      } catch (FileNotFoundException e) {
		    	  e.printStackTrace();
		      } catch (URISyntaxException e) {
		    	  e.printStackTrace();
		      }
		      
		      // Execute a query and generate a ResultSet instance
		      ResultSet rs = stmt.executeQuery("select TABNAME from syscat.tables where tabschema = 'VSISP51'");                    
		      System.out.println("**** Created JDBC ResultSet object");

		      // Print all of the employee numbers to standard output device
		      while (rs.next()) {
		        String col1 = rs.getString(1);
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
	
	public static void dropTables(){
		System.out.println("Hello");
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
		      URL url = ClassLoader.getSystemResource("DropAllTables.sql");
		      
		      String sql= "";
		      Scanner scanIn = null;
		      try {
		    	  scanIn = new Scanner(new File(url.toURI()));
		    	  while(scanIn.hasNext()){
		    		  sql = scanIn.nextLine();
		    		  sql = sql.replace(";", "");
		    		  if(sql.length()>5){
		    			  System.out.println(sql);
		    			  stmt.executeUpdate(sql);
		    			  //con.commit();
		    		  }
		    	  }
		    	  System.out.println(sql);
		      } catch (FileNotFoundException e) {
		    	  e.printStackTrace();
		      } catch (URISyntaxException e) {
		    	  e.printStackTrace();
		      }
		      
		      // Execute a query and generate a ResultSet instance
		      ResultSet rs = stmt.executeQuery("select TABNAME from syscat.tables where tabschema = 'VSISP51'");                    
		      System.out.println("**** Created JDBC ResultSet object");

		      // Print all of the employee numbers to standard output device
		      while (rs.next()) {
		        String col1 = rs.getString(1);
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
	
	public static void dropOnlySales(){
		System.out.println("Hello");
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
		      URL url = ClassLoader.getSystemResource("DropOnlySales.sql");
		      
		      String sql= "";
		      Scanner scanIn = null;
		      try {
		    	  scanIn = new Scanner(new File(url.toURI()));
		    	  while(scanIn.hasNext()){
		    		  sql = scanIn.nextLine();
		    		  sql = sql.replace(";", "");
		    		  if(sql.length()>5){
		    			  System.out.println(sql);
		    			  stmt.executeUpdate(sql);
		    			  //con.commit();
		    		  }
		    	  }
		    	  System.out.println(sql);
		      } catch (FileNotFoundException e) {
		    	  e.printStackTrace();
		      } catch (URISyntaxException e) {
		    	  e.printStackTrace();
		      }
		      
		      // Execute a query and generate a ResultSet instance
		      ResultSet rs = stmt.executeQuery("select TABNAME from syscat.tables where tabschema = 'VSISP51'");                    
		      System.out.println("**** Created JDBC ResultSet object");

		      // Print all of the employee numbers to standard output device
		      while (rs.next()) {
		        String col1 = rs.getString(1);
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
	
	public static void createInitialObjects(){
		System.out.println("Hello");
		try {
			// Get connection
			Connection con = DB2ConnectionManager.getInstance().getConnection();
			con.setAutoCommit(false);
		      System.out.println("**** Created a JDBC connection to the data source");

		      // Create the Statement
		      Statement stmt = con.createStatement();                                            
		      System.out.println("**** Created JDBC Statement object");

		      //URL url = ClassLoader.getSystemResource("createObjects.sql");
		      URL url = ClassLoader.getSystemResource("DropAllTables.sql");
		      
		      String sql= "";
		      Scanner scanIn = null;
		      try {
		    	  scanIn = new Scanner(new File(url.toURI()));
		    	  while(scanIn.hasNext()){
		    		  sql = scanIn.nextLine();
		    		  sql = sql.replace(";", "");
		    		  if(sql.length()>5){
		    			  System.out.println(sql);
		    			  stmt.executeUpdate(sql);
		    			  //con.commit();
		    		  }
		    	  }
		    	  System.out.println(sql);
		      } catch (FileNotFoundException e) {
		    	  e.printStackTrace();
		      } catch (URISyntaxException e) {
		    	  e.printStackTrace();
		      }
		      
		      // Execute a query and generate a ResultSet instance
		      ResultSet rs = stmt.executeQuery("select TABNAME from syscat.tables where tabschema = 'VSISP51'");                    
		      System.out.println("**** Created JDBC ResultSet object");

		      // Print all of the employee numbers to standard output device
		      while (rs.next()) {
		        String col1 = rs.getString(1);
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
	
	public static void showAllData(){
		System.out.println("Hello");
		try {
			// Get connection
			Connection con = DB2ConnectionManager.getInstance().getConnection();
			
		      //System.out.println("**** Created a JDBC connection to the data source");

		      // Create the Statement
		      Statement stmt = con.createStatement();                                            
		      //System.out.println("**** Created JDBC Statement object");
		      
		      String[] tableNames = {"APARTMENT", "CONTRACT", "ESTATE", "ESTATEAGENT", "HOUSE", "PERSON", "PURCHASECONTRACT", "TENANCYCONTRACT"};
		      
		      for (String tableName : tableNames) {
		    	// Execute a query and generate a ResultSet instance
			      ResultSet rs = stmt.executeQuery("select * from "+tableName);                    
			      //System.out.println("**** Created JDBC ResultSet object");
			      
			      System.out.println("Table "+tableName);
			      // Print all of the data to standard output device
			      int columnCount = rs.getMetaData().getColumnCount();
			      for (int i = 1; i <= columnCount; i++) {
		    		  System.out.print(rs.getMetaData().getColumnName(i));
		    		  if(i<columnCount){
		    			  System.out.print(" - ");
		    		  }
		    	  }
		    	  System.out.println();
			      while (rs.next()) {
			    	  for (int i = 1; i <= columnCount; i++) {
			    		  System.out.print(rs.getString(i));
			    		  if(i<columnCount){
			    			  System.out.print(" - ");
			    		  }
			    	  }
			    	  System.out.println();
			      }
			      //System.out.println("**** Fetched all rows from JDBC ResultSet");
			      // Close the ResultSet
			      rs.close();
			      //System.out.println("**** Closed JDBC ResultSet");
		      }
		      
		      // Close the Statement
		      stmt.close();
		      //System.out.println("**** Closed JDBC Statement");

		      // Connection must be on a unit-of-work boundary to allow close
		      //con.commit();
		      //System.out.println ( "**** Transaction committed" );
		    
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
