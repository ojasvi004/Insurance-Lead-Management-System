package application;

import java.util.Scanner;

public class MainApplication {

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

            switch (choice) {
                case "1":
                    RegisterLead.registerNewLead(sc);
                    break;
                case "2":
                    EditLead.editLeadDetails(sc);
                    break;
                case "3":
                    DeleteLead.deleteLeadRecord(sc);
                    break;
                case "4":
                    ShowLead.showLeadDetails(sc);
                    break;
                case "5":
                    ShowAllLeads.showAllLeadDetails(sc);
                    break;
                case "9":
                    ok = true;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
        sc.close();
    }
}