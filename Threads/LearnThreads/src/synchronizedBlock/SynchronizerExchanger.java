package synchronizedBlock;

public class SynchronizerExchanger {
    protected Object object = null;

    /*
    Synchronized block is applied on a monitor object- in this case it is instance of SynchronizerExchanger
     */
    public synchronized void setObject(Object o){
        this.object = o;
    }
    public synchronized Object getObject() {
        return this.object;
    }
    public void setObj(Object o){
        synchronized (this) {
            this.object = o;
        }
    }
    public Object getObj() {
        synchronized (this) {
            return this.object;
        }
    }
}
