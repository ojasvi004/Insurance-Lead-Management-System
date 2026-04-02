package application;

import java.util.List;
import java.util.Scanner;

public class DownloadQuotation {

	public static void download(Scanner sc) {
		System.out.println("Download Quotation");
		System.out.println("Enter the Lead ID to view their quotations");
		String leadSeq = sc.nextLine();
		if (!DataObjects.DataSource.partyData.containsKey(leadSeq)) {
			System.out.println("no lead found with this ID, enter correct ID");
			return;
		}

		if (!DataObjects.DataSource.QuotationGenerated.containsKey(leadSeq)) {
			System.out.println("No quotations have been generated for this lead yet");
			return;
		}

		DataObjects.LeadDO lead = DataObjects.DataSource.partyData.get(leadSeq);
		List<DataObjects.BasePolicyDO> quotes = DataObjects.DataSource.QuotationGenerated.get(leadSeq);

		System.out.println("Available Quotations for Lead ID " + lead.getLeadSeq() + ":");
		for (int i = 0; i < quotes.size(); i++) {
			DataObjects.BasePolicyDO policy = quotes.get(i);
			String type = "";
			String id = "";
			if (policy instanceof DataObjects.TermInsuranceDO) {
				type = "Term Insurance";
				id = ((DataObjects.TermInsuranceDO) policy).getPolicySeq();
			} else if (policy instanceof DataObjects.HealthInsuranceDO) {
				type = "Health Insurance";
				id = ((DataObjects.HealthInsuranceDO) policy).getPolicySeq();
			} else if (policy instanceof DataObjects.InvestmentPlusDO) {
				type = "Investment";
				id = ((DataObjects.InvestmentPlusDO) policy).getPolicySeq();
			}
			System.out.println((i + 1) + ". Policy ID: " + id + " (" + type + ")");
		}

		int choice = 0;
		while (true) {
			choice = InputValidation.getValidInt(sc, "Enter the number of the quotation you want to download: ");
			if (choice > 0 && choice <= quotes.size()) {
				break;
			}
			System.out.println("invalid, enter a valid number from the list.");
		}
		DataObjects.BasePolicyDO selectedPolicy = quotes.get(choice - 1);

		FileUtil.saveQuotationToFile(lead, selectedPolicy);
	}
}