package dip.follows;
public class Restaurant {
    Payment payment;
    public Restaurant(Payment payment) {
        this.payment = payment;
    }
    public void checkout() {
        payment.pay();
    }
}
