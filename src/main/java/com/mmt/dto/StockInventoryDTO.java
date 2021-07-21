/**
 * 
 */
package com.mmt.dto;

/**
 * @author pkumar
 *
 */
public class StockInventoryDTO {

	private String itemName;
	private String productName;
	private String addedDate;
	private String noofUnit;
	private String unitPerchaseCost;
	private String discountOfer;
	private String updateReason;
	private String customerName;
	private String supplierName;
	private String totalPurchaseCost;
	private String totalSellCost;
	private String totalDiscountOffer;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(String addedDate) {
		this.addedDate = addedDate;
	}

	public String getNoofUnit() {
		return noofUnit;
	}

	public void setNoofUnit(String noofUnit) {
		this.noofUnit = noofUnit;
	}

	public String getUnitPerchaseCost() {
		return unitPerchaseCost;
	}

	public void setUnitPerchaseCost(String unitPerchaseCost) {
		this.unitPerchaseCost = unitPerchaseCost;
	}

	public String getDiscountOfer() {
		return discountOfer;
	}

	public void setDiscountOfer(String discountOfer) {
		this.discountOfer = discountOfer;
	}

	public String getUpdateReason() {
		return updateReason;
	}

	public void setUpdateReason(String updateReason) {
		this.updateReason = updateReason;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getTotalPurchaseCost() {
		return totalPurchaseCost;
	}

	public void setTotalPurchaseCost(String totalPurchaseCost) {
		this.totalPurchaseCost = totalPurchaseCost;
	}

	public String getTotalSellCost() {
		return totalSellCost;
	}

	public void setTotalSellCost(String totalSellCost) {
		this.totalSellCost = totalSellCost;
	}

	public String getTotalDiscountOffer() {
		return totalDiscountOffer;
	}

	public void setTotalDiscountOffer(String totalDiscountOffer) {
		this.totalDiscountOffer = totalDiscountOffer;
	}

}
