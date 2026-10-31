package srp;

public class InvoiceDB {
    Invoice_2 invoice;

    InvoiceDB(Invoice_2 in) {
        this.invoice = in;
    }

    public void saveTODB(){
        System.out.println("Save TO B");
    }
}

