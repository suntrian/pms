package org.sunt.customize.service;

import org.springframework.stereotype.Service;
import org.sunt.customize.dao.EntityRepository;
import org.sunt.customize.dao.FieldRepository;
import org.sunt.customize.entity.EntityDTO;
import org.sunt.customize.entity.FieldDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomizeMetadataService {

    private final EntityRepository entityRepository;

    private final FieldRepository fieldRepository;

    public CustomizeMetadataService(EntityRepository entityRepository, FieldRepository fieldRepository) {
        this.entityRepository = entityRepository;
        this.fieldRepository = fieldRepository;
    }


    public Mono<EntityDTO> createCustomizeEntity(EntityDTO entityDTO) {
        return entityRepository.insert(entityDTO.toEntityDefinition()).map(EntityDTO::fromEntityDefinition);
    }

    public Flux<EntityDTO> listCustomizeEntities() {
        return entityRepository.findAll().map(EntityDTO::fromEntityDefinition);
    }


    public Mono<FieldDTO> createCustomizeField(FieldDTO fieldDTO) {
        return fieldRepository.insert(fieldDTO.toFieldDefinition()).map(FieldDTO::fromFieldDefinition);
    }

    public Flux<FieldDTO> listCustomizeField() {
        return fieldRepository.findAll().map(FieldDTO::fromFieldDefinition);
    }

}
