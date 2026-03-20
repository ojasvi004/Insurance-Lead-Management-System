package DataObjects;

public class LeadAddressDO {
    String leadAddressSeq;   
    String leadSeq;
    String addressType;
    String countryCd;
    String stateCd;
    String addressDetails;
    String pinCode;
    String primaryAddress;
    
	public String getLeadAddressSeq() {
		return leadAddressSeq;
	}
	public void setLeadAddressSeq(String leadAddressSeq) {
		this.leadAddressSeq = leadAddressSeq;
	}
	public String getLeadSeq() {
		return leadSeq;
	}
	public void setLeadSeq(String leadSeq) {
		this.leadSeq = leadSeq;
	}
	public String getAddressType() {
		return addressType;
	}
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	public String getCountryCd() {
		return countryCd;
	}
	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
	}
	public String getStateCd() {
		return stateCd;
	}
	public void setStateCd(String stateCd) {
		this.stateCd = stateCd;
	}
	public String getAddressDetails() {
		return addressDetails;
	}
	public void setAddressDetails(String addressDetails) {
		this.addressDetails = addressDetails;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getPrimaryAddress() {
		return primaryAddress;
	}
	public void setPrimaryAddress(String primaryAddress) {
		this.primaryAddress = primaryAddress;
	}
	@Override
    public String toString() {
        return "Type: " + addressType + "\n" +
               "Details: " + addressDetails + "\n" +
               "State: " + stateCd + "\n" +
               "Country: " + countryCd + "\n" +
               "Pin Code: " + pinCode + "\n" +
               "Primary: " + primaryAddress + "\n";
    }
}
