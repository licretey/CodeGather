package org.licretey.dp;

public abstract class ComponentCollege extends ComponentTest{

    public ComponentCollege(String name, String desc) {
        super(name,desc);
    }

    public void print(){

        System.out.println(super.getName() + " " + super.getDesc());
    }
}
