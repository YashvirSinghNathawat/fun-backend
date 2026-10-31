package dip.follows;
public class WiredPayment implements Payment{
    @Override 
    void pay() {
        System.out.println("Payment through wired payment system");
    }
}
