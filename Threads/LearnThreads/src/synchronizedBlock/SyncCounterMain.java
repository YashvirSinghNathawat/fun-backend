package synchronizedBlock;

public class SyncCounterMain {
    public static void main(String[] args) {
        SyncCounter sc = new SyncCounter();

        Thread t1 = new Thread(() -> {
            for(int i=0;i<100000;i++)  sc.incCount();
            System.out.println("Thread 1 Final Count : " + sc.getCount());
        });

        Thread t2 = new Thread(() -> {
            for(int i=0;i<100000;i++)  sc.incCount();
            System.out.println("Thread 1 Final Count : " + sc.getCount());
        });

        t1.start();
        t2.start();


    }
}
