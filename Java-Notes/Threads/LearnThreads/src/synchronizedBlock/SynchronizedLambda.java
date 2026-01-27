package synchronizedBlock;

import java.util.function.Consumer;

public class SynchronizedLambda {
    static int counter = 0;
    public static void main(String[] args) {

        Consumer<String> func = (String param) -> {
            synchronized (SynchronizedLambda.class) {
                System.out.println(
                        Thread.currentThread().getName() + " step 1: " + counter
                );
                for(int i=0;i<100000;i++) {
                    counter++;
                }
                System.out.println(
                        Thread.currentThread().getName() + " step 2: " + counter
                );
            }
        };
        Thread t1 = new Thread(() -> {
            func.accept("Parameter");
        }, "Thread1");

        Thread t2 = new Thread(() -> {
            func.accept("Somethind");
        }, "Thread2");
        t1.start();
        t2.start();
    }
}
