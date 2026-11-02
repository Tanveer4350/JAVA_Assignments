import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Scanner;

public class Q4_EventManagement {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Accept event details
        System.out.print("Enter event name: ");
        String eventName = scanner.nextLine();

        System.out.print("Enter event date (YYYY-MM-DD): ");
        LocalDate eventDate =
                LocalDate.parse(scanner.nextLine());

        System.out.print("Enter start time (HH:MM): ");
        LocalTime startTime =
                LocalTime.parse(scanner.nextLine());

        System.out.print("Enter venue (leave blank if none): ");
        String venueInput = scanner.nextLine();

        Optional<String> venue =
                venueInput.isBlank()
                        ? Optional.empty()
                        : Optional.of(venueInput);

        // Display event details
        System.out.println("\n--- Event Details ---");
        System.out.println("Event Name: " + eventName);
        System.out.println("Event Date: " + eventDate);
        System.out.println("Start Time: " + startTime);

        System.out.println(
                "Venue: " +
                venue.orElse("Venue not specified")
        );

        // Current date
        LocalDate today = LocalDate.now();

        // Check event date
        if (eventDate.isAfter(today)) {
            System.out.println("Event is in the future.");
        } else if (eventDate.isEqual(today)) {
            System.out.println("Event is today.");
        } else {
            System.out.println("Event date has passed.");
        }

        // Calculate days remaining
        long daysRemaining =
                ChronoUnit.DAYS.between(today, eventDate);

        System.out.println(
                "Days remaining: " + daysRemaining
        );

        // Calculate end time after two hours
        LocalTime endTime = startTime.plusHours(2);

        System.out.println(
                "Event End Time: " + endTime
        );

        scanner.close();
    }
}