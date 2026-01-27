package synchronizedBlock;

public class Reentrance {
    /*
         Once a thread entered a synchronized block. The thread is said to "hold
         the lock" on the monitoring object.
         If the thread calls another method which is synchronized on same monitor
         It is allowed to go into it.
    */
    private int count = 0;

    public synchronized void inc() {
        this.count++;
    }

    public synchronized int incAndGet() {
        inc();
        return this.count;
    }

}
