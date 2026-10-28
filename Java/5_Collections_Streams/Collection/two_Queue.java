import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Demonstrates basic Queue interface operations.
 * Shows FIFO (First-In-First-Out) operations using PriorityQueue.
 */
class two_Queue {
    public static void main(String[] args) {
        // Throwing methods
        Queue<Integer> queue = new PriorityQueue<>();
        queue.add(1); queue.add(2); queue.add(3);
        System.out.println("element(): " + queue.element());
        while(!queue.isEmpty())  queue.remove();
        
        // Non-throwing methods
        queue.offer(1); queue.offer(2); queue.offer(3);
        System.out.println("peek(): " + queue.peek());
        while(!queue.isEmpty())  queue.poll();
        System.out.println("peek() on empty: " + queue.peek());

        // PriorityQueue (min-heap)
        System.out.println("\nPriorityQueue:");
        PriorityQueue<Integer> minq = new PriorityQueue<>();
        minq.add(10);  minq.add(5); minq.add(8);
        minq.forEach((Integer a) -> System.out.print(a + " "));   // Level Order Traversal
        System.out.println();
        System.out.print("Min PQ : ");
        while(!minq.isEmpty()) System.out.print(minq.poll() + " ");  

        // Max PQ
        PriorityQueue<Integer> maxQ = new PriorityQueue<>((Integer a, Integer b) -> b-a);
        maxQ.add(1);  maxQ.add(2);  maxQ.add(3);
        System.out.println();
        System.out.print("Max PQ : ");
        while (!maxQ.isEmpty()) System.out.print(maxQ.poll() + " ");
    }
}
