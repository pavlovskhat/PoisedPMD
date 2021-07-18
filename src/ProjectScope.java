import java.io.Serializable;

public class ProjectScope implements Serializable {

    // Parent object that contains the project and person objects.

    // Project scope object attributes.
    ProjectObject project;
    PersonObject architect;
    PersonObject contractor;
    PersonObject customer;

    // Project scope constructor.
    public ProjectScope(ProjectObject project, PersonObject architect, PersonObject contractor,
                        PersonObject customer){
        this.project = project;
        this.architect = architect;
        this.contractor = contractor;
        this.customer = customer;
    }

    // Setting getter methods for relevant project attributes.
    public ProjectObject getProject(){ return project; }
    public PersonObject getArchitect(){ return architect; }
    public PersonObject getContractor(){ return contractor; }
    public PersonObject getCustomer(){ return customer; }
}

