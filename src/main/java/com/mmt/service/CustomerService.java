/**
 * 
 */
package com.mmt.service;

import java.sql.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmt.Entity.CustomerEntity;
import com.mmt.Entity.SupplierEntity;
import com.mmt.command.CustomerCommandForm;
import com.mmt.command.PurchaseCommandForm;
import com.mmt.config.exception.CustomerNotFoundException;
import com.mmt.dto.CustomerDTO;
import com.mmt.repository.CustomerRepository;
import com.mmt.repository.SupplierRepository;

/**
 * @author pkumar
 *
 */

@Service
public class CustomerService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	private SupplierRepository supplierRepository;

	@Autowired
	private CustomerRepository customerRepository;

	// private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	public boolean saveSupplier(String user, PurchaseCommandForm form) {

		logger.debug("Save Supplier");
		SupplierEntity supplierEntity = new SupplierEntity();
		supplierEntity.setAddedBy(user);
		supplierEntity.setAddress(form.getSupplierAddres());
		supplierEntity.setMobielNo(form.getSupplierMobileNo());
		supplierEntity.setSuppliername(form.getSupplierName().toUpperCase());
		supplierEntity.setSupplierlicenceno(form.getSupplierLicenceNo());

		supplierRepository.save(supplierEntity);

		return true;

	}

	public List<SupplierEntity> getAllProductEntity() {
		return supplierRepository.findAll();

	}

	public boolean saveCustomer(String user, CustomerCommandForm form) {

		logger.debug("Save Supplier");
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setAddress(form.getAddress());
		customerEntity.setName(form.getName().toUpperCase());
		customerEntity.setMobileNo(form.getMobile_no());
		customerEntity.setEmail(form.getEmail());

		long cuurentMillsec = System.currentTimeMillis();
		customerEntity.setDate(new Date(cuurentMillsec));
		customerEntity.setUpdateBy("Initial");

		customerRepository.save(customerEntity);

		return true;

	}

	public boolean updateCustomer(String user, CustomerCommandForm form) {

		logger.debug("Save Supplier");
		CustomerEntity customerEntity = customerRepository.findOne(new Long(form.getCustomer_id()));
		long cuurentMillsec = System.currentTimeMillis();
		if ("".equals(form.getDues())) {
			customerEntity.setAddress(form.getAddress());
			customerEntity.setName(form.getName().toUpperCase());
			customerEntity.setMobileNo(form.getMobile_no());
			customerEntity.setEmail(form.getEmail());
			customerEntity.setUpdateReason(form.getUpdateReason());

		} else {
			customerEntity.setUpdateBy(user);
			customerEntity.setUpdatedate(new Date(cuurentMillsec));
			customerEntity.setDues(form.getDues());
			customerEntity.setUpdateReason(form.getUpdateReason());
		}

		customerRepository.save(customerEntity);

		return true;

	}

	public CustomerDTO getCustomerByNameORMobileNo(String name, String mobileNo) throws CustomerNotFoundException {

		CustomerEntity ce = null;

		if (!"".equals(name)) {
			ce = customerRepository.getCustomerByName(name);
		} else {
			ce = customerRepository.getCustomerByMobileNO(mobileNo);
		}

		return mapDataFromEntity(ce);

	}

	private CustomerDTO mapDataFromEntity(CustomerEntity ce) throws CustomerNotFoundException {
		if (ce == null)
			throw new CustomerNotFoundException("Given Mobile No OR NAME NOT IN RECORD");
		CustomerDTO cDTO = new CustomerDTO();
		cDTO.setAddress(ce.getAddress());
		cDTO.setCustomer_id(ce.getCustomer_id());
		cDTO.setMobileNo(ce.getMobileNo());
		cDTO.setName(ce.getName());
		cDTO.setDues(ce.getDues());
		cDTO.setEmail(ce.getEmail());
		cDTO.setDate(ce.getDate());
		cDTO.setUpdatedate(ce.getUpdatedate());

		return cDTO;

	}
	
	public boolean isAvalbleBySupplierName(String name) {
		List<SupplierEntity> se = supplierRepository.findBysuppliername(name.toUpperCase());
		
		
		if (se.size() > 0)
			return false;

		return true;
	}

	public boolean isAvalblebyCustomerName(String name) {
		List<CustomerEntity> ce = customerRepository.findByname(name.toUpperCase());

		if (ce.size() > 0)
			return false;
		return true;

	}

}
