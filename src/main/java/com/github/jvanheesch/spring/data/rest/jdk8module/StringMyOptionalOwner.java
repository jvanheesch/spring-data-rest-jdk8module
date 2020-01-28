package com.github.jvanheesch.spring.data.rest.jdk8module;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.persistence.*;

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
    @AttributeOverride(name = "value", column = @Column(name = "VALUE_1"))
    private StringContainer stringContainer1;
    @JsonUnwrapped
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "VALUE_2"))
    private StringContainer stringContainer2;

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

    public StringContainer getStringContainer1() {
        return stringContainer1;
    }

    public void setStringContainer1(StringContainer stringContainer) {
        this.stringContainer1 = stringContainer;
    }

    public StringContainer getStringContainer2() {
        return stringContainer2;
    }

    public void setStringContainer2(StringContainer stringContainer2) {
        this.stringContainer2 = stringContainer2;
    }
}
