package synchronizedBlock;

public class MultipleMonitorObjects {
    private Object monitor1 = new Object();
    private Object monitor2 = new Object();

    private int counter1 = 0;
    private int counter2 = 0;

    /*
    Since both methods are synchronized uses 2 different monitor object so they both can be invoked same time
    at the same instance of MultipleMonitorObjects
    No two threads can call incCounter1 at same time if they share same instance of MultipleMonitorObjects
     */

    public void incCounter1() {
        synchronized (this.monitor1){
            this.counter1++;
        }
    }

    public void incCounter2() {
        synchronized (this.monitor2){
            this.counter2++;
        }
    }
}
