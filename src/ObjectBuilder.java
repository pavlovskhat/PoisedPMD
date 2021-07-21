import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ObjectBuilder {

    // Method to create a person object instance. User prompts and input will populate the constructor.
    // Verifications are required within the while loop, data can be re-entered if user
    // does not verify the data.
    public ProjectObject buildProject() {

        Scanner userInput = new Scanner(System.in).useDelimiter("\n");

        // Setting variable for project fee.
        ProjectObject newProject;

        while (true) {

            // Using setter methods to enter user inputs into blank project object instance.
            System.out.print("\nEnter project number: ");
            String projectNumber = userInput.next();

            System.out.print("Enter project name: ");
            String projectName = userInput.next();

            System.out.print("Enter building type: ");
            String buildingType = userInput.next();

            System.out.print("Enter project address: ");
            String projectAddress = userInput.next();

            System.out.print("Enter ERF number: ");
            String projectErfNumber = userInput.next();

            // Running local helper method to capture project fee.
            int projectFee = projectFeeCreator();

            // Running local helper method to capture project deadline.
            String projectDeadline = dateDeadlineCreator();

            newProject = new ProjectObject(projectNumber, projectName, buildingType, projectAddress,
                    projectErfNumber, projectFee, 0, projectDeadline,
                    "ongoing", "null");

            // Project object details are output to user for verification.
            System.out.print(newProject.toString());

            System.out.print("\n\nPlease verify that the project details are correct (y/n).");
            String projectVerification = userInput.next();

            if (projectVerification.equals("y")) {
                break;

            } else {
                System.out.print("\nPlease re-enter the project details:");
            }
        }

        return newProject;
    }

    private int projectFeeCreator() {

        Scanner userInput = new Scanner(System.in).useDelimiter("\n");

        // Setting variable for project fee.
        int newProjectFee;

        // Try catch loop to check that project fee is an integer before adding to project object.
        while(true) {
            try {
                System.out.print("Enter project fee: ");
                newProjectFee = Integer.parseInt(userInput.next());
                break;

            } catch (Exception error) {
                System.out.println("You have not entered a numeric value for project fee.");
            }
        }

        return newProjectFee;
    }

    // Method for creating project deadline in a valid date format.
    // Researched the code used below from here:
    // https://www.javatpoint.com/how-to-compare-dates-in-java
    private String dateDeadlineCreator() {

        Scanner userInput = new Scanner(System.in).useDelimiter("\n");

        // Initializing deadline and date format as variables.
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String deadline;

        // Try catch loop to check that date is in format that can be parsed.
        while(true) {
            try {
                System.out.print("Enter deadline date in YYYY-MM-DD format: ");
                Date dateDeadline = dateFormat.parse(userInput.next());
                deadline = dateFormat.format(dateDeadline);
                break;

            } catch (ParseException error) {
                System.out.print("\nYou did not enter the date in the correct format.");
            }
        }

        return deadline;
    }

    // Method to create a person object instance. User prompts and input will populate the constructor. Person
    // type is populated automatically based on person object type.
    // Verifications are required within the while loops for each person type, data can be re-entered if user
    // does not verify the data.
    public PersonObject buildArchitect() {

        PersonObject newArchitect;

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

        PersonObject newContractor;

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

        PersonObject newCustomer;

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
