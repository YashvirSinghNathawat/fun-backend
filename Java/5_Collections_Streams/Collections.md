# Collections

## Notes - [Collection_1](https://drive.google.com/file/d/1H_vxlVh01C59LhArLdy7o0mOO_ITPPov/view?usp=sharing) | [Collection_2](https://drive.google.com/file/d/1cuxpSOH8a2E5aHZouFUV6sw97ed_lF9E/view?usp=sharing)

## Q&A

---

### Iteration

**Q: Why was Iterable introduced when we already had Collection?**

A: `Iterable` provides a common way to iterate over elements, even for classes that are not `Collection`.

```java
class MyData implements Iterable<String> {
    // provides iterator()
}
```

```
Iterable
   ↑
Collection
   ↑
List, Set, Queue
```

Example:
```java
for (String item : myData) {
    System.out.println(item);
}
```

---

**Q: How does the enhanced for loop work internally in Java?**

A:

**For Collections:**

Before compilation:
```java
for (String name : list) {
    System.out.println(name);
}
```

After compilation (roughly):
```java
Iterator<String> it = list.iterator();

while (it.hasNext()) {
    String name = it.next();
    System.out.println(name);
}
```

**For Arrays:**

Before compilation:
```java
for (String name : arr) {
    System.out.println(name);
}
```

After compilation (roughly):
```java
for (int i = 0; i < arr.length; i++) {
    String name = arr[i];
    System.out.println(name);
}
```

---

### Sorting

**Q: What is the difference between Comparable and Comparator in Java?**

A:

| | `Comparable` | `Comparator` |
|---|---|---|
| **Purpose** | Natural/default sorting | Custom sorting |
| **Implemented by** | The class itself | A separate class or lambda |
| **Method** | `compareTo()` | `compare()` |
| **Sorting logics** | One default(defined in class) | Multiple |
| **Package** | `java.lang` | `java.util` |

**1. Comparable Example:**
```java
class Employee implements Comparable<Employee> {
    int id;
    String name;

    Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int compareTo(Employee other) {
        return this.id - other.id; // Sort by ID
    }
}
```

```java
List<Employee> employees = new ArrayList<>();

employees.add(new Employee(2, "John"));
employees.add(new Employee(1, "Alice"));

Collections.sort(employees); // Uses compareTo()
```

**2. Comparator Example:**
```java
Comparator<Employee> sortByName =
        (e1, e2) -> e1.name.compareTo(e2.name);

Collections.sort(employees, sortByName);
```

---

**Q: Why do we need Comparable if we have Comparator?**

A: Although `Comparator` can provide the same sorting logic, `Comparable` is useful for defining the natural/default ordering of a class.

- `Comparable` → defines one natural ordering **inside** the class
- `Comparator` → defines external/custom orderings **outside** the class

With `Comparable`, sorting is simpler because no comparator needs to be provided.

```java
class Employee implements Comparable<Employee> {
    int id;

    @Override
    public int compareTo(Employee other) {
        return Integer.compare(this.id, other.id);
    }
}
```

Now:
```java
Collections.sort(employees); // automatically sorts by natural ordering (ID)
```

---

### Map Internals

**Q: How does HashMap work internally in Java? Explain how data is stored, how put() and get() work, the hashCode()/equals() contract, load factor, threshold, resizing/rehashing, and treeification.**

A:

HashMap stores data as key-value pairs using an internal array called a `table`. Each position in this array is called a bucket.

```
HashMap
   |
   v
table[]
+----+----+----+----+----+
|    |    |    |    |    |
+----+----+----+----+----+
        |
        v
      Entry
    +---------+
    | key     |
    | value   |
    | hash    |
    | next    |
    +---------+
```

In modern Java, the internal entry is called `Node<K,V>`.

**1. What happens when we call put()?**

```java
Map<String, Integer> map = new HashMap<>();
map.put("Apple", 100);
```

**Step 1: Calculate the hash**

```
key
 ↓
hashCode()
 ↓
hash spreading
```

**Step 2: Find the bucket**

```java
bucketIndex = hash & (table.length - 1)
```

**Step 3: Store the Entry**

```java
Node<K, V> {
    int hash;
    K key;
    V value;
    Node<K, V> next;
}
```

```
Bucket 5
+----------------------+
| hash                 |
| key = "Apple"        |
| value = 100          |
| next = null          |
+----------------------+
```

**2. What happens if two keys go to the same bucket?**

This is called a hash collision. Collisions are stored using a linked list:

```
table[5]
   |
   v
+---------+      +---------+
| Apple   | ---> | Orange  |
| 100     |      | 200     |
+---------+      +---------+
```

