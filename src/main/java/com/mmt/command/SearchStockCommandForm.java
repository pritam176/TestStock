/**
 * 
 */
package com.mmt.command;

/**
 * @author pkumar
 *
 */
public class SearchStockCommandForm {

	
	private String startDate;
	private String endDate;
	private String reason;
	
	private String noofUnit;
	
	private String unitPrice;

	private String productID;

	private String itemID;

	

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public String getNoofUnit() {
		return noofUnit;
	}

	public void setNoofUnit(String noofUnit) {
		this.noofUnit = noofUnit;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	};

}
