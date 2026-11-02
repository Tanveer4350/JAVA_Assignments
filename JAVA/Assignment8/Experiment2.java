import java.io.*;

public class Experiment2 {

    public static void main(String[] args) {

        String inputFile = "log.txt";
        String outputFile = "filtered_logs.txt";

        int totalEntries = 0;
        int errorCount = 0;
        int warningCount = 0;

        try (
            BufferedReader reader = new BufferedReader(
                new FileReader(inputFile)
            );

            BufferedWriter writer = new BufferedWriter(
                new FileWriter(outputFile)
            )
        ) {

            String line;

            while ((line = reader.readLine()) != null) {

                totalEntries++;

                if (line.contains("ERROR") || line.contains("WARNING")) {

                    writer.write(line);
                    writer.newLine();

                    if (line.contains("ERROR")) {
                        errorCount++;
                    }

                    if (line.contains("WARNING")) {
                        warningCount++;
                    }
                }
            }

            System.out.println("Log analysis completed successfully.");
            System.out.println("Total log entries: " + totalEntries);
            System.out.println("ERROR entries: " + errorCount);
            System.out.println("WARNING entries: " + warningCount);
            System.out.println(
                "Filtered logs saved to: " + outputFile
            );

        } catch (IOException e) {
            System.out.println("Error while processing log file: "
                    + e.getMessage());
        }
    }
}