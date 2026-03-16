package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class EditLead {

    public static void editLeadDetails(Scanner sc) {
        System.out.println("Enter the details of the lead ID you want to edit");
        String leadSeq = sc.nextLine();

        if (!DataObjects.DataSource.partyData.containsKey(leadSeq)) {
            System.out.println("No lead found with this ID. Please enter correct ID");
            return;
        }
        DataObjects.LeadDO leadToEdit = DataObjects.DataSource.partyData.get(leadSeq);
        System.out.println("1: Edit title");
        System.out.println("2: Edit first name");
        System.out.println("3: Edit date of birth");
        System.out.println("4: Edit gender");
        System.out.println("5: Update addresses");
        System.out.println("6: Update contacts");
        System.out.println("7: Update FNA Response");
        System.out.print("Please select an option: ");
        String choice = sc.nextLine();

        if (choice.equals("1")) {
        	String newTitle = InputValidation.getValidRegexString(sc, "Enter Title(Mr/Ms/Mrs): ", "^(Mr|Ms|Mrs)$", "Title must be  Mr, Ms or Mrs");
            leadToEdit.setTitle(newTitle);
            System.out.println("Title updated");
        } else if (choice.equals("2")) {
        	String firstName = InputValidation.getValidRegexString(sc, "Enter first name: ", "^[a-zA-Z\\s]+$", "Name can only contain letters");
            leadToEdit.setFirstName(firstName);
            System.out.println("First name updated");
        } else if (choice.equals("3")) {
            System.out.println("Enter date of birth (dd/mm/yyyy): ");
            String dobInput = sc.nextLine();
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
                sdf.setLenient(false);
                Date dob = sdf.parse(dobInput);
                leadToEdit.setBirthDate(dob);
                System.out.println("Date of birth updated");
            } catch (ParseException e) {
                System.out.println("Invalid date format.");
            }
        } else if (choice.equals("4")) {
        	String gender = InputValidation.getValidRegexString(sc, "Enter gender (male/female/transgender): ", "^(male|female|transgender)$", "Gender must be male, female or transgender");
            leadToEdit.setGenderCd(gender);
            System.out.println("Gender updated");
            } else if (choice.equals("5")) {
            List<DataObjects.LeadAddressDO> addressList = leadToEdit.getLeadAddressDOList();
            if (addressList.size() == 0) {
                System.out.println("No address found");
            } else {
                for (int i = 0; i < addressList.size(); i++) {
                    System.out.println((i + 1) + ": " + addressList.get(i).getAddressType() + " - "
                            + addressList.get(i).getPinCode());
                }
                int ind = InputValidation.getValidInt(sc, "Select address to edit: ") - 1;
                if (ind >= 0 && ind < addressList.size()) {
                    DataObjects.LeadAddressDO addrToEdit = addressList.get(ind);
                    String inputVal = InputValidation.getValidRegexString(sc, "Address Type (RESIDENTIAL/OFFICE). Enter 1 for RESIDENTIAL or 2 for OFFICE: ", "^(1|2)$", "Must be exactly 1 or 2");
                    if (inputVal.equals("1")) {
                        addrToEdit.setAddressType("RESIDENTIAL");
                    } else {
                        addrToEdit.setAddressType("OFFICE");
                    }
                    addrToEdit.setAddressDetails(InputValidation.getValidString(sc, "Enter Address details: "));
                    addrToEdit.setStateCd(InputValidation.getValidString(sc, "Enter State code: "));
                    addrToEdit.setCountryCd(InputValidation.getValidString(sc, "Enter Country code: "));
                    addrToEdit.setPinCode(InputValidation.getValidRegexString(sc, "Enter Pin code: ", "^\\d{6}$", "Pin code must be 6 digits"));
                    addrToEdit.setPrimaryAddress(InputValidation.getValidRegexString(sc, "Is this Primary address? (YES/NO): ", "^(YES|NO|yes|no)$", "Must be exactly YES or NO").toUpperCase());
                    System.out.println("Address updated successfully");
                } else {
                    System.out.println("Invalid selection");
                }
            }
        } else if (choice.equals("6")) {
            List<DataObjects.LeadContactDO> contactList = leadToEdit.getLeadContactDOList();
            if (contactList.size() == 0) {
                System.out.println("No contacts found");
            }
            for (int i = 0; i < contactList.size(); i++) {
                System.out.println((i + 1) + ": " + contactList.get(i).getContactType() + " - "
                        + contactList.get(i).getContactNum());
            }
            System.out.println("Selcet the contact you want to edit");
            int ind = Integer.parseInt(sc.nextLine()) - 1;
            if (ind >= 0 && ind < contactList.size()) {
                DataObjects.LeadContactDO contactToEdit = contactList.get(ind);
                String contactType = InputValidation.getValidRegexString(sc, "Contact type (EMAIL/PHONE): ", "^(EMAIL|PHONE|email|phone)$", "Type must be EMAIL or PHONE").toUpperCase();
                contactToEdit.setContactType(contactType);
                if (contactType.equals("EMAIL")) {
                    String email = InputValidation.getValidRegexString(sc, "Enter Email address: ", "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", "Must be a valid email");
                    contactToEdit.setContactNum(email);
                } else {
                    String phone = InputValidation.getValidRegexString(sc, "Enter Phone number: ", "^\\d{10}$", "Phone number must be 10 digits");
                    contactToEdit.setContactNum(phone);
                }
                System.out.println("Contact updated");
            } else {
                System.out.println("Invalid selection");
            }
        } else if (choice.equals("7")) {
            List<DataObjects.FNAQuestionResponseDO> fnaList = leadToEdit.getFNAQuestionResponseDOList();
            if (fnaList.size() == 0) {
                System.out.println("No FNA responses found");
            } else {
                for (int i = 0; i < fnaList.size(); i++) {
                    System.out.println((i + 1) + ": " + fnaList.get(i).getQuestiondesc());
                    System.out.println("Current value: " + fnaList.get(i).getResponseValue());
                }
                System.out.println("Select the question you want to edit:");
                int ind = Integer.parseInt(sc.nextLine()) - 1;
                if (ind < fnaList.size()) {
                    DataObjects.FNAQuestionResponseDO fnaToEdit = fnaList.get(ind);
                    System.out.println("Question: " + fnaToEdit.getQuestiondesc());
                    System.out.print("Enter new response: ");
                    fnaToEdit.setResponseValue(sc.nextLine());
                    System.out.println("FNA response updated");
                } else {
                    System.out.println("Invalid selection");
                }
            }
        } else {
            System.out.println("Invalid option");
        }
        DataObjects.DataSource.partyData.put(leadSeq, leadToEdit);
    }
}