package com.github.jvanheesch.spring.data.rest.jdk8module;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class StringMyOptionalOwner {
    @Id
    private Long id;
    @Transient
    private MyOptional<String> stringMyOptional1;
    @Transient
    private MyOptional<String> stringMyOptional2;
    @Transient
    private MyOptional<String> stringMyOptional3;
    @Embedded
    private StringContainer stringContainer;

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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public MyOptional<String> getStringMyOptional2() {
        return stringMyOptional2;
    }

    public void setStringMyOptional2(MyOptional<String> stringMyOptional2) {
        this.stringMyOptional2 = stringMyOptional2;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public MyOptional<String> getStringMyOptional3() {
        return stringMyOptional3;
    }

    public void setStringMyOptional3(MyOptional<String> stringMyOptional3) {
        this.stringMyOptional3 = stringMyOptional3;
    }

    public StringContainer getStringContainer() {
        return stringContainer;
    }

    public void setStringContainer(StringContainer stringContainer) {
        this.stringContainer = stringContainer;
    }
}
