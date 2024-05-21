package org.licretey.dp;

public abstract class ComponentTest {
    private String name;
    private String desc;

    public ComponentTest(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public void print(){
        System.out.println(this.name + " " + this.desc);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
