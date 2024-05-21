package com.functionInterface;

@FunctionalInterface
public interface F01 {
    public abstract void test();

    boolean equals(Object obj);

    default boolean equal2(Object obj){
        return false;
    };

    default public void setaa(){

    }
}
