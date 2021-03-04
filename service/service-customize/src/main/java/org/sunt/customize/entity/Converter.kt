package org.sunt.customize.entity

import org.springframework.beans.BeanUtils
import org.sunt.api.customize.dictionary.DataType
import org.sunt.api.customize.entity.EntityDTO
import org.sunt.api.customize.entity.FieldDTO

object Converter {


    fun convert(fieldDefinition: FieldDefinition): FieldDTO {
        val fieldDTO = FieldDTO()
        BeanUtils.copyProperties(fieldDefinition, fieldDTO)
        return fieldDTO
    }

    fun convert(fieldDTO: FieldDTO): FieldDefinition {
        with(fieldDTO) {
            return FieldDefinition(id,
                    name,
                    label,
                    comment,
                    entityId,
                    DataType.valueOf(dataType),
                    unique,
                    nullable,
                    displayField,
                    defaultValue,
                    constraints.map { Regex(it) },
                    reference,
                    position,
                    style);
        }
    }

    fun convert(entityDefinition: EntityDefinition): EntityDTO {
        val entityDTO = EntityDTO()
        BeanUtils.copyProperties(entityDefinition, entityDTO)
        return entityDTO
    }

    fun convert(entityDTO: EntityDTO): EntityDefinition {
        with(entityDTO) {
            return EntityDefinition(id, name, label, comment)
        }
    }

}