import java.io.*;
import java.util.*;

public class Customer extends Account {
    private List<Product> cart;

    public Customer(String username, String password, String name, Address address, String email, String phone) {
        super(username, password, name, address, email, phone, "Customer");
        this.cart = new ArrayList<>();
    }

    public void browseItem(String name) {
        boolean found = false;
        try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[1].equalsIgnoreCase(name)) {
                    System.out.println("Product found:");
                    System.out.println("ID: " + data[0]);
                    System.out.println("Name: " + data[1]);
                    System.out.println("Description: " + data[2]);
                    System.out.println("Price: " + data[3]);
                    System.out.println("Quantity: " + data[4]);
                    System.out.println("\n1. Add to Cart");
                    System.out.println("2. Exit");
                    System.out.print("Enter your choice: ");
                    Scanner scanner = new Scanner(System.in);
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (choice) {
                        case 1:
                            System.out.print("Enter quantity to add: ");
                            int quantity = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            addProductToCart(data[0], data[1], data[2], Double.parseDouble(data[3]), quantity);
                            break;
                        case 2:
                            System.out.println("Exiting...");
                            break;
                        default:
                            System.out.println("Invalid choice. Exiting...");
                            break;
                    }
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Item does not exist in shop.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addProductToCart(String id, String name, String desc, double price, int quantity) {
        Product product = new Product(id, name, desc, price, quantity);
        cart.add(product);
        System.out.println(quantity + " " + name + "(s) added to cart.");
    }

    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Items in your cart:");
            for (Product product : cart) {
                System.out.println("Name: " + product.getP_name() + ", Quantity: " + product.getP_quantity() + ", Price: " + product.getP_price());
            }
        }
    }

    public void checkout() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. Nothing to checkout.");
            return;
        }

        System.out.println("Checking out items in your cart:");
        double total = calculateTotal();
        System.out.println("Total Price: RM" + total);

        // Assuming here that payment method is handled in Main class directly.
        // Proceed with payment logic here if needed.

        // Clear cart after checkout
        cart.clear();
    }

    private double calculateTotal() {
        double total = 0.0;
        for (Product product : cart) {
            total += product.getP_price() * product.getP_quantity();
        }
        return total;
    }

    // Other methods...
}
