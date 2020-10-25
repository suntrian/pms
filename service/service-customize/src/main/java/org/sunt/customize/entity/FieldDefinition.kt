package org.sunt.customize.entity

import org.sunt.customize.meta.DataType

data class FieldDefinition(val id: Int,
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
                           val style: String
)