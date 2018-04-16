package com.priceengine.entity;

public class Products {

	private String productCode;
	private String supplyStatus;
	private String demandStatus;
		
	public Products(String productCode, String suppluStatus, String demandStatus) {
		super();
		this.productCode = productCode;
		this.supplyStatus = suppluStatus;
		this.demandStatus = demandStatus;
	}
	
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getSupplyStatus() {
		return supplyStatus;
	}
	public void setSupplyStatus(String suppluStatus) {
		this.supplyStatus = suppluStatus;
	}
	public String getDemandStatus() {
		return demandStatus;
	}
	public void setDemandStatus(String demandStatus) {
		this.demandStatus = demandStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productCode == null) ? 0 : productCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Products other = (Products) obj;
		if (productCode == null) {
			if (other.productCode != null)
				return false;
		} else if (!productCode.equals(other.productCode))
			return false;
		return true;
	}
	
	
	
}
