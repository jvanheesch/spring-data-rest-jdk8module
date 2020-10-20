package com.github.jvanheesch.model.serialization;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class Verdict {
    private Long id;
    private String name;

    @JsonCreator
    public Verdict(String name) {
        this.name = name;
    }

    public Verdict() {
    }

    public Long getId() {
        return id;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
