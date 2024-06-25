import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> cartItems;
    private static final String CART_FILE = "cart.txt"; // File path for storing cart items

    public Cart() {
        this.cartItems = loadCartFromFile();
    }

    private List<Product> loadCartFromFile() {
        List<Product> loadedCart = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CART_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) { // Ensure all fields are present
                    String p_id = data[0].trim();
                    String p_name = data[1].trim();
                    String p_desc = data[2].trim();
                    double p_price = Double.parseDouble(data[3].trim());
                    int p_quantity = Integer.parseInt(data[4].trim());
                    Product product = new Product(p_id, p_name, p_desc, p_price, p_quantity);
                    loadedCart.add(product);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cart file not found. Creating new cart.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedCart;
    }

    private void saveCartToFile() {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(CART_FILE)))) {
            for (Product item : cartItems) {
                writer.println(item.getP_id() + "," + item.getP_name() + "," + item.getP_desc() + "," +
                        item.getP_price() + "," + item.getP_quantity());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addProductToCart(String p_id, String p_name, String p_desc, double p_price, int p_quantity) {
        Product item = new Product(p_id, p_name, p_desc, p_price, p_quantity);
        cartItems.add(item);
        saveCartToFile(); // Save updated cart to file after adding a product
        System.out.println(p_quantity + " " + p_name + "(s) added to cart.");
    }

    public List<Product> getCartItems() {
        return cartItems;
    }

    public void clearCart() {
        cartItems.clear();
        saveCartToFile();
    }
}
