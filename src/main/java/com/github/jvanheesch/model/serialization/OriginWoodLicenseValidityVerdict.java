package com.github.jvanheesch.model.serialization;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("OW_LICENSE_VALIDITY")
public class OriginWoodLicenseValidityVerdict extends Verdict {
}
