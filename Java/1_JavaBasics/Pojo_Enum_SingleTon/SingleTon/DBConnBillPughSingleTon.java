package SingleTon;

public class DBConnBillPughSingleTon {

    private DBConnBillPughSingleTon() {
    }

    private static class BillPughSingleTonHelper {
         private static final DBConnBillPughSingleTon INSTANCE_OBJECT = new DBConnBillPughSingleTon();
    }

    public synchronized static DBConnBillPughSingleTon getInstance() {
        return BillPughSingleTonHelper.INSTANCE_OBJECT;
    }
}
