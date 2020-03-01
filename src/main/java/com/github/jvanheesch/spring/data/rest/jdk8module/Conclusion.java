package com.github.jvanheesch.spring.data.rest.jdk8module;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;

@Entity
@Table(name = "EUTR_CONCLUSION")
@SequenceGenerator(allocationSize = 1, name = "EUTR_CONCLUSION_IDGEN", sequenceName = "EUTR_CONCLUSION_SEQ")
public class Conclusion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EUTR_CONCLUSION_IDGEN")
    private Long id;
    @JsonUnwrapped
    @RestResource(exported = false)
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "OW_EVALUATION_ID")
    private OriginWoodEvaluation originWoodEvaluation;

    public Long getId() {
        return id;
    }

    public OriginWoodEvaluation getOriginWoodEvaluation() {
        return originWoodEvaluation;
    }

    public void setOriginWoodEvaluation(OriginWoodEvaluation originWoodEvaluation) {
        this.originWoodEvaluation = originWoodEvaluation;
    }
}
