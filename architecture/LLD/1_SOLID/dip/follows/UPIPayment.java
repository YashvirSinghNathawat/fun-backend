package dip.follows;
public class UPIPayment implements Payment { 
    @Override 
    public void pay() {
        System.out.println("Payment through UPI payment system");
    }
}
