public class Demo {
    public static void main(String[] args) {
        ShoppingCart sc = new ShoppingCart();
        sc.setPaymentStrategy(new CashPayment());

        sc.checkout(100.0);
    }
}
