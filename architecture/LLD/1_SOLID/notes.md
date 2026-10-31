# Low-Level Design (LLD)

> [!tip] What is LLD?
> LLD is about **how** each piece of a system works internally — classes, interfaces, relationships, and patterns.

---

## Table of Contents
[[#1. HLD vs LLD]]
[[#2. Design Pattern Categories]]
[[#3. Object Relationships]]
[[#4. SOLID Principles]]

---

## 1. HLD vs LLD

> [!abstract] Analogy
> Think of building a city vs designing a single building:
> - **HLD** = city map — roads, zones, utilities, how districts connect
> - **LLD** = building blueprint — rooms, walls, wiring, floor plan

| | HLD | LLD |
|---|---|---|
| Scope | System-level | Class-level |
| Focus | Architecture | Implementation |
| Artifacts | Services, DBs, APIs | Classes, methods, fields |
| Question | *What exists & how do they talk?* | *How does each piece work?* |

### HLD Example — E-commerce System

```
User
  ↓
API Gateway
  ↓
Order Service ──────→ Payment Service
      ↓
   Database
```

> [!note] HLD decides
> Which services exist, how they communicate, which DB to use, scalability strategy.

### LLD Example — Order Service internals

```java
class Order {
    private int id;
    private List<OrderItem> items;

    double calculateTotal() { ... }
}

class OrderItem {
    private Product product;
    private int quantity;
}
```

> [!note] LLD decides
> Class structure, method signatures, fields, design patterns used.

---

## 2. Design Pattern Categories

> [!tip] Memory Trick
> **C → S → B** — *Create → Structure → Behave*

### Creational — *How objects are born*

> [!info] Purpose
> Control object creation to avoid tight coupling or complex instantiation.

| Pattern | One-liner |
|---|---|
| Singleton | Only one instance ever exists |
| Factory Method | Subclasses decide which object to create |
| Abstract Factory | Factory of factories |
| Builder | Step-by-step construction of complex objects |
| Prototype | Clone an existing object |

### Structural — *How objects are assembled*

> [!info] Purpose
> Compose classes and objects into larger structures.

| Pattern | One-liner |
|---|---|
| Adapter | Makes incompatible interfaces work together |
| Decorator | Adds behavior without changing the class |
| Facade | Simple interface over a complex subsystem |
| Proxy | Controls access to another object |
| Composite | Treats a group of objects like a single object |

### Behavioral — *How objects talk to each other*

> [!info] Purpose
> Define communication and responsibility between objects.

| Pattern | One-liner |
|---|---|
| Observer | Notify many objects when one changes state |
| Strategy | Swap algorithms at runtime |
| Command | Encapsulate a request as an object |
| State | Change behavior when internal state changes |
| Template Method | Skeleton in base class, steps in subclass |

---

## 3. Object Relationships

### Is-A vs Has-A

| | Is-A | Has-A |
|---|---|---|
| Mechanism | `extends` / `implements` | Composition / Aggregation |
| Meaning | Child is a type of parent | One class contains another |
| Example | `Dog` is-a `Animal` | `Car` has-a `Engine` |

```java
// Is-A
class Dog extends Animal { }

// Has-A
class Car {
    private Engine engine;   // Car has-a Engine
}
```

---

### Association → Aggregation → Composition

> [!abstract] The Spectrum
> ```
> Loosely coupled ◄─────────────────────────► Tightly coupled
>   Association        Aggregation             Composition
>   (uses)             (has, shared)           (owns, lifecycle tied)
> ```

#### Association — *uses*

Two independent objects reference each other. Neither owns the other.

```java
class Student {
    private Teacher teacher;   // Student uses a Teacher
}
```

```
Student ─────── Teacher     (both exist independently)
```

#### Aggregation — *has, but shared*

Container holds a reference, but the contained object can outlive the container.

```java
class Department {
    private List<Teacher> teachers;   // passed in, not created here

    Department(List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
```

```
Department ◇──── Teacher    (Teacher survives if Department is deleted)
```

#### Composition — *owns*

Container creates and owns the contained object. If container dies, so does the contained.

```java
class House {
    private Room room;

    House() {
        this.room = new Room();   // Room belongs to House
    }
}
```

```
House ◆──── Room    (Room cannot exist without House)
```

### Quick Reference

| Relationship | Symbol | Ownership | Lifecycle |
|---|---|---|---|
| Association | `───` | None | Independent |
| Aggregation | `◇───` | Weak | Independent |
| Composition | `◆───` | Strong | Dependent on owner |

---

## 4. SOLID Principles

> [!tip] Why SOLID?
> **Maintainability** — easy to change one thing without breaking others
> **Readability** — each class/method has a clear, focused purpose
> **Fewer bugs** — changes are isolated, side effects are minimised

| Letter | Principle | One-liner | Notes |
|---|---|---|---|
| **S** | Single Responsibility | A class should have only one reason to change | [Notes](https://drive.google.com/file/d/1B91WZmdaEhVsI9Pc_dDmoub5eCdNO00O/view?usp=sharing) |
| **O** | Open/Closed | Open for extension, closed for modification | [Notes](https://drive.google.com/file/d/1x7n9D6aaVPELLUPaHVgfEACdUvJOHG2t/view?usp=sharing) |
| **L** | Liskov Substitution | Subtypes must be substitutable for their base type | [Notes](https://drive.google.com/file/d/1BhCq_QKw_Y_tJjJhfeUvXQbW3ksOMyNx/view?usp=sharing) |
| **I** | Interface Segregation | Don't force classes to implement what they don't use | [Notes](https://drive.google.com/file/d/1n3Iypz8LTgN8WfoiS-XMQQ159KBvsZZg/view?usp=sharing) |
| **D** | Dependency Inversion | Depend on abstractions, not concrete implementations | [Notes](https://drive.google.com/file/d/1GfckYdvaEz0zUhKu_6y1KcwsULFhna7G/view?usp=sharing) |
