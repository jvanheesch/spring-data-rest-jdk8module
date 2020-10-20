package com.github.jvanheesch.model.serialization;

public class OriginWoodOwner {
    private Long id;
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
