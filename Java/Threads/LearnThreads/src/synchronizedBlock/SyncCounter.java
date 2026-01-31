package synchronizedBlock;

public class SyncCounter {
    private int count = 0;

    public synchronized int getCount(){
        return count;
    }

    public synchronized void incCount() {
        this.count++;
    }
}
