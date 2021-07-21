package com.mmt.dto;

public class ProductItemDTO {
	
	private long id;
	private long product_id;
	private String item_name;
	private double unit_price;
	private double stock_avalble;
	private String product_name;
	private String gstRate;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public double getUnit_price() {
		return unit_price;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}
	public double getStock_avalble() {
		return stock_avalble;
	}
	public void setStock_avalble(double stock_avalble) {
		this.stock_avalble = stock_avalble;
	}
	public String getGstRate() {
		return gstRate;
	}
	public void setGstRate(String gstRate) {
		this.gstRate = gstRate;
	}

}
