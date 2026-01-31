package VirtualThreads;

import java.util.ArrayList;
import java.util.List;

public class VirtualThread {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            for(int i=0;i<10;i++) {
                System.out.println("Index : " + i);
            }
        };

        Thread vt1 = Thread.ofVirtual().start(runnable);

        // Created + Unstarted
        Thread vt2 = Thread.ofVirtual().unstarted(runnable);

        vt2.start();

        try {
            vt1.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        // We can create a large number of virtual threads
        List<Thread> vThreads = new ArrayList<>();
        int threadCount = 100000;

        for(int i=0;i<threadCount;i++) {
            int vThreadIndex = i;
            Thread vThread = Thread.ofVirtual().start(() -> {
                System.out.println("Thread: " + vThreadIndex);
            });
            vThreads.add(vThread);
        }

        try {
            for(int i=0;i<threadCount;i++) {
                vThreads.get(i).join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
