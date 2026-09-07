# Annotations

## Notes - [Click](https://drive.google.com/file/d/1qS2eYYwrhphp7d3h17AIx75_CIQ9BGqB/view?usp=drive_link)

## Q&A

**Q: What is the issue with `@SuppressWarnings` in Java?**

A: It hides compiler warnings without fixing the underlying problem, which can allow real issues to go unnoticed.

```java
@SuppressWarnings("unchecked")
List<String> list = (List<String>) obj;
```

The warning is suppressed, but an invalid cast can still cause a `ClassCastException` at runtime.

---

**Q: What is `@SafeVarargs` in Java?**

A: `@SafeVarargs` tells the compiler that a generic varargs method is safe and does not cause heap pollution.

**Why use it?**

Due to type erasure, generic types are not fully available at runtime. `List<Integer>...` is treated as approximately `List[]` at runtime, which can potentially cause heap pollution — so Java emits an unchecked warning. `@SafeVarargs` suppresses this warning when we know the method is safe.

```java
@SafeVarargs
static void print(List<Integer>... lists) {
    for (List<Integer> list : lists) {
        System.out.println(list);
    }
}
```

After type erasure, it is approximately:

```java
static void print(List[] lists) {
    for (List list : lists) {
        System.out.println(list);
    }
}
```

`Integer` is erased because Java removes generic type information at runtime.

**What is heap pollution?**

Heap pollution occurs when a variable with a parameterized type refers to an object not compatible with that type. Because the runtime sees `List[]` instead of `List<Integer>[]`, a method may accidentally place a `List<String>` into an array the compiler treats as containing only `List<Integer>` objects:

```java
static void addWrongList(List<Integer>... lists) {
    Object[] array = lists;
    array[0] = List.of("text");

    Integer value = lists[0].get(0); // ClassCastException
}
```

The compiler believes `lists[0]` is a `List<Integer>`, but it actually contains a `String`. The error may not occur at insertion — it surfaces later when the value is read and Java tries to cast it to `Integer`.

Heap pollution is dangerous because it:
- Breaks the type-safety guarantees of generics
- Causes unexpected `ClassCastException` at runtime
- Makes errors hard to find — the bad assignment and the failure can occur in different places

**A generic varargs method is safe when it does NOT:**
- Store values into the varargs array
- Expose the varargs array to untrusted code
- Pass the array to another method that might modify it
- Perform unsafe casts involving the array

---