In modern Java, if a bucket becomes sufficiently crowded, this linked structure can be converted into a Red-Black Tree.

**3. How does get() work?**

```
"Apple"
   ↓
hashCode()
   ↓
hash spreading
   ↓
calculate bucket index
   ↓
go to that bucket
   ↓
compare keys
   ↓
return value
```

HashMap uses both hash and key equality:

```java
if (node.hash == hash &&
    (node.key == key || key.equals(node.key))) {
    return node.value;
}
```

`hashCode()` finds the possible bucket; `equals()` finds the exact key.

**4. hashCode() and equals() contract**

The most important rule:

> If two objects are equal according to `equals()`, they **must** have the same `hashCode()`.

```
equals() == true
        ↓
hashCode() MUST be same

hashCode() same
        ↓
equals() does NOT have to be true  ← that's just a collision
```

If you override `equals()`, you must also override `hashCode()`. Failing to do so can cause HashMap to store entries in different buckets and fail to retrieve them.

**5. Load Factor and Threshold**

```
Capacity     = 16
Load Factor  = 0.75  (default)
Threshold    = 16 × 0.75 = 12
```

When the number of stored entries exceeds the threshold, the map resizes.

**6. Resizing / Redistribution**

When the threshold is exceeded, HashMap doubles the capacity and redistributes entries:

```
16 buckets → 32 buckets
```

Each entry either stays at `oldIndex` or moves to `oldIndex + oldCapacity`. The hash is not recalculated from scratch — redistribution uses the already-stored hash.

**7. Treeification**

```
TREEIFY_THRESHOLD    = 8
MIN_TREEIFY_CAPACITY = 64
UNTREEIFY_THRESHOLD  = 6
```

When a bucket reaches ~8 nodes, HashMap may convert the linked list into a Red-Black Tree, improving worst-case lookup from O(n) to O(log n). However, if the table capacity is below 64, HashMap resizes instead of treeifying.

**8. Complete put() flow**

```
map.put(key, value)
        ↓
Calculate key.hashCode() + hash spreading
        ↓
Calculate bucket index
        ↓
Bucket empty? ──Yes──> Create Node
        │
        No
        ↓
Same key exists? ──Yes──> Replace value
        │
        No
        ↓
Collision → Add Node to bucket
        ↓
Too many nodes? → Treeify if table.length >= 64
        ↓
size > threshold? → Resize + redistribute
```

**9. Complete get() flow**

```
map.get(key)
     ↓
Calculate hash + bucket index
     ↓
Compare hash → compare key via equals()
     ↓
Found? ──Yes──> return value
     │
     No ──> continue / return null
```

---

**Q: How does LinkedHashMap maintain insertion order internally?**

A:

LinkedHashMap is a subclass of HashMap. It uses the same hash table for storing and finding entries, but additionally maintains a **doubly linked list** connecting all entries in insertion order.

```
LinkedHashMap
     |
     +---- Hash table        → Fast lookup
     +---- Doubly linked list → Maintains order
```

**Example**

```java
Map<String, Integer> map = new LinkedHashMap<>();
map.put("Apple", 100);
map.put("Banana", 200);
map.put("Orange", 300);
```

The hash table stores entries by bucket (order unrelated to insertion):

```
bucket 2 → Banana
bucket 5 → Apple
bucket 9 → Orange
```

But LinkedHashMap also maintains:

```
head
 ↓
Apple ⇄ Banana ⇄ Orange
                   ↑
                  tail
```

So iteration always follows insertion order:

```java
for (String key : map.keySet()) {
    System.out.println(key); // Apple, Banana, Orange
}
```

**How is this implemented?**

LinkedHashMap uses a specialized node that extends `HashMap.Node` with two extra references:

```java
class Entry<K,V> extends HashMap.Node<K,V> {
    Entry<K,V> before;
    Entry<K,V> after;
}
```

Each node therefore holds:
- `hash`, `key`, `value`, `next` → for the hash bucket/collision chain
- `before`, `after` → for the ordered iteration linked list

**What happens during put()?**

LinkedHashMap inserts the entry into the hash table normally, then appends it to the tail of the doubly linked list:

```
After put("Apple"):   Apple
After put("Banana"):  Apple ⇄ Banana
After put("Orange"):  Apple ⇄ Banana ⇄ Orange
```

**Key difference from HashMap**

```
HashMap
   → Hash table only
   → No guaranteed iteration order

LinkedHashMap
   → Hash table        (lookup)
   → Doubly linked list (order)
```

The hash table is not ordered — the linked list is what maintains insertion order.

---
