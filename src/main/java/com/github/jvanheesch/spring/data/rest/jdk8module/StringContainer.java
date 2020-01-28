package com.github.jvanheesch.spring.data.rest.jdk8module;

import javax.persistence.Embeddable;

@Embeddable
public class StringContainer {
    private String value;

    public StringContainer(String value) {
        this.value = value;
    }

    public StringContainer() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
