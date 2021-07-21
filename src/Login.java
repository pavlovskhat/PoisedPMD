import java.sql.*;
import java.util.Scanner;

public class Login {

    public int programLogin(Connection connection) {

        Scanner userInput = new Scanner(System.in).useDelimiter("\n");
        int engineerId = 0;

        // Try catch block to connect to mySQL server database 'PoisePMS'.
        try {

            boolean login = false;

            while (true) {

                System.out.print("\nEnter username: ");
                String username = userInput.next();

                System.out.print("Enter password: ");
                String password = userInput.next();

                // Create a direct line to the database for running our queries
                Statement statement = connection.createStatement();
                ResultSet results;  // Initializing ResultSet as variable.

                results = statement.executeQuery("Select * from login");

                while (results.next()) {

                    if (username.equals(results.getString(2)) &&
                            password.equals(results.getString(3))) {

                        login = true;
                    }
                }

                results = statement.executeQuery("select eng_id from login where username = '" + username + "'");

                while (results.next()) {
                    engineerId = results.getInt(1);
                }

                if (login) {
                   break;

                } else {
                    System.out.print("\nUsername or password incorrect, please try again.");
                }
            }
        } catch(SQLException error) {

        System.out.print("Error while trying to access SQL database.");
        }

        return engineerId;
    }

    public Connection loginConnect() {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/PoisePMS?useSSL=false",
                    "otheruser",
                    "Husky420!"
            );

        } catch (SQLException error) {

            System.out.print("Error while trying to access SQL database.");
        }

        return connection;
    }
}
