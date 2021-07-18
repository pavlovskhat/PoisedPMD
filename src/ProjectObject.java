import java.io.Serializable;

public class ProjectObject implements Serializable {

    // Project object attributes.
    String projectNumber;
    String projectName;
    String buildingType;
    String projectAddress;
    String erfNumber;
    int projectFee;
    int paidToDate;
    String dateDeadline;
    String projectStatus;
    String completeDate;

    // Project constructor.
    public ProjectObject(String projectNumber, String projectName, String buildingType, String projectAddress,
                         String erfNumber, int projectFee, int paidToDate, String dateDeadline, String projectStatus,
                         String completeDate){
        this.projectNumber = projectNumber;
        this.projectName = projectName;
        this.buildingType = buildingType;
        this.projectAddress = projectAddress;
        this.erfNumber = erfNumber;
        this.projectFee = projectFee;
        this.paidToDate = paidToDate;
        this.dateDeadline = dateDeadline;
        this.projectStatus = projectStatus;
        this.completeDate = completeDate;
    }

    // Setting getter methods for relevant project attributes.
    public String getProjectNumber() { return projectNumber; }
    public String getProjectName() { return projectName; }
    public String getErfNumber() { return erfNumber; }
    public String getProjectAddress() { return projectAddress; }
    public int getProjectFee() { return projectFee; }
    public int getPaidToDate() { return paidToDate; }
    public String getDateDeadline() { return dateDeadline; }
    public String getProjectStatus() { return projectStatus; }
    public String getCompleteDate() { return completeDate; }

    // Setting setter methods for relevant project attributes.
    public void setPaidToDate(int newPaidToDate) { paidToDate = newPaidToDate; }
    public void setDateDeadline(String newDateDeadLine) { dateDeadline = newDateDeadLine; }
    public void setProjectStatus(String newProjectStatus) { projectStatus = newProjectStatus; }
    public void setCompleteDate(String newCompleteDate) { completeDate = newCompleteDate; }

    // Adding toString method to project object
    public String toString() {
        String output = "\nProject No: " + this.projectNumber;
        output += "\nProject Name: " + this.projectName;
        output += "\nBuilding Type: " + this.buildingType;
        output += "\nProject Address: " + this.projectAddress;
        output += "\nERF Number: " + this.erfNumber;
        output += "\nProject Fee: R" + this.projectFee;
        output += "\nPaid to Date: R" + this.paidToDate;
        output += "\nDeadline Date: " + this.dateDeadline;

        return output;
    }
}
