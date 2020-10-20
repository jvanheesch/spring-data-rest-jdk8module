package com.github.jvanheesch.model.serialization;

import javax.persistence.*;

@Entity
public class OriginWoodOwner {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private OriginWoodEvaluation originWoodEvaluation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OriginWoodEvaluation getOriginWoodEvaluation() {
        return originWoodEvaluation;
    }

    public void setOriginWoodEvaluation(OriginWoodEvaluation originWoodEvaluation) {
        this.originWoodEvaluation = originWoodEvaluation;
    }
}
