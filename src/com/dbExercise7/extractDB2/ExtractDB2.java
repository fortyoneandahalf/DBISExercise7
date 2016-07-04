package com.dbExercise7.extractDB2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dbExercise7.data.*;
import com.dbExercise7.util.DB2ConnectionManager;


public class ExtractDB2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	public static void extractFromDB(){
		//Just copying from here.. nothing to see!
		ExtractDB2.getTableProductCategoryId();
		ExtractDB2.getTableProductFamilyId();
		ExtractDB2.getTableProductGroupId();
		ExtractDB2.getTableArticleId();
		ExtractDB2.getTableLandId();
		ExtractDB2.getTableRegionId();
		ExtractDB2.getTableStadtId();
		ExtractDB2.getTableShopId();
	}
	
	public static boolean getTableProductCategoryId(){
		try {
			// Get connection
			Connection con = DB2ConnectionManager.getInstance().getConnection();
			
			// Prepare Statement
			String selectSQL = "SELECT * FROM DB2INST1.PRODUCTCATEGORYID";
			PreparedStatement pstmt = con.prepareStatement(selectSQL);

			// Processing result
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductCategoryId object = new ProductCategoryId(rs.getInt("PRODUCTCATEGORYID"), rs.getString("NAME"));
				object.save();
			}
			rs.close();
			pstmt.close();
			DB2ConnectionManager.getInstance().closeConnection();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean getTableProductFamilyId(){
		try {
			// Get connection
			Connection con = DB2ConnectionManager.getInstance().getConnection();
			
			// Prepare Statement
			String selectSQL = "SELECT * FROM DB2INST1.PRODUCTFAMILYID";
			PreparedStatement pstmt = con.prepareStatement(selectSQL);

			// Processing result
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductFamilyId object = new ProductFamilyId(rs.getInt("PRODUCTFAMILYID"), rs.getInt("PRODUCTCATEGORYID"), rs.getString("NAME"));
				object.save();
			}
			rs.close();
			pstmt.close();
			DB2ConnectionManager.getInstance().closeConnection();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean getTableProductGroupId(){
		try {
			// Get connection
			Connection con = DB2ConnectionManager.getInstance().getConnection();
			
			// Prepare Statement
			String selectSQL = "SELECT * FROM DB2INST1.PRODUCTGROUPID";
			PreparedStatement pstmt = con.prepareStatement(selectSQL);

			// Processing result
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductGroupId object = new ProductGroupId(rs.getInt("PRODUCTGROUPID"), rs.getInt("PRODUCTFAMILYID"), rs.getString("NAME"));
				object.save();
			}
			rs.close();
			pstmt.close();
			DB2ConnectionManager.getInstance().closeConnection();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean getTableArticleId(){
		try {
			// Get connection
			Connection con = DB2ConnectionManager.getInstance().getConnection();
			
			// Prepare Statement
			String selectSQL = "SELECT * FROM DB2INST1.ARTICLEID";
			PreparedStatement pstmt = con.prepareStatement(selectSQL);

			// Processing result
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ArticleId object = new ArticleId(rs.getInt("ARTICLEID"), rs.getInt("PRODUCTGROUPID"), rs.getString("NAME"), rs.getDouble("PREIS"));
				object.save();
			}
			rs.close();
			pstmt.close();
			DB2ConnectionManager.getInstance().closeConnection();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean getTableLandId(){
		try {
			// Get connection
			Connection con = DB2ConnectionManager.getInstance().getConnection();
			
			// Prepare Statement
			String selectSQL = "SELECT * FROM DB2INST1.LANDID";
			PreparedStatement pstmt = con.prepareStatement(selectSQL);

			// Processing result
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				LandId object = new LandId(rs.getInt("LANDID"), rs.getString("NAME"));
				object.save();
			}
			rs.close();
			pstmt.close();
			DB2ConnectionManager.getInstance().closeConnection();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean getTableRegionId(){
		try {
			// Get connection
			Connection con = DB2ConnectionManager.getInstance().getConnection();
			
			// Prepare Statement
			String selectSQL = "SELECT * FROM DB2INST1.REGIONID";
			PreparedStatement pstmt = con.prepareStatement(selectSQL);

			// Processing result
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				RegionId object = new RegionId(rs.getInt("REGIONID"), rs.getInt("LANDID"), rs.getString("NAME"));
				object.save();
			}
			rs.close();
			pstmt.close();
			DB2ConnectionManager.getInstance().closeConnection();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean getTableStadtId(){
		try {
			// Get connection
			Connection con = DB2ConnectionManager.getInstance().getConnection();
			
			// Prepare Statement
			String selectSQL = "SELECT * FROM DB2INST1.STADTID";
			PreparedStatement pstmt = con.prepareStatement(selectSQL);

			// Processing result
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				StadtId object = new StadtId(rs.getInt("STADTID"), rs.getInt("REGIONID"), rs.getString("NAME"));
				object.save();
			}
			rs.close();
			pstmt.close();
			DB2ConnectionManager.getInstance().closeConnection();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean getTableShopId(){
		try {
			// Get connection
			Connection con = DB2ConnectionManager.getInstance().getConnection();
			
			// Prepare Statement
			String selectSQL = "SELECT * FROM DB2INST1.SHOPID";
			PreparedStatement pstmt = con.prepareStatement(selectSQL);

			// Processing result
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ShopId object = new ShopId(rs.getInt("SHOPID"), rs.getInt("STADTID"), rs.getString("NAME"));
				object.save();
			}
			rs.close();
			pstmt.close();
			DB2ConnectionManager.getInstance().closeConnection();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
