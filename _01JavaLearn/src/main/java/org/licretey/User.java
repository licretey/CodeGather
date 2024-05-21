package org.licretey;
public class User {
    private String name;
    private static Integer age;
    private int size;

    public User(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Integer getAge() {
        return age;
    }

    public static void setAge(Integer age) {
        User.age = age;
    }

    public int getSize() {
        return  size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", size=" + size +
                '}'+User.getAge();
    }
}
