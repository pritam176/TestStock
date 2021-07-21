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
 *         This Tabel can be traet as stock of item
 *
 */
@Entity
@Table(name = "ProductItem")
public class ProductItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long productitementity_id;

	private String itemname;
	private double unit_price;
	private double stock_avalble;// no of unit
	private Date addedon;
	private String addedby;

	private boolean isActive;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductEntity productEntity;

	@OneToMany(mappedBy = "productItemEntities", cascade = CascadeType.ALL)
	private Set<ProductItemHistoryEntity> productItemHistory;

	public ProductEntity getProductEntity() {
		return productEntity;
	}

	public void setProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}

	

	public double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}

	public double getStock_avalble() {
		return stock_avalble;
	}

	public void setStock_avalble(double stock_avalble) {
		this.stock_avalble = stock_avalble;
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

	public Set<ProductItemHistoryEntity> getProductItemHistory() {
		return productItemHistory;
	}

	public void setProductItemHistory(Set<ProductItemHistoryEntity> productItemHistory) {
		this.productItemHistory = productItemHistory;
	}

	public long getProductitementity_id() {
		return productitementity_id;
	}

	public void setProductitementity_id(long productitementity_id) {
		this.productitementity_id = productitementity_id;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

}
