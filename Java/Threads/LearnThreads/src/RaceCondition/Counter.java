package RaceCondition;

public class Counter {

    private int count = 0;

    public int incAndGet() {
        // Critical Section here
        this.count++;
        return this.count;
    }
    public int getCount(){
        return this.count;
    }
}
