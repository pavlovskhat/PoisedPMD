import java.io.Serializable;

public class PersonObject implements Serializable {

    // Project person attributes.
    String personType;
    String name;
    String surname;
    String phoneNumber;
    String email;
    String address;

    // Project person constructor.
    public PersonObject(String personType, String name, String surname,
                        String phoneNumber, String email, String address) {
        this.personType = personType;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    // Setting getter methods for required attributes.
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }

    // Project person toString output method.
    public String toString() {
        String output = "\n" + this.personType + " Details:";
        output += "\nName: " + this.name;
        output += "\nSurname: " + this.surname;
        output += "\nTelephone: " + this.phoneNumber;
        output += "\nemail: " + this.email;
        output += "\naddress: " + this.address;

        return output;
    }
}
