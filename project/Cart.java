import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> products;

    public Cart() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
        System.out.println("Added to cart: " + product.getP_name());
    }

    public void removeProduct(String productId) {
        products.removeIf(product -> product.getP_id().equals(productId));
        System.out.println("Removed from cart with ID: " + productId);
    }

    public void viewCart() {
        System.out.println("Viewing cart:");
        for (Product product : products) {
            System.out.println("ID: " + product.getP_id() + ", Name: " + product.getP_name() + ", Description: " + product.getP_desc() + ", Price: " + product.getP_price() + ", Quantity: " + product.getP_quantity());
        }
    }

    public double calculateTotal() {
        return products.stream().mapToDouble(product -> product.getP_price() * product.getP_quantity()).sum();
    }

    // Other methods...
}
