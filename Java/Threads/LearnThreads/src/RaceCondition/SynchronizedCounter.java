package RaceCondition;

public class SynchronizedCounter {
    private int count = 0;

    public int incAndGet() {
        // Critical Section here
        synchronized (this) {
            this.count++;
            return this.count;
        }
    }
    public int getCount(){
        synchronized (this) {
            return this.count;
        }
    }
}
