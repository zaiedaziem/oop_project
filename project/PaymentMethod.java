import java.util.Scanner;

public class PaymentMethod {
    public static void choosePaymentMethod() {
        System.out.println("Choose payment method:");
        System.out.println("1. Cash on Delivery (COD)");
        System.out.println("2. Online Banking");
        System.out.println("3. Touch 'n Go (TNG)");
        System.out.print("Enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        int paymentChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (paymentChoice) {
            case 1:
                System.out.println("You have chosen Cash on Delivery (COD).");
                break;
            case 2:
                System.out.println("You have chosen Online Banking.");
                break;
            case 3:
                System.out.println("You have chosen Touch 'n Go (TNG).");
                break;
            default:
                System.out.println("Invalid choice. Exiting...");
                return;
        }
    }
}
