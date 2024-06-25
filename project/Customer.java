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
                            addToCart(data[0], data[1], data[2], Double.parseDouble(data[3]), quantity);
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

    private void addToCart(String id, String name, String desc, double price, int quantity) {
        Product product = new Product(id, name, desc, price, quantity);
        cart.add(product);
        System.out.println(quantity + " " + name + "(s) added to cart.");
    }

    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Items in your cart:");
            for (int i = 0; i < cart.size(); i++) {
                Product product = cart.get(i);
                System.out.println((i + 1) + ". Name: " + product.getP_name() + ", Quantity: " + product.getP_quantity() + ", Price: " + product.getP_price());
            }

            System.out.println("\n1. Remove item from cart");
            System.out.println("2. Checkout");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter item number to remove: ");
                    int itemNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    removeCartItem(itemNumber);
                    break;
                case 2:
                    checkout();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Exiting...");
                    break;
            }
        }
    }

    private void removeCartItem(int itemNumber) {
        if (itemNumber < 1 || itemNumber > cart.size()) {
            System.out.println("Invalid item number.");
            return;
        }

        Product removedProduct = cart.remove(itemNumber - 1);
        System.out.println(removedProduct.getP_name() + " removed from cart.");
    }

    private void checkout() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. Nothing to checkout.");
            return;
        }

        System.out.println("Checking out items in your cart:");
        double total = calculateTotal();
        System.out.println("Total Price: RM" + total);

        PaymentMethod.choosePaymentMethod();

        // Update product quantities in products.txt
        updateProductQuantitiesInFile();

        // Clear cart after checkout
        cart.clear();
        System.out.println("Checkout complete. Thank you for your purchase!");
    }

    private double calculateTotal() {
        double total = 0.0;
        for (Product product : cart) {
            total += product.getP_price() * product.getP_quantity();
        }
        return total;
    }

    private void updateProductQuantitiesInFile() {
        List<String> updatedLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String p_id = data[0].trim();
                String p_name = data[1].trim();
                String p_desc = data[2].trim();
                double p_price = Double.parseDouble(data[3].trim());
                int p_quantity = Integer.parseInt(data[4].trim());

                for (Product cartItem : cart) {
                    if (p_id.equals(cartItem.getP_id())) {
                        p_quantity -= cartItem.getP_quantity();
                        break;
                    }
                }

                String updatedLine = p_id + "," + p_name + "," + p_desc + "," + p_price + "," + p_quantity;
                updatedLines.add(updatedLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write updated lines back to products.txt
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("products.txt")))) {
            for (String line : updatedLines) {
                writer.println(line);
            }
            System.out.println("Product quantities updated successfully in products.txt");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to update product quantities in products.txt");
        }
    }
}
