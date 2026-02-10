package synchronizedBlock;

public class MizedSynchronization {
    private static Object staticObject = null;

    public Object instanceObject = null;

    public static synchronized void setStaticObject(Object obj) {
        // setStaticObject method is synchronize on class object MizedSynchronization
        staticObject = obj;
    }

    public synchronized void setInstanceObject(Object obj) {
        // setInstanceObject method is synchronized on instance of MizedSynchronization
        this.instanceObject = obj;
    }

    /*
    1. Two methods are not syncrhonized on same monitor object\
    2. Since monitor object are different we can have 1 thread calling setStaticObject and another threading
        calling instance method setInstanceObject at the same time.
    3.  However if two threads access setStaticObject one will be blocked
    4.  Similarly if two threads were calling setInstanceObject on same instance they will be blocked but if
        they are calling on two instances then setInstanceObject is synchronized on 2 different monitor object
     */
}
