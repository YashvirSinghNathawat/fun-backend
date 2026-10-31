package openclosed;

import srp.Invoice;

public class FileInvoiceDao implements InvoiceDao {
    Invoice invoice;

    FileInvoiceDao(Invoice in) {
        invoice = in;
    }

    @Override
    public void save() {
        System.out.println("Saving to File");
    }
}