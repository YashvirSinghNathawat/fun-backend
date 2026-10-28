import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class six_Map {
    public static void main(String[] args) {
        // pracHashMap();
        // pracLinkedHashMap();
        pracTreeMap();
    }

    private static void pracTreeMap() {
        Map<Integer, String> mp = new TreeMap<>((Integer a, Integer b) ->  b-a);
        mp.put(1, null);
        mp.put(21, "2345");
        mp.put(23, "4567");
        mp.put(124, "32853");

        mp.putIfAbsent(21, "7839");
        mp.putIfAbsent(23, "2134");
        for (Map.Entry<Integer, String> ent : mp.entrySet()) {
            System.out.println("Key : " + ent.getKey() + " Value : " + ent.getValue());
        }
    }
    
    private static void pracLinkedHashMap() {
        // Maintain Insertion Order
        // Map<Integer, String> mp = new LinkedHashMap<>();
        // Maintain Access Order
        Map<Integer, String> mp = new LinkedHashMap<>(16, 75F, true);
        mp.put(null, "1234");
        mp.put(1, null);
        mp.put(21, "2345");
        mp.put(23, "4567");
        mp.put(124, "32853");

        mp.putIfAbsent(21, "7839");
        mp.putIfAbsent(23, "2134");
        for (Map.Entry<Integer, String> ent : mp.entrySet()) {
            System.out.println("Key : " + ent.getKey() + " Value : " + ent.getValue());
        }
    }

    private static void pracHashMap() {
        /* MAP INTERFACE */
        Map<Integer, String> mp = new HashMap<>();
        mp.put(null, "1234");
        mp.put(1, null);
        mp.put(21, "2345");
        mp.put(23, "4567");
        mp.put(124, "32853");

        mp.putIfAbsent(4, "7839");
        mp.putIfAbsent(1, "2134");

        System.out.println("=== Initial Map ===");
        for (Map.Entry<Integer, String> ent : mp.entrySet()) {
            System.out.println("Key : " + ent.getKey() + " Value : " + ent.getValue());
        }

        System.out.println("\n=== Contains Key 1? ===");
        System.out.println(mp.containsKey(1));

        System.out.println("\n=== Contains Value '1234'? ===");
        System.out.println(mp.containsValue("1234"));

        System.out.println("\n=== Get Key 1 ===");
        System.out.println(mp.get(1));

        System.out.println("\n=== Remove Key 1 ===");
        System.out.println(mp.remove(1));

        System.out.println("\n=== Get Key 5 with Default ===");
        System.out.println(mp.getOrDefault(5, "Default"));

        System.out.println("\n=== Map after removal ===");
        for (Map.Entry<Integer, String> ent : mp.entrySet()) {
            System.out.println("Key : " + ent.getKey() + " Value : " + ent.getValue());
        }

        System.out.println("\n=== All Keys ===");
        for (Integer ent : mp.keySet()) {
            System.out.println("Key : " + ent);
        }

        System.out.println("\n=== All Values ===");
        for (String val : mp.values()) {
            System.out.println("Values : " + val);
        }
    }
}
