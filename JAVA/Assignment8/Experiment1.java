import java.io.*;

public class Experiment1 {

    public static void main(String[] args) {

        // Part 1: Copying a binary file using byte streams
        String sourceFile = "source.jpg";
        String destinationFile = "copy.jpg";

        try (
            FileInputStream input = new FileInputStream(sourceFile);
            FileOutputStream output = new FileOutputStream(destinationFile)
        ) {
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }

            System.out.println("Binary file copied successfully.");

        } catch (IOException e) {
            System.out.println("Error while copying file: " + e.getMessage());
        }


        // Part 2: Writing student details using character stream
        String studentFile = "students.txt";

        try (FileWriter writer = new FileWriter(studentFile)) {

            writer.write("Student ID: 101\n");
            writer.write("Name: Tanveer Singh\n");
            writer.write("Course: Computer Science\n");
            writer.write("Semester: 5\n");

            System.out.println("Student details written successfully.");

        } catch (IOException e) {
            System.out.println("Error while writing file: " + e.getMessage());
        }


        // Part 3: Reading student details using character stream
        System.out.println("\nStudent Details:");

        try (FileReader reader = new FileReader(studentFile)) {

            int character;

            while ((character = reader.read()) != -1) {
                System.out.print((char) character);
            }

        } catch (IOException e) {
            System.out.println("Error while reading file: " + e.getMessage());
        }
    }
}