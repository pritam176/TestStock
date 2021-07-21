/**
 * 
 */
package com.mmt.Entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@Table(name = "gstslab")
public class GSTSlabEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long slab_id;
	@Column(unique = true)
	private String name;
	@Column(unique = true)
	private String taxrate;
	private String addedDate;
	private String updtatedDate;
	
	@OneToMany(mappedBy = "gSTSlabEntity", cascade = CascadeType.ALL)
	private Set<ProductEntity> productEntity;
	
	@OneToMany(mappedBy = "gSTSlabEntity", cascade = CascadeType.ALL)
	private Set<ItemTransactionEntity> itemTransactionEntity;
	
	
	public long getSlab_id() {
		return slab_id;
	}
	public void setSlab_id(long slab_id) {
		this.slab_id = slab_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTaxrate() {
		return taxrate;
	}
	public void setTaxrate(String taxrate) {
		this.taxrate = taxrate;
	}
	public String getAddedDate() {
		return addedDate;
	}
	public void setAddedDate(String addedDate) {
		this.addedDate = addedDate;
	}
	public String getUpdtatedDate() {
		return updtatedDate;
	}
	public void setUpdtatedDate(String updtatedDate) {
		this.updtatedDate = updtatedDate;
	}
	public Set<ProductEntity> getProductEntity() {
		return productEntity;
	}
	public void setProductEntity(Set<ProductEntity> productEntity) {
		this.productEntity = productEntity;
	}
	public Set<ItemTransactionEntity> getItemTransactionEntity() {
		return itemTransactionEntity;
	}
	public void setItemTransactionEntity(Set<ItemTransactionEntity> itemTransactionEntity) {
		this.itemTransactionEntity = itemTransactionEntity;
	}

}
