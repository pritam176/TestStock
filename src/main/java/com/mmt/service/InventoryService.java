/**
 * 
 */
package com.mmt.service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmt.Entity.ProductItemHistoryEntity;
import com.mmt.command.SearchStockCommandForm;
import com.mmt.dto.StockInventoryDTO;
import com.mmt.repository.ProductItemHistoryRepository;

/**
 * @author pkumar
 *
 */

@Service
public class InventoryService {

	private static final Logger logger = LoggerFactory.getLogger(InventoryService.class);

	private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	@Autowired
	ProductItemHistoryRepository productItemHistoryRepository;

	public List<StockInventoryDTO> getItemHistorydata(SearchStockCommandForm searchStockCommandForm)
			throws ParseException {

		logger.debug("getItemHistorydata");

		long item_id = new Long(searchStockCommandForm.getItemID());

		String reason = "%";
		if ("UPDATE".equals(searchStockCommandForm.getReason()))
			reason = "UPDATE";
		if ("SELL".equals(searchStockCommandForm.getReason()))
			reason = "SELL";
		long cuurentMillsec = System.currentTimeMillis();

		java.util.Date startdateUtil = new java.util.Date(cuurentMillsec);
		java.util.Date endDateUtil = new java.util.Date(cuurentMillsec);
		if (!"".equals(searchStockCommandForm.getStartDate())) {
			startdateUtil = sdf.parse(searchStockCommandForm.getStartDate());
			endDateUtil = sdf.parse(searchStockCommandForm.getEndDate());
		}

		List<StockInventoryDTO> sidList = new ArrayList<>();

		Iterator<ProductItemHistoryEntity> it = productItemHistoryRepository
				.getProductData(item_id, reason, new Date(startdateUtil.getTime()), new Date(endDateUtil.getTime()))
				.iterator();

		while (it.hasNext()) {
			StockInventoryDTO sid = new StockInventoryDTO();
			ProductItemHistoryEntity temp = it.next();
			sid.setAddedDate(sdf.format(temp.getAddedon()));

			sid.setDiscountOfer(temp.getDiscount() + "");
			sid.setItemName(temp.getProductItemEntities().getItemname());
			sid.setNoofUnit(temp.getNoofunit() + "");
			sid.setProductName(temp.getProductItemEntities().getProductEntity().getProductname());

			sid.setUpdateReason(temp.getUpdateReason());

			sid.setCustomerName((temp.getCustomerEntity() == null) ? "" : temp.getCustomerEntity().getName());
			sid.setSupplierName((temp.getSupplier() == null) ? "" : temp.getSupplier().getSuppliername());

			sid.setUnitPerchaseCost(temp.getUnitperchasecost() + "");

			if (!"SELL".equals(temp.getUpdateReason()))
				sid.setTotalPurchaseCost(temp.getTotalcost() + "");

			sid.setTotalSellCost(temp.getFinalCost() + "");

			sid.setTotalDiscountOffer(temp.getDiscount() * temp.getNoofunit() + "");
			sidList.add(sid);
		}

		return sidList;
	}

}
