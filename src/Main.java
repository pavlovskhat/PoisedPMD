import java.sql.Connection;

public class Main {

    // Main method.
    public static void main(String[] args) {

        // Initializing external modules.
        MenuBuilder menu = new MenuBuilder();
        DBAccess database = new DBAccess();
        Login signIn = new Login();

        // Running methods to get connection to SQL and retrieve engineer id from 'login' table.
        Connection connection = signIn.loginConnect();
        int engineerId = signIn.programLogin(connection);

        // Introduction message.
        System.out.print("""
                    \nWelcome to the poised project management assistant.
                    Please select one of the following menu options.""");

        // Setting main method in loop to allow for multiple operations until 'quit' option is selected.
        while (true) {

            // Running main menu method.
            String choice = menu.mainMenu();

            // Selecting option 1: create new project.
            if (choice.equals("1")) {

                // Initializing project and person creator class.
                ObjectBuilder newObjectInstance = new ObjectBuilder();

                // Initializing and running project compiler and running methods to build new project,
                // new architect, contractor and customer as objects within the project compiler.
                ProjectScope newProject = new ProjectScope
                        (newObjectInstance.buildProject(),
                                newObjectInstance.buildArchitect(),
                                newObjectInstance.buildContractor(),
                                newObjectInstance.buildCustomer());

                // Running method to write all new information to relevant tables.
                database.DBWriter(connection, newProject, engineerId);
            }

            // Selecting option 2: edit existing project.
            if (choice.equals("2")) {

                ProjectEditor modifyProject = new ProjectEditor();
                modifyProject.editProject(connection);
            }

            // Selecting option 3: finalizing existing projects.
            if (choice.equals("3")) {

                ProjectEditor projectComplete = new ProjectEditor();
                projectComplete.finalizeProject(connection);
            }

            // Selecting option 4: view ongoing projects.
            if (choice.equals("4")) {
                ProjectSearchEngine searchData = new ProjectSearchEngine();
                searchData.getOngoingProjects(connection);
            }

            // Selecting option 5: view overdue projects.
            if (choice.equals("5")) {
                ProjectSearchEngine searchData = new ProjectSearchEngine();
                searchData.getOverdueProjects(connection);
            }

            // Selecting option 6: find project.
            if (choice.equals("6")) {
                ProjectSearchEngine searchData = new ProjectSearchEngine();
                searchData.searchProjectDatabase(connection);
            }

            // Selecting option 7: exit program.
            if (choice.equals("7")) {
                break;
            }
        }
    }
}


