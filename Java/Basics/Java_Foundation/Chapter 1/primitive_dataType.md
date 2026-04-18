# 5 Non Primitive
1. Strings
2. Arrays
3. Classes
4. Enums
5. Records

# Types of Variables in Java

There are four types of variables in Java:

---

## 1. Non-static Fields (Instance Variables)

- Belong to objects (instances of a class)
- Store object-specific data
- Each object has its own copy

---

## 2. Static Fields (Class Variables)

- Belong to the class itself
- Shared across all objects of the class
- Only one copy exists

---

## 3. Local Variables

- Declared inside methods, constructors, or blocks
- Accessible only within that method/block
- Created when method is called and destroyed after execution

---

## 4. Parameters

- Variables passed to methods
- Receive values when the method is called
- Accessible only inside the method 

# Variable Scope in Java

## 1. Class Scope
- Declared inside a class, outside methods
- Accessible throughout the class
- Example: instance variables
- ```java
   public class ClassScopeExample {
     private Integer amount = 0;
     public void exampleMethod() {
      amount++;
     }
    public void anotherExampleMethod() {
      Integer anotherAmount = amount + 4;
    }
  }
---

## 2. Method Scope
- Declared inside a method
- Accessible only within that method

---

## 3. Loop Scope
- Declared inside a loop
- Accessible only inside the loop block

---

## 4. Block (Bracket) Scope
- Declared inside `{ }`
- Accessible only within those brackets

---

## 5. Variable Shadowing
- Local variable with same name as class variable
- Local variable hides the class variable
- Use `this.variableName` to access class variable  

# Java Type Casting
Type casting is the process of converting one data type into another.
## 🔹 Types of Type Casting in Java
### 1️⃣ Widening Casting (Implicit Casting)
- Smaller data type → Larger data type
- Done automatically by Java
- No data loss
- Safe conversion  
- **Order of widening:** byte → short → int → long → float → double

# Narrowing Type Casting in Java (Explicit Typecasting)
- Narrowing Type Casting is the manual conversion of a larger data type into a smaller data type using parentheses
- Example= int data = (int) num;
- Data Loss may occur

# Other types of Conversion
```java
// Int to Str
int num = 10;
String data = String.valueOf(num);

// Str to Int
int num = Integer.parseInt(data);
// If str variable is invalid integer variable then NumberFormatException
  

