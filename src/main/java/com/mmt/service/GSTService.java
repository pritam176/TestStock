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
import org.springframework.transaction.annotation.Transactional;

import com.mmt.Entity.GSTSlabEntity;
import com.mmt.Entity.ItemTransactionEntity;
import com.mmt.command.GSTSlabCommand;
import com.mmt.dto.GSTDTO;
import com.mmt.repository.GstSlabRepository;

/**
 * @author pkumar
 *
 */
@Service
public class GSTService {

	private static final Logger logger = LoggerFactory.getLogger(GSTService.class);

	private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	@Autowired
	private GstSlabRepository gstSlabRepository;

	@Transactional
	public boolean saveGstSlab(GSTSlabCommand gSTSlabCommand) {
		logger.debug("GST SERVICE SAVE");
		GSTSlabEntity gstEntity = new GSTSlabEntity();

		gstEntity.setName(gSTSlabCommand.getName().toUpperCase());
		gstEntity.setTaxrate(gSTSlabCommand.getTaxrate());

		gstEntity = gstSlabRepository.save(gstEntity);

		if (gstEntity.getSlab_id() > 1)
			return true;
		return false;
	}

	@Transactional
	public boolean updateGstSlab(GSTSlabCommand gSTSlabCommand) {
		logger.debug("GST SERVICE SAVE");
		GSTSlabEntity gstEntity = gstSlabRepository.findOne(new Long(gSTSlabCommand.getSlabId()));

		gstEntity.setName(gSTSlabCommand.getName().toUpperCase());
		gstEntity.setTaxrate(gSTSlabCommand.getTaxrate());

		gstEntity = gstSlabRepository.save(gstEntity);

		if (gstEntity.getSlab_id() > 1)
			return true;
		return false;
	}

	public List<GSTSlabEntity> getAllGstSlab() {
		return gstSlabRepository.findAll();
	}

	public List<GSTDTO> getAllGstSlabData(GSTSlabCommand gSTSlabCommand) throws ParseException {
		long cuurentMillsec = System.currentTimeMillis();

		long slabId = new Long(gSTSlabCommand.getSlabId());

		ArrayList<GSTDTO> gstDtoList = new ArrayList<>();

		java.util.Date startdateUtil = new java.util.Date(cuurentMillsec);
		java.util.Date endDateUtil = new java.util.Date(cuurentMillsec);
		if (!"".equals(gSTSlabCommand.getStartDate())) {
			startdateUtil = sdf.parse(gSTSlabCommand.getStartDate());
			endDateUtil = sdf.parse(gSTSlabCommand.getEndDate());
		}
		Iterator<ItemTransactionEntity> gstList = gstSlabRepository
				.getGSTDataBySlabIdAndDate(slabId, new Date(startdateUtil.getTime()), new Date(endDateUtil.getTime()))
				.iterator();

		while (gstList.hasNext()) {
			GSTDTO gstDto = new GSTDTO();
			ItemTransactionEntity temp = gstList.next();
			GSTSlabEntity ge = temp.getgSTSlabEntity();

			gstDto.setInvoice_date(sdf.format(temp.getInvoice_date()));
			gstDto.setItem_transaction_id(temp.getTransactionEntity().getTransaction_id());
			gstDto.setDiscountOffer(temp.getDiscountOffer() + "");
			gstDto.setItem_name(temp.getItem_name());
			gstDto.setSell_unit(temp.getSell_unit() + "");
			gstDto.setProduct_name(temp.getProduct_name());
			gstDto.setFinalCost(temp.getFinalCost() + "");
			gstDto.setCustomerName((temp.getCustomerEntity() == null) ? "" : temp.getCustomerEntity().getName());
			gstDto.setGstPerc(ge.getTaxrate());
			gstDto.setGstAmount(temp.getGstAmount() + "");
			gstDto.setTotal_cost(temp.getTotal_cost() + "");
			gstDto.setUnit_cost(temp.getUnit_cost() + "");
			gstDto.setHsnCode(temp.getHsnCode());
			gstDtoList.add(gstDto);

		}

		return gstDtoList;

	}

	public boolean isAvalblebySlabName(String name) {
		List<GSTSlabEntity> ge = gstSlabRepository.findByname(name.toUpperCase());
		System.out.println(ge.size());
		if (ge.size() > 0)
			return false;

		return true;

	}

	public boolean isAvalblebyTaxRate(String taxrate) {

		List<GSTSlabEntity> ge = gstSlabRepository.findBytaxrate(taxrate);
		System.out.println(ge.size());
		if (ge.size() > 0)
			return false;
		return true;

	}

}
