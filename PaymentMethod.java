import java.util.InputMismatchException;
import java.util.Scanner;

abstract class PaymentMethod {
    public abstract void displayPaymentMethod();
    public abstract void processPayment();
}

class CashOnDelivery extends PaymentMethod {
    @Override
    public void displayPaymentMethod() {
        System.out.println("You have chosen Cash on Delivery (COD).");
    }

    @Override
    public void processPayment() {
        System.out.println("Processing payment: Please have the cash ready when your order arrives.");
    }
}

class OnlineBanking extends PaymentMethod {
    @Override
    public void displayPaymentMethod() {
        System.out.println("You have chosen Online Banking.");
    }

    @Override
    public void processPayment() {
        System.out.println("Processing payment: Redirecting to your bank's online portal.");
    }
}

class TouchNGo extends PaymentMethod {
    @Override
    public void displayPaymentMethod() {
        System.out.println("You have chosen Touch 'n Go (TNG).");
    }

    @Override
    public void processPayment() {
        System.out.println("Processing payment: Please scan the QR code using your TNG eWallet app.");
    }
}

public class PaymentMethodSelector {
    public static void choosePaymentMethod() {
        System.out.println("Choose payment method:");
        System.out.println("1. Cash on Delivery (COD)");
        System.out.println("2. Online Banking");
        System.out.println("3. Touch 'n Go (TNG)");
        System.out.print("Enter your choice: ");
        Scanner scanner = new Scanner(System.in);

        try {
            int paymentChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            PaymentMethod paymentMethod;

            switch (paymentChoice) {
                case 1:
                    paymentMethod = new CashOnDelivery();
                    break;
                case 2:
                    paymentMethod = new OnlineBanking();
                    break;
                case 3:
                    paymentMethod = new TouchNGo();
                    break;
                default:
                    System.out.println("Invalid choice. Exiting...");
                    return;
            }

            paymentMethod.displayPaymentMethod();
            paymentMethod.processPayment();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number between 1 and 3.");
        }
    }

    public static void main(String[] args) {
        choosePaymentMethod();
    }
}
