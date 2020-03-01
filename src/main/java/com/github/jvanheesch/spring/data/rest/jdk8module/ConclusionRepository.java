package com.github.jvanheesch.spring.data.rest.jdk8module;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource
public interface ConclusionRepository extends CrudRepository<Conclusion, Long> {
    @RestResource
    @Override
    Iterable<Conclusion> findAll();

    @RestResource
    @Override
    Optional<Conclusion> findById(Long id);
}
