package com.github.jvanheesch.spring.data.rest.jdk8module.scratch;

import javax.persistence.Embeddable;

@Embeddable
public class SomeEmbeddable {
    private String string;

    public SomeEmbeddable(String string) {
        this.string = string;
    }

    public SomeEmbeddable() {
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
