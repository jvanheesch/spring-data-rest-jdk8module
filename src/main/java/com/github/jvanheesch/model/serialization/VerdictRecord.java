package com.github.jvanheesch.model.serialization;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;

@Entity
@Table(name = "VERDICT_RECORD")
@SequenceGenerator(allocationSize = 1, name = "VERDICT_RECORD_IDGEN", sequenceName = "VERDICT_RECORD_SEQ")
public class VerdictRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VERDICT_RECORD_IDGEN")
    private Long id;
    @JsonUnwrapped
    @RestResource(exported = false)
    @OneToOne(fetch = FetchType.LAZY)
    private Verdict verdict;

//    @JsonCreator
    public VerdictRecord(Verdict verdict) {
        this.verdict = verdict;
    }

    public VerdictRecord() {
    }

    public Long getId() {
        return id;
    }

//    @JsonValue
    public Verdict getVerdict() {
        return verdict;
    }

    public void setVerdict(Verdict verdict) {
        this.verdict = verdict;
    }
}
