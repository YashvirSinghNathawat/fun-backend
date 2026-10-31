package dip.follows;
public class Main {
    public static void main(String[] args) {
        Payment payment = new UPIPayment();

        Restaurant res = new Restaurant(payment);

        res.checkout();
    }
}
