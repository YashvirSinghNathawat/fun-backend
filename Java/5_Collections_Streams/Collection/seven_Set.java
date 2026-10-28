import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

public class seven_Set {
    public static void main(String[] args) {
        practiceHashSet();
        // practiceThreadSafeHashSet();
    }

    private static void practiceThreadSafeHashSet() {
        // HashSet not thread safe
        // Set<Integer> s1 = new HashSet<>();
        Set<Integer> s1 = ConcurrentHashMap.newKeySet();
        s1.add(1);
        s1.add(2);
        s1.add(4);
        Iterator<Integer> it1 = s1.iterator();
        while(it1.hasNext()) {
            Integer val = it1.next();
            if (val == 2) {
                s1.remove(val);
            }
        }
        s1.forEach((Integer a) -> System.out.print(a + " "));
    }

    private static void practiceHashSet() {
        // Set<Integer> s1 = new HashSet<>();
        // Set<Integer> s1 = new LinkedHashSet<>();   // Maintais Insertion order
        Set<Integer> s1 = new TreeSet<>();   // Sorted Order
        s1.add(1);  s1.add(21); s1.add(4);

        Set<Integer> s2 = new HashSet<>();
        s2.add(1); s2.add(2); s2.add(3);

        s1.addAll(s2);
        System.out.println("Union : ");
        s1.forEach((Integer a) -> System.out.print(a + " "));
        System.out.println();

        s1 = new HashSet<>();
        s1.add(1); s1.add(2); s1.add(4);

        s1.removeAll(s2);
        System.out.println("Difference : ");
        s1.forEach((Integer a) -> System.out.print(a + " "));
        System.out.println();

        s1 = new HashSet<>();
        s1.add(1);
        s1.add(2);
        s1.add(4);

        s1.retainAll(s2);
        System.out.println("Intersection : ");
        s1.forEach((Integer a) -> System.out.print(a + " "));
        System.out.println();
    }
}
