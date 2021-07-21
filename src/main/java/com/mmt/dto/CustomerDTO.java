/**
 * 
 */
package com.mmt.dto;

import java.sql.Date;

/**
 * @author pkumar
 *
 */
public class CustomerDTO {

	private long customer_id;
	private String name;
	private String mobileNo;
	private String email;
	private String address;
	private String dues;
	private Date updatedate;
	private String updateReason;
	private String updateBy;
	private String customertype;

	private Date date;

	public long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDues() {
		return dues;
	}

	public void setDues(String dues) {
		this.dues = dues;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	public String getUpdateReason() {
		return updateReason;
	}

	public void setUpdateReason(String updateReason) {
		this.updateReason = updateReason;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getCustomertype() {
		return customertype;
	}

	public void setCustomertype(String customertype) {
		this.customertype = customertype;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
