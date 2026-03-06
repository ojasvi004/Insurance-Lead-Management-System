package DataObjects;

public class FundDO {
	String fundName;
	Double allocationPercentage;
	
	public String getFundName() {
		return fundName;
	}
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	public Double getAllocationPercentage() {
		return allocationPercentage;
	}
	public void setAllocationPercentage(Double allocationPercentage) {
		this.allocationPercentage = allocationPercentage;
	}
}
