import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("studentsEN.txt"));
             FileWriter writer = new FileWriter("studentsGrade5.txt")) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }
                String[] parts = line.split("\t");
                String grade = parts[parts.length - 1].trim();
                if (grade.equals("5")) {
                    writer.write(line + System.lineSeparator());
                    count++;
                }
            }

            System.out.println("Students with grade 5 saved: " + count);
        } catch (IOException e) {
            System.out.println("Error while processing files: " + e.getMessage());
        }
    }
}