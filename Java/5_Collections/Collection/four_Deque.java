import java.util.ArrayDeque;
import java.util.Deque;

public class four_Deque {
    public static void main(String[] args) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(1); 
        queue.addLast(2);
        queue.addLast(3);
        System.out.print("Queue : ");
        while(!queue.isEmpty()) System.out.print(queue.removeFirst() + " ");
        System.out.println();
        System.out.println("***************************************");

        Deque<Integer> stack = new ArrayDeque<>();
        stack.addFirst(1);
        stack.addFirst(2);
        stack.addFirst(3);
        System.out.print("Stack : ");
        while (!stack.isEmpty())
            System.out.print(stack.removeFirst() + " ");
        
    }
}
