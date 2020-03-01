package com.github.jvanheesch.spring.data.rest.jdk8module;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class VerdictRecordModelProcessor implements RepresentationModelProcessor<EntityModel<VerdictRecord>> {
    @Override
    public EntityModel<VerdictRecord> process(EntityModel<VerdictRecord> model) {
        model.add(new Link("https://www.google.com", "google"));
        return model;
    }
}
