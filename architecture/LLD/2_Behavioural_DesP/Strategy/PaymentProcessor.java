public class PaymentProcessor {
    public void processPayment(String type, double amount) {
        switch (type) {
            case "CC":
                System.out.println("Paid $" + amount + " using credit card");
                break;
            case "Cash":
                System.out.println("Paid $" + amount + " using Cash");
                break;
            case "UPI":  // Violates OpenClose Principle
                System.out.println("Paid $" + amount + " using UPI");
        }
    }
}
