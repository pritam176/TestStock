/**
 * 
 */
package com.mmt.dto;

/**
 * @author pkumar
 *
 */
public class ItemDTO {

	private long id;
	private String transaction_id;
	private String product_id;
	private String product_name;
	private String item_name;
	private double unit_cost;
	private double total_unit;
	private double total_price;
	private double offer_price;
	private String transaction_status;
	private String gstPerc;//not used
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public double getUnit_cost() {
		return unit_cost;
	}

	public void setUnit_cost(double unit_cost) {
		this.unit_cost = unit_cost;
	}

	public double getTotal_unit() {
		return total_unit;
	}

	public void setTotal_unit(double total_unit) {
		this.total_unit = total_unit;
	}

	public double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	private String other;

	public double getOffer_price() {
		return offer_price;
	}

	public void setOffer_price(double offer_price) {
		this.offer_price = offer_price;
	}

	public String getTransaction_status() {
		return transaction_status;
	}

	public void setTransaction_status(String transaction_status) {
		this.transaction_status = transaction_status;
	}

	public String getGstPerc() {
		return gstPerc;
	}

	public void setGstPerc(String gstPerc) {
		this.gstPerc = gstPerc;
	}

	

}
