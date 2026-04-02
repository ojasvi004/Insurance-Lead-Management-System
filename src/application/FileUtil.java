package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {

	public static void saveQuotationToFile(DataObjects.LeadDO lead, DataObjects.BasePolicyDO policy) {
		String folderName = "Quotations";
		File folder = new File(folderName);
		if (!folder.exists()) {
			folder.mkdir();
		}
		String policyId = "";
		String policyType = "";
		double premium = 0.0;
		String extraDetails = "";
		if(policy instanceof DataObjects.TermInsuranceDO) {
			DataObjects.TermInsuranceDO term = (DataObjects.TermInsuranceDO) policy;
			policyId = term.getPolicySeq();
			policyType = "Term Insurance";
			premium = term.getPremiumAmt();
			extraDetails = "sum assured: " + term.getSumAssured()+'\n';
			if(!term.getRiderDOList().isEmpty()) {
				extraDetails+="Included riders: \n";
				for(DataObjects.RiderDO rider:term.getRiderDOList()) {
					extraDetails+="-"+rider.getRiderName()+"(cover amount:" +rider.getCoverAmount() + ")\n";
				}
			}
			
		}else if(policy instanceof DataObjects.HealthInsuranceDO) {
			DataObjects.HealthInsuranceDO health = (DataObjects.HealthInsuranceDO)policy;
			policyId = health.getPolicySeq();	
			policyType = "Health Insurance";
			premium = health.getPremiumAmt();
			extraDetails = "sum assured: " + health.getSumAssured()+'\n';
			if(!health.getRiderDOList().isEmpty()) {
				extraDetails+="Included riders: \n";
				for(DataObjects.RiderDO rider:health.getRiderDOList()) {
					extraDetails+="-"+rider.getRiderName()+"(cover amount:" +rider.getCoverAmount() + ")\n";
				}
			}
		}else if(policy instanceof DataObjects.InvestmentPlusDO) {
			DataObjects.InvestmentPlusDO invest = (DataObjects.InvestmentPlusDO) policy;
			policyId = invest.getPolicySeq();
			policyType="Investment";
			premium = invest.getPremiumAmt();
		}
		String fileName = folderName + File.separator + policyId + "_lead_" + lead.getLeadSeq() + ".txt";
		try {
			FileWriter writer = new FileWriter(fileName);
			writer.write("QUOTATION");
			writer.write("User details:");
			writer.write("Lead ID: " + lead.getLeadSeq()+ "\n");
            writer.write("Name: " +lead.getTitle()+ " " + lead.getFirstName() + "\n");
            writer.write("Gender: " + lead.getGenderCd() + "\n");
            writer.write("Policy details");
            writer.write("Policy ID: " + policyId + "\n");
            writer.write("Product Type: " + policyType + "\n");
            writer.write("Payment Frequency: " + policy.getPaymentFrequency() + "\n");
            writer.write("Modal Premium: Rs. " + premium + "\n");
            writer.write(extraDetails);
            writer.close();
		}catch(IOException e) {
			System.out.println("An error occurred while writing the file: " + e.getMessage());
		}
	}
}