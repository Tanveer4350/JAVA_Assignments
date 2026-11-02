import java.util.Objects;
import java.util.Optional;

public class Q5_GeneralUtility {

    // 1. Check whether two objects are equal
    public static <T> boolean isEqual(T a, T b) {
        return Objects.equals(a, b);
    }

    // 2. Return contained value or default value
    public static <T> T getDefault(
            Optional<T> value, T defaultValue) {

        return value.orElse(defaultValue);
    }

    // 3. Return the larger of two comparable objects
    public static <T extends Comparable<T>> T max(T a, T b) {

        if (a.compareTo(b) >= 0) {
            return a;
        }

        return b;
    }

    // 4. Check whether an object is null
    public static boolean isNull(Object obj) {
        return Objects.isNull(obj);
    }

    public static void main(String[] args) {

        // Demonstrating isEqual()
        System.out.println(
                "Strings equal: " +
                isEqual("Java", "Java")
        );

        System.out.println(
                "Integers equal: " +
                isEqual(10, 20)
        );


        // Demonstrating getDefault()
        Optional<String> name =
                Optional.of("Tanveer");

        Optional<String> emptyName =
                Optional.empty();

        System.out.println(
                "\nName: " +
                getDefault(name, "Guest")
        );

        System.out.println(
                "Default Name: " +
                getDefault(emptyName, "Guest")
        );


        // Demonstrating max()
        System.out.println(
                "\nMaximum Integer: " +
                max(50, 100)
        );

        System.out.println(
                "Maximum String: " +
                max("Apple", "Banana")
        );


        // Demonstrating isNull()
        String value = null;

        System.out.println(
                "\nIs value null? " +
                isNull(value)
        );

        System.out.println(
                "Is Java null? " +
                isNull("Java")
        );
    }
}