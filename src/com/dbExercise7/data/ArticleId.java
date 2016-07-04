package com.dbExercise7.data;

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
		return true;
	}
}
