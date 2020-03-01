package com.github.jvanheesch.spring.data.rest.jdk8module;

import javax.persistence.*;

@Entity
@Table(name = "VERDICT_RECORD")
@SequenceGenerator(allocationSize = 1, name = "VERDICT_RECORD_IDGEN", sequenceName = "VERDICT_RECORD_SEQ")
public class VerdictRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VERDICT_RECORD_IDGEN")
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    private Verdict verdict;

    public VerdictRecord(Verdict verdict) {
        this.verdict = verdict;
    }

    public VerdictRecord() {
    }

    public Long getId() {
        return id;
    }

    public Verdict getVerdict() {
        return verdict;
    }

    public void setVerdict(Verdict verdict) {
        this.verdict = verdict;
    }
}
