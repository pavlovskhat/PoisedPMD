import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ProjectEditor {

    // Method to edit a specific project.
    public void editProject(String filePath) {

        // Initializing relevant classes.
        MenuBuilder menu = new MenuBuilder();
        FileAccess accessFile = new FileAccess();

        // Calling all project data as list from file.
        List<ProjectScope> projectData = accessFile.fileReader(filePath);

        // Running method to find and return a project to edit.
        List <ProjectScope> projectToEdit = extractProject(projectData);

        // Running edit menu method.
        String editChoice = menu.projectEditMenu();

        ProjectScope fullProject = projectToEdit.get(0);
        ProjectObject project = fullProject.getProject();
        PersonObject contractor = fullProject.getContractor();

        // Running local helper method to edit the project details based on user input.
        getEditCommand(editChoice, project, contractor);

        projectData.add(fullProject);

        // Writing list with modified internal objects back to file.
        accessFile.fileWriter(projectData, filePath);
    }

    // Helper method to verify what part of the project the user wants to edit.
    // After command is recognized project data is modified as required and the details are output
    // afterwards for user to check the changes that have been made.
    private void getEditCommand(String editChoice, ProjectObject project, PersonObject contractor) {

        Scanner userInput = new Scanner(System.in).useDelimiter("\n");

        // If user selects to edit deadline date.
        switch (editChoice) {
            case "1" -> {

                // Initializing deadline and date format as variables.
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String newDeadline = null;

                // Try catch loop to check that date is in format that can be parsed.
                while(true) {
                    try {
                        System.out.print("Enter deadline date in YYYY-MM-DD format: ");
                        Date dateDeadline = dateFormat.parse(userInput.next());
                        newDeadline = dateFormat.format(dateDeadline);
                        break;

                    } catch (ParseException error) {
                        System.out.print("\nYou did not enter the date in the correct format.");
                    }
                }

                // Amending new information to pre created 'newProject'.
                project.setDateDeadline(newDeadline);
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

                // Amending new information to pre created 'newProject'.
                project.setPaidToDate(newPaidToDate);
            }

            // If user selects to update contactor's contact details.
            case "3" -> {

                // Input / output to ask for new details and then capturing input details to variables.
                System.out.print("\nUpdate contractor phone number:");
                String newNumber = userInput.next();
                System.out.print("\nUpdate contractor email:");
                String newEmail = userInput.next();

                // Amending new information to pre created 'newProject'.
                contractor.setPhoneNumber(newNumber);
                contractor.setEmail(newEmail);
            }
        }

        // Returns an overview of the project as String with the changes made.
        System.out.print(project.toString() +
                "\n" + contractor.toString());
    }

    // Method to find a specific project based on project name or number.
    public List extractProject(List projectData) {

        // Initializing relevant classes.
        Scanner userInput = new Scanner(System.in).useDelimiter("\n");
        DataBuilder buildData = new DataBuilder();

        // New list created for searched project.
        List<ProjectScope> projectFound = new ArrayList<>();

        System.out.print("\nEnter name or number of project to edit: ");
        String projectChoice = userInput.next();  // Capturing user input.

        // Iterates over projects list.
        Iterator<ProjectScope> iterateCompiler = projectData.iterator();
        while (iterateCompiler.hasNext()) {
            ProjectScope project = iterateCompiler.next();

            // When user input matches relevant project detail the project is removed from the
            // original list and added to a new list for editing.
            if (project.getProject().getProjectName().equalsIgnoreCase(projectChoice)
            || project.getProject().getProjectNumber().equalsIgnoreCase(projectChoice)) {
                iterateCompiler.remove();
                projectFound.add(project);
            }
        }

        // Found project details is output to user for visual confirmation.
        buildData.outputAllProjectData(projectFound);

        // Found project is returned from method.
        return projectFound;
    }
}
