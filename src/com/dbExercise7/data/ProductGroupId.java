package com.dbExercise7.data;

public class ProductGroupId {
	
	private int productGroupId;
	private int productFamilyId;
	private String name;
	
	public ProductGroupId(int productGroupId, int productFamilyId, String name) {
		super();
		this.productGroupId = productGroupId;
		this.productFamilyId = productFamilyId;
		this.name = name;
	}

	public int getProductGroupId() {
		return productGroupId;
	}
	
	public void setProductGroupId(int productGroupId) {
		this.productGroupId = productGroupId;
	}
	
	public int getProductFamilyId() {
		return productFamilyId;
	}
	
	public void setProductFamilyId(int productFamilyId) {
		this.productFamilyId = productFamilyId;
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
