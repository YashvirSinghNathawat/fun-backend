
## Before Java 9
- String was internally stored in
  - ```java
    private final char[] value;
- Each char is 2Byte in java
- Encoding using utf-16 which can represent Hindi, English and Emojis and other languages.

## From Java 9
- Compact Strings was introduced
  - ```java
    private final byte[] value;
    private final byte coder;
   
- Case 1: Only Latin-1 characters (English, numbers, basic symbols): 1 Byte per character
- Case 2: Contain Unicode characters : 2B per character

# String Literals as Constants or Singletons
```java
String myString1 = "Hello World";
String myString2 = "Hello World";
```
- Java virtual machine may only create a single String instance in memory.
- To ensure 2 different String object
```java
String myString1 = new String("Hello World");
String myString2 = new String("Hello World");
```

# String Concatenation Performance
```java
String[] strings = 
  new String[]{"one", "two", "three", "four", "five"};

String result = null;
for(String string : strings) {
    result = result + string;
}
```
Translated by compiler to-
```java
String[] strings = 
  new String[]{"one", "two", "three", "four", "five"};

String result = null;
for(String string : strings) {
    result = new StringBuilder(result)
                    .append(string).toString();
}
```
- Every time the new StringBuilder(result) code is executed, the StringBuilder constructor copies all characters from the result String into the StringBuilder. The more iterations the loop has, the bigger the result String grows. The bigger the result String grows, the longer it takes to copy the characters from it into a new StringBuilder, and again copy the characters from the StringBuilder into the temporary String created by the toString() method. In other words, the more iterations the slower each iteration becomes.
- Better way-
```java
String[] strings = 
  new String[]{"one", "two", "three", "four", "five"};

StringBuilder temp  = new StringBuilder();
for(String string : strings) {
    temp.append(string);
}
String result = temp.toString();
```

# Basic Conversions
- Number to String: String.valueOf(123.00)
- Object to String:
```java
Integer integer = new Integer(123);
String intStr = integer.toString();
String inStr = String.valueOf(integer);
```
- String to Number
```java
int i = Integer.parseInt("123");
double d = Double.parseDouble("123.45");
float f = Float.parseFloat("12.5");
long l = Long.parseLong("1000");
boolean b = Boolean.parseBoolean("true");
```