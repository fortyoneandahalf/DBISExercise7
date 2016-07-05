package com.dbExercise7.viewer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import com.dbExercise7.util.ConsoleUtil;
import com.dbExercise7.util.DB2ConnectionManager;

public class CubeViewer
{
	private static final int ROLLUP = 1;
	private static final int DRILL_DOWN = 2;
	private static final int EXIT = 0;
	
	private static final String INITIAL_QUERY = 
			"SELECT " +
				"c.name AS City, " +
				"MONTH(s.date) AS Month, " +
				"a.name AS Product, " +
				"SUM(s.quantity) AS Units, " +
				"SUM(s.amount) AS Sales " +
			"FROM SALES s " +
			"LEFT JOIN SHOPID sid " +
				"ON sid.shopid = s.shopid " +
			"LEFT JOIN STADTID c " +
				"ON c.stadtid = sid.stadtid " +
			"LEFT JOIN ARTICLEID a " +
				"ON a.articleid = s.articleid " +
			"GROUP BY CUBE(c.name, MONTH(s.date), a.name)";
	
	private static Connection conn;

	public static void main(String[] args)
	{
		ResultSet rs = runQuery(INITIAL_QUERY);
		ConsoleUtil.displayResults(rs);
		
		showMenu();
		
		DB2ConnectionManager.getInstance().closeConnection();
		
		System.out.println("Goodbye.");
	}
	
	private static void showMenu()
	{
		Menu navMenu = new Menu("Cube Navigation:");
		navMenu.addEntry("Roll Up", ROLLUP);
		navMenu.addEntry("Drill Down", DRILL_DOWN);
		navMenu.addEntry("Exit", EXIT);
		int selection = navMenu.show();
		
		switch(selection)
		{
		case(ROLLUP):
		{
			Menu rollupMenu = new Menu("Select dimension to roll up:");
			
		}
		case(DRILL_DOWN):
		{
			Menu drillDownMenu = new Menu("Select dimension to drill down:");
		}
		case(EXIT): break;
		}
	}

	private static ResultSet runQuery(String queryString)
	{
		PreparedStatement pstmt;
		ResultSet rs = null;
		
		conn = DB2ConnectionManager.getInstance().getConnection();
		
		try
		{
			pstmt = conn.prepareStatement(queryString);
			
			rs = pstmt.executeQuery();
			
//			rs.close();
//			pstmt.close();
		}
		catch(SQLException e)
		{
			System.err.println("Error querying data warehouse, stack trace follows:");
			e.printStackTrace();
		}
		
		return rs;
	}
	
}
