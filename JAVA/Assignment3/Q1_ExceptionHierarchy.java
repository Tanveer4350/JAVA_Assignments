/**
 * Q1: To understand Java's exception hierarchy and implement appropriate
 * exception-handling mechanisms for different types of exceptions.
 *
 * Java Exception Hierarchy:
 *
 *          Throwable
 *         /         \
 *      Error       Exception
 *                 /          \
 *    RuntimeException     Checked Exceptions
 *   (Unchecked)          (e.g., IOException)
 *
 * - Checked Exceptions: Must be declared or handled (compile-time).
 * - Unchecked Exceptions (RuntimeException): Not required to be declared.
 * - Errors: Serious problems not meant to be caught (e.g., OutOfMemoryError).
 */

import java.io.*;

public class Q1_ExceptionHierarchy {

    static void readFile(String filename) throws IOException {
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        String line;
        System.out.println("Reading file: " + filename);
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }

    static int divide(int a, int b) {
        return a / b;
    }

    static void accessArray() {
        int[] arr = {10, 20, 30};
        System.out.println("Accessing invalid index: " + arr[5]);
    }

    static void nullPointerDemo() {
        String str = null;
        System.out.println(str.length());
    }

    static int parseNumber(String s) {
        return Integer.parseInt(s);
    }

    static void classCastDemo() {
        Object obj = "Hello";
        Integer num = (Integer) obj;
    }

    static void infiniteRecursion() {
        infiniteRecursion();
    }

    static void multiCatchDemo(int choice) {
        try {
            switch (choice) {
                case 1:
                    int[] arr = new int[5];
                    arr[10] = 1;
                    break;
                case 2:
                    String s = null;
                    s.length();
                    break;
                case 3:
                    int result = 10 / 0;
                    break;
                default:
                    System.out.println("No exception triggered.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("[MultiCatch] ArrayIndexOutOfBoundsException: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("[MultiCatch] NullPointerException: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("[MultiCatch] ArithmeticException: " + e.getMessage());
        } finally {
            System.out.println("[MultiCatch] Finally block always executes.");
        }
    }

    static void exceptionChaining() throws Exception {
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            throw new Exception("High-level operation failed", e);
        }
    }

    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("  Q1: Java Exception Hierarchy Demo");
        System.out.println("==============================================\n");

        System.out.println("--- 1. Checked Exception: IOException ---");
        try {
            readFile("nonexistent_file.txt");
        } catch (IOException e) {
            System.out.println("Caught IOException: " + e.getMessage());
        }

        System.out.println("\n--- 2. Unchecked: ArithmeticException ---");
        try {
            int result = divide(10, 0);
        } catch (ArithmeticException e) {
            System.out.println("Caught ArithmeticException: " + e.getMessage());
        }

        System.out.println("\n--- 3. Unchecked: ArrayIndexOutOfBoundsException ---");
        try {
            accessArray();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught ArrayIndexOutOfBoundsException: " + e.getMessage());
        }

        System.out.println("\n--- 4. Unchecked: NullPointerException ---");
        try {
            nullPointerDemo();
        } catch (NullPointerException e) {
            System.out.println("Caught NullPointerException: " + e.getMessage());
        }

        System.out.println("\n--- 5. Unchecked: NumberFormatException ---");
        try {
            int num = parseNumber("ABC");
        } catch (NumberFormatException e) {
            System.out.println("Caught NumberFormatException: " + e.getMessage());
        }

        System.out.println("\n--- 6. Unchecked: ClassCastException ---");
        try {
            classCastDemo();
        } catch (ClassCastException e) {
            System.out.println("Caught ClassCastException: " + e.getMessage());
        }

        System.out.println("\n--- 7. Error: StackOverflowError ---");
        try {
            infiniteRecursion();
        } catch (StackOverflowError e) {
            System.out.println("Caught StackOverflowError: Stack overflowed due to infinite recursion.");
        }

        System.out.println("\n--- 8. Multi-catch and finally block ---");
        multiCatchDemo(1);
        multiCatchDemo(2);
        multiCatchDemo(3);
        multiCatchDemo(0);

        System.out.println("\n--- 9. Exception Chaining ---");
        try {
            exceptionChaining();
        } catch (Exception e) {
            System.out.println("Caught Exception: " + e.getMessage());
            System.out.println("Caused by: " + e.getCause());
        }

        System.out.println("\n==============================================");
        System.out.println("  Java Exception Hierarchy Summary:");
        System.out.println("==============================================");
        System.out.println("Throwable");
        System.out.println("  |-- Error (StackOverflowError, OutOfMemoryError)");
        System.out.println("  |-- Exception");
        System.out.println("        |-- RuntimeException (Unchecked)");
        System.out.println("        |     |-- ArithmeticException");
        System.out.println("        |     |-- NullPointerException");
        System.out.println("        |     |-- ArrayIndexOutOfBoundsException");
        System.out.println("        |     |-- NumberFormatException");
        System.out.println("        |     |-- ClassCastException");
        System.out.println("        |-- Checked Exceptions");
        System.out.println("              |-- IOException");
        System.out.println("              |-- FileNotFoundException");
        System.out.println("==============================================");
    }
}