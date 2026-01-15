import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FinanceTrackerApp {
    private static final List<Transaction> transactions = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) { // Infinite loop
            printDetails();
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline
            switch (choice) { // Switch-case structure
                case 1:
                    addTransaction();
                    break;
                case 2:
                    viewTransactions();
                    break;
                case 3:
                    updateTransaction();
                    break;
                case 4:
                    deleteTransaction();
                    break;
                case 5:
                    calculateBalance();
                    break;
                case 6:
                    System.out.println("Exiting... Goodbye!");
                    sc.close(); // Correct place to close the scanner
                    System.exit(0); // Exit
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void addTransaction() {
        String id = String.valueOf(transactions.size() + 1);
        System.out.print("Type (INCOME/EXPENSE): ");
        String type = sc.nextLine();
        System.out.print("Amount: ");
        double amount = Double.parseDouble(sc.nextLine());

        System.out.print("Description: ");
        String description = sc.nextLine();


        String date = Transaction.takeDateInput(sc);
        if (amount > 0 && (type.equals("INCOME") || type.equals("EXPENSE"))) {
            try {
                Transaction t = new Transaction(id, type, amount, description, date);
                transactions.add(t);
                System.out.println("Transaction added successfully!");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Validation failed. Transaction not added.");
        }
    }

    private static void viewTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        System.out.printf("%-5s| %-10s| %-10s| %-20s| %-12s\n", "ID", "Type", "Amount", "Description", "Date");

        for (Transaction t : transactions) {
            System.out.printf("%-5s| %-10s| %-10s| %-20s| %-12s\n",
                    t.getId(), t.getType(), t.getAmount(),
                    t.getDescription(), t.getDate());
        }
    }

    private static void updateTransaction() {
        System.out.print("Enter Transaction ID to update: ");
        String id = sc.nextLine();
        for (Transaction t : transactions) {
            if (t.getId().equals(id)) {

                System.out.print("New Type (INCOME/EXPENSE): ");
                t.setType(sc.nextLine());

                System.out.print("New Amount: ");
                t.setAmount(Double.parseDouble(sc.nextLine()));

                System.out.print("New Description: ");
                t.setDescription(sc.nextLine());

                t.setDate(Transaction.takeDateInput(sc));

                System.out.println("Transaction updated");
                return;
            }
        }
        System.out.println("Transaction not found!!");
    }

    private static void deleteTransaction() {
        System.out.print("Enter ID to delete: ");
        String id = sc.nextLine();
        boolean removed = transactions.removeIf(t -> t.getId().equals(id));
        if (removed) System.out.println("Successfully Deleted.");
        else System.out.println("ID not found.");
    }

    private static void calculateBalance() {
        double totalIncome = 0;
        double totalExpense = 0;
        for (Transaction t : transactions) {
            if (t.getType().equals("INCOME")) totalIncome += t.getAmount();
            else totalExpense += t.getAmount();
        }
        double balance = totalIncome - totalExpense;
        System.out.println("Total Income: " + totalIncome);
        System.out.println("Total Expense: " + totalExpense);
        System.out.println("Current Balance: " + balance);
    }

    public static void printDetails() {
        System.out.println("\n=== Personal Finance Tracker ===");
        System.out.println("1. Add Transaction");
        System.out.println("2. View All Transactions");
        System.out.println("3. Update Transaction");
        System.out.println("4. Delete Transaction");
        System.out.println("5. Calculate Balance");
        System.out.println("6. Exit");
        System.out.print("Choose option: ");
    }

}