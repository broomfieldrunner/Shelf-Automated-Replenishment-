package com.smartbuy.ocb.dto;

public class SkuDTO {
	
	private String skuNumber;
	private String skuName;
	private int ordReq;
	private String poCrt;
	private int vendMin;
	
	public SkuDTO() 
	{
		
	}

	public String getSkuNumber() 
	{
		return skuNumber;
	}


	public void setSkuNumber(String skuNumber) 
	{
		this.skuNumber = skuNumber;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) 
	{
		this.skuName = skuName;
	}
	
	public int getordReq() 
	{
		return ordReq;
	}

	public void setOrdReq(int ordReq) 
	{
		this.ordReq = ordReq;
	}

	public String getPoCrt() {
		return poCrt;
	}

	public void setPoCrt(String poCrt) 
	{
		this.poCrt = poCrt;
	}

	public int getVendMin() 
	{
		return vendMin;
	}

	public void setVendMin(int vendMin) 
	{
		this.vendMin = vendMin;
	}

}
