package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class RegisterLead {

    public static void registerNewLead(Scanner sc) {
        System.out.println("Register new lead");
        DataObjects.LeadDO newLead = new DataObjects.LeadDO();

        String newLeadId = Operations.SequenceGenerator.getInstance().getNextSequence();
        newLead.setLeadSeq(newLeadId);
        String newTitle = InputValidation.getValidRegexString(sc, "Enter title(Mr/Ms/Mrs): ", "^(Mr|Ms|Mrs)$", "Title must be Mr, Ms or Mrs");
        newLead.setTitle(newTitle);

        String firstName = InputValidation.getValidString(sc, "Enter first name: ");
        newLead.setFirstName(firstName);

        boolean validDate = false;
        while (!validDate) {
            String dobInput = InputValidation.getValidRegexString(sc, "Enter date of birth (dd/mm/yyyy): ", "^\\d{2}/\\d{2}/\\d{4}$", "Must be dd/mm/yyyy format");
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);
                Date dob = sdf.parse(dobInput);
                newLead.setBirthDate(dob);
                validDate = true;
            } catch (ParseException e) {
                System.out.println("Invalid date. Ensure it is a real date");
            }
        }
        String gender = InputValidation.getValidRegexString(sc, "Enter Gender (male/female/transgender): ", "^(male|female|transgender)$", "Gender must be male, female or transgender");
        newLead.setGenderCd(gender);
        
        newLead.setLeadAddressDOList(CaptureDetails.captureUserAddresses(newLeadId, sc));
        newLead.setLeadContactDOList(CaptureDetails.captureUserContacts(newLeadId, sc));
        newLead.setFNAQuestionResponseDOList(CaptureDetails.captureFNAQuestions(sc));

        DataObjects.DataSource.partyData.put(newLeadId, newLead);
        System.out.println("Success! Lead registered with ID: " + newLeadId);
    }
}