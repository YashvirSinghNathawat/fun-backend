import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Student implements Comparator<Student>{
    String name;
    int age;
    Student() {

    }

    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    @Override 
    public int compare(Student s1, Student s2) {
        return s2.name.compareTo(s1.name);
    }
}

class StudentComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.name.compareTo(s2.name);
    }
}

public class three_Comparator_Comparable {
    public static void main(String[] args) {
        // Comparator is a functional interface
        Student s1 = new Student("Yashvir", 25);
        Student s2 = new Student("Gaurav", 26);
        List<Student> sl = new ArrayList<>();
        sl.add(s1);  sl.add(s2);
        // Collections.sort(sl);  Erros as sl is not Comparable class
        
        // Comparator #1
        Collections.sort(sl, (Student obj1, Student obj2) -> obj1.name.compareTo(obj2.name));
        sl.forEach((Student obj) -> System.out.println("Name : " + obj.name));
        System.out.println("*******************************");

        // Comparator #2
        Collections.sort(sl, new Student());
        sl.forEach((Student obj) -> System.out.println("Name : " + obj.name));
        System.out.println("*******************************");

        // Comparator #3
        Collections.sort(sl, new StudentComparator());
        sl.forEach((Student obj) -> System.out.println("Name : " + obj.name));
        System.out.println("*******************************");

        /* Comparable
         */
        StudentComparable s3 = new StudentComparable("Yashvir", 25);
        StudentComparable s4 = new StudentComparable("Gaurav", 26);
        List<StudentComparable> sl_2 = new ArrayList<>();
        sl_2.add(s3);
        sl_2.add(s4);
        Collections.sort(sl_2);
        sl_2.forEach((StudentComparable obj) -> System.out.println("Name : " + obj.name));
    }
}


class StudentComparable implements Comparable<StudentComparable> {

    String name;
    Integer age;

    StudentComparable(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override 
    public int compareTo(StudentComparable s2) {
        return this.age.compareTo(s2.age);
    }
}