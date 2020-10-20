package com.github.jvanheesch.model.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class OriginWoodEvaluation {
    @Id
    private Long id;
    @OneToOne
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private VerdictRecord supplierLocatedInEurope;
    @OneToOne
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private VerdictRecord flegtLicense;
    @OneToOne
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private VerdictRecord citesLicense;
    @OneToOne
    @JsonInclude(JsonInclude.Include.NON_NULL)
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
