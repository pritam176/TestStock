/**
 * 
 */
package com.mmt.controller;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itextpdf.text.DocumentException;
import com.mmt.command.SearchStockCommandForm;
import com.mmt.util.GeneratePdfReport;

/**
 * @author pkumar
 *
 */
@Controller
@RequestMapping("report")
public class PDFController {

	@Autowired
	GeneratePdfReport generatePdfReport;

	@GetMapping("invoice.pdf")
	public void getPDFForInvoice(@RequestParam(value = "startdate", required = false) String startdate,
			@RequestParam(value = "enddate", required = false) String enddate, HttpServletResponse response)
			throws IOException, DocumentException {

		try {
			byte[] pdf = generatePdfReport.invoiceReport(startdate, enddate);

			response.reset();
			response.setContentType("application/pdf");
			response.getOutputStream().write(pdf);
			response.getOutputStream().flush();

		} catch (ParseException e) {

		}
	}

	@GetMapping("stock.pdf")
	public void getPDFForStock(@RequestParam(value = "itemID", required = false) String itemID,
			@RequestParam(value = "startdate", required = false) String startdate,
			@RequestParam(value = "enddate", required = false) String enddate, HttpServletResponse response)
			throws IOException, DocumentException, ParseException {

		SearchStockCommandForm searchStockCommandForm = new SearchStockCommandForm();

		searchStockCommandForm.setItemID(itemID);
		searchStockCommandForm.setStartDate(startdate);
		searchStockCommandForm.setEndDate(enddate);
		
		

		byte[] pdf = generatePdfReport.stockPDFReport(searchStockCommandForm);

		response.reset();
		response.setContentType("application/pdf");
		response.getOutputStream().write(pdf);
		response.getOutputStream().flush();
	}
	@GetMapping("bill.pdf")
	public void getBill(@RequestParam(value = "billId", required = false) String billId,HttpServletResponse response) throws IOException{
		byte[] pdf = generatePdfReport.getBillPage(billId);

		response.reset();
		response.setContentType("application/pdf");
		response.getOutputStream().write(pdf);
		response.getOutputStream().flush();
	}

}
