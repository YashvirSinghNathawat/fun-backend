package SingleTon;

public class SynchronizedSingleTon {
    private static SynchronizedSingleTon dbCon;

    private SynchronizedSingleTon(){

    }

    public synchronized static SynchronizedSingleTon getInstance() {
        if (dbCon == null) {
            dbCon = new SynchronizedSingleTon();
        }
        return dbCon;
    }
}
