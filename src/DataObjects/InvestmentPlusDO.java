package DataObjects;

import java.util.ArrayList;
import java.util.List;

public class InvestmentPlusDO extends BasePolicyDO{
	String policySeq;
	Double premiumAmt;
	List<FundDO> FundAllocation = new ArrayList<FundDO>();
	
	public String getPolicySeq() {
		return policySeq;
	}
	public void setPolicySeq(String policySeq) {
		this.policySeq = policySeq;
	}
	public Double getPremiumAmt() {
		return premiumAmt;
	}
	public void setPremiumAmt(Double premiumAmt) {
		this.premiumAmt = premiumAmt;
	}
	public List<FundDO> getFundAllocation() {
		return FundAllocation;
	}
	public void setFundAllocation(List<FundDO> fundAllocation) {
		FundAllocation = fundAllocation;
	}


}
