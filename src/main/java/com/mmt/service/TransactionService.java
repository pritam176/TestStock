/**
 * 
 */
package com.mmt.service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mmt.Entity.CustomerEntity;
import com.mmt.Entity.GSTSlabEntity;
import com.mmt.Entity.ItemTransactionEntity;
import com.mmt.Entity.ProductEntity;
import com.mmt.Entity.ProductItemEntity;
import com.mmt.Entity.ProductItemHistoryEntity;
import com.mmt.Entity.TransactionEntity;
import com.mmt.command.TransactionCommandForm;
import com.mmt.dto.ItemDTO;
import com.mmt.dto.TransactionDTO;
import com.mmt.dto.TransactionItemDTO;
import com.mmt.repository.CustomerRepository;
import com.mmt.repository.ProductItemHistoryRepository;
import com.mmt.repository.ProductItemRepository;
import com.mmt.repository.TransactionRepository;
import com.mmt.config.exception.*;

/**
 * @author pkumar
 *
 */
@Service
public class TransactionService {

	@Autowired
	private ProductItemRepository productItemRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private ProductItemHistoryRepository productItemHistoryRepository;

	@Autowired
	private CustomerRepository customerRepository;

	private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	/**
	 * @author sellitem to customer & deduct data from stock
	 * @throws NotEnoughStockException
	 *
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = NotEnoughStockException.class)
	public long saveTransaction(String user, TransactionCommandForm transactionCommandForm)
			throws NotEnoughStockException {

		
		
		
		CustomerEntity ce = customerRepository.findOne(new Long(transactionCommandForm.getCustomerId()));

		TransactionEntity te = new TransactionEntity();
		long cuurentMillsec = System.currentTimeMillis();
		
		Date invoiceDate = new Date(cuurentMillsec);

		Iterator<ItemDTO> itemDtoit = transactionCommandForm.getItemDto().iterator();
		ItemDTO temp = new ItemDTO();
		ItemTransactionEntity ite = null;

		double transactionAmountAll = 0;
		double transactionDiscountAll = 0;
		double gstTotalAmount = 0;

		Set<ItemTransactionEntity> itemTransactionEntitySet = new HashSet<ItemTransactionEntity>();
		while (itemDtoit.hasNext()) {
			temp = itemDtoit.next();

			ProductItemEntity stock = productItemRepository.getOne(new Long(temp.getId()));
			
			ProductEntity pe = stock.getProductEntity();

			// check stock
			double sellUnit = temp.getTotal_unit();
			if (!(stock.getStock_avalble() >= sellUnit)) {

				throw new NotEnoughStockException("Not Enough Stock RollBacking All");
			}

			GSTSlabEntity ge = stock.getProductEntity().getgSTSlabEntity();

			double withoutGST = sellUnit * temp.getUnit_cost();

			double offerPrice = temp.getOffer_price();
			double gstPerc = Double.valueOf(ge.getTaxrate());
			double discountedCost = withoutGST - offerPrice;
			double gstAmount = Math.round(discountedCost *( gstPerc/100));
			
			

			double finalCost = discountedCost + gstAmount;

			transactionDiscountAll += offerPrice;
			transactionAmountAll += discountedCost;
			gstTotalAmount += gstAmount;

			// Updation
			stock.setStock_avalble(stock.getStock_avalble() - sellUnit);

			ite = new ItemTransactionEntity();
			ite.setProduct_id(temp.getProduct_id());
			ite.setProduct_name(temp.getProduct_name());
			ite.setItem_id(temp.getId());
			ite.setItem_name(temp.getItem_name());
			ite.setDiscountOffer(offerPrice);
			ite.setTotal_cost(discountedCost);
			ite.setSell_unit(temp.getTotal_unit());
			ite.setUnit_cost(temp.getUnit_cost());
			ite.setHsnCode(pe.getHsnCode());
			ite.setGstAmount(gstAmount);
			ite.setgSTSlabEntity(ge);
			ite.setCustomerEntity(ce);
			ite.setFinalCost(finalCost);
			ite.setInvoice_date(invoiceDate);
			ite.setTransactionEntity(te);
			itemTransactionEntitySet.add(ite);

			/* Entery In product History Tabel */

