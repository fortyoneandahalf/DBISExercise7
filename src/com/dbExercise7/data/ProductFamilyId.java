package com.dbExercise7.data;

public class ProductFamilyId{
	private int productFamilyId;
	private int productCategoryId;
	private String name;
	
	public ProductFamilyId(int productFamilyId, int productCategoryId, String name) {
		this.productFamilyId = productFamilyId;
		this.productCategoryId = productCategoryId;
		this.name = name;
	}

	public int getProductFamilyId() {
		return productFamilyId;
	}
	
	public void setProductFamilyId(int productFamilyId) {
		this.productFamilyId = productFamilyId;
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
