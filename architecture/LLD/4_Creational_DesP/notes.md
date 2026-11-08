# Design Patterns

| Pattern | Notes |
|---|---|
| Abstract Factory | [Notes](https://drive.google.com/file/d/1Y5146LSP4EMRmbfuEgL5NKnjeAkfw4B2/view?usp=sharing) |
| Factory | [Notes](https://drive.google.com/file/d/1hPySm3unHvK0bmv7ebSYj6hD5wmXfiWT/view?usp=sharing) |

## Without Factory Pattern

The client creates concrete objects directly using `new`.

```java
public class Main {
    public static void main(String[] args) {

        Shape shape;

        shape = new Circle();
        shape.draw();

        shape = new Rectangle();
        shape.draw();
    }
}
```

**Disadvantages**

1. **Tight Coupling** — Client directly depends on concrete classes (`Circle`, `Rectangle`).
2. **Difficult to Extend** — Adding `Triangle` requires modifying client code.
3. **Violation of Open/Closed Principle** — Client needs changes whenever new shape types are introduced.
4. **Scattered Creation Logic** — If multiple clients create shapes, object creation is duplicated across them.
