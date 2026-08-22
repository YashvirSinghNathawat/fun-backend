package SingleTon;

public class LazyInitialization {
    private static LazyInitialization dbCon;

    private LazyInitialization(){

    }

    public static LazyInitialization getInstance() {
        if (dbCon == null) {
            dbCon = new LazyInitialization();
        }
        return dbCon;
    }
}
