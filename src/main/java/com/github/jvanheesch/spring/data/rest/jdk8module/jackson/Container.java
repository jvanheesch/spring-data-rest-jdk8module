package com.github.jvanheesch.spring.data.rest.jdk8module.jackson;

public class Container<T> {
    private T containedValue;

    public Container(T containedValue) {
        this.containedValue = containedValue;
    }

    public Container() {
    }

    public T getContainedValue() {
        return containedValue;
    }

    public void setContainedValue(T containedValue) {
        this.containedValue = containedValue;
    }
}
