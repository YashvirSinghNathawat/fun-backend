package synchronizedBlock;

public class StaticSynchronizedExchanger {
    private static Object object = null;

    /*
    1. Static Synchronized Method uses Class object as monitor object as static method does not belongs to instance
    2. Since only one class object exists in the Java VM per class,
       only one thread can execute inside a static synchronized method in the same class.
    3. If there are more than one synchronized method, only 1 method can be invoked at a time.
     */
    public static synchronized void setObject(Object o){
        object = o;
    }
    public static synchronized Object getObject() {
        return object;
    }
    public static void setObj(Object o){
        synchronized (StaticSynchronizedExchanger.class) {
            object = o;
        }
    }
    public static Object getObj() {
        synchronized (StaticSynchronizedExchanger.class) {
            return object;
        }
    }
}
