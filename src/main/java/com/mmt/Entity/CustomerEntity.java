package com.mmt.Entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long customer_id;
	@Column(unique = true)
	private String name;
	@Column(unique = true)
	private String mobileNo;
	private String email;
	private String address;
	private String dues;
	private Date updatedate;
	private String updateReason;
	private String updateBy;

	@OneToMany(mappedBy = "customerEntity", cascade = CascadeType.ALL)
	private Set<ProductItemHistoryEntity> productItemHistoryEntity;
	
	@OneToMany(mappedBy = "customerEntity")
	private Set<TransactionEntity> transactionEntity;

	@Column(name = "type")
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

	public Set<ProductItemHistoryEntity> getProductItemHistoryEntity() {
		return productItemHistoryEntity;
	}

	public void setProductItemHistoryEntity(Set<ProductItemHistoryEntity> productItemHistoryEntity) {
		this.productItemHistoryEntity = productItemHistoryEntity;
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

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

}
