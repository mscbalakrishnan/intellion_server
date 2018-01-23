/**
 * 
 */
package com.intellion.cms.domain.dto;

/**
 * @author Kumaraguru_Narayanan
 *
 */
public class ReportDto {
	private String data = null;

	public ReportDto(String data) {
		super();
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
}
