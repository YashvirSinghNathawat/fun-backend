# Table of Contents
1. [OOPS](#oops)
2. [Java and its 3 Main Components](#java-and-its-3-main-components-)
3. [Java Primitive Variables](#java-primitive-variables)
4. [Java Non Primitive Variables](#java-non-primitive-variables)
5. [Methods and Constructor](#methods-and-constructor)
6. [Java Memory Management](#java-memory-management)
7. [Classes in Java](#classes-in-java)

---

# OOPS
1. https://drive.google.com/file/d/1cwZOV5mkK4B4RUoHtnBeVg-2nFyNI1Z6/view?usp=drive_link

---

**Q: How does encapsulation help achieve loose coupling between classes? Illustrate with a before/after example.**

Without Encapsulation (Tight Coupling)
```java
class Employee {
    public double salary;
}

class Payroll {
    void increment(Employee emp) {
        emp.salary += 1000; // directly depends on Employee's data
    }
}
```

With Encapsulation (Loose Coupling)
```java
class Employee {
    private double salary;

    void incrementSalary(double amount) {
        salary += amount;
    }
}

class Payroll {
    void increment(Employee emp) {
        emp.incrementSalary(1000); // depends only on Employee's behavior
    }
}
```

Encapsulation hides implementation details, so changes inside `Employee` do not affect `Payroll`, leading to loose coupling.

---

**Q: How does encapsulation provide data control?**

Encapsulation provides data control by hiding data using private variables and allowing access through methods, where validation and rules can be applied.

```java
class Employee {
    private int age;

    public void setAge(int age) {
        if (age >= 18) this.age = age;
    }
}
```

Here, `age` cannot be changed directly, ensuring only valid data is stored.

---

**Q: Why is multiple inheritance not allowed in Java?**

Java does not support multiple inheritance with classes to avoid ambiguity (Diamond Problem). If two parent classes have the same method, the child class cannot determine which method to inherit.

```java
class A {
    void show() {}
}

class B {
    void show() {}
}

// Ambiguous: Which show() should C inherit?
class C extends A, B {}
```

---

**Q: What is Aggregation and Composition in Java?**

Both represent a **HAS-A** relationship but differ in lifecycle ownership.

**Aggregation (Weak HAS-A)**
Parent and child objects can exist independently. Parent uses the child but does not own its lifecycle.

```java
class Student {}

class School {
    Student student; // Student is created outside, exists independently
}
```

**Composition (Strong HAS-A)**
Child object depends on the parent and is created/owned by the parent.

```java
class ClassRoom {}

class School {
    ClassRoom classroom = new ClassRoom(); // School creates and owns ClassRoom
}
```

| | Aggregation | Composition |
|---|---|---|
| Relationship | Weak HAS-A | Strong HAS-A |
| Lifecycle | Independent | Dependent on parent |
| Example | School — Student | School — ClassRoom |

---

# Java and its 3 Main Components-
1. Notes - https://drive.google.com/file/d/1VD6gkGKhmyOAEQnEpCfnhOdqoGhD3kso/view?usp=sharing

---

**Q: Can we write and compile Java programs using only JRE?**

No. JRE is only for running Java programs, not developing them.

**JRE (Java Runtime Environment)** — runs Java programs.
- Contains: JVM + core libraries
- Does NOT contain the `javac` compiler

**JDK (Java Development Kit)** — write, compile, and run Java programs.
- Contains: JRE + `javac` compiler + development tools

```
JDK
 └── JRE
      └── JVM
```

**Example:**

If you have a compiled `Hello.class`, JRE is enough:
```bash
java Hello
```

If you have `Hello.java`, you need JDK to compile it:
```bash
javac Hello.java
java Hello
```
---

**Q: What are the main components of JVM?**

1. **Class Loader** — Loads `.class` files into memory.
   - Example: Loads `Hello.class` when we run `java Hello`.

2. **Bytecode Verifier** — Checks bytecode for security and correctness.
   - Example: Prevents execution of invalid or unsafe bytecode.

3. **Runtime Memory Areas** — Stores data during program execution.
   - **Heap:** Stores objects → `Student s = new Student();`
   - **Stack:** Stores method calls and local variables → `int x = 10;`

4. **Execution Engine** — Executes the bytecode. Contains:
   - **Interpreter:** Executes bytecode instruction by instruction.
   - **JIT Compiler:** Converts hotspot bytecode into native machine code for speed.
     - Example: A loop running 1 million times is compiled once into machine code.
   - **Garbage Collector:** Removes objects from heap when no reference points to them.
   
---

**Q: Why do we need JIT when the Interpreter can already execute bytecode?**

The Interpreter executes bytecode line by line and translates it every time, which is slow for repeated code. JIT detects frequently executed code (hotspots), compiles it into native machine code once, and reuses it for faster execution.

**Example — a loop running 1,000,000 times:**

- **Interpreter:** Translates the loop instructions 1,000,000 times.
- **JIT:** Compiles the loop once into machine code and executes it directly for the remaining iterations.

---

**Q: What does JDK contain?**

JDK contains everything required to develop, compile, debug, package, and run Java programs.

- JRE
  - JVM

- Development Tools

| Tool | Purpose | Example |
|---|---|---|
| `javac` | Compiles Java code into bytecode | `javac Hello.java` → `Hello.class` |
| `java` | Runs Java programs | `java Hello` |
| `jdb` | Debugs Java programs | `jdb Hello` |
| `javadoc` | Generates documentation | `javadoc Hello.java` |
| `jar` | Packages `.class` files into JAR | `jar cf app.jar Hello.class` |
| `jshell` | Tests Java statements interactively | `System.out.println("Hello");` |

---

**Q: Why are C/C++ programs platform dependent but Java programs are platform independent?**

The difference lies in what the compiler generates.

- **C/C++:** A platform-dependent compiler converts source code directly into platform-dependent machine code.
  - Example: A Windows compiler creates `Hello.exe`, which runs only on Windows.

- **Java:** A platform-dependent JDK (`javac`) converts Java code into platform-independent bytecode (`Hello.class`). A platform-specific JVM then converts this bytecode into machine code at runtime.
  - Example: The same `Hello.class` runs on Windows, Linux, and macOS using their respective JVMs.

```
C/C++ → Platform-dependent compiler → Platform-dependent machine code

Java  → Platform-dependent JDK → Platform-independent bytecode → Platform-specific JVM execution
```

---

**Q: What is `main()` and how is it connected with JVM?**

`main()` is the entry point of a Java program. When we run a Java program, the JVM looks for the `main()` method and starts execution from it.

```java
class Hello {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
```

JVM loads `Hello.class`, finds the `main()` method, and executes the statements inside it.

**Why is the signature `public static void main(String[] args)`?**

| Keyword | Reason |
|---|---|
| `public` | JVM calls `main()` from outside the class, so it must be accessible everywhere. |
| `static` | JVM calls `main()` without creating an object of the class. |
| `void` | `main()` does not return anything to the JVM. |
| `String[] args` | Allows passing command-line arguments to the program. |

---

**Q: Why can a Java file have only one public class?**

A Java file can have only one public class because the file name must match the public class name. The Java compiler and class loader use this rule to uniquely identify and load classes. If multiple public classes were allowed, the JVM would not know which class name should match the file name.

**Valid:**
```java
// File name: Student.java
public class Student {}
class Teacher {}
```

**Invalid:**
```java
// Cannot have two public classes in one file
public class Student {}
public class Teacher {}
```

`Teacher` cannot be public here — the file name can only match one class.

---

# Java Primitive Variables
1. Notes - https://drive.google.com/file/d/1vWVJ9nAIbih0vier0XD-jFaUCujWuYnU/view

---

**Q: What is the default value behavior for class member variables vs local variables?**

- **Class member variables** are automatically assigned default values by the JVM.
- **Local variables** must be explicitly assigned before use, otherwise the compiler throws an error.

```java
class Employee {
    int age;       // default: 0
    String name;   // default: null
    boolean active; // default: false
}

class Main {
    void show() {
        int x;
        System.out.println(x); // Compile error: variable x might not have been initialized
    }
}
```

| Type | Default Value |
|---|---|
| `int`, `long`, `short`, `byte` | `0` |
| `float`, `double` | `0.0` |
| `boolean` | `false` |
| `char` | `NULL` |
| Object reference | `null` |

---

**Q: What are `float` and `double` in Java? How is their data stored?**

`float` and `double` are primitive types for decimal numbers, following the **IEEE 754 standard**. A number is stored in three parts: **sign bit**, **exponent**, and **mantissa**.

| Feature | `float` | `double` |
|---------|---------|----------|
| Size | 4 bytes (32 bits) | 8 bytes (64 bits) |
| Sign bits | 1 | 1 |
| Exponent bits | 8 | 11 |
| Mantissa bits | 23 | 52 |
| Precision | ~6–7 decimal digits | ~15–16 decimal digits |
| Default decimal type | No | Yes |

`double` provides higher precision and a larger range because it allocates more bits to the exponent and mantissa.


---

# Java Reference/Non Primitive Variables
1. Notes - https://drive.google.com/file/d/19qwmrufd4UeZmNzsDA0dcegqokiJL2IE/view?usp=sharing


**Q: How are String objects stored in Java memory?**

```java
String s1 = "hello";
String s2 = "hello";
String s3 = new String("hello");
```

String literals are stored in the **String Constant Pool (SCP)** inside the heap.
- `s1` creates `"hello"` in SCP.
- `s2` reuses the same `"hello"` object from SCP.
- `new String("hello")` creates a new object in the normal heap, so `s3` points to a different object.

```
SCP:          "hello"
               ↑   ↑
              s1   s2

Heap:         "hello"
                ↑
               s3
```

**Total objects created: 2**
- 1 object in String Constant Pool
- 1 object in Heap

```java
s1 == s2      // true  (same SCP reference)
s1 == s3      // false (different objects)
s1.equals(s3) // true  (same content)
```

---

# Methods and Constructor
1. Notes - https://drive.google.com/drive/folders/1JehUMQvwNDTEqpjoSQo8PZiUn2UxWi9x?dmr=1&ec=wgc-drive-%5Bmodule%5D-goto

---

**Q: What is the difference between default and protected access modifiers in Java?**

The main difference is in their accessibility scope:

- `default` (package-private) members are accessible only within the same package.
- `protected` members are accessible within the same package and also in subclasses, even if those subclasses are in a different package.

Suppose `Parent` is in package A and `Child` extends `Parent` in package B.

```java
// Package A
public class Parent {
    void defaultMethod() { }
    protected void protectedMethod() { }
}
```

```java
// Package B
public class Child extends Parent {
    public void test() {
        // defaultMethod();   // Compilation Error
        protectedMethod();    // Works
    }
}
```

`defaultMethod()` cannot be accessed because it is limited to package A, whereas `protectedMethod()` can be accessed through inheritance in `Child`.

---

**Q: Why is the return type ignored in method overloading?**

Method overloading is resolved using the method name and parameter list. The return type is ignored because the compiler cannot determine which method to call based only on the return type.

```java
int add(int a, int b) { return a + b; }
// double add(int a, int b) { return a + b; } // Compilation Error
```

add(10,20) it will call what? <-- Ambiguity

---

**Q: What issue would occur if static methods were allowed to directly access non-static methods?**

A non-static method belongs to a specific object, but a static method has no object context (`this`). If multiple objects exist, the compiler would not know which object's method should be called.

```java
class Employee {
    String name;

    void printName() {
        System.out.println(name);
    }

    static void show() {
        printName(); // Which Employee object's printName()?
    }
}
```

`show()` has no way to determine whether it should call `printName()` for `employee1`, `employee2`, or any other object.

Static methods belong to the class and are loaded into the Method Area (Metaspace) when the class is loaded. They can be called without creating an object.

Non-static methods are also stored in the Method Area (Metaspace), but they execute in the context of a specific object and require a `this` reference to access instance variables stored in the heap.

---

**Q: Why are static methods not overridden in Java? What issue would it cause?**

Static methods belong to the class, not to an object. Method overriding relies on runtime polymorphism, where the method to execute is decided based on the actual object's type. Since static methods can be called without an object, they are resolved at compile time, not runtime.

If static methods could be overridden, it would create confusion because the compiler and runtime could potentially select different methods.

```java
class Parent {
    static void show() {
        System.out.println("Parent");
    }
}

class Child extends Parent {
    static void show() {
        System.out.println("Child");
    }
}

Parent p = new Child();
p.show(); // Output: Parent
```

The method called depends on the reference type (`Parent`), not the object type (`Child`). This is called **method hiding**, not overriding.

---

# Java Memory Management
1. Notes - https://drive.google.com/file/d/1DZKdTORtASz2sbMUPtLUI9g6uC2mPNAJ/view?usp=sharing

---

# Classes in Java
1. Notes - https://drive.google.com/file/d/1eE5Pculcp0qem6tMPHDz9h-jhMclzGIt/view?usp=sharing
