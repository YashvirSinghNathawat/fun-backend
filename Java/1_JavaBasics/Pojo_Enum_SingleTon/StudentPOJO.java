
public class StudentPOJO {
    private String name;
    private int age;
    private String address;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress (String address) {
        this.address = address;
    }

    public void setAge (int age) {
        this.age = age;
    }

    public String getStudentInfo() {
        return name + ": " + age + ": " + address;
    }
}