import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProjectSearchEngine {

    // Method to search the entire project database with specific project details as key words.
    public void searchProjectDatabase(String filePath) {

        // Initializing relevant classes.
        Scanner userInput = new Scanner(System.in);
        FileAccess accessFile = new FileAccess();
        DataBuilder buildData = new DataBuilder();

        // Using method to recall previously saved project data as list.
        List<ProjectScope> projectData = accessFile.fileReader(filePath);

        // Creating blank list that will store search results.
        List<ProjectScope> projectSearchResult = new ArrayList<>();

        // Output of search parameters to user.
        System.out.print("\nProject database can be searched with one of the following project details:" +
                "i) Project Number" +
                "ii) Project Name" +
                "iii) Project Address" +
                "iv) Project ERF Number" +
                "v) Customer Name" +
                "vi) Customer Surname");

        System.out.print("\nEnter project detail to search project database for: ");
        String projectDetail = userInput.next();  // Capturing user keyword.

        // Iterating through the project database and saving the parameter keywords as variables.
        for (ProjectScope project : projectData) {
            String projectName = project.getProject().getProjectName();
            String projectNumber = project.getProject().getProjectNumber();
            String projectAddress = project.getProject().getProjectAddress();
            String erfNumber = project.getProject().getErfNumber();
            String customerName = project.getCustomer().getName();
            String customerSurname = project.getCustomer().getSurname();

            // User keyword is then cross checked with each variable for a match.
            // Case is ignored to allow for less user error.
            if (projectDetail.equalsIgnoreCase(projectName) ||
                    projectDetail.equalsIgnoreCase(projectNumber) ||
                    projectDetail.equalsIgnoreCase(projectAddress) ||
                    projectDetail.equalsIgnoreCase(erfNumber) ||
                    projectDetail.equalsIgnoreCase(customerName) ||
                    projectDetail.equalsIgnoreCase(customerSurname)) {

                projectSearchResult.add(project);  // Any matches are stored to empty list.

            } else {
                System.out.print("No projects matched the provided detail.");
            }
        }

        // List is output to show user all search results.
        System.out.print("Search results:\n");
        buildData.outputAllProjectData(projectSearchResult);
    }
}

