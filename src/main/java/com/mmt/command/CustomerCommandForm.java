/**
 * 
 */
package com.mmt.command;

import java.sql.Date;

/**
 * @author pkumar
 *
 */
public class CustomerCommandForm {

	private String customer_id;
	private String address;
	private String type;
	private String date;
	private String dues;
	private String email;
	private String mobile_no;
	private String name;
	
	private Date updatedate;
	private String updateReason;
	private String updateBy;

	
	
	
	
	
	
	
	
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDues() {
		return dues;
	}
	public void setDues(String dues) {
		this.dues = dues;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

}
