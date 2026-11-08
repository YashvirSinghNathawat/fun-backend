import java.util.ArrayList;
import java.util.List;

public class IPhoneStockObservable implements StockAvailabilityObservable{
    private final String name;
    private final List<StockNotificationObserver> stockObservers;
    private int quantity;

    IPhoneStockObservable(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
        stockObservers = new ArrayList<>();
    }

    @Override 
    public void addObserver(StockNotificationObserver observer) {
        this.stockObservers.add(observer);
        System.out.println("[+]" + observer.getUserId() + " subscribed for notifications on " + name);
    }

    @Override
    public void removeObserver(StockNotificationObserver observer) {
        this.stockObservers.remove(observer);
        System.out.println("[-]" + observer.getUserId() + " unsubscribed for notifications on " + name);
    }

    @Override 
    public void notifyStockObservers() {
        stockObservers.forEach((observer)->{
            observer.update();
        });
    }

    @Override 
    public boolean purchase(int quantity) {
        if (this.quantity < quantity) {
            System.out.println("PURCHASE FAILED: " + name + " is out of stock! | " + "Available Quantity: " + quantity);
            return false;
        }
        this.quantity -= quantity;
        System.out.println("PURCHASE SUCCESS: " + quantity + " units of " + name + " | " + "Remaining stock: " + this.quantity);
        return true;
    } 

    @Override 
    public void restock(int quantity) {
        boolean wasOutOfStock = (this.quantity == 0); 
        this.quantity += quantity;
        System.out.println(
                "RESTOCKED: " + name + " - Added " + quantity + " items " + " | " + "Current stock: " + quantity);
        if (wasOutOfStock && this.quantity > 0) {
            notifyStockObservers();
        } 
    }
}
