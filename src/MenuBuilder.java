import java.util.Scanner;

public class MenuBuilder {

    Scanner read = new Scanner(System.in);

    // Main menu method.
    public String mainMenu() {

        // Initializing choice String variable.
        String choice;

        // Running menu method in loop for the case of using incorrect menu option.
        // Selected menu option number is returned from method as String.
        while (true) {
            System.out.print(
                    "\n\n1) Create new project" +
                            "\n2) Edit existing project" +
                            "\n3) Finalize existing project" +
                            "\n4) View ongoing projects" +
                            "\n5) View overdue projects" +
                            "\n6) Find project" +
                            "\n7) Exit");

            System.out.print("\nChoose menu number: ");
            String userInput = read.next();

            if (userInput.equals("7")) {

                choice = userInput;
                break;

            } else if (userInput.equals("1") || userInput.equals("2") || userInput.equals("3") || userInput.equals("4")
                    || userInput.equals("5") || userInput.equals("6")) {

                choice = userInput;
                break;

            }else {
                System.out.print("\nYou have not entered a valid menu option.");
            }
        }

        return choice;
    }

    // Edit menu method.
    public String projectEditMenu() {

        // Initializing choice variable.
        String choice;

        // Running menu method in loop for the case of using incorrect menu option.
        // Selected menu option number is returned from method as String.
        while(true) {

            System.out.print("\nPlease select one of the following options:" +
                    "\n\n1) Change project deadline date" +
                    "\n2) Update total customer has paid to date" +
                    "\n3) Update contractor's contact details");

            System.out.print("\nChoose menu number: ");
            String userInput = read.next();

            if (userInput.equals("1") || userInput.equals("2") || userInput.equals("3")) {

                choice = userInput;
                break;

            }else {
                System.out.print("\nYou have not entered a valid menu option.");
            }
        }

        return choice;
    }
}