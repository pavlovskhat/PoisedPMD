import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {

    // Main method.
    public static void main(String[] args) {

        // Initializing class to access files.
        FileAccess accessFile = new FileAccess();
        DataBuilder buildData = new DataBuilder();
        String projectFile = "projectInfo.ser";
        Scanner userInput = new Scanner(System.in).useDelimiter("\n");

        // Using method to recall previously saved project data as list.
        List<ProjectScope> projectData = accessFile.fileReader(projectFile);

        // Initializing external modules.
        MenuBuilder menu = new MenuBuilder();

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
                ProjectBuilder newProjectInstance = new ProjectBuilder();
                PersonBuilder newPersonInstance = new PersonBuilder();

                // Initializing and running project compiler and running methods to build new project,
                // new architect, contractor and customer as objects within the project compiler.
                ProjectScope newProject = new ProjectScope
                        (newProjectInstance.buildProject(),
                                newPersonInstance.buildArchitect(),
                                newPersonInstance.buildContractor(),
                                newPersonInstance.buildCustomer());

                // Adding newly created project data to project list.
                projectData.add(newProject);

                // Writing modified list back to file.
                accessFile.fileWriter(projectData, projectFile);
            }

            // Selecting option 2: edit existing project.
            if (choice.equals("2")) {

                ProjectEditor modifyProject = new ProjectEditor();
                modifyProject.editProject(projectFile);
            }

            // Selecting option 3: finalizing existing projects.
            if (choice.equals("3")) {

                ProjectFinalizer projectComplete = new ProjectFinalizer();
                projectComplete.finalizeProject(projectFile);
            }

            // Selecting option 4: view ongoing projects.
            if (choice.equals("4")) {
                buildData.getOngoingProjects(projectFile);
            }

            // Selecting option 5: view overdue projects.
            if (choice.equals("5")) {
                buildData.getOverdueProjects(projectFile);
            }

            // Selecting option 6: find project.
            if (choice.equals("6")) {

                ProjectSearchEngine search = new ProjectSearchEngine();
                search.searchProjectDatabase(projectFile);
            }

            // Selecting option 7: exit program.
            if (choice.equals("7")) {
                break;
            }
        }
    }
}


