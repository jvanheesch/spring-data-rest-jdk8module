package com.github.jvanheesch.spring.data.rest.jdk8module;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;

@Component
public class SpringRestConfigurer implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.disableDefaultExposure();
        config.withEntityLookup().forValueRepository(VerdictRepository.class, Verdict::getId, VerdictRepository::findById);
    }
}
