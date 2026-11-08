public class PushNotificationObserver implements StockNotificationObserver{
    private final String userId;
    private final String deviceToken;

    PushNotificationObserver(String id, String token) {
        this.userId = id;
        deviceToken = token;
    }

    @Override 
    public void update() {
        sendPushNotification();
    }

    private void sendPushNotification() {
        System.out.println("!! PUSH NOTIFICATION SENT to: " + deviceToken + " - " + "Product is back in stock! Hurry Up!!");
    }

    @Override
    public String getUserId() {
        return userId;
    }
}
