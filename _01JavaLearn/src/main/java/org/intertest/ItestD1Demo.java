package org.intertest;

public class ItestD1Demo implements Itest {
    private Long age;
    private String name;
    private boolean used;

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ItestD1Demo{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", used=" + used +
                '}';
    }
}