			ProductItemHistoryEntity pihe = new ProductItemHistoryEntity();
			pihe.setAddedby(user);

			pihe.setAddedon(invoiceDate);
			pihe.setNoofunit(sellUnit);
			pihe.setProductItemEntities(stock);
			pihe.setUnitperchasecost(temp.getUnit_cost());
			pihe.setFinalCost(finalCost);
			pihe.setGstAmount(gstAmount);
			pihe.setTotalcost(discountedCost);
			pihe.setUpdateReason("SELL");
			pihe.setCustomerEntity(ce);
			pihe.setDiscount(offerPrice);

			productItemHistoryRepository.save(pihe);

			productItemRepository.save(stock);

		}

		te.setInvoice_date(invoiceDate);
		te.setAmount(transactionAmountAll);
		te.setTotal_discount(transactionDiscountAll);
		te.setTransaction_type("SELL");
		te.setTransaction_status(temp.getTransaction_status());
		te.setNoOfItem(transactionCommandForm.getItemDto().size());
		te.setLogedin_user(user);
		te.setPaymentMode(transactionCommandForm.getPaymentMode());
		te.setCheqNo(transactionCommandForm.getCheckNo());
		te.setCustomerEntity(ce);
		te.setCustomer_name(ce.getName());
		te.setGst_fee(gstTotalAmount);
		System.out.println(itemTransactionEntitySet.size());

		te.setItemTransactionEntity(itemTransactionEntitySet);

		te = transactionRepository.save(te);

		return te.getTransaction_id();

	}

	public TransactionDTO getTransactionData(String key) {

		TransactionDTO transactionDTO = new TransactionDTO();

		TransactionEntity te = transactionRepository.findOne(new Long(key));
		if (te != null) {

			CustomerEntity ce = te.getCustomerEntity();

			Set<ItemTransactionEntity> iTeSet = te.getItemTransactionEntity();
			transactionDTO.setTransactionid(te.getTransaction_id() + "");
			transactionDTO.setAddress(ce.getAddress());
			transactionDTO.setCustomerName(ce.getName());
			transactionDTO.setMobileNo(ce.getMobileNo());
			transactionDTO.setCheckNo(te.getCheqNo());
			transactionDTO.setPaymentMode(te.getPaymentMode());

			Iterator<ItemTransactionEntity> it = iTeSet.iterator();
			Set<TransactionItemDTO> transactionItemDTO = new HashSet<>();

			double transactionAmountAll = 0;
			double transactionDiscountAll = 0;

			double gstTotalAmount = 0;

			while (it.hasNext()) {
				ItemTransactionEntity temp = it.next();
				TransactionItemDTO tempItem = new TransactionItemDTO();
				tempItem.setDiscount(temp.getDiscountOffer() + "");
				tempItem.setItemId(temp.getItem_id() + "");
				tempItem.setItemName(temp.getItem_name());
				tempItem.setProductId(temp.getProduct_id());
				tempItem.setProductName(temp.getProduct_name());
				tempItem.setSellUnit(temp.getSell_unit() + "");
				tempItem.setUnitcost(temp.getUnit_cost() + "");
				//tempItem.setTotalamount(temp.getTotal_cost() + "");

				tempItem.setHsnCode(temp.getHsnCode());
				
				GSTSlabEntity ge = temp.getgSTSlabEntity();

				double sellUnit = temp.getSell_unit();
				double withoutGST = sellUnit * temp.getUnit_cost();

				double offerPrice = temp.getDiscountOffer();
				double gstPerc = Double.valueOf(ge.getTaxrate());
				double discountedCost = withoutGST - offerPrice;
				double gstAmount = Math.round(discountedCost *( gstPerc/100));

				// totalPaybleAll = discountedCost + gstAmount;
				transactionDiscountAll += offerPrice;
				transactionAmountAll += discountedCost;
				gstTotalAmount += gstAmount;
				
				tempItem.setCgstAmt(gstAmount/2+ "");
				tempItem.setSgstAmt(gstAmount/2+ "");
				tempItem.setCgstPerc(gstPerc/2+ "");
				tempItem.setSgstPerc(gstPerc/2+ "");
				tempItem.setTotalamount(discountedCost+ "");
				tempItem.setTotalGstAmt(gstAmount+ "");
				tempItem.setPaybleAmount(discountedCost+gstAmount+ "");
				transactionItemDTO.add(tempItem);
			}
			transactionDTO.setTransactionItemDTO(transactionItemDTO);
			transactionDTO.setTotalamount(transactionAmountAll + "");
			transactionDTO.setTotaldiscount(transactionDiscountAll + "");
			transactionDTO.setPaybleamount(transactionAmountAll + gstTotalAmount + "");
			transactionDTO.setGstAmount(gstTotalAmount + "");
			transactionDTO.setTransactionDate(sdf.format(te.getInvoice_date()));
		}

		return transactionDTO;
	}

	public List<TransactionDTO> getAllTransactionData(String startdate, String endDate) throws ParseException {

		List<TransactionDTO> transactionList = new ArrayList<TransactionDTO>();

		// Iterator<TransactionEntity> it =
		// transactionRepository.findAll().iterator();

		long cuurentMillsec = System.currentTimeMillis();

		java.util.Date startdateUtil = new java.util.Date(cuurentMillsec);
		java.util.Date endDateUtil = new java.util.Date(cuurentMillsec);
		if (!"".equals(startdate)) {
			startdateUtil = sdf.parse(startdate);
			endDateUtil = sdf.parse(endDate);
		}
		Iterator<TransactionEntity> it = transactionRepository
				.findByInvoice_DateBetween(new Date(startdateUtil.getTime()), new Date(endDateUtil.getTime()))
				.iterator();

		while (it.hasNext()) {

			TransactionDTO transactionDTO = new TransactionDTO();

			TransactionEntity tempte = it.next();

			Set<ItemTransactionEntity> iTeSet = tempte.getItemTransactionEntity();

			transactionDTO.setTransactionid(tempte.getTransaction_id() + "");
			transactionDTO.setNoOfItem(iTeSet.size() + "");

			Iterator<ItemTransactionEntity> tempit = iTeSet.iterator();
			Set<TransactionItemDTO> transactionItemDTO = new HashSet<>();

			double transactionAmountAll = 0;
			double transactionDiscountAll = 0;

			double gstTotalAmount = 0;

			while (tempit.hasNext()) {
				ItemTransactionEntity temp = tempit.next();
				TransactionItemDTO tempItem = new TransactionItemDTO();
				tempItem.setDiscount(temp.getDiscountOffer() + "");
				tempItem.setItemId(temp.getItem_id() + "");
				tempItem.setItemName(temp.getItem_name());
				tempItem.setProductId(temp.getProduct_id());
				tempItem.setProductName(temp.getProduct_name());
				tempItem.setSellUnit(temp.getSell_unit() + "");
				tempItem.setUnitcost(temp.getUnit_cost() + "");
				tempItem.setTotalamount(temp.getTotal_cost() + "");
				tempItem.setHsnCode(temp.getHsnCode());
				transactionItemDTO.add(tempItem);

				GSTSlabEntity ge = temp.getgSTSlabEntity();

				double sellUnit = temp.getSell_unit();
				double withoutGST = sellUnit * temp.getUnit_cost();

				double offerPrice = temp.getDiscountOffer();
				double gstPerc = Double.valueOf(ge.getTaxrate());
				double discountedCost = withoutGST - offerPrice;
				double gstAmount = Math.round(discountedCost *( gstPerc/100));

				// totalPaybleAll = discountedCost + gstAmount;
				transactionDiscountAll += offerPrice;
				transactionAmountAll += discountedCost;
				gstTotalAmount += gstAmount;
			}
			transactionDTO.setTransactionItemDTO(transactionItemDTO);
			transactionDTO.setTotalamount(transactionAmountAll + "");
			transactionDTO.setTotaldiscount(transactionDiscountAll + "");
			transactionDTO.setPaybleamount(transactionAmountAll + gstTotalAmount + "");
			transactionDTO.setTransactionDate(sdf.format(tempte.getInvoice_date()));
			transactionDTO.setCustomerName(tempte.getCustomer_name());
			transactionDTO.setPaymentMode(tempte.getPaymentMode());
			transactionDTO.setGstAmount(gstTotalAmount + "");
			transactionList.add(transactionDTO);
		}
		return transactionList;
	}

}
