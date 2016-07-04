package com.dbExercise7.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dbExercise7.util.DB2ConnectionManager;

public class StadtId {
	private int stadtId;
	private int regionId;
	private String name;
	
	public StadtId(int stadtId, int regionId, String name) {
		super();
		this.stadtId = stadtId;
		this.regionId = regionId;
		this.name = name;
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
	
	public int getRegionId() {
		return regionId;
	}
	
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	
	public boolean save(){
		try {
			// Get connection
			Connection con = DB2ConnectionManager.getInstance().getConnection();
						
			String insertSQL = "INSERT INTO VSISP51.STADTID VALUES (?, ?, ?)";

			PreparedStatement pstmt = con.prepareStatement(insertSQL);

			// Set parameters of the prepared statements.
			pstmt.setInt(1, getStadtId());
			pstmt.setInt(2, getRegionId());
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
