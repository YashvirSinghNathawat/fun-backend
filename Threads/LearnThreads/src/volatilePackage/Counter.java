package volatilePackage;

public class Counter {
    private volatile int count = 0;
    /*
    1.  Java volatile is not always enough
     */
    public boolean inc() {
        // Two threads can come here with value 9 and can both increment
        if(this.count == 10) {
            return false;
        }
        /* Another problem is increment count is not atomic operation
            Read of variable(local register)->Incr -> Write of variable
            Solution -> Make this synchronized or use AtomicCountInteger
         */
        this.count++;
        return true;
    }
}
