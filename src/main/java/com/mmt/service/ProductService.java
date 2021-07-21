package com.mmt.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mmt.Entity.GSTSlabEntity;
import com.mmt.Entity.ProductEntity;
import com.mmt.Entity.ProductItemEntity;
import com.mmt.Entity.ProductItemHistoryEntity;
import com.mmt.Entity.SupplierEntity;
import com.mmt.command.PurchaseCommandForm;
import com.mmt.dto.ProductItemDTO;
import com.mmt.repository.GstSlabRepository;
import com.mmt.repository.ProductItemHistoryRepository;
import com.mmt.repository.ProductItemRepository;
import com.mmt.repository.ProductRepository;
import com.mmt.repository.SupplierRepository;

/**
 * 
 */

/**
 * @author pkumar
 *
 */
@Service
public class ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductItemRepository productItemRepository;

	@Autowired
	private ProductItemHistoryRepository productItemHistoryRepository;

	@Autowired
	private SupplierRepository supplierRepository;

	@Autowired
	private GstSlabRepository gstSlabRepository;

	public List<ProductEntity> getAllProductEntity() {
		return productRepository.findAll();

	}

	public List<GSTSlabEntity> getAllGstSlab() {
		return gstSlabRepository.findAll();
	}

	public List<ProductItemDTO> getOneProductEntity(String key) {
		logger.debug("Requested Key is --" + key);
		ProductEntity pe = productRepository.findOne(new Long(key));

		GSTSlabEntity ge = pe.getgSTSlabEntity();
		String gstRate = ge.getTaxrate();

		List<ProductItemDTO> pidto = new ArrayList<>();

		Set<ProductItemEntity> pItem = pe.getProductItemEntities();

		for (ProductItemEntity p : pItem) {
			ProductItemDTO temp = new ProductItemDTO();
			temp.setId(p.getProductitementity_id());
			temp.setItem_name(p.getItemname());
			temp.setStock_avalble(p.getStock_avalble());
			temp.setUnit_price(p.getUnit_price());
			temp.setProduct_name(pe.getProductname());
			temp.setProduct_id(pe.getProduct_id());
			temp.setGstRate(gstRate);
			pidto.add(temp);
		}
		logger.debug("list return  --" + pidto.size());
		return pidto;

	}

	@Transactional
	public boolean saveProduct(String productName, String logedinUser, String slabId, String hsncode) {

		GSTSlabEntity ge = gstSlabRepository.getOne(new Long(slabId));

		ProductEntity productEntity = new ProductEntity();
		productEntity.setActive(true);
		productEntity.setProductname(productName.toUpperCase());
		productEntity.setAddedby(logedinUser);
		productEntity.setgSTSlabEntity(ge);
		productEntity.setHsnCode(hsncode);

		productEntity = productRepository.save(productEntity);

		if (productEntity.getProduct_id() > 1)
			return true;
		return false;

	}

	/**
	 * @author pkumar add new Item to Product
	 */

	@Transactional
	public boolean saveProductItem(String logedinUser, PurchaseCommandForm form) {

		double purchasePriceunit = Double.parseDouble(form.getPurchasePriceUnit());

		double noofunit = Double.parseDouble(form.getNoofUnit());

		double sellingPrice = Double.parseDouble(form.getUnitPrice());

		ProductEntity pe = productRepository.findOne(new Long(form.getProductID()));

		SupplierEntity supplier = supplierRepository.findOne(new Long(form.getSupplierId()));

		Set<ProductItemEntity> itemSet = new HashSet<>();

		ProductItemEntity pie = new ProductItemEntity();
		pie.setActive(true);
		pie.setAddedby(logedinUser);
		pie.setItemname(form.getItemName().toUpperCase());
		pie.setStock_avalble(noofunit);
		pie.setUnit_price(sellingPrice);

		pie.setProductEntity(pe);
		itemSet.add(pie);

		pe.setProductItemEntities(itemSet);

		ProductItemHistoryEntity pihe = new ProductItemHistoryEntity();
		pihe.setAddedby(logedinUser);
		long cuurentMillsec = System.currentTimeMillis();
		pihe.setAddedon(new Date(cuurentMillsec));
		pihe.setNoofunit(noofunit);
		pihe.setProductItemEntities(pie);
		pihe.setUnitperchasecost(purchasePriceunit);
		pihe.setTotalcost(purchasePriceunit * noofunit);
		pihe.setUpdateReason("INITIAL");
		pihe.setSupplier(supplier);

		productItemHistoryRepository.save(pihe);

		pe = productRepository.save(pe);

		itemSet = pe.getProductItemEntities();

		return true;
	}

	public boolean updateItemDetail(String logedinUser, PurchaseCommandForm form) {

		double purchasePriceunit = Double.parseDouble(form.getPurchasePriceUnit());

		double noofunit = Double.parseDouble(form.getNoofUnit());

		double sellingPrice = Double.parseDouble(form.getUnitPrice());

		ProductItemEntity pie = productItemRepository.findOne(new Long(form.getItemID()));

		SupplierEntity supplier = supplierRepository.findOne(new Long(form.getSupplierId()));

		double avalable_stock = pie.getStock_avalble();

		avalable_stock += noofunit;
		pie.setStock_avalble(avalable_stock);
		pie.setUnit_price(sellingPrice);

		ProductItemHistoryEntity pihe = new ProductItemHistoryEntity();
		pihe.setAddedby(logedinUser);
		long cuurentMillsec = System.currentTimeMillis();
		pihe.setAddedon(new Date(cuurentMillsec));
		pihe.setNoofunit(noofunit);

		pihe.setProductItemEntities(pie);

		pihe.setUnitperchasecost(purchasePriceunit);
		pihe.setTotalcost(purchasePriceunit * noofunit);
		pihe.setUpdateReason("UPDATE");
		pihe.setSupplier(supplier);

		productItemHistoryRepository.save(pihe);

		pie = productItemRepository.save(pie);

		return true;
	}

	public ProductItemDTO getOneItemdetail(String key) {
		ProductItemEntity p = productItemRepository.findOne(new Long(key));

		ProductItemDTO temp = new ProductItemDTO();
		temp.setId(p.getProductitementity_id());
		temp.setItem_name(p.getItemname());
		temp.setStock_avalble(p.getStock_avalble());
		temp.setUnit_price(p.getUnit_price());

		return temp;
	}

	public boolean isAvalblebyProductName(String productName) {
		List<ProductEntity> pe = productRepository.findByproductname(productName.toUpperCase());
		
		System.out.println(pe.size());
		if (pe.size() > 0)
			return false;

		return true;
	}

	public boolean isAvalblebyItemName(String itemName) {
		List<ProductItemEntity> pie = productItemRepository.findByitemname(itemName.toUpperCase());

		if (pie.size() > 0)
			return false;
		return true;

	}

}
