import java.util.ArrayList;
import java.util.List;

public class Seller extends Account {
    private List<Product> products;

    public Seller(String username, String password, String name, Address address, String email, String phone) {
        super(username, password, name, address, email, phone, "Seller");
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
        System.out.println("Product added: " + product.getP_name());
    }

    public void removeProduct(String productId) {
        products.removeIf(product -> product.getP_id().equals(productId));
        System.out.println("Product removed with ID: " + productId);
    }

    public void listProducts() {
        System.out.println("Listing all products:");
        for (Product product : products) {
            System.out.println("ID: " + product.getP_id() + ", Name: " + product.getP_name() + ", Description: " + product.getP_desc() + ", Price: " + product.getP_price() + ", Quantity: " + product.getP_quantity());
        }
    }

    // Other methods...
}
