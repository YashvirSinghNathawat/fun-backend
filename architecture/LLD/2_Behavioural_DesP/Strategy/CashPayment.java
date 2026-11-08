public class CashPayment implements PaymentStrategy{
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Cash");
    }
}
