package com.github.jvanheesch.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Optional;

public class OptionalContainer {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Optional<String> optional1;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Optional<String> optional2;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Optional<String> optional3;

    public Optional<String> getOptional1() {
        return optional1;
    }

    public void setOptional1(Optional<String> optional1) {
        this.optional1 = optional1;
    }

    public Optional<String> getOptional2() {
        return optional2;
    }

    public void setOptional2(Optional<String> optional2) {
        this.optional2 = optional2;
    }

    public Optional<String> getOptional3() {
        return optional3;
    }

    public void setOptional3(Optional<String> optional3) {
        this.optional3 = optional3;
    }
}
