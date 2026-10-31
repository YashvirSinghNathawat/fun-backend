package srp;

public class Invoice_2 {
    private Marker marker;
    private int quantity;
    private int total;

    // Single Responsibility = Violates SRP
    public Invoice_2(Marker marker, int quantity) {
        this.marker = marker;
        this.quantity = quantity;
    }

    // Responsibility 1: Calculate total price
    public void calculateTotal() {
        this.total = this.marker.price * quantity;
    }
}