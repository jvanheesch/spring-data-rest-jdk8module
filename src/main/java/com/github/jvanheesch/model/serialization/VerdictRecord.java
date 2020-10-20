package com.github.jvanheesch.model.serialization;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class VerdictRecord {
    @Id
    private Long id;
    @OneToOne
    private Verdict verdict;

    @JsonCreator
    public VerdictRecord(Verdict verdict) {
        this.verdict = verdict;
    }

    public VerdictRecord() {
    }

    public Long getId() {
        return id;
    }

    @JsonValue
    public Verdict getVerdict() {
        return verdict;
    }

    public void setVerdict(Verdict verdict) {
        this.verdict = verdict;
    }
}
