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
@Table(name = "product")
public class ProductEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long product_id;
	private String  productname;
	private Date addedon;
	private String addedby;
	private String hsnCode;
	private boolean isActive;
	
	@OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL)
	private Set<ProductItemEntity> productItemEntities;
	
	@ManyToOne
	@JoinColumn(name = "slab_id")
	private GSTSlabEntity gSTSlabEntity;
	
	
	public Set<ProductItemEntity> getProductItemEntities() {
		return productItemEntities;
	}
	public void setProductItemEntities(Set<ProductItemEntity> productItemEntities) {
		this.productItemEntities = productItemEntities;
	}
	public long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}
	
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
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public GSTSlabEntity getgSTSlabEntity() {
		return gSTSlabEntity;
	}
	public void setgSTSlabEntity(GSTSlabEntity gSTSlabEntity) {
		this.gSTSlabEntity = gSTSlabEntity;
	}
	public String getHsnCode() {
		return hsnCode;
	}
	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}

}
