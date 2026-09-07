# Exception Handling

## Notes - [Click](https://drive.google.com/file/d/1EV7NgRP18ltDDG7BAkQ7kwlPmGVrpTVJ/view?usp=sharing)

## Q&A

**Q: What is the difference between an Error and an Exception in Java?**

A: Both `Error` and `Exception` are subclasses of the `Throwable` class, but they represent different types of problems.

| | `Error` | `Exception` |
|---|---|---|
| **Represents** | Serious problems in the JVM or system | Problems that occur during program execution |
| **Handleable?** | Usually cannot be handled by the application | Can often be handled using `try-catch` |
| **Examples** | `OutOfMemoryError`, `StackOverflowError` | `IOException`, `NullPointerException` |

---

**Q: What is an `OutOfMemoryError` in Java?**

A: `OutOfMemoryError` occurs when the JVM does not have enough memory to create new objects.

```java
List<int[]> list = new ArrayList<>();

while (true) {
    list.add(new int[1_000_000]);
}
```

The program keeps consuming memory until the JVM runs out of memory.

---

**Q: What is a `StackOverflowError` in Java?**

A: `StackOverflowError` occurs when the call stack becomes full, usually because of infinite or excessively deep method calls.

```java
void print() {
    print(); // Calls itself infinitely
}
```

Eventually, the stack becomes full and Java throws a `StackOverflowError`.

---
