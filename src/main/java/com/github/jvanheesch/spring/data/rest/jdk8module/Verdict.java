package com.github.jvanheesch.spring.data.rest.jdk8module;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Verdict {
    @Id
    @JsonProperty("value")
    private Long id;
    @JsonProperty("label")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
