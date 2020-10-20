package com.github.jvanheesch.model.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;

public class OriginWoodEvaluation {
    private Long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private VerdictRecord supplierLocatedInEurope;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private VerdictRecord flegtLicense;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private VerdictRecord citesLicense;
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
