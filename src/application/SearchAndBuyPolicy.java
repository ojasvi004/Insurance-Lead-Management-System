package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SearchAndBuyPolicy {

    public static void processPayment(Scanner sc) {
        System.out.println("Search quotation & Buy policy \n");
        ArrayList<String> searchOptions = new ArrayList<>();
        searchOptions.add("ALL");
        searchOptions.add("USER");
        String searchType = InputValidation.getValidOption(sc, "Do you want to search all quotations or by user? (Type ALL or USER): ", searchOptions);

        ArrayList<DataObjects.BasePolicyDO> displayPolicies = new ArrayList<>();
        ArrayList<String> displayLeads = new ArrayList<>();
        if (searchType.equalsIgnoreCase("ALL")) {
            for (String leadId : DataObjects.DataSource.QuotationGenerated.keySet()) {
                List<DataObjects.BasePolicyDO> userQuotes = DataObjects.DataSource.QuotationGenerated.get(leadId);
                for (DataObjects.BasePolicyDO policy : userQuotes) {
                    displayPolicies.add(policy);
                    displayLeads.add(leadId);
                }
            }
        } else {
        	System.out.println("Enter the Lead ID");
    		String leadSeq = sc.nextLine();
    		if (DataObjects.DataSource.QuotationGenerated.containsKey(leadSeq)) {
                for (DataObjects.BasePolicyDO policy : DataObjects.DataSource.QuotationGenerated.get(leadSeq)) {
                    displayPolicies.add(policy);
                    displayLeads.add(leadSeq);
                }
            } else {
                System.out.println("No quotations found for this user.");
                return;
            }
        }
        if (displayPolicies.isEmpty()) {
            System.out.println("No quotations available to display.");
            return;
        }
        System.out.println("Available quotations: \n");
        for (int i = 0; i < displayPolicies.size(); i++) {
            DataObjects.BasePolicyDO policy = displayPolicies.get(i);
            String type = "";
            String id = "";
            double premium = 0.0;

            if (policy instanceof DataObjects.TermInsuranceDO) {
                type = "Term Insurance";
                id = ((DataObjects.TermInsuranceDO) policy).getPolicySeq();
                premium = ((DataObjects.TermInsuranceDO) policy).getPremiumAmt();
            } else if (policy instanceof DataObjects.HealthInsuranceDO) {
                type = "Health Insurance";
                id = ((DataObjects.HealthInsuranceDO) policy).getPolicySeq();
                premium = ((DataObjects.HealthInsuranceDO) policy).getPremiumAmt();
            } else if (policy instanceof DataObjects.InvestmentPlusDO) {
                type = "Investment Plus";
                id = ((DataObjects.InvestmentPlusDO) policy).getPolicySeq();
                premium = ((DataObjects.InvestmentPlusDO) policy).getPremiumAmt();
            }

            System.out.println((i + 1) + ". Lead ID: " + displayLeads.get(i) + " | Policy ID: " + id + " | Type: " + type + " | Premium to Pay: Rs. " + premium);
        }
        int choice = 0;
        while (true) {
            choice = InputValidation.getValidInt(sc, "Enter the number of the policy you want to buy: \n");
            if (choice > 0 && choice <= displayPolicies.size()) {
                break;
            }
            System.out.println("Invalid selection, try again.");
        }
        DataObjects.BasePolicyDO selectedPolicy = displayPolicies.get(choice - 1);
        String selectedId = "";
        double finalPremium = 0.0;

        if (selectedPolicy instanceof DataObjects.TermInsuranceDO) {
            selectedId = ((DataObjects.TermInsuranceDO) selectedPolicy).getPolicySeq();
            finalPremium = ((DataObjects.TermInsuranceDO) selectedPolicy).getPremiumAmt();
        } else if (selectedPolicy instanceof DataObjects.HealthInsuranceDO) {
            selectedId = ((DataObjects.HealthInsuranceDO) selectedPolicy).getPolicySeq();
            finalPremium = ((DataObjects.HealthInsuranceDO) selectedPolicy).getPremiumAmt();
        } else if (selectedPolicy instanceof DataObjects.InvestmentPlusDO) {
            selectedId = ((DataObjects.InvestmentPlusDO) selectedPolicy).getPolicySeq();
            finalPremium = ((DataObjects.InvestmentPlusDO) selectedPolicy).getPremiumAmt();
        }
        ArrayList<String> yesNo = new ArrayList<>();
        yesNo.add("YES");
        yesNo.add("NO");
        String agree = InputValidation.getValidOption(sc, "The premium to pay is Rs. " + finalPremium + ". Do you agree to pay? (YES/NO): \n", yesNo);
        if (agree.equalsIgnoreCase("YES")) {
            if (DataObjects.DataSource.paidPolicies.contains(selectedId)) {
                System.out.println("Payment failed: this is a repeat payment. Policy ID " + selectedId + " has already been purchase");
            } else {
                DataObjects.DataSource.paidPolicies.add(selectedId);
                System.out.println("You have successfully purchased Policy ID " + selectedId + ".");
            }
        } else {
            System.out.println("Transaction cancelled");
        }
    }
}