/**
 * 
 */
package com.mmt.Entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author pkumar
 *
 *         This Tabel treat as item per transaction. This is nothing to do with
 *         stock.can be use for report
 */
@Entity
@Table(name = "transactionitem")
public class ItemTransactionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long item_transaction_id;
	private long item_id;

	private String product_id;
	private String product_name;
	private String hsnCode;
	private String item_name;
	private double unit_cost;
	private double total_cost;

	private double sell_unit;
	private double gstAmount;
	private double finalCost;
	private double discountOffer;
	private Date invoice_date;

	@ManyToOne
	@JoinColumn(name = "transaction_id", nullable = false)
	private TransactionEntity transactionEntity;

	@ManyToOne
	@JoinColumn(name = "slab_id")
	private GSTSlabEntity gSTSlabEntity;
	
	@ManyToOne
	@JoinColumn(name = "customer_id",nullable = true)
	private CustomerEntity customerEntity;

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

	public long getItem_transaction_id() {
		return item_transaction_id;
	}

	public void setItem_transaction_id(long item_transaction_id) {
		this.item_transaction_id = item_transaction_id;
	}

	public double getSell_unit() {
		return sell_unit;
	}

	public void setSell_unit(double sell_unit) {
		this.sell_unit = sell_unit;
	}

	public double getDiscountOffer() {
		return discountOffer;
	}

	public void setDiscountOffer(double discountOffer) {
		this.discountOffer = discountOffer;
	}

	public TransactionEntity getTransactionEntity() {
		return transactionEntity;
	}

	public void setTransactionEntity(TransactionEntity transactionEntity) {
		this.transactionEntity = transactionEntity;
	}

	public long getItem_id() {
		return item_id;
	}

	public void setItem_id(long item_id) {
		this.item_id = item_id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public double getTotal_cost() {
		return total_cost;
	}

	public void setTotal_cost(double total_cost) {
		this.total_cost = total_cost;
	}

	public GSTSlabEntity getgSTSlabEntity() {
		return gSTSlabEntity;
	}

	public void setgSTSlabEntity(GSTSlabEntity gSTSlabEntity) {
		this.gSTSlabEntity = gSTSlabEntity;
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

	public Date getInvoice_date() {
		return invoice_date;
	}

	public void setInvoice_date(Date invoice_date) {
		this.invoice_date = invoice_date;
	}

	public CustomerEntity getCustomerEntity() {
		return customerEntity;
	}

	public void setCustomerEntity(CustomerEntity customerEntity) {
		this.customerEntity = customerEntity;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

}
