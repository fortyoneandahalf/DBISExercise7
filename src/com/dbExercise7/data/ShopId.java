package com.dbExercise7.data;

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
		return true;
	}
	
}
