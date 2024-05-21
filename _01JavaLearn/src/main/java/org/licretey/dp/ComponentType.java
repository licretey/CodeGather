package org.licretey.dp;

public abstract class ComponentType extends ComponentTest{

    public ComponentType(String name, String desc) {
        super(name,desc);
    }

    public void print(){

        System.out.println(super.getName() + " " + super.getDesc());
    }
}
