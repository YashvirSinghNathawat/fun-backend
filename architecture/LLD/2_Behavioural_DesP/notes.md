# Design Patterns

A **Design Pattern** is a reusable, proven solution to a commonly occurring software design problem.

It provides a standard way to structure code, making it more maintainable, reusable, flexible, and easier to understand.

## Types of Design Patterns (GoF)

| Category | Purpose | Examples |
|---|---|---|
| 1. Creational | How objects are created. | Singleton, Factory, Abstract Factory, Builder, Prototype |
| 2. Structural | How classes and objects are structured or connected. | Adapter, Decorator, Proxy, Facade, Composite, Bridge, Flyweight |
| 3. Behavioral | How objects communicate and distribute responsibilities. | Strategy, Observer, Command, Iterator, State, Template Method, Chain of Responsibility, Mediator, Memento, Visitor, Interpreter |

---

## Behavioral Patterns

| Pattern | Notes |
|---|---|
| Strategy | [Notes](https://drive.google.com/file/d/15GFhAb8XHVlCZdVdeCP2JTXQXTTzysv5/view?usp=sharing) |
| Observer | [Notes](https://drive.google.com/file/d/1l-0zSpFQfdH88hRTATmPm1AioufC0xYU/view?usp=sharing) |

### Why is Strategy behavioural, not structural?

Strategy focuses on **which algorithm an object uses** at runtime, not how objects are composed or connected.

- You have a `Payment` interface.
- Different strategies implement it: `CreditCardPayment`, `UPIPayment`, `PayPalPayment`.
- At runtime, you can switch between payment algorithms.

Structural patterns are about composition/connection (e.g. wrapping, adapting, bridging). Strategy doesn't change structure — it changes behaviour.
