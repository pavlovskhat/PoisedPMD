import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProjectFinalizer {

    // Method to finalize a chosen project.
    public void finalizeProject(String filePath) {

        // Initializing relevant classes.
        FileAccess fileAccess = new FileAccess();
        ProjectEditor editProject = new ProjectEditor();

        List<ProjectScope> projectData = fileAccess.fileReader(filePath);

        // Running method to find and return a project to finalize.
        List<ProjectScope> projectToEdit = editProject.extractProject(projectData);

        ProjectScope fullProject = projectToEdit.get(0);
        ProjectObject project = fullProject.getProject();
        PersonObject customer = fullProject.getCustomer();

        // Running method to finalize and create invoice from project, information string is saved
        // to variable.
        String projectInvoice = generateInvoice(project, customer);

        // Output of final invoice.
        System.out.print(projectInvoice);
    }

    // Method to generate an invoice and finalize the project.
    private String generateInvoice(ProjectObject project, PersonObject customer) {

        // Initializing relevant classes.
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Setting date formatting.
        Date todayDate = new Date();  // Initializing an instance of current date.

        // Creating relevant variables.
        String projectInvoice = "";  // Blank start point for invoice String.
        String newStatus = "Completed";
        String today = dateFormat.format(todayDate);  // Formatting current date to String in preferred format.

        // Adding status 'completed' to project.
        project.setProjectStatus(newStatus);

        // Adding today's date as completion date.
        project.setCompleteDate(today);

        // Output of final project details for visual confirmation.
        System.out.print("\nFinal project details:" +
                "\n" + project.toString() +
                "\nProject Status: " + project.getProjectStatus() +
                "\nCompletion Date: " + project.getCompleteDate());

        // Calculating the balance of the project owed by the customer.
        int projectBalance = (project.getProjectFee()) - (project.getPaidToDate());

        // Generates an invoice String if there is a balance on the project.
        if (projectBalance > 0) {

            projectInvoice = ("\nPOISED PROJECT INVOICE"
                    + "\nProject Name: " + project.getProjectName()
                    + "\nProject Number :" + project.getProjectNumber()
                    + "\n" + customer.toString() +
                    "\n\nProject fee balance payable: \nR" + projectBalance);

        } else {
            System.out.print("The project fee has been settled in full.");

        }
            return projectInvoice;
    }
}
