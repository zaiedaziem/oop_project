import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Register");
            System.out.println("2. Login");
            System.out.println("3. Quit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 1) {
                System.out.println("\nChoose account type:");
                System.out.println("1. Customer");
                System.out.println("2. Seller");
                System.out.print("Enter choice: ");
                int accountTypeChoice = scanner.nextInt();
                scanner.nextLine();
                String accountType = accountTypeChoice == 1 ? "Customer" : "Seller";

                Account account = Account.createAccount(accountType, scanner);
                if (account != null) {
                    account.register();
                }
            } 
            
            else if (choice == 2) {
                Account account = Account.login(scanner);
                if (account != null) {
                    while (true) {
                        System.out.println("\n1. View Account Info");
                        System.out.println("2. Edit Profile");
                        
                        if ("Customer".equalsIgnoreCase(account.getAccountType())) {
                            System.out.println("3. Browse Items");
                            System.out.println("4. Buy Items");
                            System.out.println("5. Logout");
                            System.out.print("Choose an option: ");
                            int customerChoice = scanner.nextInt();
                            scanner.nextLine(); 

                            Customer customer = (Customer) account;
                            if (customerChoice == 1) {
                                customer.viewAccountInfo();
                            } else if (customerChoice == 2) {
                                customer.editProfile(scanner);
                            } else if (customerChoice == 3) {
                                System.out.print("Enter item name to browse: ");
                                String itemName = scanner.nextLine();
                                customer.browseItem(itemName);
                            } else if (customerChoice == 4) {
                                System.out.print("Enter item name to buy: ");
                                String itemName = scanner.nextLine();
                                customer.buyItem(itemName);
                            } else if (customerChoice == 5) {
                                break;
                            }
                        } 
                        
                        else if ("Seller".equalsIgnoreCase(account.getAccountType())) {
                            System.out.println("3. Add Item");
                            System.out.println("4. Manage Orders");
                            System.out.println("5. Logout");
                            System.out.print("Choose an option: ");
                            int sellerChoice = scanner.nextInt();
                            scanner.nextLine(); // consume newline

                            Seller seller = (Seller) account;
                            if (sellerChoice == 1) {
                                seller.viewAccountInfo();
                            } else if (sellerChoice == 2) {
                                seller.editProfile(scanner);
                            } else if (sellerChoice == 3) {
                                System.out.print("Enter item name to add: ");
                                String itemName = scanner.nextLine();
                                System.out.print("Enter item price: ");
                                double price = scanner.nextDouble();
                                scanner.nextLine(); // consume newline
                                seller.addItem(itemName, price);
                            } else if (sellerChoice == 4) {
                                seller.manageOrders();
                            } else if (sellerChoice == 5) {
                                break;
                            }
                        }
                    }
                }
            } 
            
            else if (choice == 3) {
                break;
            }
        }

        scanner.close();
    }
}
