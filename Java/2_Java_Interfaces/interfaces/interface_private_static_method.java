
interface Bird {
    void canFly();   // public abstract void fly();

    default void minimumFlyingHeight() {
        privateStaticMethod();
        myStaticPublicMethod();
        privateMethod();
    }

    static void myStaticPublicMethod() {
        privateStaticMethod();
    }
    
    private void privateMethod() {
        privateStaticMethod();
    }

    static private void privateStaticMethod() {

    }
}

public class interface_private_static_method {
    
}
