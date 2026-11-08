public class ECommerceStockNotificationApp {
    public static void main(String[] args) {
        StockAvailabilityObservable iphoneProduct = new IPhoneStockObservable("IP15", 5);

        StockNotificationObserver emailNotificationObserver = new EmailNotificationObserver("Yash", "yashvirnathawat@gmail.com");
        StockNotificationObserver pushNotificationObserver = new PushNotificationObserver("Gaurav", "9351");
        iphoneProduct.addObserver(emailNotificationObserver);
        iphoneProduct.addObserver(pushNotificationObserver);

        // Black Friday Sale - Purchase all 10 units
        iphoneProduct.purchase(5);

        // Restock 20 units of iPhone 15
        iphoneProduct.restock(20);

        iphoneProduct.purchase(1);

    }
}
