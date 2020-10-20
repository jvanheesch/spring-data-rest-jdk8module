package com.github.jvanheesch.model.serialization;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class VerdictRecord {
    private Long id;
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
