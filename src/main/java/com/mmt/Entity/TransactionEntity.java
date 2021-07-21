/**
 * 
 */
package com.mmt.Entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author pkumar
 *
 */

@Entity
@Table(name = "transaction")
public class TransactionEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long transaction_id;
	private String transaction_type;
	private String customer_name;
	private String invoice_no;
	private String tin_sirn;
	private double amount;
	private double total_discount;
	private String transaction_status;
	private String product_id;
	private int vat;
	private double vat_amount;
	private double gst_fee;
	private double others_charges;
	private double total;
	private int noOfItem;
	private String logedin_user;
	
	private String paymentMode;
	
	private String cheqNo;
	
	@OneToMany(mappedBy = "transactionEntity", cascade = CascadeType.ALL)
	private Set<ItemTransactionEntity> itemTransactionEntity;
	
	@ManyToOne
	@JoinColumn(name = "customer_id",nullable = true)
	private CustomerEntity customerEntity;

	private Date invoice_date;
	
	
	
	
	

	public String getTransaction_type() {
		return transaction_type;
	}

	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getInvoice_no() {
		return invoice_no;
	}

	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}

	public String getTin_sirn() {
		return tin_sirn;
	}

	public void setTin_sirn(String tin_sirn) {
		this.tin_sirn = tin_sirn;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTransaction_status() {
		return transaction_status;
	}

	public void setTransaction_status(String transaction_status) {
		this.transaction_status = transaction_status;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public int getVat() {
		return vat;
	}

	public void setVat(int vat) {
		this.vat = vat;
	}

	public double getVat_amount() {
		return vat_amount;
	}

	public void setVat_amount(double vat_amount) {
		this.vat_amount = vat_amount;
	}

	

	public double getOthers_charges() {
		return others_charges;
	}

	public void setOthers_charges(double others_charges) {
		this.others_charges = others_charges;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getInvoice_date() {
		return invoice_date;
	}

	public void setInvoice_date(Date invoice_date) {
		this.invoice_date = invoice_date;
	}

	public long getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(long transaction_id) {
		this.transaction_id = transaction_id;
	}

	public Set<ItemTransactionEntity> getItemTransactionEntity() {
		return itemTransactionEntity;
	}

	public void setItemTransactionEntity(Set<ItemTransactionEntity> itemTransactionEntity) {
		this.itemTransactionEntity = itemTransactionEntity;
	}

	public double getTotal_discount() {
		return total_discount;
	}

	public void setTotal_discount(double total_discount) {
		this.total_discount = total_discount;
	}

	public int getNoOfItem() {
		return noOfItem;
	}

	public void setNoOfItem(int noOfItem) {
		this.noOfItem = noOfItem;
	}

	public String getLogedin_user() {
		return logedin_user;
	}

	public void setLogedin_user(String logedin_user) {
		this.logedin_user = logedin_user;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getCheqNo() {
		return cheqNo;
	}

	public void setCheqNo(String cheqNo) {
		this.cheqNo = cheqNo;
	}

	public CustomerEntity getCustomerEntity() {
		return customerEntity;
	}

	public void setCustomerEntity(CustomerEntity customerEntity) {
		this.customerEntity = customerEntity;
	}

	public double getGst_fee() {
		return gst_fee;
	}

	public void setGst_fee(double gst_fee) {
		this.gst_fee = gst_fee;
	}

	

	

	

}
