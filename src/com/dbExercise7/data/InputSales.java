package com.dbExercise7.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.dbExercise7.util.DB2ConnectionManager;

public class InputSales 
{	
	private Date date;
	private String shop;
	private String product;
	private int units;
	private double sales;
	
	public InputSales(Date date, String shop, String product, int units, double sales){
		this.date = date;
		this.shop = shop;
		this.product = product;
		this.units = units;
		this.sales = sales;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getShop() {
		return shop;
	}
	public void setShop(String shop) {
		this.shop = shop;
	}
	
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	
	public double getSales() {
		return sales;
	}
	public void setSales(double sales) {
		this.sales = sales;
	}
	
	public boolean save(){
		int shopId = -1, articleId = -1;
		
		try {
			// Get connection
			Connection con = DB2ConnectionManager.getInstance().getConnection();
			
			// Prepare Statement
			String selectSQL = "SELECT SHOPID FROM VSISP51.SHOPID WHERE NAME = ?";
			PreparedStatement pstmt = con.prepareStatement(selectSQL);
			pstmt.setString(1, getShop());

			// Processing result
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				shopId = rs.getInt("SHOPID");
			}
			rs.close();
			pstmt.close();
			DB2ConnectionManager.getInstance().closeConnection();
			if(shopId < 0){
				System.out.println("Shop not exist.");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			// Get connection
			Connection con = DB2ConnectionManager.getInstance().getConnection();
			
			// Prepare Statement
			String selectSQL = "SELECT ARTICLEID FROM VSISP51.ARTICLEID WHERE NAME = ?";
			PreparedStatement pstmt = con.prepareStatement(selectSQL);
			pstmt.setString(1, getProduct());

			// Processing result
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				articleId = rs.getInt("ARTICLEID");
			}
			rs.close();
			pstmt.close();
			DB2ConnectionManager.getInstance().closeConnection();
			if(articleId < 0){
				System.out.println("Product not exist.");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			// Get connection
			Connection con = DB2ConnectionManager.getInstance().getConnection();
						
			String insertSQL = "INSERT INTO VSISP51.SALES VALUES (?, ?, ?, ?, ?)";

			PreparedStatement pstmt = con.prepareStatement(insertSQL);

			// Set parameters of the prepared statements.
			pstmt.setDate(1, (java.sql.Date) this.date);
			pstmt.setInt(2, shopId);
			pstmt.setInt(3, articleId);
			pstmt.setInt(4, getUnits());
			pstmt.setDouble(5, getSales());
			
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
