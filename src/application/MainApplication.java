package application;

import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainApplication {
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean ok = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Insurance Lead System");

        while (!ok) {
            System.out.println("1: Register new lead");
            System.out.println("2: Edit lead details");
            System.out.println("3: Delete lead");
            System.out.println("4: Search lead by ID");
            System.out.println("5: View all leads");
            System.out.println("6: Generate quotation");
            System.out.println("7: Download quotation");
            System.out.println("8: Search quotation & buy Policy");
            System.out.println("9: Exit");
            System.out.print("Please select an option: ");
            String choice = sc.nextLine();

            if (choice.equals("1")) {
                registerNewLead(sc);
            }	else {
                System.out.println("Invalid option.");
            }
        }
        sc.close(); 
    }

    static void registerNewLead(Scanner sc) {
        System.out.println("Register new lead");
        DataObjects.LeadDO newLead = new DataObjects.LeadDO();
        String newLeadId = String.valueOf(DataObjects.DataSource.partyData.size() + 1);
        newLead.setLeadSeq(newLeadId);
        
        System.out.println("Enter Title(Mr/Ms/Mrs): ");
        String newTitle = sc.nextLine();
        newLead.setTitle(newTitle);
        
        System.out.println("Enter first name: ");
        String firstName = sc.nextLine();
        newLead.setFirstName(firstName);
        
        System.out.println("Enter date of birth (dd/mm/yyyy): ");
        String dobInput = sc.nextLine();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            Date dob = sdf.parse(dobInput);
            newLead.setBirthDate(dob);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use dd/mm/yyyy.");
        }
        
        System.out.print("Enter Gender (male/female/transgender): ");
        newLead.setGenderCd(sc.nextLine());       
        
        newLead.setLeadAddressDOList(captureUserAddresses(newLeadId, sc));
        newLead.setLeadContactDOList(captureUserContacts(newLeadId, sc));
        DataObjects.DataSource.partyData.put(newLeadId, newLead);
        System.out.println("Success! Lead registered with ID: " + newLeadId);
    }
    
    
    static List<DataObjects.LeadAddressDO> captureUserAddresses(String leadSeq, Scanner sc) {
        List<DataObjects.LeadAddressDO> addresses = new ArrayList<>();
        
        System.out.println("How many addresses do you want to add?");
        int count = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < count; i++) {
            DataObjects.LeadAddressDO addr = new DataObjects.LeadAddressDO();
            addr.setLeadAddressSeq(String.valueOf(i + 1));
            addr.setLeadSeq(leadSeq);
            
            System.out.println("Address: " + (i + 1));
            System.out.print("Address Type (RESIDENTIAL/OFFICE). Enter 1 for RESIDENTIAL or 2 for OFFICE: ");
            String inputVal = sc.nextLine();
            if(inputVal.equals("1")) {
                addr.setAddressType("RESIDENTIAL");
            } else {
                addr.setAddressType("OFFICE");
            }
            
            System.out.print("Enter Pin Code: ");
            addr.setPinCode(sc.nextLine());
            
            System.out.print("Is this Primary Address? (YES/NO): ");
            addr.setPrimaryAddress(sc.nextLine());
            
            addresses.add(addr);
        }
       return addresses;
    }
    static List<DataObjects.LeadContactDO> captureUserContacts(String leadSeq, Scanner sc) {
        List<DataObjects.LeadContactDO> contacts = new java.util.ArrayList<>();
        
        System.out.println("How many contacts do you want to add?");
        int count = Integer.parseInt(sc.nextLine()); 
        
        for (int i = 0; i < count; i++) {
            DataObjects.LeadContactDO contact = new DataObjects.LeadContactDO();
            contact.setLeadContactSeq(String.valueOf(i + 1));
            contact.setLeadContactSeq(leadSeq);
            System.out.println("Contact " + (i + 1));
            System.out.print("Contact Type (EMAIL/PHONE): ");
            contact.setContactType(sc.nextLine());
            System.out.print("Enter Contact Details: ");
            contact.setContactNum(sc.nextLine());
            contacts.add(contact);
        }
        return contacts;
    }
}