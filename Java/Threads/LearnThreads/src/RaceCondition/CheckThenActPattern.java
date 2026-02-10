package RaceCondition;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CheckThenActPattern {
    public static void main(String[] args) {
        Map<String, String> sharedMap = new HashMap<>();
        /*
        Concurrent wont allow both threads to remove same rather one removes and
        others returns null so once in a while if(val == null) can happen
         */
        Thread t1 = new Thread(getRunnable(sharedMap));
        Thread t2 = new Thread(getRunnable(sharedMap));
//        t1.start();
//        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (Exception e) {

        }
        Thread t3 = new Thread(getRunnableSynchronized(sharedMap));
        Thread t4 = new Thread(getRunnableSynchronized(sharedMap));
        t3.start();
        t4.start();

    }

    private static Runnable getRunnable(Map<String, String> sharedMap) {
        return () -> {
            for(int i=0;i<1_000_000;i++) {
                if (sharedMap.containsKey("key")) {
                    String val = sharedMap.remove("key");
                    if (val == null) {
                        System.out.println(
                                "Iteration : " + i + ": Value for 'key' was null"
                        );
                    }
                } else {
                    sharedMap.put("key", "value");
                }
            }
        };
    }

    private static Runnable getRunnableSynchronized(Map<String, String> sharedMap) {
        return () -> {
            for(int i=0;i<1_000_000;i++) {
                synchronized (sharedMap) {
                    if (sharedMap.containsKey("key")) {
                        String val = sharedMap.remove("key");
                        if (val == null) {
                            System.out.println(
                                    "Iteration : " + i + ": Value for 'key' was null"
                            );
                        }
                    } else {
                        sharedMap.put("key", "value");
                    }
                }
            }
        };
    }
}
