package org.licretey.dp;

import java.util.ArrayList;
import java.util.List;

public abstract class ComponentT<T> extends ComponentTest{
    List<? extends ComponentTest> list = new ArrayList<>();

    public ComponentT(String name, String desc) {
        super(name,desc);
    }

    public void add(ComponentTest t) {
        // list.add(t);
        // Map<Object, Object> objectObjectMap = new Map<>();
    }

    public void print(){

        System.out.println(super.getName() + " " + super.getDesc());
        list.forEach(ComponentTest::print);
    }
}
