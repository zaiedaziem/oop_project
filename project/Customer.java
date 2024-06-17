public class Customer extends Account {

    public Customer(String username, String password, String name, Address address, String email, String phone) {
        super(username, password, name, address, email, phone, "Customer");
    }

    public void browseItem(String itemName) {
        System.out.println("Customer browsing item: " + itemName);
    }

    public void buyItem(String itemName) {
        System.out.println("Customer buying item: " + itemName);
    }
}
