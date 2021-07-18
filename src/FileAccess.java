import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileAccess {

    // Researched serialized objects and writing and reading them from here:
    // https://www.tutorialspoint.com/java/java_serialization.htm
    // https://stackoverflow.com/questions/16111496/java-how-can-i-write-my-arraylist-to-a-file-and-read-load-that-file-to-the

    // Method to recall serialized list data from given file path.
    public List fileReader(String filePath) {

        // Initializing blank list for return value.
        List<ProjectScope> recallProjectData = new ArrayList<>();

        try {
            FileInputStream readData = new FileInputStream(filePath);
            ObjectInputStream readStream = new ObjectInputStream(readData);

            // Adding read data to initialized list value.
            recallProjectData = (ArrayList<ProjectScope>) readStream.readObject();
            readStream.close();

        } catch (Exception e) {
            System.out.println("Error while reading projects from file.");
        }

        return recallProjectData;
    }

    // Method to write serialized list data to file.
    public void fileWriter(List<ProjectScope> newData, String filePath) {

        try {
            FileOutputStream writeData = new FileOutputStream(filePath);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(newData);
            writeStream.close();

        } catch (IOException error) {
            System.out.println("Error while writing project to file.");
        }
    }
}
