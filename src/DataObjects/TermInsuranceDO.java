package DataObjects;

import java.util.ArrayList;
import java.util.List;

public class TermInsuranceDO extends BasePolicyDO{
	String policySeq;
	Double premiumAmt;
	Double sumAssured;
	List<RiderDO> riderDOList = new ArrayList<RiderDO>();
	
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
	public Double getSumAssured() {
		return sumAssured;
	}
	public void setSumAssured(Double sumAssured) {
		this.sumAssured = sumAssured;
	}
	public List<RiderDO> getRiderDOList() {
		return riderDOList;
	}
	public void setRiderDOList(List<RiderDO> riderDOList) {
		this.riderDOList = riderDOList;
	}
	
}
