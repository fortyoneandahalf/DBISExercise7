package com.dbExercise7.data;

import java.sql.Date;

public class Sales {
	private Date date;
	private int shopId;
	private int articleId;
	private int quantity;
	private double amount;
	
	public Sales(Date date, int shopId, int articleId, int quantity, double amount) {
		super();
		this.date = date;
		this.shopId = shopId;
		this.articleId = articleId;
		this.quantity = quantity;
		this.amount = amount;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getShopId() {
		return shopId;
	}
	
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	
	public int getArticleId() {
		return articleId;
	}
	
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public boolean save(){
		return true;
	}
}
