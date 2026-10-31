public class Main {
    public static void main(String[] args) {
        Marker marker = new Marker("Renolds", "Black", 123, 2001);
        Invoice_2 invoice = new Invoice_2(marker, 2);
        InvoiceDB invoiceDB = new InvoiceDB(invoice);
        InvoicePrinter invoicePrinter = new InvoicePrinter(invoice);

        invoice.calculateTotal();
        invoiceDB.saveTODB();
        invoicePrinter.printInvoice();
    }
}
