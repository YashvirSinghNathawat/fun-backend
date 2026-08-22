public class StudentDemo {
    public static void main (String [] args) {
        StudentPOJO student1 = new StudentPOJO();
        student1.setName("Yashvir");
        student1.setAddress("A1");
        student1.setAge(20);

        StudentPOJO student2 = new StudentPOJO();
        student2.setName("Abhay");
        student2.setAddress("A2");
        student2.setAge(21);

        if(student1.getAge() < student2.getAge()) {
            System.out.println(student2.getName() + " is older");
        }

        StudentPOJO[] students = new StudentPOJO[2];
        students[0] = student1;
        students[1] = student2;

        for (StudentPOJO s: students) {
            System.out.println(s.getStudentInfo());
        }
    }

}
