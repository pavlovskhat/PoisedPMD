import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class ProjectEditor {

    // Method to edit a specific project.
    public void editProject(Connection connection) {

        MenuBuilder menu = new MenuBuilder();
        Scanner userInput = new Scanner(System.in).useDelimiter("\n");

        String editChoice = menu.projectEditMenu();

        System.out.print("\nEnter project number to edit: ");
        String projectDetail = userInput.next();  // Capturing user keyword.

        // Creating instance of project number removing the non digit "PP" code the user will have on his record.
        String projectNo = projectDetail.replaceAll("[^\\d]", "");
        projectNo = projectNo.trim();

        // Converting number to integer after removing any white space.
        int projectNumber = Integer.parseInt(projectNo);

        // If user selects to edit deadline date.
        switch (editChoice) {
            case "1" -> {

                // Initializing deadline and date format as variables.
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String newDeadline;

                // Try catch loop to check that date is in format that can be parsed.
                while (true) {
                    try {
                        System.out.print("Enter deadline date in YYYY-MM-DD format: ");
                        Date dateDeadline = dateFormat.parse(userInput.next());
                        newDeadline = dateFormat.format(dateDeadline);
                        break;

                    } catch (ParseException error) {
                        System.out.print("\nYou did not enter the date in the correct format.");
                    }
                }

                try {
                    // SQL statement to update deadline on database.
                    PreparedStatement updateProject = connection.prepareStatement(
                            "update projects set project_deadline = ? where project_no = ?"
                    );

                    // Adding values to prepared statement.
                    updateProject.setString(1, newDeadline);
                    updateProject.setInt(2, projectNumber);

                } catch (SQLException error) {
                    System.out.print("Error while trying to access SQL database.");
                }
            }

            // If user selects to update total paid to date by customer.
            case "2" -> {

                int newPaidToDate;

                // Try catch loop to check that paid to date is an integer before adding to project object.
                while(true) {
                    try {
                        System.out.print("\nEnter total amount customer has paid to date: ");
                        newPaidToDate = Integer.parseInt(userInput.next());
                        break;

                    } catch (Exception error) {
                        System.out.println("You have not entered a numeric value for paid to date.");
                    }
                }

                try {
                    // SQL statement to update paid to date on database.
                    PreparedStatement updateProject = connection.prepareStatement(
                            "update projects set paid_to_date = ? where project_no = ?"
                    );

                    // Adding values to prepared statement.
                    updateProject.setInt(1, newPaidToDate);
                    updateProject.setInt(2, projectNumber);

                } catch (SQLException error) {
                    System.out.print("Error while trying to access SQL database.");
                }
            }

            // If user selects to update contactor's contact details.
            case "3" -> {

                // Input / output to ask for new details and then capturing input details to variables.
                System.out.print("\nUpdate contractor phone number:");
                String newNumber = userInput.next();
                System.out.print("\nUpdate contractor email:");
                String newEmail = userInput.next();

                try {
                    // SQL statement to update paid to date on database.
                    PreparedStatement updateProject = connection.prepareStatement(
                            """
                                   update contractors
                                   inner join project_overview
                                   on contractors.contractor_id = project_overview.contractor_id
                                   inner join projects
                                   on projects.project_no = project_overview.project_no
                                   set contractors.phone_no = ?,
                                   contractors.email = ?
                                   where projects.project_no = ?
                                   """
                    );

                    // Adding values to prepared statement.
                    updateProject.setString(1, newNumber);
                    updateProject.setString(2, newEmail);
                    updateProject.setInt(3, projectNumber);

                } catch (SQLException error) {
                    System.out.print("Error while trying to access SQL database.");
                }
            }
        }
    }

    // Method to generate an invoice and finalize the project.
    public void finalizeProject(Connection connection) {

        // Initializing relevant classes.
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Setting date formatting.
        Date todayDate = new Date();  // Initializing an instance of current date.
        Scanner userInput = new Scanner(System.in).useDelimiter("\n");

        // Creating relevant variables.
        int projectBalance = 0;
        String newStatus = "Completed";
        String today = dateFormat.format(todayDate);  // Formatting current date to String in preferred format.

        System.out.print("\nEnter project number to edit: ");
        String projectDetail = userInput.next();  // Capturing user keyword.

        // Creating instance of project number removing the non digit "PP" code the user will have on his record.
        String projectNo = projectDetail.replaceAll("[^\\d]", "");
        projectNo = projectNo.trim();

        // Converting number to integer after removing any white space.
        int projectNumber = Integer.parseInt(projectNo);

        try {

            // Initializing java SQL statement.
            Statement statement = connection.createStatement();

            // SQL statement to update deadline on database.
            PreparedStatement updateProject = connection.prepareStatement(
                    "update projects set project_status = ?, completion_date = ? where project_no = ?"
            );

            // Adding values to prepared statement.
            updateProject.setString(1, newStatus);
            updateProject.setString(2, today);
            updateProject.setInt(3, projectNumber);

            ResultSet results = statement.executeQuery("select project_fee, paid_to_date from projects " +
                    "where project_no = " + projectNo);

            while (results.next()) {
                projectBalance = ((results.getInt(1)) - (results.getInt(2)));
            }

        } catch (SQLException error) {
            System.out.print("Error while trying to access SQL database.");
        }

        // Generates an invoice String if there is a balance on the project.
        if (projectBalance > 0) {

            try {

                // Initializing java SQL statement.
                Statement statement = connection.createStatement();

                // Sending SQL command to database.
                ResultSet foundProject = statement.executeQuery("select " +
                        "projects.project_no, " +
                        "projects.project_name, " +
                        "projects.project_fee, " +
                        "projects.paid_to_date, " +
                        "customers.name, " +
                        "customers.surname, " +
                        "customers.phone_no, " +
                        "customers.email, " +
                        "customers.address " +
                        "from project_overview " +
                        "inner join architects " +
                        "on architects.architect_id = project_overview.architect_id " +
                        "inner join contractors " +
                        "on contractors.contractor_id = project_overview.contractor_id " +
                        "inner join customers " +
                        "on customers.customer_id = project_overview.customer_id " +
                        "inner join projects " +
                        "on projects.project_no = project_overview.project_no " +
                        "where " +
                        "projects.project_no = " + projectNumber);

                // Iterate through SQL query and output all information to user.
                while (foundProject.next()) {

                    System.out.println("\nPOISED PROJECT INVOICE");
                    System.out.println("\nProject No: PP" + projectNumber);
                    System.out.println("Project Name: " + foundProject.getString(2));
                    System.out.println("Customer Name: " + foundProject.getString(5));
                    System.out.println("Customer Surname: " + foundProject.getString(6));
                    System.out.println("Customer Phone No: " + foundProject.getString(7));
                    System.out.println("Customer Email: " + foundProject.getString(8));
                    System.out.println("Customer Address: " + foundProject.getString(9));
                    System.out.println("Project Fee: R" + foundProject.getInt(3));
                    System.out.println("Paid To Date: R" + foundProject.getInt(4));
                    System.out.println("Project Balance: R" + projectBalance);

                }

            } catch (SQLException error) {
                System.out.print("Error while trying to access SQL database.");
            }

        } else {
            System.out.print("The project fee has been settled in full.");

        }
    }
}
