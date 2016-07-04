package com.dbExercise7.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.dbExercise7.util.DB2ConnectionManager;

public class InputSales 
{	
	private String date;
	private String shop;
	private String product;
	private int shopId;
	private int articleId;
	private int units;
	private double sales;
	private boolean ignore = false;
	
	public InputSales(String date, String shop, String product, int units, double sales){
		this.date = date;
		this.shop = shop;
		this.product = product;
		this.units = units;
		this.sales = sales;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
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
	

	
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	
	public boolean getIgnore(){
		return ignore;
	}
	
	public void setIgnore(boolean ignore){
		this.ignore = ignore;
	}

	public boolean save(){
		try {
			// Get connection
			Connection con = DB2ConnectionManager.getInstance().getConnection();
						
			String insertSQL = "INSERT INTO VSISP51.SALES VALUES (?, ?, ?, ?, ?)";

			PreparedStatement pstmt = con.prepareStatement(insertSQL);

			// Set parameters of the prepared statements.
			pstmt.setString(1, this.date);
			pstmt.setInt(2, this.shopId);
			pstmt.setInt(3, this.articleId);
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
	
	public void save(PreparedStatement pstmt){
		try {
			pstmt.setString(1, this.date);
			pstmt.setInt(2, this.shopId);
			pstmt.setInt(3, this.articleId);
			pstmt.setInt(4, getUnits());
			pstmt.setDouble(5, getSales());
			pstmt.addBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
