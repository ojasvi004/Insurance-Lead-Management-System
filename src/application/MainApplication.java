package application;

import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import DataObjects.FNAQuestionResponseDO;

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
			} else if (choice.equals("2")) {
				editLeadDetails(sc);
			} else if (choice.equals("3")) {
				deleteLeadRecord(sc);
			} else if (choice.equals("4")) {
				showLeadDetails(sc);
			} else if (choice.equals("5")) {
				showAllLeadDetails(sc);
			} else {
				System.out.println("Invalid option.");
			}
		}
		sc.close();
	}

	static void registerNewLead(Scanner sc) {
		System.out.println("Register new lead");
		DataObjects.LeadDO newLead = new DataObjects.LeadDO();
		String newLeadId = Operations.SequenceGenerator.getInstance().getNextSequence();
		newLead.setLeadSeq(newLeadId);
		String newTitle = getValidString(sc, "Enter Title(Mr/Ms/Mrs): ");
		newLead.setTitle(newTitle);
		String firstName = getValidString(sc, "Enter first name: ");
		newLead.setFirstName(firstName);

		boolean validDate = false;
		while (!validDate) {
			String dobInput = getValidString(sc, "Enter date of birth (dd/mm/yyyy): ");
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
				sdf.setLenient(false);
				Date dob = sdf.parse(dobInput);
				newLead.setBirthDate(dob);
				validDate = true;
			} catch (ParseException e) {
				System.out.println("Invalid date format. use dd/mm/yyyy.");
			}
		}
		String gender = getValidString(sc, "Enter Gender (male/female/transgender): ");
		newLead.setGenderCd(gender);
		newLead.setLeadAddressDOList(captureUserAddresses(newLeadId, sc));
		newLead.setLeadContactDOList(captureUserContacts(newLeadId, sc));
		newLead.setFNAQuestionResponseDOList(captureFNAQuestions(sc));
		DataObjects.DataSource.partyData.put(newLeadId, newLead);
		System.out.println("Success! Lead registered with ID: " + newLeadId);
	}

