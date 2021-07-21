/**
 * 
 */
package com.mmt.command;

/**
 * @author pkumar
 *
 */
public class SerachInvoiceCommand {
	
	private String startDate;
	private String endDate;
	private String invoiceNo;
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
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

}
