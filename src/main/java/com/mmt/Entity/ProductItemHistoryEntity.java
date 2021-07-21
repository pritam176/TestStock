/**
 * 
 */
package com.mmt.Entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



/**
 * @author pkumar
 * This Tabel is track of all changes in stock
 *
 */
@Entity
@Table(name = "productitemhistory")
public class ProductItemHistoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long itemHistory_id;

	private Date addedon;
	private String addedby;
	private double unitperchasecost;
	private double noofunit;
	private double totalcost;
	
	private double gstAmount;
	private double finalCost;
	private double discount;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "productitementity_id")
	private ProductItemEntity productItemEntities;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "supplier_id")
	private SupplierEntity supplier;
	
	@ManyToOne
	@JoinColumn(name = "customer_id",nullable = true)
	private CustomerEntity customerEntity;
	
	private String updateReason;
	
	
	

	public Date getAddedon() {
		return addedon;
	}

	public void setAddedon(Date addedon) {
		this.addedon = addedon;
	}

	public String getAddedby() {
		return addedby;
	}

	public void setAddedby(String addedby) {
		this.addedby = addedby;
	}

	public double getUnitperchasecost() {
		return unitperchasecost;
	}

	public void setUnitperchasecost(double unitperchasecost) {
		this.unitperchasecost = unitperchasecost;
	}

	public double getNoofunit() {
		return noofunit;
	}

	public void setNoofunit(double noofunit) {
		this.noofunit = noofunit;
	}

	public double getTotalcost() {
		return totalcost;
	}

	public void setTotalcost(double totalcost) {
		this.totalcost = totalcost;
	}

	public long getItemHistory_id() {
		return itemHistory_id;
	}

	public void setItemHistory_id(long itemHistory_id) {
		this.itemHistory_id = itemHistory_id;
	}

	public ProductItemEntity getProductItemEntities() {
		return productItemEntities;
	}

	public void setProductItemEntities(ProductItemEntity productItemEntities) {
		this.productItemEntities = productItemEntities;
	}

	public SupplierEntity getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierEntity supplier) {
		this.supplier = supplier;
	}

	public CustomerEntity getCustomerEntity() {
		return customerEntity;
	}

	public void setCustomerEntity(CustomerEntity customerEntity) {
		this.customerEntity = customerEntity;
	}

	public String getUpdateReason() {
		return updateReason;
	}

	public void setUpdateReason(String updateReason) {
		this.updateReason = updateReason;
	}

	

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getGstAmount() {
		return gstAmount;
	}

	public void setGstAmount(double gstAmount) {
		this.gstAmount = gstAmount;
	}

	public double getFinalCost() {
		return finalCost;
	}

	public void setFinalCost(double finalCost) {
		this.finalCost = finalCost;
	}

	

}
