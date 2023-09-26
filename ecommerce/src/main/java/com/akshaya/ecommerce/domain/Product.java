package com.akshaya.ecommerce.domain;

public class Product {
	
	private String productID;
	private String productName;
	private String productDesc;
	private float rating;
	private double price;
	private String shopID;
	private String categoryID;
	
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDescription) {
		this.productDesc = productDescription;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getShopID() {
		return shopID;
	}
	public void setShopID(String shopID) {
		this.shopID = shopID;
	}
	public String getCategory() {
		return categoryID;
	}
	public void setCategory(String category) {
		this.categoryID = category;
	}
	
}
