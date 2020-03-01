package com.github.jvanheesch.spring.data.rest.jdk8module;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource
public interface OriginWoodEvaluationRepository extends CrudRepository<OriginWoodEvaluation, Long> {
    @RestResource
    @Override
    Iterable<OriginWoodEvaluation> findAll();

    @RestResource
    @Override
    Optional<OriginWoodEvaluation> findById(Long id);
}
