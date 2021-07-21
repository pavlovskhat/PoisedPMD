import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ProjectSearchEngine {

    // Method to search the project database with specific project details as key words.
    public void searchProjectDatabase(Connection connection) {

        // Initializing relevant classes.
        Scanner userInput = new Scanner(System.in).useDelimiter("\n");

        // Output of search parameters to user.
        System.out.print("\nProject database can be searched with one of the following project details:" +
                "i) Project Number" +
                "ii) Project Name");

        System.out.print("\nEnter project detail to search project database for: ");
        String projectDetail = userInput.next();  // Capturing user keyword.

        // Creating instance of project number removing the non digit "PP" code the user will have on his record.
        String projectNo = projectDetail.replaceAll("[^\\d]", "");
        projectNo = projectNo.trim();

        // Converting number to integer after removing any white space.
        int projectNumber = Integer.parseInt(projectNo);

        try {

            // Researched how to have multiple inner joins here:
            // https://stackoverflow.com/questions/10195451/sql-inner-join-with-3-tables

            // Initializing java SQL statement.
            Statement statement = connection.createStatement();

            // Sending SQL command to database.
            ResultSet foundProject = statement.executeQuery("select project_overview.eng_id, " +
                    "projects.project_no, " +
                    "projects.project_name, " +
                    "projects.building_type, " +
                    "projects.project_address, " +
                    "projects.erf_number, " +
                    "projects.project_fee, " +
                    "projects.paid_to_date, " +
                    "projects.project_deadline, " +
                    "architects.architect_id, " +
                    "architects.name, " +
                    "architects.surname, " +
                    "architects.phone_no, " +
                    "architects.email, " +
                    "architects.address, " +
                    "contractors.contractor_id, " +
                    "contractors.name, " +
                    "contractors.surname, " +
                    "contractors.phone_no, " +
                    "contractors.email, " +
                    "contractors.address, " +
                    "customers.customer_id, " +
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
                    "projects.project_no = " + projectNumber + " or " +
                    "projects.project_name = '" + projectDetail + "'");

            // Iterate through SQL query and output all information to user.
            while (foundProject.next()) {

                System.out.println("\nEngineer Id: " + foundProject.getInt(1));
                System.out.println("Project No: PP" + foundProject.getString(2));
                System.out.println("Project Name: " + foundProject.getString(3));
                System.out.println("Building Type: " + foundProject.getString(4));
                System.out.println("Project Address: " + foundProject.getString(5));
                System.out.println("ERF Number: " + foundProject.getString(6));
                System.out.println("Project Fee: R" + foundProject.getInt(7));
                System.out.println("Paid To Date: R" + foundProject.getInt(8));
                System.out.println("Project Deadline: " + foundProject.getString(9));
                System.out.println("Architect Id: A" + foundProject.getString(10));
                System.out.println("Architect Name: " + foundProject.getString(11));
                System.out.println("Architect Surname: " + foundProject.getString(12));
                System.out.println("Architect Phone No: " + foundProject.getString(13));
                System.out.println("Architect Email: " + foundProject.getString(14));
                System.out.println("Architect Address: " + foundProject.getString(15));
                System.out.println("Contractor Id: CO" + foundProject.getString(16));
                System.out.println("Contractor Name: " + foundProject.getString(17));
                System.out.println("Contractor Surname: " + foundProject.getString(18));
                System.out.println("Contractor Phone No: " + foundProject.getString(19));
                System.out.println("Contractor Email: " + foundProject.getString(20));
                System.out.println("Contractor Address: " + foundProject.getString(21));
                System.out.println("Customer Id: CU" + foundProject.getString(22));
                System.out.println("Customer Name: " + foundProject.getString(23));
                System.out.println("Customer Surname: " + foundProject.getString(24));
                System.out.println("Customer Phone No: " + foundProject.getString(25));
                System.out.println("Customer Email: " + foundProject.getString(26));
                System.out.println("Customer Address: " + foundProject.getString(27));
            }

        } catch (SQLException error) {
            System.out.print("Error while trying to access SQL database.");
        }
    }

    // Method to output all ongoing projects from database.
    public void getOngoingProjects(Connection connection) {

        try {

            // Initializing java SQL statement.
            Statement statement = connection.createStatement();

            // Sending SQL command to database.
            ResultSet ongoingProjects = statement.executeQuery("""
                    select project_overview.eng_id,
                    projects.project_no,
                    projects.project_name,
                    projects.building_type,
                    projects.project_address,
                    projects.erf_number,
                    projects.project_fee,
                    projects.paid_to_date,
                    projects.project_deadline,
                    architects.architect_id,
                    architects.name,
                    architects.surname,
                    architects.phone_no,
                    architects.email,
                    architects.address,
                    contractors.contractor_id,
                    contractors.name,
                    contractors.surname,
                    contractors.phone_no,
                    contractors.email,
                    contractors.address,
                    customers.customer_id,
                    customers.name,
                    customers.surname,
                    customers.phone_no,
                    customers.email,
                    customers.address
                    from project_overview
                    inner join architects
                    on architects.architect_id = project_overview.architect_id
                    inner join contractors
                    on contractors.contractor_id = project_overview.contractor_id
                    inner join customers
                    on customers.customer_id = project_overview.customer_id
                    inner join projects
                    on projects.project_no = project_overview.project_no
                    where projects.project_status = 'ongoing';"""
            );

            // Iterate through SQL query and output all information to user.
            while (ongoingProjects.next()) {

                System.out.println("\nEngineer Id: " + ongoingProjects.getInt(1));
                System.out.println("Project No: PP" + ongoingProjects.getString(2));
                System.out.println("Project Name: " + ongoingProjects.getString(3));
                System.out.println("Building Type: " + ongoingProjects.getString(4));
                System.out.println("Project Address: " + ongoingProjects.getString(5));
                System.out.println("ERF Number: " + ongoingProjects.getString(6));
                System.out.println("Project Fee: R" + ongoingProjects.getInt(7));
                System.out.println("Paid To Date: R" + ongoingProjects.getInt(8));
                System.out.println("Project Deadline: " + ongoingProjects.getString(9));
                System.out.println("Architect Id: A" + ongoingProjects.getString(10));
                System.out.println("Architect Name: " + ongoingProjects.getString(11));
                System.out.println("Architect Surname: " + ongoingProjects.getString(12));
                System.out.println("Architect Phone No: " + ongoingProjects.getString(13));
                System.out.println("Architect Email: " + ongoingProjects.getString(14));
                System.out.println("Architect Address: " + ongoingProjects.getString(15));
                System.out.println("Contractor Id: CO" + ongoingProjects.getString(16));
                System.out.println("Contractor Name: " + ongoingProjects.getString(17));
                System.out.println("Contractor Surname: " + ongoingProjects.getString(18));
                System.out.println("Contractor Phone No: " + ongoingProjects.getString(19));
                System.out.println("Contractor Email: " + ongoingProjects.getString(20));
                System.out.println("Contractor Address: " + ongoingProjects.getString(21));
                System.out.println("Customer Id: CU" + ongoingProjects.getString(22));
                System.out.println("Customer Name: " + ongoingProjects.getString(23));
                System.out.println("Customer Surname: " + ongoingProjects.getString(24));
                System.out.println("Customer Phone No: " + ongoingProjects.getString(25));
                System.out.println("Customer Email: " + ongoingProjects.getString(26));
                System.out.println("Customer Address: " + ongoingProjects.getString(27));
            }

        } catch (SQLException error) {
            System.out.print("Error while trying to access SQL database.");
        }
    }

    // Method to output all overdue projects from database.
    public void getOverdueProjects(Connection connection) {

        try {

            // Initializing java SQL statement.
            Statement statement = connection.createStatement();

            // Sending SQL command to database.
            ResultSet overdueProjects = statement.executeQuery("""
                    select project_overview.eng_id,
                    projects.project_no,
                    projects.project_name,
                    projects.building_type,
                    projects.project_address,
                    projects.erf_number,
                    projects.project_fee,
                    projects.paid_to_date,
                    projects.project_deadline,
                    architects.architect_id,
                    architects.name,
                    architects.surname,
                    architects.phone_no,
                    architects.email,
                    architects.address,
                    contractors.contractor_id,
                    contractors.name,
                    contractors.surname,
                    contractors.phone_no,
                    contractors.email,
                    contractors.address,
                    customers.customer_id,
                    customers.name,
                    customers.surname,
                    customers.phone_no,
                    customers.email,
                    customers.address
                    from project_overview
                    inner join architects
                    on architects.architect_id = project_overview.architect_id
                    inner join contractors
                    on contractors.contractor_id = project_overview.contractor_id
                    inner join customers
                    on customers.customer_id = project_overview.customer_id
                    inner join projects
                    on projects.project_no = project_overview.project_no;"""
            );

            // Iterate through SQL query.
            while (overdueProjects.next()) {

                // Initializing preferred data formatting.
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Setting date formatting.

                // Initializing date variables.
                Date deadlineDate = null;
                Date todayDate = new Date();

                String projectDeadline = overdueProjects.getString(9);

                // Isolates the deadline date and tries to format to date format.
                try {
                    deadlineDate = dateFormat.parse(projectDeadline);

                } catch (ParseException error) {
                    System.out.print("\nError while reading project deadline.");
                }

                // Output all relevant information to user.
                if (deadlineDate.compareTo(todayDate) < 0) {

                    System.out.println("\nEngineer Id: " + overdueProjects.getInt(1));
                    System.out.println("Project No: PP" + overdueProjects.getString(2));
                    System.out.println("Project Name: " + overdueProjects.getString(3));
                    System.out.println("Building Type: " + overdueProjects.getString(4));
                    System.out.println("Project Address: " + overdueProjects.getString(5));
                    System.out.println("ERF Number: " + overdueProjects.getString(6));
                    System.out.println("Project Fee: R" + overdueProjects.getInt(7));
                    System.out.println("Paid To Date: R" + overdueProjects.getInt(8));
                    System.out.println("Project Deadline: " + projectDeadline);
                    System.out.println("Architect Id: A" + overdueProjects.getString(10));
                    System.out.println("Architect Name: " + overdueProjects.getString(11));
                    System.out.println("Architect Surname: " + overdueProjects.getString(12));
                    System.out.println("Architect Phone No: " + overdueProjects.getString(13));
                    System.out.println("Architect Email: " + overdueProjects.getString(14));
                    System.out.println("Architect Address: " + overdueProjects.getString(15));
                    System.out.println("Contractor Id: CO" + overdueProjects.getString(16));
                    System.out.println("Contractor Name: " + overdueProjects.getString(17));
                    System.out.println("Contractor Surname: " + overdueProjects.getString(18));
                    System.out.println("Contractor Phone No: " + overdueProjects.getString(19));
                    System.out.println("Contractor Email: " + overdueProjects.getString(20));
                    System.out.println("Contractor Address: " + overdueProjects.getString(21));
                    System.out.println("Customer Id: CU" + overdueProjects.getString(22));
                    System.out.println("Customer Name: " + overdueProjects.getString(23));
                    System.out.println("Customer Surname: " + overdueProjects.getString(24));
                    System.out.println("Customer Phone No: " + overdueProjects.getString(25));
                    System.out.println("Customer Email: " + overdueProjects.getString(26));
                    System.out.println("Customer Address: " + overdueProjects.getString(27));
                }
            }

        } catch (SQLException error) {
            System.out.print("Error while trying to access SQL database.");
        }
    }
}

