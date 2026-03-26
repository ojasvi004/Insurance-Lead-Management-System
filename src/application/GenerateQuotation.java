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
                if (question.contains("medical issues")) {
                    if (!suggestedProducts.contains("HEALTHINSURANCE")) {
                        suggestedProducts.add("HEALTHINSURANCE");
                    }
                }
                else if (question.contains("death")) {
                    if (!suggestedProducts.contains("TERMINSURANCE")) {
                        suggestedProducts.add("TERMINSURANCE");
                    }
                } 
                else if (question.contains("retirement") || question.contains("lumpsum") || question.contains("specific goal")) {
                    if (!suggestedProducts.contains("Investment")) {
                        suggestedProducts.add("Investment");
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
        
        int policyChoice = 0;
        while (true) {
            policyChoice = InputValidation.getValidInt(sc, "Enter the number of the product you want to generate a quotation for: ");
            if (policyChoice > 0 && policyChoice <= suggestedProducts.size()) {
                break;
            }
            System.out.println("enter a valid number from the list");
        }        
        String selectedPolicy = suggestedProducts.get(policyChoice - 1);
        String policySeq = Operations.SequenceGenerator.getInstance().getNextSequence(); 
        System.out.println("policy ID assigned: " + policySeq);
        
        DataObjects.PremiumCalculation calc = new DataObjects.PremiumCalculation();
        DataObjects.BasePolicyDO finalPolicy = null;
        double calculatedPremium = 0.0;

        if (selectedPolicy.equalsIgnoreCase("Investment")) {
            double premium = (double) InputValidation.getValidInt(sc, "enter premium amount: ");
            int termYears = InputValidation.getValidInt(sc, "enter policy term : ");
            double return4 = premium * ((Math.pow(1 + 0.04, termYears) - 1) / 0.04);
            double return8 = premium * ((Math.pow(1 + 0.08, termYears) - 1) / 0.08);
            double return10 = premium * ((Math.pow(1 + 0.10, termYears) - 1) / 0.10);
            System.out.println("Investment returns:");
            System.out.println("Expected return for 4%: Rs. " + return4);
            System.out.println("Expected return for 8%: Rs. " + return8);
            System.out.println("Expected return for 10%: Rs. " + return10);

            DataObjects.InvestmentPlusDO invPolicy = new DataObjects.InvestmentPlusDO();
            invPolicy.setLeadSeq(leadSeq);
            invPolicy.setPolicySeq(policySeq);
            invPolicy.setPremiumAmt(premium);
            invPolicy.setPaymentFrequency("annually");
            
            calculatedPremium = premium;
            finalPolicy = invPolicy;
            
        } else {
            ArrayList<String> freqOptions = new ArrayList<>();
            freqOptions.add("monthly");
            freqOptions.add("quarterly");
            freqOptions.add("annually");
            String frequency = InputValidation.getValidOption(sc, "Enter payment frequency (monthly, quarterly, annually): ", freqOptions);
            double sumAssured = (double) InputValidation.getValidInt(sc, "enter sum assured amount: ");
            
            ArrayList<String> availableRiders = DataObjects.DataSource.policyRiders.get(selectedPolicy);
            List<DataObjects.RiderDO> ridersList = new ArrayList<>();
            int numRiders = InputValidation.getValidInt(sc, "How many riders do you want to add?: ");
            
            if(numRiders > 0) {
                System.out.println("available riders:");
                for (int i = 0; i < availableRiders.size(); i++) {
                    System.out.println((i + 1) + ". " + availableRiders.get(i));
                }
                for (int i = 0; i < numRiders; i++) {
                    int riderChoice = InputValidation.getValidInt(sc, "Enter rider number: ");

                    if (riderChoice > 0 && riderChoice <= availableRiders.size()) {
                        String chosenRiderName = availableRiders.get(riderChoice - 1);
                        double coverAmount = 0.0;
                        if (selectedPolicy.equals("TERMINSURANCE")) {
                            coverAmount = sumAssured * 0.02;
                        } else if (selectedPolicy.equals("HEALTHINSURANCE")) {
                            coverAmount = sumAssured * 0.20;
                        }                   
                        DataObjects.RiderDO rider = new DataObjects.RiderDO();
                        rider.setRiderName(chosenRiderName); 
                        rider.setCoverAmount(coverAmount);
                        ridersList.add(rider);
                        System.out.println(chosenRiderName + " added successfully");
                    } else {
                        System.out.println("Invalid selection, try again");
                        i--;
                    }
                }
            }
            
            if (selectedPolicy.equals("TERMINSURANCE")) {
                DataObjects.TermInsuranceDO termPolicy = new DataObjects.TermInsuranceDO();
                termPolicy.setLeadSeq(leadSeq); 
                termPolicy.setPaymentFrequency(frequency); 
                termPolicy.setSumAssured(sumAssured);
                termPolicy.setRiderDOList(ridersList);
                termPolicy.setPolicySeq(policySeq);
                
                calculatedPremium = calc.premiumCalTermIns(termPolicy);
                termPolicy.setPremiumAmt(calculatedPremium);
                
                finalPolicy = termPolicy;
                
            } else if (selectedPolicy.equals("HEALTHINSURANCE")) {
                DataObjects.HealthInsuranceDO healthPolicy = new DataObjects.HealthInsuranceDO();
                healthPolicy.setLeadSeq(leadSeq);
                healthPolicy.setPaymentFrequency(frequency);
                healthPolicy.setSumAssured(sumAssured);
                healthPolicy.setRiderDOList(ridersList);
                healthPolicy.setPolicySeq(policySeq);
              
                calculatedPremium = calc.premiumCalTermHealthIns(healthPolicy);
                healthPolicy.setPremiumAmt(calculatedPremium);
                finalPolicy = healthPolicy;
            }
        }
        
        if (finalPolicy != null) {
            System.out.println("generated quotation: ");
            System.out.println("Policy ID: " + policySeq);
            System.out.println("Policy type: " + selectedPolicy);
            System.out.println("Payment frequency: " + finalPolicy.getPaymentFrequency());
            System.out.println("Modal premium to pay: Rs. " + calculatedPremium);
            List<DataObjects.BasePolicyDO> existingQuotes = DataObjects.DataSource.QuotationGenerated.get(leadSeq);
            if (existingQuotes == null) {
                existingQuotes = new ArrayList<>();
            }
            existingQuotes.add(finalPolicy);
            DataObjects.DataSource.QuotationGenerated.put(leadSeq, existingQuotes);
        }
    }
}