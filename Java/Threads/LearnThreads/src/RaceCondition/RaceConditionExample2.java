package RaceCondition;

public class RaceConditionExample2 {
    public static void main(String[] args) {
        // Only one threads reads and one thread write
        // No Race Condition but theoritical can be due to visibility problem.
        Counter counter = new Counter();

        Thread t1 = new Thread(getIncrementRunnable(counter, "Thread1 Final Count : "));
        Thread t2= new Thread(getReadingRunnable(counter));
        t1.start();
        t2.start();
    }

    private static Runnable getIncrementRunnable(Counter counter, String message) {
        return () -> {
            for(int i=0;i<1000000;i++){
                counter.incAndGet();
            }
            System.out.println(message + counter.getCount());
        };
    }

    private static Runnable getReadingRunnable(Counter counter) {
        return () -> {
            for(int i=0;i<5;i++) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread2 count: " + counter.getCount());
            }
        };
    }

}
