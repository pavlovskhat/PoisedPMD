import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataBuilder {

    // Method that builds a data string of all the object toString methods from a provided list.
    public void outputAllProjectData(List projectData) {

        // Iterates through provided list and a detailed output of each object is provided to user.
        Iterator<ProjectScope> iterateCompiler = projectData.iterator();
        while (iterateCompiler.hasNext()) {
            ProjectScope project = iterateCompiler.next();
            System.out.print(project.getProject().toString() + "\n"
            + project.getArchitect().toString() + "\n"
            + project.getContractor().toString() + "\n"
            + project.getCustomer().toString() + "\n"
            + "*************************************************************" + "\n");
        }
    }

    // Method to compile a data list of all projects that are ongoing.
    public void getOngoingProjects(String filePath) {

        // Initializing relevant class.
        FileAccess accessFile = new FileAccess();

        // Using method to recall previously saved project data as list.
        List<ProjectScope> projectData = accessFile.fileReader(filePath);

        // Creating empty list to store ongoing projects.
        List<ProjectScope> ongoingProjects = new ArrayList<>();

        // Iterates through list and isolates the project status as a variable.
        Iterator<ProjectScope> iterateCompiler = projectData.iterator();
        while (iterateCompiler.hasNext()) {
            ProjectScope project = iterateCompiler.next();
            String projectStatus = project.getProject().getProjectStatus();

            // If variable is equal to ongoing project is added to new list.
            if (projectStatus.equals("ongoing")) {
                ongoingProjects.add(project);
            }
        }

        // Ongoing list is output to screen.
        outputAllProjectData(ongoingProjects);
    }

    // Method to compile a data list of all projects that are overdue.
    public void getOverdueProjects(String filePath) {

        // Initializing relevant classes.
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Setting date formatting.
        FileAccess accessFile = new FileAccess();

        // Initializing date variables.
        Date projectDeadline = null;
        Date todayDate = new Date();

        // Using method to recall previously saved project data as list.
        List<ProjectScope> projectData = accessFile.fileReader(filePath);
        List<ProjectScope> overdueProjects = new ArrayList<>();

        // Iterates through list.
        Iterator<ProjectScope> iterateCompiler = projectData.iterator();
        while (iterateCompiler.hasNext()) {
            ProjectScope project = iterateCompiler.next();
            String deadline = project.getProject().getDateDeadline();

            // Isolates the deadline date and tries to format to date format.
            try {
                projectDeadline = dateFormat.parse(deadline);

            } catch (ParseException error) {
                System.out.print("\nError while reading project deadline.");
            }

            // Cross checks date with current date, if overdue project is added to new list.
            if (projectDeadline.compareTo(todayDate) < 0) {
                overdueProjects.add(project);
            }
        }

        // Overdue list is output to screen.
        outputAllProjectData(overdueProjects);
    }
}



