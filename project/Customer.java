public class Customer extends Account {
    private Cart cart;

    public Customer(String username, String password, String name, Address address, String email, String phone) {
        super(username, password, name, address, email, phone, "Customer");
        this.cart = new Cart();
    }

    public void browseItem(String name) {
        // Implement browsing items logic here
        System.out.println("Browsing item: " + name);
    }

    public void buyItem(Product product) {
        cart.addProduct(product);
    }

    public void viewCart() {
        cart.viewCart();
    }

    public void checkout(PaymentMethod paymentMethod) {
        double total = cart.calculateTotal();
        paymentMethod.processPayment(total);
        cart = new Cart();  // Clear cart after checkout
    }
}
