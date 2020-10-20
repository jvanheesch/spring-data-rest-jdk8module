package com.github.jvanheesch.model.serialization;

public class OriginWoodEvaluation {
    private Long id;
    private VerdictRecord supplierLocatedInEurope;
    private VerdictRecord flegtLicense;
    private VerdictRecord citesLicense;
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
