package com.github.jvanheesch.model.serialization;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

// TODO_JORIS: render type in json, delete all subclasses & repo and handle in f/e
@Entity(name = "VERDICT")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
public abstract class Verdict {
    @Id
    @JsonProperty("value")
    private Long id;
    @JsonProperty("label")
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
