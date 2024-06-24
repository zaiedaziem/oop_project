import java.io.*;
import java.util.*;

public class Seller extends Account {
    private List<Product> products;

    public Seller(String username, String password, String name, Address address, String email, String phone) {
        super(username, password, name, address, email, phone, "Seller");
        this.products = new ArrayList<>();
        loadProductsFromFile();
    }

    public void addProduct(Product product) {
        products.add(product);
        saveProductsToFile(); 
        System.out.println("Product added: " + product.getP_name());
    }

    public void removeProduct(String productId) {
        products.removeIf(product -> product.getP_id().equals(productId));
        saveProductsToFile(); 
        System.out.println("Product removed with ID: " + productId);
    }

    public void listProducts() {
        System.out.println("Listing all products:");
        for (Product product : products) {
            System.out.println("ID: " + product.getP_id() + ", Name: " + product.getP_name() + ", Description: " + product.getP_desc() + ", Price: " + product.getP_price() + ", Quantity: " + product.getP_quantity());
        }
    }

    // Load products from file
    private List<Product> loadProductsFromFile() {
        List<Product> productList = new ArrayList<>();
        File file = new File("products.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 5) { // Ensure all required fields are present
                    String p_id = data[0].trim();
                    String p_name = data[1].trim();
                    String p_desc = data[2].trim();
                    double p_price = Double.parseDouble(data[3].trim());
                    int p_quantity = Integer.parseInt(data[4].trim());

                    Product product = new Product(p_id, p_name, p_desc, p_price, p_quantity);
                    productList.add(product);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("products.txt not found. Initializing with an empty list.");
        } catch (IOException e) {
            System.out.println("Error reading products.txt: " + e.getMessage());
        }

        return productList;
    }

    // Save products to file
    private void saveProductsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("products.txt"))) {
            for (Product product : products) {
                writer.println(product.getP_id() + "," + product.getP_name() + "," + product.getP_desc() + "," + product.getP_price() + "," + product.getP_quantity());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
