/**
 * 
 */
package com.mmt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @author pkumar
 *
 */
@Configuration
@PropertySource("classpath:application.properties")
public class PropertyConfig {

	@Value("${tradersName}")
	private String tradersName;
	
	@Value("${gstnNo}")
	private String gstnNo;
	
	@Value("${product}")
	private String product;
	
	@Value("${address}")
	private String address;
	
	@Value("${state}")
	private String state;
	
	@Value("${dist}")
	private String dist;
	
	@Value("${mobileno}")
	private String mobileno;
	
	@Value("${imagePath}")
	private String imagePath;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	public String getTradersName() {
		return tradersName;
	}

	public void setTradersName(String tradersName) {
		this.tradersName = tradersName;
	}

	public String getGstnNo() {
		return gstnNo;
	}

	public void setGstnNo(String gstnNo) {
		this.gstnNo = gstnNo;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDist() {
		return dist;
	}

	public void setDist(String dist) {
		this.dist = dist;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}
