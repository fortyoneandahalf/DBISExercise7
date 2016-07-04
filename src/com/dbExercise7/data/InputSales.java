package com.dbExercise7.data;

import java.util.Date;

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
	
	
	
}
