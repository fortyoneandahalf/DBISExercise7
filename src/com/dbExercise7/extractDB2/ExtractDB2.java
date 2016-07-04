package com.dbExercise7.extractDB2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dbExercise7.data.ProductCategoryId;
import com.dbExercise7.util.DB2ConnectionManager;


public class ExtractDB2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	public static void extractFromDB(){
		getTableProductCategoryId();
	}
	
	public static ArrayList<ProductCategoryId> getTableProductCategoryId(){
		ArrayList<ProductCategoryId> objects = new ArrayList<>();
		try {
			// Get connection
			Connection con = DB2ConnectionManager.getInstance().getConnection();
			
			// Prepare Statement
			String selectSQL = "SELECT * FROM DB2INST1.PRODUCTCATEGORYID";
			PreparedStatement pstmt = con.prepareStatement(selectSQL);

			// Processing result
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				objects.add(new ProductCategoryId(rs.getInt("PRODUCTCATEGORYID"), rs.getString("NAME")));
			}
			rs.close();
			pstmt.close();
			DB2ConnectionManager.getInstance().closeConnection();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return objects;
	}
	
	public static ArrayList<ProductFamilyId> getTableProductFamilyId(){
		ArrayList<ProductFamilyId> objects = new ArrayList<>();
		try {
			// Get connection
			Connection con = DB2ConnectionManager.getInstance().getConnection();
			
			// Prepare Statement
			String selectSQL = "SELECT * FROM DB2INST1.PRODUCTFAMILYID";
			PreparedStatement pstmt = con.prepareStatement(selectSQL);

			// Processing result
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				objects.add(new ProductFamilyId(rs.getInt("PRODUCTFAMILYID"), rs.getInt("PRODUCTCATEGORYID"), rs.getString("NAME")));
			}
			rs.close();
			pstmt.close();
			DB2ConnectionManager.getInstance().closeConnection();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return objects;
	}
	
	public static ArrayList<ProductGroupId> getTableProductGroupId(){
		ArrayList<ProductGroupId> objects = new ArrayList<>();
		try {
			// Get connection
			Connection con = DB2ConnectionManager.getInstance().getConnection();
			
			// Prepare Statement
			String selectSQL = "SELECT * FROM DB2INST1.PRODUCTGROUPID";
			PreparedStatement pstmt = con.prepareStatement(selectSQL);

			// Processing result
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				objects.add(new ProductGroupId(rs.getInt("PRODUCTGROUPID"), rs.getInt("PRODUCTFAMILYID"), rs.getString("NAME")));
			}
			rs.close();
			pstmt.close();
			DB2ConnectionManager.getInstance().closeConnection();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return objects;
	}
	
	public static ArrayList<ArticleId> getTableArticleId(){
		ArrayList<ArticleId> objects = new ArrayList<>();
		try {
			// Get connection
			Connection con = DB2ConnectionManager.getInstance().getConnection();
			
			// Prepare Statement
			String selectSQL = "SELECT * FROM DB2INST1.ARTICLEID";
			PreparedStatement pstmt = con.prepareStatement(selectSQL);

			// Processing result
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				objects.add(new ArticleId(rs.getInt("ARTICLEID"), rs.getInt("PRODUCTGROUPID"), rs.getString("NAME"), rs.getDouble("PREIS")));
			}
			rs.close();
			pstmt.close();
			DB2ConnectionManager.getInstance().closeConnection();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return objects;
	}
	
	public boolean save() {
		
		try {
			// Get connection
			Connection con = DB2ConnectionManager.getInstance().getConnection();
						
			String insertSQL = "INSERT INTO apartment(id, floor, rent, rooms, balcony, builtinkitchen) VALUES (?, ?, ?, ?, ?, ?)";

			PreparedStatement pstmt = con.prepareStatement(insertSQL);

			// Set parameters of the prepared statements.
			//pstmt.setInt(1, getId());
			//pstmt.setInt(2, getFloor());
			//pstmt.setFloat(3, getRent());
			//pstmt.setInt(4, getRooms());
			//pstmt.setInt(5, isBalcony()? 1 : 0);
			//pstmt.setInt(6, isBuiltInKitchen()? 1 : 0);
			pstmt.executeUpdate();
			pstmt.close();
			
			con.commit();
			DB2ConnectionManager.getInstance().closeConnection();;
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
}
