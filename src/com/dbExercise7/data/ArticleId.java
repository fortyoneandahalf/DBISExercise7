package com.dbExercise7.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dbExercise7.util.DB2ConnectionManager;

public class ArticleId {
	
	private int articleId;
	private int productGroupId;
	private String name;
	private double price;
	
	public ArticleId(int articleId, int productGroupId, String name, double price) {
		super();
		this.articleId = articleId;
		this.productGroupId = productGroupId;
		this.name = name;
		this.price = price;
	}

	public int getArticleId() {
		return articleId;
	}
	
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getProductGroupId() {
		return productGroupId;
	}
	
	public void setProductGroupId(int productGroupId) {
		this.productGroupId = productGroupId;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public boolean save(){
		try {
			// Get connection
			Connection con = DB2ConnectionManager.getInstance().getConnection();
						
			String insertSQL = "INSERT INTO VSISP51.ARTICLEID VALUES (?, ?, ?, ?)";

			PreparedStatement pstmt = con.prepareStatement(insertSQL);

			// Set parameters of the prepared statements.
			pstmt.setInt(1, getArticleId());
			pstmt.setInt(2, getProductGroupId());
			pstmt.setString(3, getName());
			pstmt.setDouble(4, getPrice());
			
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
