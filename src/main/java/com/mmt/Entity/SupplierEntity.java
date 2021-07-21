/**
 * 
 */
package com.mmt.Entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author pkumar
 *
 */

@Entity
@Table(name = "supplier")
public class SupplierEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long supplier_id;
	
	private String suppliername;
	
	private String supplierlicenceno;
	
	private String addedon;
	
	private String addedBy;
	
	private String mobielNo;
	
	private String address;
	
	@OneToMany
	(mappedBy = "supplier", cascade = CascadeType.ALL)
	private Set<ProductItemHistoryEntity> productItemHistory;

	

	public String getSuppliername() {
		return suppliername;
	}

	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}

	public String getSupplierlicenceno() {
		return supplierlicenceno;
	}

	public void setSupplierlicenceno(String supplierlicenceno) {
		this.supplierlicenceno = supplierlicenceno;
	}

	public String getAddedon() {
		return addedon;
	}

	public void setAddedon(String addedon) {
		this.addedon = addedon;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public String getMobielNo() {
		return mobielNo;
	}

	public void setMobielNo(String mobielNo) {
		this.mobielNo = mobielNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	

	public long getSupplier_id() {
		return supplier_id;
	}

	public void setSupplier_id(long supplier_id) {
		this.supplier_id = supplier_id;
	}

	public Set<ProductItemHistoryEntity> getProductItemHistory() {
		return productItemHistory;
	}

	public void setProductItemHistory(Set<ProductItemHistoryEntity> productItemHistory) {
		this.productItemHistory = productItemHistory;
	}

}
