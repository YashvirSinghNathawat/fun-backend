package synchronizedBlock;

public class SharedMonitorObject {
    private Object monitor = new Object();
    private int counter = 0;

    public SharedMonitorObject(Object monitor) {
        if (monitor == null) {
            throw new IllegalArgumentException("Monitor Object cannot be null");
        }
        this.monitor = monitor;
    }

    public void incCounter() {
        synchronized (this.monitor) {
            this.counter++;
        }
    }

}
