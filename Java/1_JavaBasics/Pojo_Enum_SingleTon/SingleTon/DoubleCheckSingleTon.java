package SingleTon;

public class DoubleCheckSingleTon {
    // Volatile Variable = Visibility + No Reordering
    private static volatile DoubleCheckSingleTon dbCon;

    private DoubleCheckSingleTon(){
    }

    public synchronized static DoubleCheckSingleTon getInstance() {
        if (dbCon == null) {
            synchronized (DoubleCheckSingleTon.class) {
                dbCon = new DoubleCheckSingleTon();
            }
        }
        return dbCon;
    }
}
