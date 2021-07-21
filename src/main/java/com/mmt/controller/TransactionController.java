/**
 * 
 */
package com.mmt.controller;

import java.security.Principal;
import java.util.Date;
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
import com.mmt.Entity.ProductEntity;
import com.mmt.command.CustomerCommandForm;
import com.mmt.command.TransactionCommandForm;
import com.mmt.config.exception.NotEnoughStockException;
import com.mmt.dto.ProductItemDTO;
import com.mmt.dto.TransactionDTO;
import com.mmt.service.ProductService;
import com.mmt.service.TransactionService;
import com.mmt.util.DateUtil;

/**
 * @author pkumar
 *
 */

@Controller
@RequestMapping("sell")
public class TransactionController {

	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private TransactionService transactionService;

	private ObjectMapper mapper = new ObjectMapper();

	@GetMapping(value = "/sellitem.html")
	public String sellitemPage(Model model, HttpServletResponse resp) {
		logger.debug("Enter In to transaction page");
		TransactionCommandForm transactionCommandForm = new TransactionCommandForm();
		model.addAttribute("transactionCommandForm", transactionCommandForm);

		List<ProductEntity> productList = productService.getAllProductEntity();

		Map<Long, String> result1 = new HashMap<>();
		Iterator<ProductEntity> It = productList.iterator();
		while (It.hasNext()) {
			ProductEntity pe = It.next();
			result1.put(pe.getProduct_id(), pe.getProductname());
		}

		model.addAttribute("productList", result1);

		CustomerCommandForm customerCommandForm = new CustomerCommandForm();

		model.addAttribute("customerCommandForm", customerCommandForm);

		return "transaction.tiles";

	}

	@PostMapping(value = "/sellitem.html")
	public String sellitemPost(@ModelAttribute("transactionCommandForm") TransactionCommandForm transactionCommandForm,
			HttpServletResponse resp,Principal principal) {
		logger.debug("sellitem Post ");
		try {
			logger.debug("sellitem Post no of item- " + transactionCommandForm.getItemDto().size());
		} catch (Exception e) {
			return "redirect:/mmt/thankyou.html?msg=No Item Selected for Purchase";
		}

		String view = "";
		long id;
		try {
			id = transactionService.saveTransaction(principal.getName(), transactionCommandForm);
			view = "redirect:/sell/getReportItemData.html?key=" + id;
		} catch (NotEnoughStockException e) {
			view = "redirect:/mmt/thankyou.html?msg=Not Enough Stock";
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		// return "sellreportPage.tiles";
		return view;

	}

	@GetMapping(value = "/getProductItemData.html")
	public @ResponseBody String getProductItemData(@RequestParam(value = "key", required = false) String key,
			HttpServletRequest request) throws JsonProcessingException {
		logger.debug("Sub Type Ajax Requested");
		logger.debug("key Request= " + key);
		List<ProductItemDTO> productItemList = null;
		if (!StringUtils.isEmpty(key)) {
			productItemList = productService.getOneProductEntity(key);
			
		}
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(productItemList);

	}

	@GetMapping(value = "/getReportItemData.html")
	public String getReportItemData(@RequestParam(value = "key", required = false) String key,
			HttpServletRequest request, Model model) throws JsonProcessingException {
		logger.debug("getReportItemData Requested");
		logger.debug("key Request= " + key);
		TransactionDTO tranasctionData = null;
		String view = "redirect:/mmt/thankyou.html?msg=No Key Provided";
		if (!StringUtils.isEmpty(key)) {
			tranasctionData = transactionService.getTransactionData(key);
			view = "sellreportPage.tiles";
			if (tranasctionData.getPaybleamount() == null)
				view = "redirect:/mmt/thankyou.html?msg=Wrong Key Provided";

		}
		model.addAttribute("reportDate",DateUtil.getDateTimeAsString(new Date()));
		model.addAttribute("reportdata", tranasctionData);

		return view;

	}

}
