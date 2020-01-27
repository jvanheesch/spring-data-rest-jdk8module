package com.github.jvanheesch.spring.data.rest.jdk8module.jackson;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.core.util.VersionUtil;

/**
 * Automatically generated from PackageVersion.java.in during
 * packageVersion-generate execution of maven-replacer-plugin in
 * pom.xml.
 */
public final class MyPackageVersion implements Versioned {
    public final static Version VERSION = VersionUtil.parseVersion(
        "2.10.2", "com.fasterxml.jackson.datatype", "jackson-datatype-jdk8");

    @Override
    public Version version() {
        return VERSION;
    }
}
