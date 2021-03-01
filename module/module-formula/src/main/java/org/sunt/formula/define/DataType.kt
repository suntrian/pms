package org.sunt.formula.define

import java.io.Serializable
import java.util.regex.Pattern

class DataType private constructor(
    val name: String,
    val parentType: DataType? = ANY,
    val genericType: List<DataType> = emptyList()
) : Serializable {

    fun name(): String = name

    /**
     * this dataType is parent of
     * @param dataType
     */
    fun isAssignableFrom(dataType: DataType): Boolean {
        //类型完全相同
        if (this == dataType) {
            return true
        }
        if (this === ANY || dataType === NONE) {
            return true
        }
        //int 赋值给double
        if (this === DECIMAL && dataType === INTEGER) {
            return true
        }
        //string赋值给date/time
        if (this.isDate() && dataType === STRING) {
            return true
        }

        //本类，父类，及泛型类相同
        if (name.equals(dataType.name, true)
            && parentType == dataType.parentType
        ) {
            for ((index, thisGenericType) in genericType.withIndex()) {
                if (!thisGenericType.isAssignableFrom(dataType.genericType.getOrNull(index) ?: return false)) {
                    return false
                }
            }
            return true
        }

        //本类为父类
        var parType = dataType.parentType
        while (parType != null && parType != ANY) {
            if (isAssignableFrom(parType)) {
                return true
            }
            parType = parType.parentType
        }

        return false
    }

    fun isDate(): Boolean {
        return isDate(this)
    }

    fun isNumeric(): Boolean {
        return isNumeric(this)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DataType) return false

        if (!name.equals(other.name, true)) return false
        if (parentType != other.parentType) return false
        if (genericType != other.genericType) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + parentType.hashCode()
        result = 31 * result + genericType.hashCode()
        return result
    }

    override fun toString(): String {
        return if (genericType.isNotEmpty()) {
            name.plus("<").plus(genericType.joinToString(", ")).plus(">")
        } else name
    }


    companion object {

        @JvmField
        val ANY = PrimitiveType.ANY.dataType        //任何类的父类
        @JvmField
        val NONE = PrimitiveType.NONE.dataType       //任何类的子类
        @JvmField
        val DECIMAL = PrimitiveType.DECIMAL.dataType
        @JvmField
        val INTEGER = PrimitiveType.INTEGER.dataType
        @JvmField
        val BOOLEAN = PrimitiveType.BOOLEAN.dataType
        @JvmField
        val STRING = PrimitiveType.STRING.dataType
        @JvmField
        val DATE = PrimitiveType.DATE.dataType
        @JvmField
        val TIME = PrimitiveType.TIME.dataType
        @JvmField
        val DATETIME = PrimitiveType.DATETIME.dataType


        @JvmStatic
        fun of(type: String): DataType {
            val uType = type.toUpperCase();
            return try {
                PrimitiveType.valueOf(uType).dataType
            } catch (e: IllegalArgumentException) {
                when (uType) {
                    "CHAR", "VARCHAR", "LONG" -> STRING        // oracle LONG type FOR String
                    "FLOAT", "DOUBLE", "REAL", "MONEY" -> DECIMAL
                    "INT" -> INTEGER
                    "BOOL", "BIT" -> BOOLEAN
                    "TIMESTAMP" -> DATETIME
                    else -> {
                        return parseDataType(type) ?: when {
                            uType.contains("NUM") -> DECIMAL        // ORACLE number
                            uType.contains("DEC") -> DECIMAL
                            uType.contains("INT") -> INTEGER        // BIGINT TINYINT...
                            uType.contains("CHAR") -> STRING
                            uType.contains("STRING") -> STRING
                            uType.contains("TEXT") -> STRING
                            uType.contains("DATETIME") -> DATETIME  // SQLSERVER smalldatetime
                            uType.contains("TIMESTAMP") -> DATETIME // ORACLE TIMESTAMP INTERVAL(..)
                            uType.contains("DATE") -> DATE
                            uType.contains("TIME") -> TIME
                            uType.contains("VARY") -> STRING
                            uType.contains("money") -> DECIMAL      // sqlserver smallmoney
                            else -> ANY
                        }
                    }
                }
            }
        }

        @JvmStatic
        fun isDate(type: DataType): Boolean {
            return when (type) {
                DATETIME, DATE, TIME -> true
                else -> false
            }
        }

        @JvmStatic
        fun isNumeric(type: DataType): Boolean {
            return DECIMAL == type || INTEGER == type
        }

        @JvmStatic
        fun commonType(least: DataType, vararg dataTypes: DataType): DataType {
            var result = least
            for (dataType in dataTypes) {
                if (dataType.isAssignableFrom(result)) {
                    result = dataType
                } else if (!result.isAssignableFrom(dataType)) {
                    return ANY
                }
            }
            return result
        }

        fun commonType(dataTypes: Collection<DataType>): DataType {
            if (dataTypes.isEmpty()) return NONE
            val iter = dataTypes.iterator()
            var result = iter.next()
            while (iter.hasNext()) {
                val dataType = iter.next()
                if (dataType.isAssignableFrom(result)) {
                    result = dataType
                } else if (!result.isAssignableFrom(dataType)) {
                    return ANY
                }
            }
            return result
        }

        private val GenericTypePattern = Pattern.compile("(?<name>.+)<(?<generic>.+)>")
        private fun parseDataType(type: String): DataType? {
            val matcher = GenericTypePattern.matcher(type.trim())
            if (matcher.matches()) {
                val name = matcher.group("name").trim()
                val genericTypes = matcher.group("generic").split(",")
                return DataType(name, ANY, genericTypes.map { of(it) })
            }
            return null
        }

    }

    enum class PrimitiveType(val dataType: DataType) {
        ANY(DataType("ANY", null)),
        DECIMAL(DataType("DECIMAL")),
        INTEGER(DataType("INTEGER")),
        BOOLEAN(DataType("BOOLEAN")),
        STRING(DataType("STRING")),
        DATE(DataType("DATE")),
        TIME(DataType("TIME")),
        DATETIME(DataType("DATETIME")),
        NONE(DataType("NONE"));
    }
}