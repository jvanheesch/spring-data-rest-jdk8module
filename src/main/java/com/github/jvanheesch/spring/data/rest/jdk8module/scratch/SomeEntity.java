package com.github.jvanheesch.spring.data.rest.jdk8module.scratch;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SomeEntity {
    @Id
    private Long id;
    @Embedded
    private SomeEmbeddable someEmbeddable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SomeEmbeddable getSomeEmbeddable() {
        return someEmbeddable;
    }

    public void setSomeEmbeddable(SomeEmbeddable someEmbeddable) {
        this.someEmbeddable = someEmbeddable;
    }
}
