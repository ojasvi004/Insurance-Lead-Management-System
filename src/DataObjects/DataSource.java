package DataObjects;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class DataSource 
{
	public static LinkedHashMap<String, List<BasePolicyDO>> QuotationGenerated=new LinkedHashMap<String, List<BasePolicyDO>>();

	public static HashMap<String, String> partyData=new HashMap();
	
	static 
	{
		partyData.put("1","Yash");
		partyData.put("2","Ojasvi");
		partyData.put("3","Light");
	}
}
