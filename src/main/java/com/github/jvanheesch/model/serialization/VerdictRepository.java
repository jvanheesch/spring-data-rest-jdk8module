package com.github.jvanheesch.model.serialization;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "verdictOptions")
public interface VerdictRepository extends CrudRepository<Verdict, Long> {
    @RestResource
    @Override
    Iterable<Verdict> findAll();
}
