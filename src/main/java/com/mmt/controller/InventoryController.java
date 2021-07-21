/**
 * 
 */
package com.mmt.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mmt.Entity.ProductEntity;
import com.mmt.command.SearchStockCommandForm;
import com.mmt.command.SerachInvoiceCommand;
import com.mmt.dto.ProductItemDTO;
import com.mmt.dto.StockInventoryDTO;
import com.mmt.dto.TransactionDTO;
import com.mmt.service.InventoryService;
import com.mmt.service.ProductService;
import com.mmt.service.TransactionService;
import com.mmt.util.DateUtil;

/**
 * @author pkumar
 *
 */
@Controller
@RequestMapping("inventory")
public class InventoryController {

	private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);

	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private ProductService productService;

	@Autowired
	private TransactionService transactionService;

	@GetMapping(value = "/stock.html")
	public String getStockPage(Model model, HttpServletResponse resp) {
		SearchStockCommandForm searchStockCommandForm = new SearchStockCommandForm();
		model.addAttribute("searchStockCommandForm", searchStockCommandForm);
		logger.debug("getStockPage");

		List<ProductEntity> productList = productService.getAllProductEntity();

		Map<Long, String> result1 = new HashMap<>();
		Iterator<ProductEntity> It = productList.iterator();
		while (It.hasNext()) {
			ProductEntity pe = It.next();
			result1.put(pe.getProduct_id(), pe.getProductname());
		}

		model.addAttribute("productList", result1);
		long cuurentMillsec = System.currentTimeMillis();
		java.util.Date startdateUtil = new java.util.Date(cuurentMillsec);

		String selectedDate = DateUtil.getDateAsString(startdateUtil) + "-" + DateUtil.getDateAsString(startdateUtil);
		model.addAttribute("daterange", selectedDate);

		return "stock.tiles";

	}

	@PostMapping(value = "/stock.html")
	public String sellitemPost(
			@ModelAttribute("searchInvoiceCommandForm") SearchStockCommandForm searchStockCommandForm,
			HttpServletResponse resp, Model model) throws ParseException {
		logger.debug("sellitem Post no of item ID- " + searchStockCommandForm.getItemID());
		model.addAttribute("searchInvoiceCommandForm", searchStockCommandForm);

		List<ProductEntity> productList = productService.getAllProductEntity();

		Map<Long, String> result1 = new HashMap<>();
		Iterator<ProductEntity> It = productList.iterator();
		while (It.hasNext()) {
			ProductEntity pe = It.next();
			result1.put(pe.getProduct_id(), pe.getProductname());
		}

		List<ProductItemDTO> productItemList = productService
				.getOneProductEntity(searchStockCommandForm.getProductID());

		Map<Long, String> itemListMap = new HashMap<>();
		Iterator<ProductItemDTO> It1 = productItemList.iterator();
		while (It1.hasNext()) {
			ProductItemDTO pe = It1.next();
			itemListMap.put(pe.getId(), pe.getItem_name());
		}

		model.addAttribute("productItemList", itemListMap);
		model.addAttribute("productList", result1);

		List<StockInventoryDTO> dtoList = inventoryService.getItemHistorydata(searchStockCommandForm);

		model.addAttribute("searchStockCommandForm", searchStockCommandForm);
		model.addAttribute("invoicedtoList", dtoList);
		long cuurentMillsec = System.currentTimeMillis();
		java.util.Date startdateUtil = new java.util.Date(cuurentMillsec);

		String selectedDate = DateUtil.getDateAsString(startdateUtil) + "-" + DateUtil.getDateAsString(startdateUtil);
		model.addAttribute("daterange", selectedDate);
		
		if("".equals(searchStockCommandForm.getStartDate())){
			searchStockCommandForm.setStartDate(DateUtil.getDateAsString(startdateUtil));
			searchStockCommandForm.setEndDate(DateUtil.getDateAsString(startdateUtil));
		}

		if (dtoList.size() > 0) {
			model.addAttribute("download",
					"/report/stock.pdf?startdate=" + searchStockCommandForm.getStartDate() + "&enddate="
							+ searchStockCommandForm.getEndDate() + "&itemID=" + searchStockCommandForm.getItemID());
		}

		return "stock.tiles";

	}

	@GetMapping(value = "/invoice.html")
	public String getinvoicePage(Model model, HttpServletResponse resp) throws ParseException {

		logger.debug("invoice");
		SerachInvoiceCommand serachInvoiceCommand = new SerachInvoiceCommand();

		model.addAttribute("serachInvoiceCommand", serachInvoiceCommand);

		List<TransactionDTO> transactionList = transactionService.getAllTransactionData("", "");
		model.addAttribute("transactionList", transactionList);

		long cuurentMillsec = System.currentTimeMillis();

		java.util.Date startdateUtil = new java.util.Date(cuurentMillsec);

		String selectedDate = DateUtil.getDateAsString(startdateUtil) + "-" + DateUtil.getDateAsString(startdateUtil);

		if ("".equals(serachInvoiceCommand.getStartDate())) {
			selectedDate = serachInvoiceCommand.getStartDate() + "-" + serachInvoiceCommand.getEndDate();
		}

		model.addAttribute("daterange", selectedDate);

		if (transactionList.size() > 0) {
			model.addAttribute("download", "/report/invoice.pdf?startdate=" + DateUtil.getDateAsString(startdateUtil)
					+ "&enddate=" + DateUtil.getDateAsString(startdateUtil));
		}
		return "invoice.tiles";

	}

	@PostMapping(value = "/invoiceSerach.html")
	public String getinvoiceSerachPage(Model model, HttpServletResponse resp,
			@ModelAttribute("serachInvoiceCommand") SerachInvoiceCommand serachInvoiceCommand) throws ParseException {

		logger.debug("invoiceSerach");

		model.addAttribute("serachInvoiceCommand", serachInvoiceCommand);

		List<TransactionDTO> transactionList = transactionService
				.getAllTransactionData(serachInvoiceCommand.getStartDate(), serachInvoiceCommand.getEndDate());
		model.addAttribute("transactionList", transactionList);
		String selectedDate = serachInvoiceCommand.getStartDate() + "-" + serachInvoiceCommand.getEndDate();
		model.addAttribute("daterange", selectedDate);

		if (transactionList.size() > 0) {
			model.addAttribute("download", "/report/invoice.pdf?startdate=" + serachInvoiceCommand.getStartDate()
					+ "&enddate=" + serachInvoiceCommand.getEndDate());
		}

		return "invoice.tiles";

	}

	@PostMapping(value = "/invoiceSerachone.html")
	public String getinvoiceSerachonePage(Model model, HttpServletResponse resp,
			@ModelAttribute("serachInvoiceCommand") SerachInvoiceCommand serachInvoiceCommand) throws ParseException {

		logger.debug("invoiceSerachone");

		model.addAttribute("serachInvoiceCommand", serachInvoiceCommand);
		String view = "redirect:/mmt/thankyou.html?msg=Incorect invoice id Provided";

		if (!"".equals(serachInvoiceCommand.getInvoiceNo()) && !(serachInvoiceCommand.getInvoiceNo().length() < 3))

			view = "redirect:/sell/getReportItemData.html?key=" + serachInvoiceCommand.getInvoiceNo().substring(3);
		return view;

	}

}
