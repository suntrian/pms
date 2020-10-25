package org.sunt.customize.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import org.sunt.customize.entity.EntityDefinition;

@Repository()
public interface EntityRepository extends ReactiveMongoRepository<EntityDefinition, Integer> {
}
