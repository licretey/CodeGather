package org.licretey;

public class Book {
    public Book(String msg){
        this.size =10;
        Book.age = 100;
    }

    static Integer age;
    private Integer size;

    public void setAge(){};
    public static void setSize(){}
    public static void get(){
        Book book = new Book("");
        // this.setAge();
        int i = Book.age + book.size;
        System.out.println();

    }

    public Integer getAge() {
        return age;
    }

    public static void setAge(Integer age) {
        Book.age = age;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
