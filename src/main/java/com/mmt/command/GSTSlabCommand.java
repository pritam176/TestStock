/**
 * 
 */
package com.mmt.command;

/**
 * @author pkumar
 *
 */
public class GSTSlabCommand {

	private String name;
	private String slabId;
	private String taxrate;
	private String startDate;
	private String endDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTaxrate() {
		return taxrate;
	}

	public void setTaxrate(String taxrate) {
		this.taxrate = taxrate;
	}

	public String getSlabId() {
		return slabId;
	}

	public void setSlabId(String slabId) {
		this.slabId = slabId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
