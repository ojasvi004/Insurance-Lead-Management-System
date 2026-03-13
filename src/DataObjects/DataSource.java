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
}
