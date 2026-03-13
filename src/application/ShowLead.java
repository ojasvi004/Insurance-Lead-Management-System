package application;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class ShowLead {

    public static void showLeadDetails(Scanner sc) {
        System.out.println("Enter the details of the lead ID you want to see: ");
        String leadSeq = sc.nextLine();

        if (!DataObjects.DataSource.partyData.containsKey(leadSeq)) {
            System.out.println("Lead ID not found. Enter correct Lead ID");
            return;
        }
        DataObjects.LeadDO leadInfo = DataObjects.DataSource.partyData.get(leadSeq);
        System.out.println("Lead ID: " + leadInfo.getLeadSeq());
        System.out.println("Name: " + leadInfo.getTitle() + " " + leadInfo.getFirstName());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        System.out.println("Date of Birth: " + sdf.format(leadInfo.getBirthDate()));
        System.out.println("Gender: " + leadInfo.getGenderCd());
        System.out.println("Addresses");
        List<DataObjects.LeadAddressDO> addresses = leadInfo.getLeadAddressDOList();
        if (addresses.size() > 0) {
            for (int i = 0; i < addresses.size(); i++) {
                DataObjects.LeadAddressDO addr = addresses.get(i);
                System.out.println((i + 1) + ": " + addr.getAddressType() + " - " + addr.getPinCode());
            }
        } else {
            System.out.println("No addresses found.");
        }
        System.out.println("Contacts");
        List<DataObjects.LeadContactDO> contacts = leadInfo.getLeadContactDOList();
        if (contacts.size() > 0) {
            for (int i = 0; i < contacts.size(); i++) {
                DataObjects.LeadContactDO contact = contacts.get(i);
                System.out.println((i + 1) + ": " + contact.getContactType() + " - " + contact.getContactNum());
            }
            System.out.println("FNA Responses");
            List<DataObjects.FNAQuestionResponseDO> fnaList = leadInfo.getFNAQuestionResponseDOList();
            if (fnaList.size() > 0) {
                for (int i = 0; i < fnaList.size(); i++) {
                    System.out.println("Question:" + fnaList.get(i).getQuestiondesc());
                    System.out.println("Answer: " + fnaList.get(i).getResponseValue());
                }
            }
        } else {
            System.out.println("No contacts found");
        }
    }
}