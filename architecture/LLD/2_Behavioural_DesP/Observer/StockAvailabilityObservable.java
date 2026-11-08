public interface StockAvailabilityObservable {

    // Methods are public + abstract
    void addObserver(StockNotificationObserver observer);
    
    void removeObserver(StockNotificationObserver observer);

    void notifyStockObservers();

    boolean purchase(int quantity);

    void restock(int quantity);
}
