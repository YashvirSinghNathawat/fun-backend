import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Demonstrates basic Collection interface operations.
 * Shows how to add, remove, and query elements in a Collection.
 */
class one_Collection {
    public static void main(String[] args) {
        // Create a Collection (ArrayList) to store Integer values
        Collection<Integer> collectonList = new ArrayList<>();
        
        // Add elements to the collection
        collectonList.add(1);
        collectonList.add(2);
        collectonList.add(3);
        collectonList.add(1);  // Collections can contain duplicates

        // Display the collection contents
        System.out.println("Collection contents: " + collectonList);
        
        // Display the size of the collection
        System.out.println("Size of collection: " + collectonList.size());
        
        // Check if the collection is empty
        System.out.println("Is collection empty: " + collectonList.isEmpty());
        
        // Check if collection contains the element 4
        System.out.println("Does collection contain 4: " + collectonList.contains(4));
        
        // Remove the first occurrence of element 1 from collection
        collectonList.remove(1);
        System.out.println("Collection after removing 1: " + collectonList);
        
        // Create a List from the collection
        List<Integer> list = new ArrayList<>();
        list.addAll(collectonList);
        
        // Remove element at index 1
        list.remove(1);
        System.out.println("List after removing index 1: " + list);
        
        // Remove the first occurrence of Integer value 1
        list.remove(Integer.valueOf(1));
        System.out.println("List after removing Integer value 1: " + list);

        // Remove all elements from list that are present in collectonList
        list.removeAll(collectonList);
        System.out.println("List after removing all elements from collectonList: " + list);

        // Add all elements from collectonList back to list
        list.addAll(collectonList);
        System.out.println("List after adding all elements from collectonList: " + list);
        
        // Iterate through list using forward iterator
        Iterator<Integer> forwardIterator = list.iterator();
        System.out.println("\nIterating through list using Iterator:");
        while(forwardIterator.hasNext()) {
            System.out.println("  Element: " + forwardIterator.next());
        }

        // Sort the list in ascending order
        Collections.sort(list);
        System.out.println("\nList after sorting in ascending order: " + list);
        
        // Reverse the order of elements in the list
        Collections.reverse(list);
        System.out.println("List after reversing: " + list);
        
        // Rotate the list by 1 position to the right
        Collections.rotate(list, 1);
        System.out.println("List after rotating right by 1: " + list);
        
        // Find and display the maximum element in the list
        System.out.println("\nMaximum element in list: " + Collections.max(list));
        
        // Clear all elements from the list
        list.clear();
        System.out.println("List after clearing: " + list);
    }
}