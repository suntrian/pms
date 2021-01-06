package org.sunt.customize.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import org.sunt.customize.entity.FieldDefinition;

@Repository
public interface FieldRepository extends ReactiveMongoRepository<FieldDefinition, Integer> {


}
