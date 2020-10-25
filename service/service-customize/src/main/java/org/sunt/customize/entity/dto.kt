package org.sunt.customize.entity

import org.sunt.customize.meta.DataType

data class EntityDTO(val id: Int,
                     val name: String,
                     val label: String,
                     val comment: String) {

    fun toEntityDefinition(): EntityDefinition {
        return EntityDefinition(id, name, label, comment)
    }


    companion object {
        @JvmStatic
        fun fromEntityDefinition(entityDefinition: EntityDefinition): EntityDTO {
            return EntityDTO(entityDefinition.id, entityDefinition.name, entityDefinition.label, entityDefinition.comment)
        }
    }

}


data class FieldDTO(val id: Int,
                    val name: String,
                    val label: String,
                    val comment: String,
                    val entityId: Int,       //所属的实体
                    val dataType: DataType,
                    val unique: Boolean,
                    val nullable: Boolean,
                    val displayField: Boolean,
                    val defaultValue: String,
                    val constraints: List<Regex>,
                    val reference: String,
                    val position: Int,
                    val style: String) {

    fun toFieldDefinition(): FieldDefinition {
        return FieldDefinition(id,
                name,
                label,
                comment,
                entityId,
                dataType,
                unique,
                nullable,
                displayField,
                defaultValue,
                constraints,
                reference,
                position,
                style)
    }

    companion object {

        @JvmStatic
        fun fromFieldDefinition(field: FieldDefinition): FieldDTO {
            with(field) {
                return FieldDTO(
                        id,
                        name,
                        label,
                        comment,
                        entityId,
                        dataType,
                        unique,
                        nullable,
                        displayField,
                        defaultValue,
                        constraints,
                        reference,
                        position,
                        style
                )
            }
        }

    }

}