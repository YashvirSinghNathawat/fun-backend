package srp;

public class Invoice {
    private Marker marker;
    private int quantity;
    private int total;

    // Multiple Responsibility = Violates SRP
    public Invoice(Marker marker, int quantity) {
        this.marker = marker;
        this.quantity = quantity;
    }

    // Responsibility 1: Calculate total price
    public void calculateTotal() {
        this.total = this.marker.price * quantity;
    }

    // Responsibility 2: Printing Invoice
    public void printInvoice() {
        System.out.println("Printed Invoice");
    }

    // Responsibility 3: Saving Invoice to Database
    public void saveToDatabase() {
        System.out.println("Saved Invoice to Database");
    }
}