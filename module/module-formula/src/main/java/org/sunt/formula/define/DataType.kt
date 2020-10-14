package org.sunt.formula.define

import java.io.Serializable

enum class DataType(private val compatibility: Int) : Serializable {

    DECIMAL(3),
    INTEGER(2),
    BOOLEAN(1),
    STRING(9),
    DATE(1),
    TIME(1),
    DATETIME(2),
    ANY(Int.MAX_VALUE),
    NONE(0);

    /**
     *  当前类型是否被type兼容
     *  @param type 目标兼容类型
     */
    fun isCompatibleTo(type: DataType): Boolean {
        return compatibility <= type.compatibility
    }

    fun idDate(): Boolean {
        return isDate(this)
    }

    fun isNumeric(): Boolean {
        return isNumeric(this)
    }

    companion object {

        @JvmStatic
        fun of(type: String): DataType {
            val uType = type.toUpperCase();
            return try {
                valueOf(type);
            } catch (e: IllegalArgumentException) {
                when (uType) {
                    "CHAR", "VARCHAR", "LONG" -> STRING        // oracle LONG type FOR String
                    "FLOAT", "DOUBLE", "REAL", "MONEY" -> DECIMAL
                    "INT" -> INTEGER
                    "BOOL", "BIT" -> BOOLEAN
                    "TIMESTAMP" -> DATETIME
                    else -> {
                        when {
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
                            else -> NONE
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

    }

}