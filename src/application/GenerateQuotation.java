package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GenerateQuotation {
	public static void generateNewQuotation(Scanner sc) {
		   System.out.println("Enter the details of the lead ID you want to edit");
	        String leadSeq = sc.nextLine();
	        if (!DataObjects.DataSource.partyData.containsKey(leadSeq)) {
	            System.out.println("No lead found with this ID. Please enter correct ID");
	            return;
	        }
		DataObjects.LeadDO lead = DataObjects.DataSource.partyData.get(leadSeq);
		List<DataObjects.FNAQuestionResponseDO> fnaList = lead.getFNAQuestionResponseDOList();
		
		System.out.println("recommend products based on the FNA:");
		List<String> suggestedProducts = new ArrayList<>();
		for(DataObjects.FNAQuestionResponseDO fna: fnaList) {
			String question = fna.getQuestiondesc();
			String answer = fna.getResponseValue();
			if(answer.equals("YES")) {
				if (question.contains("medical issues") || question.contains("healthcare expenses")) {
                    if (!suggestedProducts.contains("HEALTHINSURANCE")) {
                        suggestedProducts.add("HEALTHINSURANCE");
                    }
                }
				else if (question.contains("death") || question.contains("disability")) {
                    if (!suggestedProducts.contains("TERMINSURANCE")) {
                        suggestedProducts.add("TERMINSURANCE");
                    }
                } 
                else if (question.contains("retirement") || question.contains("lumpsum") || question.contains("specific goal")) {
                    if (!suggestedProducts.contains("INVESTMENT")) {
                        suggestedProducts.add("INVESTMENT");
                    }
                }
			}
		}
		if (suggestedProducts.isEmpty()) {
            System.out.println("no specific products are recommended since all answers were no");
            return;
        }
		System.out.println("Suggestions based on lead's needs:");
		for (int i = 0; i < suggestedProducts.size(); i++) {
            System.out.println((i + 1) + ". " + suggestedProducts.get(i));
        }
		}
}
