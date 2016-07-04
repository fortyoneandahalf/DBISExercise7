package com.dbExercise7.data;

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
	
	public boolean save(){
		return true;
	}
}
