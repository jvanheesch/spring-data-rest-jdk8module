package com.github.jvanheesch.model.serialization;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OriginWoodOwnerRepository extends CrudRepository<OriginWoodOwner, Long> {
}
