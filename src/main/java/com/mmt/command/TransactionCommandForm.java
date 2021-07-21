/**
 * 
 */
package com.mmt.command;

import java.util.List;

import com.mmt.dto.ItemDTO;

/**
 * @author pkumar
 *
 */
public class TransactionCommandForm {

	private List<ItemDTO> itemDto;
	private String product;
	private String paymentMode;
	private String checkNo;
	private String customerId;
	private String customerName;

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public List<ItemDTO> getItemDto() {
		return itemDto;
	}

	public void setItemDto(List<ItemDTO> itemDto) {
		this.itemDto = itemDto;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

}
