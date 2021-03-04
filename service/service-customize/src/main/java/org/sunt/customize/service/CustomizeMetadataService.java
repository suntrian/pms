package org.sunt.customize.service;

import org.springframework.stereotype.Service;
import org.sunt.api.customize.entity.EntityDTO;
import org.sunt.api.customize.entity.FieldDTO;
import org.sunt.customize.dao.EntityRepository;
import org.sunt.customize.dao.FieldRepository;
import org.sunt.customize.entity.Converter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomizeMetadataService {

    private final EntityRepository entityRepository;

    private final FieldRepository fieldRepository;

    private static final Converter converter = Converter.INSTANCE;

    public CustomizeMetadataService(EntityRepository entityRepository, FieldRepository fieldRepository) {
        this.entityRepository = entityRepository;
        this.fieldRepository = fieldRepository;
    }

    public Mono<EntityDTO> createCustomizeEntity(EntityDTO entityDTO) {
        return entityRepository.insert(converter.convert(entityDTO)).map(converter::convert);
    }

    public Flux<EntityDTO> listCustomizeEntities() {
        return entityRepository.findAll().map(converter::convert);
    }


    public Mono<FieldDTO> createCustomizeField(FieldDTO fieldDTO) {
        return fieldRepository.insert(converter.convert(fieldDTO)).map(converter::convert);
    }

    public Flux<FieldDTO> listCustomizeField() {
        return fieldRepository.findAll().map(converter::convert);
    }

}
