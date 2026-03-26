package DataObjects;

public class BasePolicyDO implements termsAndConditionINTF{
	String leadSeq;
	String paymentFrequency;
	
	
	
	public String disclaimerMessage() {
		return "terms and condition example";
	}

	public String getLeadSeq() {
		return leadSeq;
	}

	public void setLeadSeq(String leadSeq) {
		this.leadSeq = leadSeq;
	}

	public String getPaymentFrequency() {
		return paymentFrequency;
	}

	public void setPaymentFrequency(String paymentFrequency) {
		this.paymentFrequency = paymentFrequency;
	}
	
}
