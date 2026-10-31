
// Restaurant depends on WiredPayment(Low Level Module)
public class Restaurant {
    private WiredPayment payment = new WiredPayment();

    public void checkout() {
        payment.pay();
    }
}
