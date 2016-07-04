package com.dbExercise7.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dbExercise7.util.DB2ConnectionManager;

public class ProductCategoryId {
	private int productCategoryId;
	private String name;
	
	public ProductCategoryId(int productCategoryId, String name) {
		super();
		this.productCategoryId = productCategoryId;
		this.name = name;
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
	
	
	public boolean save() {
		
		try {
			// Get connection
			Connection con = DB2ConnectionManager.getInstance().getConnection();
						
			String insertSQL = "INSERT INTO VSISP51.PRODUCTCATEGORYID VALUES (?, ?)";

			PreparedStatement pstmt = con.prepareStatement(insertSQL);

			// Set parameters of the prepared statements.
			pstmt.setInt(1, getProductCategoryId());
			pstmt.setString(2, getName());
			
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
