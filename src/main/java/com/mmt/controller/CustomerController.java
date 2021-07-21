/**
 * 
 */
package com.mmt.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mmt.command.CustomerCommandForm;
import com.mmt.command.PurchaseCommandForm;
import com.mmt.config.exception.CustomerNotFoundException;
import com.mmt.dto.CustomerDTO;
import com.mmt.service.CustomerService;

/**
 * @author pkumar
 *
 */

@Controller
@RequestMapping("customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	private ObjectMapper mapper = new ObjectMapper();

	@PostMapping(value = "/addsupplier.html")
	public String addSupplierPost(@ModelAttribute("transactionCommandForm") PurchaseCommandForm purchaseCommandForm,
			Model model, Principal principal) {

		customerService.saveSupplier(principal.getName(), purchaseCommandForm);

		return "redirect:/mmt/thankyou.html?msg=Supplier Data Saved Successfully";

	}

	@PostMapping(value = "/addcustomer.html")
	public String addCustomerPost(@ModelAttribute("customerCommandForm") CustomerCommandForm customerCommandForm,
			Model model) {

		try {
			customerService.saveCustomer("Not USED", customerCommandForm);
		} catch (Exception e) {
			logger.error(e.getMessage());

			return "redirect:/mmt/thankyou.html?msg=Customer Data Saving Error !!! May Be Duplicate Value Enered";
		}

		return "redirect:/mmt/thankyou.html?msg=Customer Data Saved Successfully";

	}

	@PostMapping(value = "/updatecustomer.html")
	public String updateCustomerPost(@ModelAttribute("customerCommandForm") CustomerCommandForm customerCommandForm,
			Model model) {
		try {
			customerService.updateCustomer("Not USED", customerCommandForm);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "redirect:/mmt/thankyou.html?msg=Customer Data Saving Error " + e.getMessage();
		}
		return "redirect:/mmt/thankyou.html?msg=Customer Data Updated Successfully";

	}

	@GetMapping(value = "/getCustomer.html")
	public @ResponseBody String getOneCustomer(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "mobileno", required = false) String mobileno, HttpServletRequest request)
			throws JsonProcessingException {

		logger.debug("Customer Ajax Requested");
		logger.debug("name Request-- " + name + " ------mobileno request -- " + mobileno);

		String view = "";

		if ("".equals(mobileno) & "".equals(name))
			return "Both Mobile No & Name can't be empty";

		try {
			CustomerDTO cDTO = customerService.getCustomerByNameORMobileNo(name, mobileno);
			if (!(cDTO.getCustomer_id() == 0))
				cDTO.setDues(cDTO.getDues()==null?"0":cDTO.getDues());
				//cDTO.setUpdatedate(cDTO.getUpdatedate()==null?null:cDTO.getUpdatedate());
				view = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cDTO);
		} catch (CustomerNotFoundException e) {
			view = "Error --" + e.getMessage();
		}

		return view;

	}

	public String updateSupplierPost() {
		return null;

	}
	
	@GetMapping(value = "/isAvalbleBySupplierName.html")
	public @ResponseBody String isAvalbleBySupplierName(@RequestParam(value = "supplierName", required = false) String supplierName){
		
		return customerService.isAvalbleBySupplierName(supplierName)+"";
		
		
	}
	
	@GetMapping(value = "/isAvalblebyCustomerName.html")
	public @ResponseBody String isAvalblebyCustomerName(@RequestParam(value = "name", required = false) String name){
		
		return customerService.isAvalblebyCustomerName(name)+"";
		
	}
	
	@GetMapping(value = "/customerPage.html")
	public  String customerPage(@RequestParam(value = "name", required = false) String name){
		
		
		return "admin.tiles";
		
	}

}
