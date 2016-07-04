package com.dbExercise7.extractDB2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dbExercise7.util.DB2ConnectionManager;


public class ExtractDB2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		list();
	}
	
	public static void extractFromDB(){
		
	}
	
	public static String getInsertsArticleId(){
		try {
			// Get connection
			Connection con = DB2ConnectionManager.getInstance().getConnection();
			
			// Prepare Statement
			String selectSQL = "SELECT * FROM DB2INST1.PRODUCTCATEGORYID";
			PreparedStatement pstmt = con.prepareStatement(selectSQL);

			// Processing result
			ResultSet rs = pstmt.executeQuery();
			String outSQL = "INSERT INTO VSISP51.PRODUCTCATEGORYID (PRODUCTCATEGORYID, NAME) VALUES ";
			
			while (rs.next()) {
				String str = "";
				str += rs.getInt("PRODUCTCATEGORYID");
				str += rs.getString("NAME");
				str += ", ";
				str += rs.getDouble("preis");
				str += ".";
				
				System.out.println(str);
			}
			rs.close();
			pstmt.close();
			DB2ConnectionManager.getInstance().closeConnection();
			return str;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
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
