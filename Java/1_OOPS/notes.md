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
