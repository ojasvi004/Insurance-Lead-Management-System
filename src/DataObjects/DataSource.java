package DataObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataSource 
{
	public static LinkedHashMap<String, List<BasePolicyDO>> QuotationGenerated=new LinkedHashMap<String, List<BasePolicyDO>>();

	public static HashMap<String, LeadDO> partyData = new HashMap<String, LeadDO>(); 
	{
//		partyData.put("1","Yash");
//		partyData.put("2","Ojasvi");
//		partyData.put("3","Light");
	}
	
	public static Map<String, LinkedHashMap<String, ArrayList<String>>> FNAQuestions = new LinkedHashMap<>();	
	static {
		ArrayList<String> yesNo = new ArrayList<>();
		yesNo.add("YES");
		yesNo.add("NO");

		LinkedHashMap<String, ArrayList<String>> investment = new LinkedHashMap<>();
		investment.put("Are you looking for the security during retirement ?", yesNo);
		investment.put("Are you saving for specific goal like children education or marriage or any other specific goal like buying car, world tour?", yesNo);
		investment.put("Do you want to make lumpsum investment ?", yesNo);
		FNAQuestions.put("INVESTMENT", investment);
		LinkedHashMap<String, ArrayList<String>> term = new LinkedHashMap<>();
		term.put("Do you want to protect your family from financial impact of sudden event of death , disability , critical illness etc ?", yesNo);
		FNAQuestions.put("TERMINSURANCE", term);
		LinkedHashMap<String, ArrayList<String>> health = new LinkedHashMap<>();
		health.put("Do you want to protect your family from financial impact of sudden event of medical issues, healthcare expenses , critical illness etc ?", yesNo);
		FNAQuestions.put("HEALTHINSURANCE", health);
		LinkedHashMap<String, ArrayList<String>> personalInfo = new LinkedHashMap<>();
		
		ArrayList<String> incomeOptions = new ArrayList<>();
		incomeOptions.add("Below 5 lacs");
		incomeOptions.add("Between 5 to 10 lacs");
		incomeOptions.add("Between 10 to 15 lacs");
		incomeOptions.add("Above 15 lacs");
		personalInfo.put("Whats your average monthly income?", incomeOptions);

		ArrayList<String> retireOptions = new ArrayList<>();
		retireOptions.add("45");
		retireOptions.add("50");
		retireOptions.add("55");
		retireOptions.add("60");
		retireOptions.add("60+");
		personalInfo.put("when are you planning to retire ?", retireOptions);
		FNAQuestions.put("PERSONALINFO", personalInfo);
	}
	public static LinkedHashMap<String, ArrayList<String>> policyRiders = new LinkedHashMap<>();
    static {
        ArrayList<String> termList = new ArrayList<>(java.util.Arrays.asList(
                "Critical Illness Benefit Rider",
                "Accidental Death Benefit Rider",
                "Terminal Illness Benefit Rider",
                "Permanent Disability Benefit Rider"
        ));
        policyRiders.put("TERMINSURANCE", termList);
       ArrayList<String> healthList = new ArrayList<>(java.util.Arrays.asList(
                "Critical Illness Rider",
                "OPD Rider",
                "Maternity Rider",
                "Room Rent Waiver",
                "Personal Accident Rider",
                "Global Cover Rider"
        ));
        policyRiders.put("HEALTHINSURANCE", healthList);
    }
//    static {
//        DataObjects.LeadDO dummyLead = new DataObjects.LeadDO();
//        dummyLead.setLeadSeq("1");
//        dummyLead.setFirstName("Tester");
//        dummyLead.setTitle("Mr");
//        dummyLead.setGenderCd("Male");
//
//        // 1. Give him a dummy address
//        List<DataObjects.LeadAddressDO> dummyAddresses = new ArrayList<>();
//        DataObjects.LeadAddressDO address = new DataObjects.LeadAddressDO();
//        address.setAddressType("RESIDENTIAL");
//        address.setAddressDetails("123 Code Street");
//        address.setPrimaryAddress("YES");
//        dummyAddresses.add(address);
//        dummyLead.setLeadAddressDOList(dummyAddresses);
//
//        // 2. Give him dummy FNA Answers so Option 6 works perfectly!
//        List<DataObjects.FNAQuestionResponseDO> dummyFNA = new ArrayList<>();
//        
//        DataObjects.FNAQuestionResponseDO healthFNA = new DataObjects.FNAQuestionResponseDO();
//        healthFNA.setQuestiondesc("medical issues"); // Matches your Option 6 logic
//        healthFNA.setResponseValue("YES");
//        dummyFNA.add(healthFNA);
//
//        DataObjects.FNAQuestionResponseDO termFNA = new DataObjects.FNAQuestionResponseDO();
//        termFNA.setQuestiondesc("death"); // Matches your Option 6 logic
//        termFNA.setResponseValue("YES");
//        dummyFNA.add(termFNA);
//        
//        DataObjects.FNAQuestionResponseDO investFNA = new DataObjects.FNAQuestionResponseDO();
//        investFNA.setQuestiondesc("retirement"); // Matches your Option 6 logic
//        investFNA.setResponseValue("YES");
//        dummyFNA.add(investFNA);
//
//        dummyLead.setFNAQuestionResponseDOList(dummyFNA);
//
//        // 3. Save him to the database!
//        partyData.put("1", dummyLead);
//    }
}
