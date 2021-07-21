import java.sql.*;

public class DBAccess {

    public void DBWriter(Connection connection, ProjectScope fullProject, int engineerId) {

        try {

            // Researched auto increment function for SQL here:
            // https://www.w3schools.com/mysql/mysql_autoincrement.asp
            // Researched how to return a created auto increment number here:
            // https://www.javaquery.com/2011/09/how-to-get-value-of-auto-increment.html


            // Initializing java SQL statement.
            Statement statement = connection.createStatement();

            // Setting variables for auto generating object id's.
            int projectNo = 0;
            int architectId = 0;
            int contractorId = 0;
            int customerId = 0;

            // Adding new project object instance to 'projects' table.
            statement.execute("insert into projects (project_name, building_type, project_address, " +
                    "erf_number, project_fee, paid_to_date, project_deadline, project_status, completion_date) " +
                    "values ('" + fullProject.getProject().getProjectName() +
                    "', '" + fullProject.getProject().getBuildingType() +
                    "', '" + fullProject.getProject().getProjectAddress() +
                    "', '" + fullProject.getProject().getErfNumber() +
                    "', " + fullProject.getProject().getProjectFee() +
                    ", " + fullProject.getProject().getPaidToDate() +
                    ", '" + fullProject.getProject().getDateDeadline() +
                    "', '" + fullProject.getProject().getProjectStatus() +
                    "', '" + fullProject.getProject().getCompleteDate() +
                    "')", Statement.RETURN_GENERATED_KEYS);

            // Returning auto generated project number from SQL.
            ResultSet projectKey = statement.getGeneratedKeys();
            if (projectKey.next()) {
                projectNo = projectKey.getInt(1);
                System.out.print("\nProject number: PP" + projectNo);  // Output new project number to user.
            }

            // Checking if assigned architect exists in 'architects' table.
            ResultSet newArchitect = statement.executeQuery("Select * from architects where name = '" +
                    fullProject.getArchitect().getName() + "' and surname = '" +
                    fullProject.getArchitect().getSurname() + "'");

            if (!newArchitect.next()) {

                // Adding architect if it does not already exist.
                statement.execute("insert into architects (name, surname, phone_no, email, address) " +
                        "values ('" + fullProject.getArchitect().getName() + "', '" +
                        fullProject.getArchitect().getSurname() + "', '" +
                        fullProject.getArchitect().getPhoneNumber() + "', '" +
                        fullProject.getArchitect().getEmail() + "', '" +
                        fullProject.getArchitect().getAddress() + "')",
                        Statement.RETURN_GENERATED_KEYS);

                // Returning new auto generated key from SQL to user.
                ResultSet architectKey = statement.getGeneratedKeys();
                if (architectKey.next()) {
                    architectId = architectKey.getInt(1);
                    System.out.print("\nNew architect Id: A" + architectId);
                }

            } else {
                // If architect object exists the id is returned from SQL.
                ResultSet architectKey = statement.executeQuery("select architect_id from login where name = '" +
                        fullProject.getArchitect().getName() + "' and surname = '" +
                        fullProject.getArchitect().getSurname());

                while (architectKey.next()) {
                    engineerId = architectKey.getInt(1);
                }
            }

            // Checking if assigned contractor exists in 'contractors' table.
            ResultSet newContractor = statement.executeQuery("Select * from contractors where name = '" +
                    fullProject.getContractor().getName() + "' and surname = '" +
                    fullProject.getContractor().getSurname() + "'");

            if (!newContractor.next()) {

                // Adding contractor if it does not already exist.
                statement.execute("insert into contractors (name, surname, phone_no, email, address) " +
                                "values ('" + fullProject.getContractor().getName() + "', '" +
                                fullProject.getContractor().getSurname() + "', '" +
                                fullProject.getContractor().getPhoneNumber() + "', '" +
                                fullProject.getContractor().getEmail() + "', '" +
                                fullProject.getContractor().getAddress() + "')",
                                Statement.RETURN_GENERATED_KEYS);

                // Returning new auto generated key from SQL to user.
                ResultSet contractorKey = statement.getGeneratedKeys();
                if (contractorKey.next()) {
                    contractorId = contractorKey.getInt(1);
                    System.out.print("\nNew contractor Id: CO" + contractorId);
                }

            } else {
                // If contractor object exists the id is returned from SQL.
                ResultSet contractorKey = statement.executeQuery("select architect_id from login where name = '" +
                        fullProject.getArchitect().getName() + "' and surname = '" +
                        fullProject.getArchitect().getSurname());

                while (contractorKey.next()) {
                    engineerId = contractorKey.getInt(1);
                }
            }

            // Checking if assigned customer exists in 'customers' table.
            ResultSet newCustomer = statement.executeQuery("Select * from customers where name = '" +
                    fullProject.getCustomer().getName() + "' and surname = '" +
                    fullProject.getCustomer().getSurname() + "'");

            if (!newCustomer.next()) {

                // Adding customer if it does not already exist.
                statement.execute("insert into customers (name, surname, phone_no, email, address) " +
                                "values ('" + fullProject.getCustomer().getName() + "', '" +
                                fullProject.getCustomer().getSurname() + "', '" +
                                fullProject.getCustomer().getPhoneNumber() + "', '" +
                                fullProject.getCustomer().getEmail() + "', '" +
                                fullProject.getCustomer().getAddress() + "')",
                                Statement.RETURN_GENERATED_KEYS);

                // Returning new auto generated key from SQL to user.
                ResultSet customerKey = statement.getGeneratedKeys();
                if (customerKey.next()) {
                    customerId = customerKey.getInt(1);
                    System.out.print("\nNew customer Id: CU" + customerId);
                }

            } else {
                // If customer object exists the id is returned from SQL.
                statement.execute("Select * from contractors where name = '" +
                        fullProject.getCustomer().getName() + "' and surname = '" +
                        fullProject.getCustomer().getSurname() + "'", Statement.RETURN_GENERATED_KEYS);

                ResultSet customerKey = statement.getGeneratedKeys();
                if (customerKey.next()) {
                    customerId = customerKey.getInt(1);
                }
            }

            // Adding object id's to 'project_overview' table with the logged in engineer id.
            statement.execute("insert into project_overview values (" +
                    projectNo + ", " +
                    engineerId + ", " +
                    architectId + ", " +
                    contractorId + ", " +
                    customerId + ")");

        } catch (SQLException error) {

            System.out.print("\nError while trying to access SQL database.");
            error.printStackTrace();
        }
    }
}
