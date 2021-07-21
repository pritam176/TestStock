/**
 * 
 */
package com.mmt.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mmt.Entity.GSTSlabEntity;
import com.mmt.Entity.ProductEntity;
import com.mmt.Entity.SupplierEntity;
import com.mmt.command.CustomerCommandForm;
import com.mmt.command.GSTSlabCommand;
import com.mmt.command.PurchaseCommandForm;
import com.mmt.dto.ProductItemDTO;
import com.mmt.service.CustomerService;
import com.mmt.service.ProductService;

/**
 * @author pkumar
 *
 */
@Controller
@RequestMapping("purchase")
public class PurchaseController {

	private static final Logger logger = LoggerFactory.getLogger(PurchaseController.class);

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private ProductService productService;

	@Autowired
	private CustomerService customerService;

	@GetMapping(value = "/purchaseproduct.html")
	public String purchaseproduct(Model model, HttpServletResponse resp) {
		logger.debug("Enter In to purchaseproduct page");
		PurchaseCommandForm purchaseCommandForm = new PurchaseCommandForm();
		CustomerCommandForm customerCommandForm = new CustomerCommandForm();
		GSTSlabCommand gstCommandForm = new GSTSlabCommand();
		model.addAttribute("purchaseCommandForm", purchaseCommandForm);
		model.addAttribute("customerCommandForm", customerCommandForm);
		model.addAttribute("gstCommandForm", gstCommandForm);

		List<ProductEntity> productList = productService.getAllProductEntity();
		
		List<GSTSlabEntity> getAllGstSlabList = productService.getAllGstSlab();
		
		List<SupplierEntity> supplierList = customerService.getAllProductEntity();

		Map<Long, String> result1 = new HashMap<>();
		Iterator<ProductEntity> It = productList.iterator();
		while (It.hasNext()) {
			ProductEntity pe = It.next();
			result1.put(pe.getProduct_id(), pe.getProductname());
		}

		model.addAttribute("productList", result1);

		

		Map<Long, String> suppliermap = new HashMap<>();
		Iterator<SupplierEntity> it = supplierList.iterator();
		while (it.hasNext()) {
			SupplierEntity pe = it.next();
			suppliermap.put(pe.getSupplier_id(), pe.getSuppliername());
		}

		model.addAttribute("suppliermap", suppliermap);
		
		
		Map<Long, String> gstslabmap = new HashMap<>();
		Iterator<GSTSlabEntity> gstit = getAllGstSlabList.iterator();
		while (gstit.hasNext()) {
			GSTSlabEntity pe = gstit.next();
			gstslabmap.put(pe.getSlab_id(), pe.getName());
		}

		model.addAttribute("gstslabmap", gstslabmap);

		return "purchase.tiles";

	}

	@PostMapping(value = "/purchaseproduct.html")
	public String purchaseproductPost(@ModelAttribute("transactionCommandForm") PurchaseCommandForm purchaseCommandForm,
			Model model,Principal principal) {
		logger.debug("Enter In to purchaseproduct post");
		System.out.println(purchaseCommandForm.getProductName());
		model.addAttribute("purchaseCommandForm", purchaseCommandForm);

		productService.saveProduct(purchaseCommandForm.getProductName(), principal.getName(),purchaseCommandForm.getGstSlabid(),purchaseCommandForm.getHsnCode());

		return "redirect:/mmt/thankyou.html?msg=Data Saved Successfully";

	}

	@PostMapping(value = "/purchaseitem.html")
	public String purchaseItemPost(@ModelAttribute("transactionCommandForm") PurchaseCommandForm purchaseCommandForm,
			Model model,Principal principal) {
		logger.debug("Enter In to purchaseproduct post");
		System.out.println(purchaseCommandForm.getItemName());
		model.addAttribute("purchaseCommandForm", purchaseCommandForm);

		productService.saveProductItem(principal.getName(), purchaseCommandForm);

		return "redirect:/mmt/thankyou.html?msg=Data Saved Successfully";

	}

	@PostMapping(value = "/updatepurchaseitem.html")
	public String updatepurchaseItemPost(
			@ModelAttribute("transactionCommandForm") PurchaseCommandForm purchaseCommandForm, Model model,Principal principal) {
		logger.debug("Enter In to updatepurchaseitem post");
		System.out.println(purchaseCommandForm.getItemName());
		model.addAttribute("purchaseCommandForm", purchaseCommandForm);

		productService.updateItemDetail(principal.getName(), purchaseCommandForm);

		return "redirect:/mmt/thankyou.html?msg=Data Saved Successfully";

	}

	@GetMapping(value = "/getItemData.html")
	public @ResponseBody String getProductItemData(@RequestParam(value = "key", required = false) String key,
			HttpServletRequest request) throws JsonProcessingException {
		logger.debug("Sub Type Ajax Requested");
		logger.debug("key Request= " + key);
		ProductItemDTO productItem = null;
		if (!StringUtils.isEmpty(key)) {
			productItem = productService.getOneItemdetail(key);
		}
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(productItem);

	}
	
	@GetMapping(value = "/isAvalblebyProductName.html")
	public @ResponseBody String isAvalblebyProductName(@RequestParam(value = "productName", required = false) String productName){
		
		return productService.isAvalblebyProductName(productName)+"";
		
		
	}
	
	@GetMapping(value = "/isAvalblebyItemName.html")
	public @ResponseBody String isAvalblebyItemName(@RequestParam(value = "itemName", required = false) String itemName){
		
		return productService.isAvalblebyItemName(itemName)+"";
		
	}

}
