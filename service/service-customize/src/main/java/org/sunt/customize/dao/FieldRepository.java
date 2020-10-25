package org.sunt.customize.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.sunt.customize.entity.FieldDefinition;

public interface FieldRepository extends ReactiveMongoRepository<FieldDefinition, Integer> {


}
