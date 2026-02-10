package RaceCondition;

public class RaceConditionExample {
    public static void main(String[] args) {
        Counter counter = new Counter();

        Thread t1 = new Thread(getRunnable(counter, "Thread1 Final Count : "));
        Thread t2= new Thread(getRunnable(counter, "Thread2 Final Count : "));
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (Exception e) {

        }
        SynchronizedCounter scounter = new SynchronizedCounter();
        Thread t3 = new Thread(getRunnableSynchronized(scounter, "Thread3 Final Count : "));
        Thread t4= new Thread(getRunnableSynchronized(scounter, "Thread4 Final Count : "));
        t3.start();
        t4.start();


    }

    private static Runnable getRunnable(Counter counter, String message) {
        return () -> {
            for(int i=0;i<1000;i++){
                counter.incAndGet();
            }
            System.out.println(message + counter.getCount());
        };
    }

    private static Runnable getRunnableSynchronized(SynchronizedCounter counter, String message) {
        return () -> {
            for(int i=0;i<1000;i++){
                counter.incAndGet();
            }
            System.out.println(message + counter.getCount());
        };
    }
}
