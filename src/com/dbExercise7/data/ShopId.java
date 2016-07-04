package com.dbExercise7.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dbExercise7.util.DB2ConnectionManager;

public class ShopId {

	private int shopId;
	private int stadtId;
	private String name;
	
	public ShopId(int shopId, int stadtId, String name) {
		super();
		this.shopId = shopId;
		this.stadtId = stadtId;
		this.name = name;
	}
	
	public int getShopId() {
		return shopId;
	}
	
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	
	public int getStadtId() {
		return stadtId;
	}
	
	public void setStadtId(int stadtId) {
		this.stadtId = stadtId;
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
						
			String insertSQL = "INSERT INTO VSISP51.SHOPID VALUES (?, ?, ?)";

			PreparedStatement pstmt = con.prepareStatement(insertSQL);

			// Set parameters of the prepared statements.
			pstmt.setInt(1, getShopId());
			pstmt.setInt(2, getStadtId());
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
