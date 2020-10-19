package com.github.jvanheesch.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.jvanheesch.spring.data.rest.jdk8module.MyOptional;

public class MyOptionalContainer {
    private MyOptional<String> optional1;
    private MyOptional<String> optional2;
    private MyOptional<String> optional3;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public MyOptional<String> getMyOptional1() {
        return optional1;
    }

    public void setMyOptional1(MyOptional<String> optional1) {
        this.optional1 = optional1;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public MyOptional<String> getMyOptional2() {
        return optional2;
    }

    public void setMyOptional2(MyOptional<String> optional2) {
        this.optional2 = optional2;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public MyOptional<String> getMyOptional3() {
        return optional3;
    }

    public void setMyOptional3(MyOptional<String> optional3) {
        this.optional3 = optional3;
    }
}
