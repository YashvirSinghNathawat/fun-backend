package volatilePackage;

public class Exchanger {
    private Object object = null;
    /*
    1.  By declaring variable volatile-> You tell JVM alway read this from MM and
        when it is changed it should be written directly to main memory immediatle
    2.  Thread2 can see old value of variable which can cause malfunction from MM
    3.  Java Volatile visibility Guarentee
    4.  Instruction reordering can break the Java volatile visibility Guarentee
    5.  To solve above Java Volatility "Happens before Guarentee" comes.
    6.  All writes before volatile write will be before.
    7.  All reads after volatile read will be after
    8.  Volatile causes performance issue so only be used we need visibility guarentee
        between threads.
     */
    private volatile boolean hasNewObject = false;


}
