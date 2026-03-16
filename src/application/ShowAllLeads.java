package application;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShowAllLeads {

    public static void showAllLeadDetails(Scanner sc) {
        List<DataObjects.LeadDO> allLeads = new ArrayList<>(DataObjects.DataSource.partyData.values());
        if (allLeads.size() == 0) {
            System.out.println("No leads found");
            return;
        }
        for (int i = 0; i < allLeads.size(); i++) {
            DataObjects.LeadDO lead = allLeads.get(i);
            System.out.println("Lead ID: " + lead.getLeadSeq());
            System.out.println("Name: " + lead.getTitle() + " " + lead.getFirstName());
            if (lead.getBirthDate() != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                System.out.println("Date of Birth: " + sdf.format(lead.getBirthDate()));
            }
            System.out.println("Gender: " + lead.getGenderCd());

            System.out.println("Addresses");
            List<DataObjects.LeadAddressDO> addresses = lead.getLeadAddressDOList();
            if (addresses.size() > 0) {
                for (int j = 0; j < addresses.size(); j++) {
                    DataObjects.LeadAddressDO addr = addresses.get(j);
                    System.out.println((j + 1) + ": " + addr.getAddressType());
                    System.out.println("Details: " + addr.getAddressDetails());
                    System.out.println("State: " + addr.getStateCd());
                    System.out.println("Country: " + addr.getCountryCd());
                    System.out.println("Pin Code: " + addr.getPinCode());
                    System.out.println("Primary: " + addr.getPrimaryAddress());
                }
            } else {
                System.out.println("No addresses found");
            }
            System.out.println("Contacts");
            List<DataObjects.LeadContactDO> contacts = lead.getLeadContactDOList();
            if (contacts.size() > 0) {
                for (int k = 0; k < contacts.size(); k++) {
                    DataObjects.LeadContactDO contact = contacts.get(k);
                    System.out.println((k + 1) + ": " + contact.getContactType() + " - " + contact.getContactNum());
                }
                System.out.println("FNA Responses");
                List<DataObjects.FNAQuestionResponseDO> fnaList = lead.getFNAQuestionResponseDOList();
                if (fnaList.size() > 0) {
                    for (int j = 0; j < fnaList.size(); j++) {
                        System.out.println("Question:" + fnaList.get(j).getQuestiondesc());
                        System.out.println("Answer: " + fnaList.get(j).getResponseValue());
                    }
                }
            } else {
                System.out.println("No contacts found");
            }
        }
    }
}