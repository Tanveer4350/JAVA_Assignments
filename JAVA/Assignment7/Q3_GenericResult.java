import java.util.Objects;
import java.util.Optional;

class Result<T extends Number> {

    private T marks;
    private double maximumMarks;

    public Result(T marks, double maximumMarks) {

        this.marks = Objects.requireNonNull(
                marks, "Marks cannot be null"
        );

        this.maximumMarks = maximumMarks;
    }

    // Calculate percentage
    public double calculatePercentage() {
        return (marks.doubleValue() / maximumMarks) * 100;
    }

    // Check whether student has passed
    public boolean hasPassed(double threshold) {
        return calculatePercentage() >= threshold;
    }

    // Display result using Optional
    public void displayResult() {

        Optional.ofNullable(marks)
                .ifPresent(value -> {
                    System.out.println("Marks: " + value);
                    System.out.println(
                            "Percentage: "
                            + calculatePercentage() + "%"
                    );
                });
    }
}

public class Q3_GenericResult {

    public static void main(String[] args) {

        Result<Integer> student1 =
                new Result<>(85, 100);

        Result<Double> student2 =
                new Result<>(72.5, 100);

        System.out.println("Student 1:");
        student1.displayResult();
        System.out.println(
                "Passed: " + student1.hasPassed(40)
        );

        System.out.println("\nStudent 2:");
        student2.displayResult();
        System.out.println(
                "Passed: " + student2.hasPassed(40)
        );
    }
}