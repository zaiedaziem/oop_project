import java.util.Scanner;

public abstract class PaymentMethod {
    public abstract void pay();

    public static PaymentMethod choosePaymentMethod(Scanner scanner) {
        System.out.println("Choose payment method:");
        System.out.println("1. Cash on Delivery (COD)");
        System.out.println("2. Online Banking");
        System.out.println("3. Touch 'n Go (TNG)");
        System.out.print("Enter your choice: ");

        int paymentChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (paymentChoice) {
            case 1:
                return new CashOnDelivery();
            case 2:
                return new OnlineBanking();
            case 3:
                return new TouchNGo();
            default:
                System.out.println("Invalid choice. Exiting...");
                return null;
        }
    }
}
