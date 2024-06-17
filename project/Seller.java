public class Seller extends Account {

    public Seller(String username, String password, String name, Address address, String email, String phone) {
        super(username, password, name, address, email, phone, "Seller");
    }

    public void addItem(String itemName, double price) {
        System.out.println("Seller adding item: " + itemName + ", Price: " + price);
    }

    public void manageOrders() {
        System.out.println("Seller managing orders");
    }
}
