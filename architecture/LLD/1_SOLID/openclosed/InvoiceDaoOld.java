package openclosed;

import srp.Invoice;

// Violates OCP
public class InvoiceDaoOld {
    private Invoice invoice;

    public InvoiceDaoOld(Invoice invoice) {
        this.invoice = invoice;
    }

    public void saveToDb() {
        System.out.println("Saving to DB");
    }

    public void saveToFile() {
        System.out.println("Saving to File");
    }
}