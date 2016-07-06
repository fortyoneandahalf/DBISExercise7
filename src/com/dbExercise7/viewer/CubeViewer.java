package com.dbExercise7.viewer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dbExercise7.util.ConsoleUtil;
import com.dbExercise7.util.DB2ConnectionManager;
import com.dbExercise7.util.FormUtil;

public class CubeViewer
{
	private static final int SHOP = 1;
	private static final int TIME = 2;
	private static final int PRODUCT = 3;
	private static final int EXIT = 0;
	
	private static int shopLevel = 2;
	private static int timeLevel = 3;
	private static int productLevel = 1;
	
//	private static final String INITIAL_QUERY = 
//			"SELECT " +
//				"c.name AS City, " +
//				"MONTH(s.date) AS Month, " +
//				"a.name AS Product, " +
//				"SUM(s.quantity) AS Units, " +
//				"SUM(s.amount) AS Sales " +
//			"FROM SALES s " +
//			"LEFT JOIN SHOPID sid " +
//				"ON sid.shopid = s.shopid " +
//			"LEFT JOIN STADTID c " +
//				"ON c.stadtid = sid.stadtid " +
//			"LEFT JOIN ARTICLEID a " +
//				"ON a.articleid = s.articleid " +
//			"GROUP BY CUBE(c.name, MONTH(s.date), a.name)";
	
//	private static final String INITIAL_QUERY = 
//	"SELECT " +
//		"c.name AS City, " +
//		"MONTH(s.date) AS Month, " +
//		"a.name AS Product, " +
////		"(SELECT a.name FROM ARTICLEID a WHERE a.articleid = s.articleid) AS Product, " +
//		"SUM(s.quantity) AS Units, " +
//		"SUM(s.amount) AS Sales " +
//	"FROM SALES s " +
//	"LEFT JOIN SHOPID sid " +
//		"ON sid.shopid = s.shopid " +
//	"LEFT JOIN STADTID c " +
//		"ON c.stadtid = sid.stadtid " +
//	"LEFT JOIN ARTICLEID a " +
//		"ON a.articleid = s.articleid " +
////	"GROUP BY CUBE(c.name, MONTH(s.date))";
//	"GROUP BY ROLLUP (c.name, MONTH(s.date), a.name, ())";
	
	private static Connection conn;

	public static void main(String[] args)
	{
		ResultSet rs = runQuery(buildQuery());
		ConsoleUtil.displayResults(rs);
		
		boolean exit = showMenu();
		
		while(exit == false)
		{
			try
			{
				rs.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			
			rs = runQuery(buildQuery());
			ConsoleUtil.displayResults(rs);
			
			exit = showMenu();
		}
		
		DB2ConnectionManager.getInstance().closeConnection();
		
		System.out.println("Goodbye.");
	}
	
	private static boolean showMenu()
	{
		boolean end = false;
		
		Menu navMenu = new Menu("Cube Navigation:");
		navMenu.addEntry("Shop dimension", SHOP);
		navMenu.addEntry("Time dimension", TIME);
		navMenu.addEntry("Product dimension", PRODUCT);
		navMenu.addEntry("Exit", EXIT);
		int selection = navMenu.show();
		
		switch(selection)
		{
		case(SHOP):
		{
			shopLevel = FormUtil.readInt("Select level to group at (1 = Shop to 4 = Country)");
			break;
		}
		case(TIME):
		{
			timeLevel = FormUtil.readInt("Select level to group at (1 = Day to 4 = Year)");
			break;
		}
		case(PRODUCT):
		{
			productLevel = FormUtil.readInt("Select level to group at (1 = Article to 4 = Product Category)");
			break;
		}
		case(EXIT):
		{
			end = true;
			break;
		}
		}
		
		return end;
	}
	
	private static String buildQuery()
	{
		String select = "SELECT ";
		String from = " FROM SALES ";
		String join = "";
		String group = "";
		
		switch(shopLevel)
		{
		case(1):
		{
			select += "shopid.name AS Shop, ";
			join += "LEFT JOIN shopid ON shopid.shopid = sales.shopid ";
			group += "shopid.name, ";
			break;
		}
		case(2):
		{
			select += "stadtid.name AS City, ";
			join += "LEFT JOIN shopid ON shopid.shopid = sales.shopid " +
					"LEFT JOIN stadtid ON stadtid.stadtid = shopid.stadtid ";
			group += "stadtid.name, ";
			break;
		}
		case(3):
		{
			select += "regionid.name AS Region, ";
			join += "LEFT JOIN shopid ON shopid.shopid = sales.shopid "+
					"LEFT JOIN stadtid ON stadtid.stadtid = shopid.stadtid " +
					"LEFT JOIN regionid ON regionid.regionid = stadtid.regionid ";
			group += "regionid.name, ";
			break;
		}
		case(4):
		{
			select += "landid.name AS Country, ";
			join += "LEFT JOIN shopid ON shopid.shopid = sales.shopid " +
					"LEFT JOIN stadtid ON stadtid.stadtid = shopid.stadtid " +
					"LEFT JOIN regionid ON regionid.regionid = stadtid.regionid " +
					"LEFT JOIN landid ON landid.landid = regionid.landid ";
			group += "landid.name,";
			break;
		}
		}
		
		switch(timeLevel)
		{
		case(1):
		{
			select += "DAY(sales.date) AS Day, ";
			group += "DAY(sales.date), ";
			break;
		}
		case(2):
		{
			select += "WEEK(sales.date) AS Week, ";
			group += "WEEK(sales.date), ";
			break;
		}
		case(3):
		{
			select += "MONTH(sales.date) AS Month, ";
			group += "MONTH(sales.date), ";
			break;
		}
		case(4):
		{
			select += "YEAR(sales.date) AS Year, ";
			group += "YEAR(sales.date), ";
			break;
		}
		}
		
		switch(productLevel)
		{
		case(1):
		{
			select += "articleid.name AS Product, ";
			join += "LEFT JOIN articleid ON articleid.articleid = sales.articleid ";
			group += "articleid.name";
			break;
		}
		case(2):
		{
			select += "productgroupid.name AS ProductGroup, ";
			join += "LEFT JOIN articleid ON articleid.articleid = sales.articleid " +
					"LEFT JOIN productgroupid ON productgroupid.productgroupid = articleid.productgroupid ";
			group += "productgroupid.name";
			break;
		}
		case(3):
		{
			select += "productfamilyid.name AS ProductFamily, ";
			join += "LEFT JOIN articleid ON articleid.articleid = sales.articleid, " +
					"LEFT JOIN productgroupid ON productgroupid.productgroupid = articleid.productgroupid " +
					"LEFT JOIN productfamilyid ON productfamilyid.productfamilyid = productgroupid.productfamilyid ";
			group += "productfamilyid.name";
			break;
		}
		case(4):
		{
			select += "productcategoryid.name AS ProductCategory, ";
			join += "LEFT JOIN articleid ON articleid.articleid = sales.articleid " +
					"LEFT JOIN productgroupid ON productgroupid.productgroupid = articleid.productgroupid " +
					"LEFT JOIN productfamilyid ON productfamilyid.productfamilyid = productgroupid.productfamilyid " +
					"LEFT JOIN productcategoryid ON productcategoryid.productcategoryid = productfamilyid.productcategoryid ";
			group += "productcategoryid.name";
			break;
		}
		}
		
		select += "SUM(sales.quantity) AS Units, SUM(sales.amount) AS Sales";
		group = " GROUP BY CUBE(" + group + ")";
		
		String query = select + from + join + group;
		return query;
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
