import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class five_List {
    
    private static void practiceArrayList() {
        List<Integer> list = new ArrayList<>();
        list.add(0,1); list.add(1,2); list.add(2, 3);
        System.out.println("List: " + list);

        list.replaceAll((Integer a) -> -a);
        System.out.println("List: " + list);
        list.set(0,1);
        list.forEach((Integer a) -> System.out.print(a + " "));
        System.out.println();
        System.out.println(list.get(0));

        list.sort((Integer a, Integer b) -> a-b);
        System.out.println("List: " + list);
        System.out.println("Index of 1: " + list.indexOf(1));

        // Iterating list forward
        ListIterator<Integer> listIterator = list.listIterator();
        System.out.println("**************************");
        System.out.print("Forward Direction : ");
        while(listIterator.hasNext()) {
            Integer next = listIterator.next();
            System.out.print(next + " ");
            if(next==-2) {
                listIterator.add(3);
            }
        }
        System.out.println();
        System.out.println("List: " + list);

        // Iterating list backward
        ListIterator<Integer> revlistIterator = list.listIterator(list.size());
        System.out.println("**************************");
        System.out.print("Backward Direction : ");
        while (revlistIterator.hasPrevious()) {
            Integer prev = revlistIterator.previous();
            System.out.print(prev + " ");
            if (prev == -2) {
                revlistIterator.set(2);
            }
        }
        System.out.println();
        System.out.println("List: " + list);  
        
        System.out.println("**************************");
        List<Integer> newList = list.subList(1,2);
        newList.add(0,1);
        System.out.println("SubList: " + newList);
        System.out.println("List: " + list);
    }

    private static void practiceLinkedList() {
        LinkedList<Integer> list = new LinkedList<>(); // Supports both deque and list
        list.addLast(1);
        list.addFirst(2);
        System.out.println("First Element : " + list.getFirst());

        list.add(1,3);
        System.out.println("First Element : " + list.get(0));
    }
    
    public static void main(String[] args) {
        // practiceArrayList();
        System.out.println("**************************");
        practiceLinkedList();
    }
}
