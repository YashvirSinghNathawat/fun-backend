package SingleTon;

public class EagerInitialization {
    private static EagerInitialization dbCon= new EagerInitialization();

    private EagerInitialization(){

    }

    public static EagerInitialization getInstance() {
        return dbCon;
    }
}
