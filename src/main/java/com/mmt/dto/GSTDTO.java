/**
 * 
 */
package com.mmt.dto;

/**
 * @author pkumar
 *
 */
public class GSTDTO {

	private long item_transaction_id;
	private long item_id;
	private String hsnCode;
	private String product_id;
	private String product_name;
	private String item_name;
	private String unit_cost;
	private String total_cost;
	private String gstPerc;
	private String sell_unit;
	private String gstAmount;
	private String finalCost;
	private String discountOffer;
	private String invoice_date;
	private String customerName;;
	public long getItem_transaction_id() {
		return item_transaction_id;
	}
	public void setItem_transaction_id(long item_transaction_id) {
		this.item_transaction_id = item_transaction_id;
	}
	public long getItem_id() {
		return item_id;
	}
	public void setItem_id(long item_id) {
		this.item_id = item_id;
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
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getUnit_cost() {
		return unit_cost;
	}
	public void setUnit_cost(String unit_cost) {
		this.unit_cost = unit_cost;
	}
	public String getTotal_cost() {
		return total_cost;
	}
	public void setTotal_cost(String total_cost) {
		this.total_cost = total_cost;
	}
	public String getSell_unit() {
		return sell_unit;
	}
	public void setSell_unit(String sell_unit) {
		this.sell_unit = sell_unit;
	}
	public String getGstAmount() {
		return gstAmount;
	}
	public void setGstAmount(String gstAmount) {
		this.gstAmount = gstAmount;
	}
	public String getFinalCost() {
		return finalCost;
	}
	public void setFinalCost(String finalCost) {
		this.finalCost = finalCost;
	}
	public String getDiscountOffer() {
		return discountOffer;
	}
	public void setDiscountOffer(String discountOffer) {
		this.discountOffer = discountOffer;
	}
	public String getInvoice_date() {
		return invoice_date;
	}
	public void setInvoice_date(String invoice_date) {
		this.invoice_date = invoice_date;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getGstPerc() {
		return gstPerc;
	}
	public void setGstPerc(String gstPerc) {
		this.gstPerc = gstPerc;
	}
	public String getHsnCode() {
		return hsnCode;
	}
	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	
}
