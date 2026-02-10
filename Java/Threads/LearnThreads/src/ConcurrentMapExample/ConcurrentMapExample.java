package ConcurrentMapExample;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentMapExample {

    public static void main(String[] args) {

        Map hashmap = new HashMap();
        /*
        Implementation in hash table are synchronized good thread safety
        Not good concurrency because of locking too much
         */
        Map hashtable = new Hashtable();

        /*
        Offers better concurrency than HashTable  ConcurrentHashMap does not lock the entire Map
        when writing to it. It only locks the part of the Map that is being written to, internally.
        Lots of threads can read and write at same part because only part of bucket is locked.
         */
        Map map = new ConcurrentHashMap();
        ConcurrentMap concurrentMap = new ConcurrentHashMap();

        concurrentMap.put("Key", "value");

        // With Java Generics
        ConcurrentMap<String, String> concurrentMap2 = new ConcurrentHashMap<>();
        concurrentMap2.put("Key2", "Value2");
        concurrentMap2.remove("Key2");

        /*
        Slipped Conditons -> Even though the methods of a ConcurrentHashMap are thread safe -
        you can still run into concurrency problems if you using it the "wrong way".
        2 Threads first check same condition and updates map and one gets overrides.
         */
        if (!concurrentMap2.containsKey("Key2")){
            concurrentMap2.put("Key2", "Value2");
        }

        /*
        Solution -> putIfAbsent or use computeIfAbsent
         */
        concurrentMap2.putIfAbsent("Key2", "value2");
        concurrentMap2.computeIfAbsent("Key2", (key) -> {
            return "val2";
        });

    }
}
