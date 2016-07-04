package com.dbExercise7.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dbExercise7.util.DB2ConnectionManager;

public class ProductGroupId {
	
	private int productGroupId;
	private int productFamilyId;
	private String name;
	
	public ProductGroupId(int productGroupId, int productFamilyId, String name) {
		super();
		this.productGroupId = productGroupId;
		this.productFamilyId = productFamilyId;
		this.name = name;
	}

	public int getProductGroupId() {
		return productGroupId;
	}
	
	public void setProductGroupId(int productGroupId) {
		this.productGroupId = productGroupId;
	}
	
	public int getProductFamilyId() {
		return productFamilyId;
	}
	
	public void setProductFamilyId(int productFamilyId) {
		this.productFamilyId = productFamilyId;
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
						
			String insertSQL = "INSERT INTO VSISP51.PRODUCTGROUPID VALUES (?, ?, ?)";

			PreparedStatement pstmt = con.prepareStatement(insertSQL);

			// Set parameters of the prepared statements.
			pstmt.setInt(1, getProductGroupId());
			pstmt.setInt(2, getProductFamilyId());
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
