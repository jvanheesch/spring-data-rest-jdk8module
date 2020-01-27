package com.github.jvanheesch.spring.data.rest.jdk8module;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EmbeddedStringMyOptionalOwner {
    @Id
    private Long id;
    @Embedded
    private MyOptional<String> stringMyOptional1;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public MyOptional<String> getStringMyOptional1() {
        return stringMyOptional1;
    }

    public void setStringMyOptional1(MyOptional<String> stringMyOptional1) {
        this.stringMyOptional1 = stringMyOptional1;
    }
}
