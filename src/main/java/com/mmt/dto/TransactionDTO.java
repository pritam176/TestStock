/**
 * 
 */
package com.mmt.dto;

import java.util.Set;

/**
 * @author pkumar
 *
 */
public class TransactionDTO {
	
	private Set<TransactionItemDTO> transactionItemDTO;
	
	private String transactionid;
	private String transactionDate;
	private String totalamount;
	private String totaldiscount;
	private String paybleamount;
	private String gstAmount;
	private String customerName;
	private String noOfItem;
	
	
	private String address;
	private String mobileNo;
	private String paymentMode;
	private String checkNo;
	
	
	public Set<TransactionItemDTO> getTransactionItemDTO() {
		return transactionItemDTO;
	}
	public void setTransactionItemDTO(Set<TransactionItemDTO> transactionItemDTO) {
		this.transactionItemDTO = transactionItemDTO;
	}
	public String getTransactionid() {
		return transactionid;
	}
	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}
	public String getTotaldiscount() {
		return totaldiscount;
	}
	public void setTotaldiscount(String totaldiscount) {
		this.totaldiscount = totaldiscount;
	}
	public String getPaybleamount() {
		return paybleamount;
	}
	public void setPaybleamount(String paybleamount) {
		this.paybleamount = paybleamount;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getNoOfItem() {
		return noOfItem;
	}
	public void setNoOfItem(String noOfItem) {
		this.noOfItem = noOfItem;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getCheckNo() {
		return checkNo;
	}
	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}
	public String getGstAmount() {
		return gstAmount;
	}
	public void setGstAmount(String gstAmount) {
		this.gstAmount = gstAmount;
	}
	

}
