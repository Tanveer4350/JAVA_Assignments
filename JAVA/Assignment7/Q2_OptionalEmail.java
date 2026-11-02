import java.util.Optional;

public class Q2_OptionalEmail {

    public static void main(String[] args) {

        // 1. Create an Optional containing an email address
        Optional<String> email =
                Optional.of("student@gmail.com");

        // 2. Create an empty Optional
        Optional<String> emptyEmail = Optional.empty();

        // 3. Display email using ifPresent()
        System.out.println("Using ifPresent():");
        email.ifPresent(value ->
                System.out.println("Email: " + value)
        );

        // 4. Use orElse() to provide a default email
        String result = emptyEmail.orElse("default@gmail.com");
        System.out.println("\nUsing orElse(): " + result);

        // 5. Use orElseThrow() for missing email
        try {
            String requiredEmail = emptyEmail.orElseThrow(
                    () -> new IllegalArgumentException(
                            "Email address is missing!"
                    )
            );

            System.out.println(requiredEmail);

        } catch (IllegalArgumentException e) {
            System.out.println(
                    "\nUsing orElseThrow(): " + e.getMessage()
            );
        }
    }
}