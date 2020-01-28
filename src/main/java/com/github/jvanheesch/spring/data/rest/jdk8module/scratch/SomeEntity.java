package com.github.jvanheesch.spring.data.rest.jdk8module.scratch;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.persistence.*;

@Entity
public class SomeEntity {
    @Id
    private Long id;
    @JsonUnwrapped
    @Embedded
    @AttributeOverride(name = "string", column = @Column(name = "string1"))
    private SomeEmbeddable someEmbeddable1;
    @JsonUnwrapped
    @Embedded
    @AttributeOverride(name = "string", column = @Column(name = "string2"))
    private SomeEmbeddable someEmbeddable2;
    @JsonUnwrapped
    @Embedded
    @AttributeOverride(name = "string", column = @Column(name = "string3"))
    private SomeEmbeddable someEmbeddable3;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SomeEmbeddable getSomeEmbeddable1() {
        return someEmbeddable1;
    }

    public void setSomeEmbeddable1(SomeEmbeddable someEmbeddable) {
        this.someEmbeddable1 = someEmbeddable;
    }

    public SomeEmbeddable getSomeEmbeddable2() {
        return someEmbeddable2;
    }

    public void setSomeEmbeddable2(SomeEmbeddable someEmbeddable2) {
        this.someEmbeddable2 = someEmbeddable2;
    }

    public SomeEmbeddable getSomeEmbeddable3() {
        return someEmbeddable3;
    }

    public void setSomeEmbeddable3(SomeEmbeddable someEmbeddable3) {
        this.someEmbeddable3 = someEmbeddable3;
    }
}
