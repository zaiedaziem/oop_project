import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Account currentAccount = null;

        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Quit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter account type (Customer/Seller): ");
                    String accountType = scanner.nextLine();
                    currentAccount = Account.createAccount(accountType, scanner);
                    if (currentAccount != null) {
                        currentAccount.register();
                    }
                    break;
                case 2:
                    currentAccount = Account.login(scanner);
                    if (currentAccount != null) {
                        System.out.println("\nLogin successful!");
                        if (currentAccount instanceof Customer) {
                            handleCustomerActions((Customer) currentAccount, scanner);
                        } else if (currentAccount instanceof Seller) {
                            handleSellerActions((Seller) currentAccount, scanner);
                        }
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleCustomerActions(Customer customer, Scanner scanner) {
        while (true) {
            System.out.println("1. Browse Items");
            System.out.println("2. View Cart");
            System.out.println("3. Checkout");
            System.out.println("4. Edit Profile");
            System.out.println("5. View Account Information");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter item name to browse: ");
                    String itemName = scanner.nextLine();
                    customer.browseItem(itemName);
                    break;
                case 2:
                    customer.viewCart();
                    break;
                case 3:
                    System.out.print("Enter payment method (Credit Card/Paypal/etc.): ");
                    String paymentMethod = scanner.nextLine();
                    customer.checkout(new PaymentMethod(paymentMethod));
                    break;
                case 4:
                    customer.editProfile(scanner);
                    break;
                case 5:
                    customer.viewAccountInfo();
                    break;
                case 6:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleSellerActions(Seller seller, Scanner scanner) {
        while (true) {
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. List Products");
            System.out.println("4. Edit Profile");
            System.out.println("5. View Account Information");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter product ID: ");
                    String p_id = scanner.nextLine();
                    System.out.print("Enter product name: ");
                    String p_name = scanner.nextLine();
                    System.out.print("Enter product description: ");
                    String p_desc = scanner.nextLine();
                    System.out.print("Enter product price: ");
                    double p_price = scanner.nextDouble();
                    System.out.print("Enter product quantity: ");
                    int p_quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Product product = new Product(p_id, p_name, p_desc, p_price, p_quantity);
                    seller.addProduct(product);
                    break;
                case 2:
                    System.out.print("Enter product ID to remove: ");
                    String productId = scanner.nextLine();
                    seller.removeProduct(productId);
                    break;
                case 3:
                    seller.listProducts();
                    break;
                case 4:
                    seller.editProfile(scanner);
                    break;
                case 5:
                    seller.viewAccountInfo();
                    break;
                case 6:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
