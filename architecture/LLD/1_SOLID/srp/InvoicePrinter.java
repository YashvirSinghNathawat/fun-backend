package srp;

public class InvoicePrinter {
    Invoice_2 invoice;

    InvoicePrinter(Invoice_2 in) {
        this.invoice = in;
    }
    
    public void printInvoice() {
        System.out.println("PRinting Invoice");
    }
}
