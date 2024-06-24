public class PaymentMethod {
    private String method;

    public PaymentMethod(String method) {
        this.method = method;
    }

    public void processPayment(double amount) {
        // Implement payment processing logic here
        System.out.println("Processing " + method + " payment of $" + amount);
    }

    // Getters and setters...
}
