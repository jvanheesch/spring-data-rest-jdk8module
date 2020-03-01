package com.github.jvanheesch.spring.data.rest.jdk8module;

import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;

@Entity
@Table(name = "OW_EVALUATION")
@SequenceGenerator(allocationSize = 1, name = "OW_EVALUATION_IDGEN", sequenceName = "OW_EVALUATION_SEQ")
public class OriginWoodEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OW_EVALUATION_IDGEN")
    private Long id;
    @RestResource(exported = false)
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private VerdictRecord supplierLocatedInEurope;
    @RestResource(exported = false)
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private VerdictRecord flegtLicense;
    @RestResource(exported = false)
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private VerdictRecord citesLicense;
    @RestResource(exported = false)
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private VerdictRecord fromIndonesia;

    public Long getId() {
        return id;
    }

    public VerdictRecord getSupplierLocatedInEurope() {
        return supplierLocatedInEurope;
    }

    public void setSupplierLocatedInEurope(VerdictRecord supplierLocatedInEurope) {
        this.supplierLocatedInEurope = supplierLocatedInEurope;
    }

    public VerdictRecord getFlegtLicense() {
        return flegtLicense;
    }

    public void setFlegtLicense(VerdictRecord flegtLicense) {
        this.flegtLicense = flegtLicense;
    }

    public VerdictRecord getCitesLicense() {
        return citesLicense;
    }

    public void setCitesLicense(VerdictRecord citesLicense) {
        this.citesLicense = citesLicense;
    }

    public VerdictRecord getFromIndonesia() {
        return fromIndonesia;
    }

    public void setFromIndonesia(VerdictRecord fromIndonesia) {
        this.fromIndonesia = fromIndonesia;
    }
}
