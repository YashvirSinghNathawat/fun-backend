public class EmailNotificationObserver implements StockNotificationObserver{
    private final String userId;
    private final String emailAddress;

    EmailNotificationObserver(String id, String email) {
        userId = id;
        emailAddress = email;
    }

    @Override 
    public void update() {
        sendEmail();
    }

    private void sendEmail() {
        System.out.println("!! EMAIL SENT to: " + emailAddress + " - " + "Product is back in stock! Hurry Up!!");
    }
    @Override 
    public String getUserId() {
        return userId; 
    }
}
