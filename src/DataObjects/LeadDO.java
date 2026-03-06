package DataObjects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LeadDO {
	
	String leadSeq;
	String title;
	String firstName;
	String lastName;
	Date birthDate;
	int age;
	String genderCd;
	List<LeadContactDO> leadContactDOList = new ArrayList<LeadContactDO>();
	List<FNAQuestionResponseDO> FNAQuestionResponseDOList = new ArrayList<FNAQuestionResponseDO>();
	List<LeadAddressDO> leadAddressDOList = new ArrayList<LeadAddressDO>();

	public String getLeadSeq() {
		return leadSeq;
	}
	public void setLeadSeq(String leadSeq) {
		this.leadSeq = leadSeq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGenderCd() {
		return genderCd;
	}
	public void setGenderCd(String genderCd) {
		this.genderCd = genderCd;
	}
	public List<LeadContactDO> getLeadContactDOList() {
		return leadContactDOList;
	}
	public void setLeadContactDOList(List<LeadContactDO> leadContactDOList) {
		this.leadContactDOList = leadContactDOList;
	}
	public List<FNAQuestionResponseDO> getFNAQuestionResponseDOList() {
		return FNAQuestionResponseDOList;
	}
	public void setFNAQuestionResponseDOList(List<FNAQuestionResponseDO> fNAQuestionResponseDOList) {
		FNAQuestionResponseDOList = fNAQuestionResponseDOList;
	}
	public List<LeadAddressDO> getLeadAddressDOList() {
		return leadAddressDOList;
	}
	public void setLeadAddressDOList(List<LeadAddressDO> leadAddressDOList) {
		this.leadAddressDOList = leadAddressDOList;
	}
}
