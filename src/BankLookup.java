import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Scanner;

public class BankLookup {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the first three digits of the bank account: ");
        String digits = scanner.nextLine().trim();

        if (!digits.matches("\\d{3}")) {
            System.out.println("Please enter exactly three digits.");
            return;
        }

        String address = "https://ewib.nbp.pl/plewibnra?dokNazwa=plewibnra.txt";
        String bankName = null;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(URI.create(address).toURL().openStream(), "Cp852"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length > 1 && parts[0].trim().equals(digits)) {
                    bankName = parts[1].trim();
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error while loading the data: " + e.getMessage());
            return;
        }

        if (bankName != null) {
            System.out.println("Bank number " + digits + ": " + bankName);
        } else {
            System.out.println("No bank found for " + digits);
        }
    }
}