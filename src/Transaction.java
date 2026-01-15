import java.time.LocalDate;
import java.util.Scanner;

public class Transaction {
    private String id; //immutable and unique
    private String type;  // INCOME or EXPENSE
    private double amount;  // positive
    private String description;
    private String date;  // YYYY-MM-DD

    // No-Args Constructor
    public Transaction() {
    }

    // All-Args Constructor
    public Transaction(String id, String type, double amount, String description, String date) {
        this.id = id;
        setType(type);
        setAmount(amount);
        this.description = description;
        this.date = date;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    // Setters
    public void setType(String type) {
        if (type.equalsIgnoreCase("INCOME") || type.equalsIgnoreCase("EXPENSE")) {
            this.type = type.toUpperCase();
        } else {
            throw new IllegalArgumentException("Type must be INCOME or EXPENSE");
        }
    }


    public void setAmount(double amount) {
        if (amount > 0) {
            this.amount = amount;
        } else {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setDate(String date) {
        this.date = date;
    }

    public static String takeDateInput(Scanner sc) {
        System.out.print("Use today's date? (y/n): ");
        String choice = sc.nextLine();

        if (choice.equalsIgnoreCase("y")) {
            return LocalDate.now().toString();
        } else {
            System.out.print("Enter date (YYYY-MM-DD): ");
            return sc.nextLine();
        }
    }
}
