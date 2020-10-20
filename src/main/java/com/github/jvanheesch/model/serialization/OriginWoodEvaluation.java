package com.github.jvanheesch.model.serialization;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.persistence.*;

@Entity
@Table(name = "OW_EVALUATION")
@SequenceGenerator(allocationSize = 1, name = "OW_EVALUATION_IDGEN", sequenceName = "OW_EVALUATION_SEQ")
public class OriginWoodEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OW_EVALUATION_IDGEN")
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private VerdictRecord supplierLocatedInEurope;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private VerdictRecord flegtLicense;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private VerdictRecord citesLicense;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private VerdictRecord fromIndonesia;
    // dit nieuwe gedrag is volgens mij echter gewoon het juiste gedrag i.t.t. het oude ...
    // denk maar bvb aan content property
    // @JsonUnwrapped
    @Embedded
    private Person person;

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

    public Person getPerson() {
        if (person == null) {
            person = new Person("joris");
        }
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
