import java.util.Scanner;

public class PersonBuilder {

    // Method to create a person object instance. User prompts and input will populate the constructor. Person
    // type is populated automatically based on person object type.
    // Verifications are required within the while loops for each person type, data can be re-entered if user
    // does not verify the data.
    public PersonObject buildArchitect() {

        PersonObject newArchitect = null;

        Scanner userInput = new Scanner(System.in).useDelimiter("\n");

        System.out.print("\nPlease enter the architect's details:");

        while (true) {

            System.out.print("\n\nEnter name: ");
            String newName = userInput.next();

            System.out.print("Enter surname: ");
            String newSurname = userInput.next();

            System.out.print("Enter phone number: ");
            String newPhoneNumber = userInput.next();

            System.out.print("Enter email: ");
            String newEmail = userInput.next();

            System.out.print("Enter address: ");
            String newAddress = userInput.next();

            newArchitect = new PersonObject("Architect", newName, newSurname, newPhoneNumber,
                    newEmail, newAddress);

            // Person object details are output to user for verification.
            System.out.print(newArchitect.toString());

            System.out.print("\n\nPlease verify that the architect's details are correct (y/n).");
            String personVerification = userInput.next();

            if (personVerification.equals("y")) {
                break;

            } else {
                System.out.print("\nPlease re-enter architect's details:");
            }
        }

        return newArchitect;
    }

    public PersonObject buildContractor() {

        PersonObject newContractor = null;

        Scanner userInput = new Scanner(System.in).useDelimiter("\n");

        System.out.print("\nPlease enter the contractors details:");

        while (true) {

            System.out.print("\n\nEnter name: ");
            String newName = userInput.next();

            System.out.print("Enter surname: ");
            String newSurname = userInput.next();

            System.out.print("Enter phone number: ");
            String newPhoneNumber = userInput.next();

            System.out.print("Enter email: ");
            String newEmail = userInput.next();

            System.out.print("Enter address: ");
            String newAddress = userInput.next();

            newContractor = new PersonObject("Contractor", newName, newSurname, newPhoneNumber,
                    newEmail, newAddress);

            // Person object details are output to user for verification.
            System.out.print(newContractor.toString());

            System.out.print("\n\nPlease verify that the contractors details are correct (y/n).");
            String personVerification = userInput.next();

            if (personVerification.equals("y")) {
                break;

            } else {
                System.out.print("\nPlease re-enter contractors details:");
            }
        }

        return newContractor;
    }

    public PersonObject buildCustomer() {

        PersonObject newCustomer = null;

        Scanner userInput = new Scanner(System.in).useDelimiter("\n");

        System.out.print("\nPlease enter the customers details:");

        while (true) {

            System.out.print("\n\nEnter name: ");
            String newName = userInput.next();

            System.out.print("Enter surname: ");
            String newSurname = userInput.next();

            System.out.print("Enter phone number: ");
            String newPhoneNumber = userInput.next();

            System.out.print("Enter email: ");
            String newEmail = userInput.next();

            System.out.print("Enter address: ");
            String newAddress = userInput.next();

            newCustomer = new PersonObject("Customer", newName, newSurname, newPhoneNumber,
                    newEmail, newAddress);

            // Person object details are output to user for verification.
            System.out.print(newCustomer.toString());

            System.out.print("\n\nPlease verify that the customers details are correct (y/n).");
            String personVerification = userInput.next();

            if (personVerification.equals("y")) {
                break;

            } else {
                System.out.print("\nPlease re-enter customers details:");
            }
        }

        return newCustomer;
    }
}
