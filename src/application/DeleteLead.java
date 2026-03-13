package application;

import java.util.Scanner;

public class DeleteLead {

    public static void deleteLeadRecord(Scanner sc) {
        System.out.println("Enter the details of the lead ID you want to delete");
        String leadSeq = sc.nextLine();

        if (!DataObjects.DataSource.partyData.containsKey(leadSeq)) {
            System.out.println("No lead found with this ID, enter correct ID");
            return;
        }
        DataObjects.DataSource.partyData.remove(leadSeq);
        System.out.println("Lead deleted successfully!");
    }
}