package com.mmt.util;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mmt.command.SearchStockCommandForm;
import com.mmt.config.PropertyConfig;
import com.mmt.dto.ProductItemDTO;
import com.mmt.dto.StockInventoryDTO;
import com.mmt.dto.TransactionDTO;
import com.mmt.dto.TransactionItemDTO;
import com.mmt.service.InventoryService;
import com.mmt.service.ProductService;
import com.mmt.service.TransactionService;

@Service
public class GeneratePdfReport {

	private static final Logger logger = LoggerFactory.getLogger(GeneratePdfReport.class);

	private static Font TIME_ROMAN = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD, new BaseColor(51, 51, 51));
	private static Font TIME_ROMAN_SMALL = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD,
			new BaseColor(51, 51, 51));
	private static Font TIME_ROMAN_EXTRA_SMALL = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD,
			new BaseColor(51, 51, 51));

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private ProductService productService;

	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private PropertyConfig propertyConfig;

	public byte[] stockPDFReport(SearchStockCommandForm searchStockCommandForm)
			throws DocumentException, ParseException {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		PdfWriter.getInstance(document, out);
		document.open();

		addMetaData(document, "STOCK DETAIL", "");

		List<StockInventoryDTO> dtoList = inventoryService.getItemHistorydata(searchStockCommandForm);

		ProductItemDTO productItem = null;
		if (!StringUtils.isEmpty(searchStockCommandForm.getItemID())) {
			productItem = productService.getOneItemdetail(searchStockCommandForm.getItemID());
		}

		PdfPTable itemTabel = new PdfPTable(2);
		itemTabel.setWidthPercentage(50);
		itemTabel.setWidths(new int[] { 2, 2 });

		itemTabel.setHorizontalAlignment(Element.ALIGN_LEFT);

		PdfPCell cell1;

		cell1 = new PdfPCell(new Phrase("Product Name :-"));
		cell1.setVerticalAlignment(Element.ALIGN_LEFT);
		cell1.setBorder(Rectangle.NO_BORDER);
		itemTabel.addCell(cell1);

		cell1 = new PdfPCell(new Phrase(dtoList.get(0).getProductName()));
		cell1.setPaddingLeft(5);
		cell1.setVerticalAlignment(Element.ALIGN_LEFT);
		cell1.setBorder(Rectangle.NO_BORDER);
		itemTabel.addCell(cell1);

		cell1 = new PdfPCell(new Phrase("Item name :-"));
		cell1.setVerticalAlignment(Element.ALIGN_LEFT);
		cell1.setBorder(Rectangle.NO_BORDER);
		itemTabel.addCell(cell1);

		cell1 = new PdfPCell(new Phrase(dtoList.get(0).getItemName()));
		cell1.setPaddingLeft(5);
		cell1.setVerticalAlignment(Element.ALIGN_LEFT);
		cell1.setBorder(Rectangle.NO_BORDER);
		itemTabel.addCell(cell1);

		cell1 = new PdfPCell(new Phrase("Avalaible Unit :-"));
		cell1.setPaddingLeft(5);
		cell1.setVerticalAlignment(Element.ALIGN_LEFT);
		cell1.setBorder(Rectangle.NO_BORDER);
		itemTabel.addCell(cell1);

		cell1 = new PdfPCell(new Phrase(productItem.getStock_avalble() + ""));
		cell1.setPaddingLeft(5);
		cell1.setVerticalAlignment(Element.ALIGN_LEFT);
		cell1.setBorder(Rectangle.NO_BORDER);
		itemTabel.addCell(cell1);

		cell1 = new PdfPCell(new Phrase("Price of Unit :-"));
		cell1.setPaddingLeft(5);
		cell1.setVerticalAlignment(Element.ALIGN_LEFT);
		cell1.setBorder(Rectangle.NO_BORDER);
		itemTabel.addCell(cell1);

		cell1 = new PdfPCell(new Phrase(productItem.getUnit_price() + ""));
		cell1.setPaddingLeft(5);
		cell1.setVerticalAlignment(Element.ALIGN_LEFT);
		cell1.setBorder(Rectangle.NO_BORDER);
		itemTabel.addCell(cell1);

		document.add(itemTabel);

		Paragraph bottom = new Paragraph();
		creteEmptyLine(bottom, 3);
		document.add(bottom);

		try {

			PdfPTable table = new PdfPTable(13);
			table.setWidthPercentage(100);
			table.setWidths(new int[] { 1, 4, 4, 3, 3, 3, 3, 3, 3, 3, 4, 3, 3 });

			Font headFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, BaseColor.BLACK);

			PdfPCell hcell;
			hcell = new PdfPCell(new Phrase("Id", headFont));

			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Item Name", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Product Name", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("No of Item", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Unit Cost", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Discount offer", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Total Discount offer", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Customer Name", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Supplier Name", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Transaction Type", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Date of Transaction", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Total Sell Amount", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Total Amount", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			int count = 1;
			Font cellFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, BaseColor.BLACK);
			for (StockInventoryDTO transactiondto : dtoList) {

				PdfPCell cell;

				cell = new PdfPCell(new Phrase("" + count, cellFont));
				cell.setVerticalAlignment(Element.ALIGN_LEFT);
				// cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(transactiondto.getItemName()), cellFont));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_LEFT);
				// cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(transactiondto.getProductName()), cellFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(transactiondto.getNoofUnit()), cellFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(transactiondto.getUnitPerchaseCost()), cellFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(transactiondto.getDiscountOfer()), cellFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(transactiondto.getTotalDiscountOffer()), cellFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(transactiondto.getCustomerName()), cellFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(transactiondto.getSupplierName()), cellFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(transactiondto.getUpdateReason()), cellFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(transactiondto.getAddedDate()), cellFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(transactiondto.getTotalSellCost(), cellFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(transactiondto.getTotalPurchaseCost(), cellFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);

				count++;
			}

			document.add(table);

			Paragraph bottom2 = new Paragraph();
			creteEmptyLine(bottom, 3);
			document.add(bottom2);

			Paragraph para2 = new Paragraph("Report data Between  " + searchStockCommandForm.getStartDate() + "-to-"
					+ searchStockCommandForm.getEndDate(), TIME_ROMAN_SMALL);

			para2.setAlignment(Element.ALIGN_RIGHT);
			document.add(para2);

			document.close();

		} catch (DocumentException ex) {

			logger.error(ex.getMessage());
		}

		return out.toByteArray();

	}

	private void addMetaData(Document document, String reportName, String subtitle) throws DocumentException {
		document.addTitle(reportName);
		document.addSubject(reportName);
		document.addAuthor("Pritam");
		document.addCreator("Pritam");

		Paragraph preface = new Paragraph();
		creteEmptyLine(preface, 1);

		Paragraph title = new Paragraph(reportName, TIME_ROMAN);

		title.setAlignment(Element.ALIGN_CENTER);
		document.add(title);

		Paragraph subtutle = new Paragraph(subtitle, TIME_ROMAN_EXTRA_SMALL);
		subtutle.setAlignment(Element.ALIGN_CENTER);
		document.add(subtutle);

		Paragraph reportDtae = new Paragraph("Date -" + DateUtil.getDateTimeAsString(new Date()), TIME_ROMAN_SMALL);
		reportDtae.setAlignment(Element.ALIGN_CENTER);
		document.add(reportDtae);

		creteEmptyLine(preface, 1);

		Paragraph name = new Paragraph(propertyConfig.getTradersName(), TIME_ROMAN_EXTRA_SMALL);
		name.setAlignment(Element.ALIGN_RIGHT);
		Paragraph gstn = new Paragraph("Gstn No-" + propertyConfig.getGstnNo(), TIME_ROMAN_EXTRA_SMALL);
		gstn.setAlignment(Element.ALIGN_RIGHT);
		Paragraph add = new Paragraph(propertyConfig.getAddress(), TIME_ROMAN_EXTRA_SMALL);
		add.setAlignment(Element.ALIGN_RIGHT);

		Paragraph add2 = new Paragraph(propertyConfig.getDist() + "," + propertyConfig.getState(),
				TIME_ROMAN_EXTRA_SMALL);
		add2.setAlignment(Element.ALIGN_RIGHT);
		Paragraph mob = new Paragraph(propertyConfig.getMobileno(), TIME_ROMAN_EXTRA_SMALL);
		mob.setAlignment(Element.ALIGN_RIGHT);

		document.add(name);
		document.add(gstn);
		document.add(add);
		document.add(add2);
		document.add(mob);

		Paragraph paragraph = new Paragraph();
		creteEmptyLine(paragraph, 2);
		document.add(paragraph);

	}

	private void addMetaDataBill(Document document, String reportName, String subtitle) throws DocumentException {
		document.addTitle(reportName);
		document.addSubject(reportName);
		document.addAuthor("Pritam");
		document.addCreator("Pritam");

	}

	private static void creteEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	public byte[] invoiceReport(String startDate, String endDate) throws ParseException, DocumentException {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		PdfWriter.getInstance(document, out);
		document.open();

		addMetaData(document, "INVOICE DETAIL", "");

		List<TransactionDTO> transactionList = transactionService.getAllTransactionData(startDate, endDate);

		try {

			PdfPTable table = new PdfPTable(9);
			table.setWidthPercentage(100);
			table.setWidths(new int[] { 1, 2, 3, 1, 1, 2, 3, 2, 2 });

			Font headFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, BaseColor.BLACK);

			PdfPCell hcell;
			hcell = new PdfPCell(new Phrase("Id", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Invoice No", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Customer Name", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("No of Item", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Discount offer", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Mode Of Payment", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Date of Purchase", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Total Payble Amount", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Total Amount", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			int count = 1;
			Font cellFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, BaseColor.BLACK);

			for (TransactionDTO transactiondto : transactionList) {

				PdfPCell cell;

				cell = new PdfPCell(new Phrase("" + count));
				cell.setVerticalAlignment(Element.ALIGN_LEFT);
				// cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf("MMT" + transactiondto.getTransactionid()), cellFont));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_LEFT);
				// cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(transactiondto.getCustomerName()), cellFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(transactiondto.getNoOfItem()), cellFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(transactiondto.getTotaldiscount()), cellFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(transactiondto.getPaymentMode()), cellFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(transactiondto.getTransactionDate()), cellFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(transactiondto.getPaybleamount()), cellFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(transactiondto.getTotalamount()), cellFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);

				count++;
			}

			document.add(table);

			Paragraph bottom = new Paragraph();
			creteEmptyLine(bottom, 3);
			document.add(bottom);

			Paragraph para2 = new Paragraph("Report data Between  " + startDate + "-to-" + endDate, TIME_ROMAN_SMALL);

			para2.setAlignment(Element.ALIGN_RIGHT);
			document.add(para2);

			document.close();

		} catch (DocumentException ex) {

			logger.error(ex.getMessage());
		}

		return out.toByteArray();
	}

	public byte[] getBillPage(String billId) {

		 
		Document document = new Document();

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			PdfWriter writer = PdfWriter.getInstance(document, out);
			//document.setMargins(2, 2, 2, 2);
			document.open();
			PdfContentByte canvas = writer.getDirectContent();
			Rectangle rect = new Rectangle(26, 16, 559, 906);
			rect.setBorder(Rectangle.BOX);
			rect.setBorderWidth(1);
			canvas.rectangle(rect);
			addMetaDataBill(document, "INVOICE ", propertyConfig.getProduct());

			Paragraph preface = new Paragraph();
			creteEmptyLine(preface, 1);

			Paragraph title = new Paragraph("INVOICE", TIME_ROMAN);

			title.setAlignment(Element.ALIGN_CENTER);
			document.add(title);

			Paragraph subtutle = new Paragraph(propertyConfig.getProduct(), TIME_ROMAN_EXTRA_SMALL);
			subtutle.setAlignment(Element.ALIGN_CENTER);
			document.add(subtutle);

			Paragraph reportDtae = new Paragraph("Date -" + DateUtil.getDateTimeAsString(new Date()), TIME_ROMAN_SMALL);
			reportDtae.setAlignment(Element.ALIGN_CENTER);
			document.add(reportDtae);

			creteEmptyLine(preface, 1);

			Paragraph name = new Paragraph(propertyConfig.getTradersName(), TIME_ROMAN_EXTRA_SMALL);
			name.setAlignment(Element.ALIGN_LEFT);
			Paragraph gstn = new Paragraph("Gstn No-" + propertyConfig.getGstnNo(), TIME_ROMAN_EXTRA_SMALL);
			gstn.setAlignment(Element.ALIGN_LEFT);
			Paragraph add = new Paragraph(propertyConfig.getAddress(), TIME_ROMAN_EXTRA_SMALL);
			add.setAlignment(Element.ALIGN_LEFT);

			Paragraph add2 = new Paragraph(propertyConfig.getDist() + "," + propertyConfig.getState(),
					TIME_ROMAN_EXTRA_SMALL);
			add2.setAlignment(Element.ALIGN_LEFT);
			Paragraph mob = new Paragraph(propertyConfig.getMobileno(), TIME_ROMAN_EXTRA_SMALL);
			mob.setAlignment(Element.ALIGN_LEFT);

			document.add(name);
			document.add(gstn);
			document.add(add);
			document.add(add2);
			document.add(mob);

			Paragraph paragraph = new Paragraph();
			creteEmptyLine(paragraph, 2);

			Rectangle one = new Rectangle(1300, 800);
			document.setPageSize(one);
			document.setMargins(2, 2, 2, 2);
			document.add(paragraph);

			TransactionDTO tranasctionData = transactionService.getTransactionData(billId);

			if (!"".equals(tranasctionData.getTransactionid())) {

				Paragraph cdetail = new Paragraph("Customer Detail", TIME_ROMAN_SMALL);
				cdetail.setAlignment(Element.ALIGN_LEFT);
				Paragraph cname = new Paragraph("Customer Name :-" + tranasctionData.getCustomerName(),
						TIME_ROMAN_EXTRA_SMALL);
				cname.setAlignment(Element.ALIGN_LEFT);
				Paragraph cadress = new Paragraph("Delevery Adress :-" + tranasctionData.getAddress(),
						TIME_ROMAN_EXTRA_SMALL);
				cadress.setAlignment(Element.ALIGN_LEFT);

				Paragraph cmobileno = new Paragraph("Customer Mobile No :-" + tranasctionData.getMobileNo(),
						TIME_ROMAN_EXTRA_SMALL);
				cmobileno.setAlignment(Element.ALIGN_LEFT);

				// document.add(cdetail);
				// document.add(cname);
				// document.add(cadress);
				// document.add(cmobileno);

				PdfPTable grsootable = new PdfPTable(4);
				grsootable.setWidthPercentage(100);
				// grsootable.setWidths(new int[] { 1, 1 });

				Font headFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, BaseColor.BLACK);

				PdfPCell hcell;

				// 1st row
				BaseColor myColor = WebColors.getRGBColor("#eee");
				hcell = new PdfPCell(new Phrase("CUSTOMER DETAIL", headFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBorder(Rectangle.NO_BORDER);
				hcell.setFixedHeight(25);
				grsootable.addCell(hcell);

				hcell = new PdfPCell(new Phrase("", headFont));
				hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hcell.setBorder(Rectangle.NO_BORDER);
				hcell.setFixedHeight(25);
				grsootable.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Invoice #", headFont));
				hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hcell.setBackgroundColor(myColor);

				hcell.setFixedHeight(25);

				grsootable.addCell(hcell);

				hcell = new PdfPCell(new Phrase("PT" + tranasctionData.getTransactionid(), headFont));
				hcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				hcell.setFixedHeight(25);

				grsootable.addCell(hcell);

				// 2nd row

				hcell = new PdfPCell(new Phrase("Customer Name :-", headFont));
				hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hcell.setBorder(Rectangle.NO_BORDER);
				hcell.setFixedHeight(25);
				grsootable.addCell(hcell);

				hcell = new PdfPCell(new Phrase(tranasctionData.getCustomerName(), headFont));
				hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hcell.setBorder(Rectangle.NO_BORDER);
				hcell.setFixedHeight(25);
				grsootable.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Transaction Date", headFont));
				hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hcell.setBackgroundColor(myColor);
				hcell.setFixedHeight(25);

				grsootable.addCell(hcell);

				hcell = new PdfPCell(new Phrase(tranasctionData.getTransactionDate(), headFont));
				hcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				hcell.setFixedHeight(25);

				grsootable.addCell(hcell);

				// 3rd row

				hcell = new PdfPCell(new Phrase("Delevery Adress :-", headFont));
				hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hcell.setBorder(Rectangle.NO_BORDER);
				hcell.setFixedHeight(25);
				grsootable.addCell(hcell);

				hcell = new PdfPCell(new Phrase(tranasctionData.getAddress(), headFont));
				hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hcell.setBorder(Rectangle.NO_BORDER);
				hcell.setFixedHeight(25);
				grsootable.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Total Amount", headFont));
				hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hcell.setBackgroundColor(myColor);
				hcell.setFixedHeight(25);
				grsootable.addCell(hcell);

				hcell = new PdfPCell(new Phrase(tranasctionData.getPaybleamount(), headFont));
				hcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				hcell.setFixedHeight(25);
				grsootable.addCell(hcell);

				// 4th row

				hcell = new PdfPCell(new Phrase("Customer Mobile No :-", headFont));
				hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hcell.setBorder(Rectangle.NO_BORDER);
				hcell.setFixedHeight(25);
				grsootable.addCell(hcell);

				hcell = new PdfPCell(new Phrase(tranasctionData.getMobileNo(), headFont));
				hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hcell.setBorder(Rectangle.NO_BORDER);
				hcell.setFixedHeight(25);
				grsootable.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Payment Mode", headFont));
				hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
				hcell.setBackgroundColor(myColor);
				hcell.setFixedHeight(25);
				grsootable.addCell(hcell);

				hcell = new PdfPCell(new Phrase(tranasctionData.getPaymentMode(), headFont));
				hcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				grsootable.addCell(hcell);

				document.add(grsootable);

				// pRODUCT tABEL

				PdfPTable producttable = new PdfPTable(14);
				producttable.setWidthPercentage(100);
				producttable.setWidths(new int[] { 1, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 });

				Paragraph bottom = new Paragraph();
				creteEmptyLine(bottom, 2);
				document.add(bottom);

				Font pFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, BaseColor.BLACK);

				getProductHeader(producttable);

				Iterator<TransactionItemDTO> it = tranasctionData.getTransactionItemDTO().iterator();
				int counter = 1;
				while (it.hasNext()) {
					TransactionItemDTO temp = it.next();

					producttable.addCell(getItemCell(counter + "", pFont));
					producttable.addCell(getItemCell(temp.getProductName(), pFont));

					producttable.addCell(getItemCell(temp.getHsnCode(), pFont));
					producttable.addCell(getItemCell(temp.getItemName(), pFont));
					producttable.addCell(getItemCell(temp.getSellUnit(), pFont));
					producttable.addCell(getItemCell(temp.getUnitcost(), pFont));
					producttable.addCell(getItemCell(temp.getDiscount(), pFont));
					producttable.addCell(getItemCell(temp.getCgstPerc(), pFont));
					producttable.addCell(getItemCell(temp.getSgstPerc(), pFont));
					producttable.addCell(getItemCell(temp.getCgstAmt(), pFont));
					producttable.addCell(getItemCell(temp.getSgstAmt(), pFont));
					producttable.addCell(getItemCell(temp.getTotalamount(), pFont));
					producttable.addCell(getItemCell(temp.getTotalGstAmt(), pFont));
					producttable.addCell(getItemCell(temp.getPaybleAmount(), pFont));

					counter++;
				}
				getProductFooter(producttable, tranasctionData);

				document.add(producttable);

			}

			Paragraph bottom = new Paragraph();
			creteEmptyLine(bottom, 3);
			document.add(bottom);

			document.close();

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out.toByteArray();

	}

	private PdfPCell getItemCell(String data, Font pFont) {
		BaseColor myColor = WebColors.getRGBColor("#eee");
		PdfPCell tempC = new PdfPCell(new Phrase(data, pFont));
		tempC.setBorderWidthBottom(1);
		tempC.setBorderColor(myColor);
		return tempC;
	}

	private void getProductFooter(PdfPTable producttable, TransactionDTO tranasctionData) {
		Font hFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, BaseColor.BLACK);
		BaseColor myColor = WebColors.getRGBColor("#eee");
		PdfPCell nullcell = new PdfPCell(new Phrase("", hFont));
		nullcell.setBorder(Rectangle.NO_BORDER);
		nullcell.setFixedHeight(20);
		producttable.addCell(nullcell);

		producttable.addCell(nullcell);
		producttable.addCell(nullcell);
		producttable.addCell(nullcell);
		producttable.addCell(nullcell);
		producttable.addCell(nullcell);
		producttable.addCell(nullcell);

		producttable.addCell(nullcell);

		PdfPCell fcell = new PdfPCell(new Phrase("Total Discount", hFont));
		fcell.setColspan(3);
		fcell.setBorderWidthBottom(1);
		fcell.setBorderColor(myColor);
		producttable.addCell(fcell);
		fcell = new PdfPCell(new Phrase(tranasctionData.getTotalamount(), hFont));
		fcell.setColspan(3);
		fcell.setBorderWidthBottom(1);
		fcell.setBorderColor(myColor);
		producttable.addCell(fcell);

		// 2nd row

		nullcell.setFixedHeight(20);
		producttable.addCell(nullcell);

		producttable.addCell(nullcell);

		producttable.addCell(nullcell);

		producttable.addCell(nullcell);
		producttable.addCell(nullcell);
		producttable.addCell(nullcell);
		producttable.addCell(nullcell);

		producttable.addCell(nullcell);

		fcell = new PdfPCell(new Phrase("Total Amount", hFont));
		fcell.setColspan(3);
		fcell.setBorderWidthBottom(1);
		fcell.setBorderColor(myColor);
		producttable.addCell(fcell);

		fcell = new PdfPCell(new Phrase(tranasctionData.getTotaldiscount(), hFont));
		fcell.setColspan(3);
		fcell.setBorderWidthBottom(1);
		fcell.setBorderColor(myColor);
		producttable.addCell(fcell);

		// 3rd row

		nullcell.setFixedHeight(20);
		nullcell.setBorder(Rectangle.BOTTOM);
		nullcell.setBorderColorBottom(myColor);
		nullcell.setBorderWidthBottom(1);
		producttable.addCell(nullcell);

		producttable.addCell(nullcell);
		producttable.addCell(nullcell);
		producttable.addCell(nullcell);
		producttable.addCell(nullcell);
		producttable.addCell(nullcell);
		producttable.addCell(nullcell);

		producttable.addCell(nullcell);

		fcell = new PdfPCell(new Phrase("Total GST Amount", hFont));
		fcell.setColspan(3);
		fcell.setBackgroundColor(myColor);
		fcell.setBorderWidthBottom(1);

		fcell.setBorderColor(myColor);
		producttable.addCell(fcell);
		fcell = new PdfPCell(new Phrase(tranasctionData.getGstAmount(), hFont));
		fcell.setColspan(3);
		fcell.setBorderWidthBottom(1);

		fcell.setBorderColor(myColor);
		producttable.addCell(fcell);

		// 4th row

		nullcell.setFixedHeight(20);

		producttable.addCell(nullcell);
		// nullcell.setBorder(Rectangle.NO_BORDER);
		producttable.addCell(nullcell);
		producttable.addCell(nullcell);
		producttable.addCell(nullcell);
		producttable.addCell(nullcell);
		producttable.addCell(nullcell);
		producttable.addCell(nullcell);

		producttable.addCell(nullcell);

		fcell = new PdfPCell(new Phrase("Total Payble Amount", hFont));
		fcell.setColspan(3);
		fcell.setBackgroundColor(myColor);
		fcell.setBorderWidthBottom(1);
		fcell.setBorderColor(myColor);
		producttable.addCell(fcell);
		fcell = new PdfPCell(new Phrase(tranasctionData.getPaybleamount(), hFont));
		fcell.setColspan(3);
		fcell.setBorderWidthBottom(1);
		fcell.setBorderColor(myColor);
		producttable.addCell(fcell);

	}

	private void getProductHeader(PdfPTable producttable) {
		Font hFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, BaseColor.BLACK);

		producttable.addCell(getItemCell("#", hFont));
		producttable.addCell(getItemCell("Product Name", hFont));
		producttable.addCell(getItemCell("HSN Code", hFont));
		producttable.addCell(getItemCell("Item Name", hFont));
		producttable.addCell(getItemCell("Unit", hFont));
		producttable.addCell(getItemCell("Price per Unit", hFont));
		producttable.addCell(getItemCell("Discount", hFont));
		producttable.addCell(getItemCell("CGST%", hFont));
		producttable.addCell(getItemCell("SGST%", hFont));
		producttable.addCell(getItemCell("CGST Amount", hFont));
		producttable.addCell(getItemCell("SGST Amount", hFont));
		producttable.addCell(getItemCell("Total", hFont));
		producttable.addCell(getItemCell("Total GST Amount", hFont));
		producttable.addCell(getItemCell("Payble Amount", hFont));

	}

}