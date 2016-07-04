package com.dbExercise7.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dbExercise7.util.DB2ConnectionManager;

public class ProductFamilyId{
	private int productFamilyId;
	private int productCategoryId;
	private String name;
	
	public ProductFamilyId(int productFamilyId, int productCategoryId, String name) {
		this.productFamilyId = productFamilyId;
		this.productCategoryId = productCategoryId;
		this.name = name;
	}

	public int getProductFamilyId() {
		return productFamilyId;
	}
	
	public void setProductFamilyId(int productFamilyId) {
		this.productFamilyId = productFamilyId;
	}
	
	public int getProductCategoryId() {
		return productCategoryId;
	}
	
	public void setProductCategoryId(int productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean save(){
		try {
			// Get connection
			Connection con = DB2ConnectionManager.getInstance().getConnection();
						
			String insertSQL = "INSERT INTO VSISP51.PRODUCTFAMILYID VALUES (?, ?, ?)";

			PreparedStatement pstmt = con.prepareStatement(insertSQL);

			// Set parameters of the prepared statements.
			pstmt.setInt(1, getProductFamilyId());
			pstmt.setInt(2, getProductCategoryId());
			pstmt.setString(3, getName());
			
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
