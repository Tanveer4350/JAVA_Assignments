import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private int employeeId;
    private String name;
    private String department;
    private double salary;

    public Employee(int employeeId, String name,
                    String department, double salary) {

        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee ID: " + employeeId +
                "\nName: " + name +
                "\nDepartment: " + department +
                "\nSalary: " + salary + "\n";
    }
}

public class Experiment3 {

    public static void main(String[] args) {

        String fileName = "employees.dat";

        // Creating multiple employee objects
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(
                101, "Tanveer", "IT", 50000
        ));

        employees.add(new Employee(
                102, "Rahul", "HR", 45000
        ));

        employees.add(new Employee(
                103, "Simran", "Finance", 55000
        ));


        // Serialization
        try (
            ObjectOutputStream output =
                new ObjectOutputStream(
                    new FileOutputStream(fileName)
                )
        ) {

            output.writeObject(employees);

            System.out.println(
                "Employee objects serialized successfully."
            );

        } catch (IOException e) {
            System.out.println(
                "Error during serialization: " + e.getMessage()
            );
        }


        // Deserialization
        try (
            ObjectInputStream input =
                new ObjectInputStream(
                    new FileInputStream(fileName)
                )
        ) {

            List<Employee> retrievedEmployees =
                (List<Employee>) input.readObject();

            System.out.println("\nEmployee Records:");

            for (Employee employee : retrievedEmployees) {
                System.out.println(employee);
            }

        } catch (IOException | ClassNotFoundException e) {

            System.out.println(
                "Error during deserialization: " + e.getMessage()
            );
        }
    }
}