package DataObjects;

public class RiderDO {
	String riderSeq;
	String riderName;
	Double coverAmount;
	String riderDescription;
	
	public String getRiderSeq() {
		return riderSeq;
	}
	public void setRiderSeq(String riderSeq) {
		this.riderSeq = riderSeq;
	}
	public String getRiderName() {
		return riderName;
	}
	public void setRiderName(String riderName) {
		this.riderName = riderName;
	}
	public Double getCoverAmount() {
		return coverAmount;
	}
	public void setCoverAmount(Double coverAmount) {
		this.coverAmount = coverAmount;
	}
	public String getRiderDescription() {
		return riderDescription;
	}
	public void setRiderDescription(String riderDescription) {
		this.riderDescription = riderDescription;
	}
}
