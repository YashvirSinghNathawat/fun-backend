package openclosed;

import srp.Invoice;

public class DatabaseInvoiceDao implements InvoiceDao {
    Invoice invoice;
    DatabaseInvoiceDao(Invoice in) {
        invoice = in;
    }
    @Override 
    public void save() {
        System.out.println("Saving to DB");
    }
}