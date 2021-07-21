/**
 * 
 */
package com.mmt.dto;

/**
 * @author pkumar
 *
 */
public class TransactionItemDTO {
	
	private String itemId;
	private String itemName;
	private String productId;
	private String productName;
	private String sellUnit;
	private String unitcost;
	private String discount;
	private String totalamount;
	private String paybleAmount;
	private String cgstAmt;
	private String sgstAmt;
	private String cgstPerc;
	private String sgstPerc;
	private String hsnCode;
	
	private String totalGstAmt;
	private String totalGstPerc;
	
	
	
	
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getSellUnit() {
		return sellUnit;
	}
	public void setSellUnit(String sellUnit) {
		this.sellUnit = sellUnit;
	}
	public String getUnitcost() {
		return unitcost;
	}
	public void setUnitcost(String unitcost) {
		this.unitcost = unitcost;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}
	public String getCgstAmt() {
		return cgstAmt;
	}
	public void setCgstAmt(String cgstAmt) {
		this.cgstAmt = cgstAmt;
	}
	public String getSgstAmt() {
		return sgstAmt;
	}
	public void setSgstAmt(String sgstAmt) {
		this.sgstAmt = sgstAmt;
	}
	public String getCgstPerc() {
		return cgstPerc;
	}
	public void setCgstPerc(String cgstPerc) {
		this.cgstPerc = cgstPerc;
	}
	public String getSgstPerc() {
		return sgstPerc;
	}
	public void setSgstPerc(String sgstPerc) {
		this.sgstPerc = sgstPerc;
	}
	public String getTotalGstAmt() {
		return totalGstAmt;
	}
	public void setTotalGstAmt(String totalGstAmt) {
		this.totalGstAmt = totalGstAmt;
	}
	public String getTotalGstPerc() {
		return totalGstPerc;
	}
	public void setTotalGstPerc(String totalGstPerc) {
		this.totalGstPerc = totalGstPerc;
	}
	public String getPaybleAmount() {
		return paybleAmount;
	}
	public void setPaybleAmount(String paybleAmount) {
		this.paybleAmount = paybleAmount;
	}
	public String getHsnCode() {
		return hsnCode;
	}
	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}
	

}
