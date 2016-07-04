package com.dbExercise7.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dbExercise7.util.DB2ConnectionManager;

public class RegionId {

	private int regionId;
	private int landId;
	private String name;
	
	public RegionId(int regionId, int landId, String name) {
		super();
		this.regionId = regionId;
		this.landId = landId;
		this.name = name;
	}
	
	public int getRegionId() {
		return regionId;
	}
	
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	
	public int getLandId() {
		return landId;
	}
	
	public void setLandId(int landId) {
		this.landId = landId;
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
						
			String insertSQL = "INSERT INTO VSISP51.REGIONID VALUES (?, ?, ?)";

			PreparedStatement pstmt = con.prepareStatement(insertSQL);

			// Set parameters of the prepared statements.
			pstmt.setInt(1, getRegionId());
			pstmt.setInt(2, getLandId());
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
