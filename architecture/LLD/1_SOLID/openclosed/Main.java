package openclosed;

import srp.Invoice;
import srp.Marker;

public class Main {
    public static void main(String[] args) {
        Marker marker = new Marker("Renolds", "Black", 123, 2001);
        Invoice invoice = new Invoice(marker, 2);
        DatabaseInvoiceDao databaseInvoiceDao = new DatabaseInvoiceDao(invoice);
        FileInvoiceDao fileInvoiceDao = new FileInvoiceDao(invoice);
        databaseInvoiceDao.save();
        fileInvoiceDao.save();
    }
}
