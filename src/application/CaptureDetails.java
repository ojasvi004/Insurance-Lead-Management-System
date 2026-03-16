package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class CaptureDetails {

	public static List<DataObjects.FNAQuestionResponseDO> captureFNAQuestions(Scanner sc) {
        List<DataObjects.FNAQuestionResponseDO> fnaList = new ArrayList<>();
        
        for (String type : DataObjects.DataSource.FNAQuestions.keySet()) {
            HashMap<String, ArrayList<String>> questionsMap = DataObjects.DataSource.FNAQuestions.get(type);
            for (String question : questionsMap.keySet()) {
                ArrayList<String> options = questionsMap.get(question);
                System.out.println(question);
                String answer = InputValidation.getValidOption(sc, "Enter response " + options + ": ", options);
                DataObjects.FNAQuestionResponseDO fnaDO = new DataObjects.FNAQuestionResponseDO();
                fnaDO.setQuestiondesc(question);
                fnaDO.setResponseValue(answer);
                fnaList.add(fnaDO);
            }
        }
        return fnaList;
    }

    public static List<DataObjects.LeadAddressDO> captureUserAddresses(String leadSeq, Scanner sc) {
        List<DataObjects.LeadAddressDO> addresses = new ArrayList<>();
        
        boolean hasPrimary = false;
        
        int count = InputValidation.getValidInt(sc, "How many addresses do you want to add? ");
        for (int i = 0; i < count; i++) {
            DataObjects.LeadAddressDO addr = new DataObjects.LeadAddressDO();
            addr.setLeadAddressSeq(String.valueOf(i + 1));
            addr.setLeadSeq(leadSeq);

            System.out.println("Address: " + (i + 1));
            String inputVal = InputValidation.getValidRegexString(sc, "Address type (RESIDENTIAL/OFFICE). Enter 1 for RESIDENTIAL or 2 for OFFICE: ", "^(1|2)$", "Must be 1 or 2");
            if (inputVal.equals("1")) {
                addr.setAddressType("RESIDENTIAL");
            } else {
                addr.setAddressType("OFFICE");
            }
            addr.setAddressDetails(InputValidation.getValidString(sc, "Enter Address details: "));
            addr.setStateCd(InputValidation.getValidString(sc, "Enter State code: "));
            addr.setCountryCd(InputValidation.getValidString(sc, "Enter Country code: "));
            
            addr.setPinCode(InputValidation.getValidRegexString(sc, "Enter Pin code: ", "^\\d{6}$", "pin code must be exactly 6 digits"));
            addr.setPrimaryAddress(InputValidation.getValidRegexString(sc, "Is this Primary address? (YES/NO): ", "^(YES|NO|yes|no)$", "Must be YES or NO").toUpperCase());
            addresses.add(addr);
            for(DataObjects.LeadAddressDO a: addresses) {
            	if (a.getPrimaryAddress().equals("YES")) {
                    hasPrimary = true;
                }
            }
            if (!hasPrimary && addresses.size() > 0) {
                addresses.get(0).setPrimaryAddress("YES");
            }
        }
        return addresses;
    }

    public static List<DataObjects.LeadContactDO> captureUserContacts(String leadSeq, Scanner sc) {
        List<DataObjects.LeadContactDO> contacts = new java.util.ArrayList<>();
        int count = InputValidation.getValidInt(sc, "How many contacts do you want to add? ");
        for (int i = 0; i < count; i++) {
            DataObjects.LeadContactDO contact = new DataObjects.LeadContactDO();
            contact.setLeadContactSeq(String.valueOf(i + 1));
            contact.setLeadContactSeq(leadSeq);
            System.out.println("Contact " + (i + 1));
            String contactType = InputValidation.getValidRegexString(sc, "Contact type (EMAIL/PHONE): ", "^(EMAIL|PHONE|email|phone)$", "Type must be EMAIL or PHONE").toUpperCase();
            contact.setContactType(contactType);
            if (contactType.equals("EMAIL")) {
                String email = InputValidation.getValidRegexString(sc, "Enter Email address: ", "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", "Must be a valid email");
                contact.setContactNum(email);
            } else {
                String phone = InputValidation.getValidRegexString(sc, "Enter Phone number: ", "^\\d{10}$", "Phone number must be 10 digits");
                contact.setContactNum(phone);
            }
            contacts.add(contact);
        }
        return contacts;
    }
}