//	static List<DataObjects.FNAQuestionResponseDO> captureFNAQuestions(Scanner sc) {
//		List<DataObjects.FNAQuestionResponseDO> fnaList = new ArrayList<>();
//		DataObjects.FNAQuestionResponseDO q1 = new DataObjects.FNAQuestionResponseDO();
//		q1.setQuestiondesc("Are you looking for the security during retirement ?");
//		q1.setResponseValue(getValidString(sc, q1.getQuestiondesc() + " (YES/NO): ").toUpperCase());
//		fnaList.add(q1);
//
//		DataObjects.FNAQuestionResponseDO q2 = new DataObjects.FNAQuestionResponseDO();
//		q2.setQuestiondesc(
//				"Are you saving for specific goal like children education or marriage or any other specific goal like buying car, world tour?");
//		q2.setResponseValue(getValidString(sc, q2.getQuestiondesc() + " (YES/NO): ").toUpperCase());
//		fnaList.add(q2);
//
//		DataObjects.FNAQuestionResponseDO q3 = new DataObjects.FNAQuestionResponseDO();
//		q3.setQuestiondesc("Do you want to make lumpsum investment ?");
//		q3.setResponseValue(getValidString(sc, q3.getQuestiondesc() + " (YES/NO): ").toUpperCase());
//		fnaList.add(q3);
//
//		DataObjects.FNAQuestionResponseDO q4 = new DataObjects.FNAQuestionResponseDO();
//		q4.setQuestiondesc(
//				"Do you want to protect your family from financial impact of sudden event of death , disability , critical illness etc ?");
//		q4.setResponseValue(getValidString(sc, q4.getQuestiondesc() + " (YES/NO): ").toUpperCase());
//		fnaList.add(q4);
//
//		DataObjects.FNAQuestionResponseDO q5 = new DataObjects.FNAQuestionResponseDO();
//		q5.setQuestiondesc(
//				"Do you want to protect your family from financial impact of sudden event of medical issues, healthcare expenses , critical illness etc ?");
//		q5.setResponseValue(getValidString(sc, q5.getQuestiondesc() + " (YES/NO): ").toUpperCase());
//		fnaList.add(q5);
//
//		DataObjects.FNAQuestionResponseDO q6 = new DataObjects.FNAQuestionResponseDO();
//		q6.setQuestiondesc("Whats your average monthly income?");
//		System.out.println(q6.getQuestiondesc());
//		System.out.println("Options: Below 5 lacs | Between 5 to 10 lacs | Between 10 to 15 lacs | Above 15 lacs");
//		q6.setResponseValue(getValidString(sc, "Enter response: "));
//		fnaList.add(q6);
//
//		DataObjects.FNAQuestionResponseDO q7 = new DataObjects.FNAQuestionResponseDO();
//		q7.setQuestiondesc("when are you planning to retire ?");
//		System.out.println(q7.getQuestiondesc());
//		System.out.println("Options: 45 | 50 | 55 | 60 | 60+");
//		q7.setResponseValue(getValidString(sc, "Enter response: "));
//		fnaList.add(q7);
//		return fnaList;
//	}
	
	static List<DataObjects.FNAQuestionResponseDO> captureFNAQuestions(Scanner sc){
		List<DataObjects.FNAQuestionResponseDO> fnaList = new ArrayList<>();
		for(String type: DataObjects.DataSource.FNAQuestions.keySet()) {
			HashMap<String, ArrayList<String>> questionsMap = DataObjects.DataSource.FNAQuestions.get(type);
			for(String question: questionsMap.keySet()) {
				ArrayList<String> options = questionsMap.get(question);
				System.out.println(question);
				String answer = getValidString(sc, "Enter response: " + options + ": ");
				DataObjects.FNAQuestionResponseDO fnaDO = new DataObjects.FNAQuestionResponseDO();
				fnaDO.setQuestiondesc(question);
				fnaDO.setResponseValue(answer);
				fnaList.add(fnaDO);
			}
		}
		return fnaList;
	}
	
	static List<DataObjects.LeadAddressDO> captureUserAddresses(String leadSeq, Scanner sc) {
		List<DataObjects.LeadAddressDO> addresses = new ArrayList<>();

		int count = getValidInt(sc, "How many addresses do you want to add? ");
		for (int i = 0; i < count; i++) {
			DataObjects.LeadAddressDO addr = new DataObjects.LeadAddressDO();
			addr.setLeadAddressSeq(String.valueOf(i + 1));
			addr.setLeadSeq(leadSeq);

			System.out.println("Address: " + (i + 1));
			String inputVal = getValidString(sc, "Address Type (RESIDENTIAL/OFFICE). Enter 1 for RESIDENTIAL or 2 for OFFICE: ");
			if (inputVal.equals("1")) {
				addr.setAddressType("RESIDENTIAL");
			} else {
				addr.setAddressType("OFFICE");
			}

			addr.setPinCode(getValidString(sc, "Enter Pin Code: "));

			addr.setPrimaryAddress(getValidString(sc, "Is this Primary Address? (YES/NO): ").toUpperCase());

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

	static void editLeadDetails(Scanner sc) {
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
			System.out.println("Enter Title(Mr/Ms/Mrs): ");
			String newTitle = sc.nextLine();
			leadToEdit.setTitle(newTitle);
			System.out.println("Title updated");
		} else if (choice.equals("2")) {
			System.out.println("Enter first name: ");
			String firstName = sc.nextLine();
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
			System.out.print("Enter Gender (male/female/transgender): ");
			leadToEdit.setGenderCd(sc.nextLine());
			System.out.println("Gender updated");
		} else if (choice.equals("5")) {
			List<DataObjects.LeadAddressDO> addressList = leadToEdit.getLeadAddressDOList();
			if (addressList.size() == 0) {
				System.out.println("No address found.");
			} else {
				for (int i = 0; i < addressList.size(); i++) {
					System.out.println((i + 1) + ": " + addressList.get(i).getAddressType() + " - "
							+ addressList.get(i).getPinCode());
				}
				System.out.println("Select address to edit: ");
				int ind = Integer.parseInt(sc.nextLine()) - 1;
				if (ind >= 0 && ind < addressList.size()) {
					DataObjects.LeadAddressDO addrToEdit = addressList.get(ind);
					System.out.print("Address Type (RESIDENTIAL/OFFICE). Enter 1 for RESIDENTIAL or 2 for OFFICE: ");
					String inputVal = sc.nextLine();
					if (inputVal.equals("1")) {
						addrToEdit.setAddressType("RESIDENTIAL");
					} else {
						addrToEdit.setAddressType("OFFICE");
					}
					System.out.print("Enter Pin Code:");
					addrToEdit.setPinCode(sc.nextLine());
					System.out.print("Is this Primary Address? (YES/NO): ");
					addrToEdit.setPrimaryAddress(sc.nextLine());
					System.out.println("address updated");
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
				System.out.print("Contact Type (EMAIL/PHONE): ");
				contactToEdit.setContactType(sc.nextLine());
				System.out.print("Enter Contact Details: ");
				contactToEdit.setContactNum(sc.nextLine());
				System.out.println("Contact updated");
			}
			System.out.println("Invalid option");
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

	static void deleteLeadRecord(Scanner sc) {
		System.out.println("Enter the details of the lead ID you want to delete");
		String leadSeq = sc.nextLine();

		if (!DataObjects.DataSource.partyData.containsKey(leadSeq)) {
			System.out.println("No lead found with this ID. Please enter correct ID");
			return;
		}
		DataObjects.DataSource.partyData.remove(leadSeq);
		System.out.println("Lead deleted successfully!");
	}

	static void showLeadDetails(Scanner sc) {
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

	static void showAllLeadDetails(Scanner sc) {
		List<DataObjects.LeadDO> allLeads = new ArrayList<>(DataObjects.DataSource.partyData.values());
		if (allLeads.size() == 0) {
			System.out.println("No leads found.");
			return;
		}
		for (int i = 0; i < allLeads.size(); i++) {
			DataObjects.LeadDO lead = allLeads.get(i);
			System.out.println("Lead ID: " + lead.getLeadSeq());
			System.out.println("Name: " + lead.getTitle() + " " + lead.getFirstName());
			if (lead.getBirthDate() != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
				System.out.println("Date of Birth: " + sdf.format(lead.getBirthDate()));
			}
			System.out.println("Gender: " + lead.getGenderCd());

			System.out.println("Addresses");
			List<DataObjects.LeadAddressDO> addresses = lead.getLeadAddressDOList();
			if (addresses.size() > 0) {
				for (int j = 0; j < addresses.size(); j++) {
					DataObjects.LeadAddressDO addr = addresses.get(j);
					System.out.println((i + 1) + ": " + addr.getAddressType() + " - " + addr.getPinCode());
				}
			} else {
				System.out.println("No addresses found");
			}
			System.out.println("Contacts");
			List<DataObjects.LeadContactDO> contacts = lead.getLeadContactDOList();
			if (contacts.size() > 0) {
				for (int k = 0; k < contacts.size(); k++) {
					DataObjects.LeadContactDO contact = contacts.get(k);
					System.out.println((i + 1) + ": " + contact.getContactType() + " - " + contact.getContactNum());
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

	// custom helper methods
	static String getValidString(Scanner sc, String question) {
		String input = "";
		while (true) {
			System.out.println(question);
			input = sc.nextLine();
			if (!input.trim().isEmpty()) {
				break;
			}
			System.out.println("Please try again");
		}
		return input;
	}

	static int getValidInt(Scanner sc, String prompt) {
		while (true) {
			System.out.print(prompt);
			String input = sc.nextLine();
			if (!input.trim().isEmpty()) {
				try {
					return Integer.parseInt(input);
				} catch (NumberFormatException e) {
					System.out.println("Invalid number format. Please enter a number");
				}
			} else {
				System.out.println("Please try again");
			}
		}
	}
}