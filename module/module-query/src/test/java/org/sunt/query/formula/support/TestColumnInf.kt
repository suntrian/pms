package org.sunt.query.formula.support

import org.sunt.query.define.DataType
import org.sunt.query.model.metadata.Column
import org.sunt.query.model.metadata.ColumnInterface

class AllMatchColumn(private val map: Map<String, DataType>) : ColumnInterface {

    override fun getColumnById(id: String): Column? {
        println("请求字段ID:${id}")
        return TestColumn(id, "", id, map[id] ?: DataType.STRING)
    }

    override fun getColumnByName(name: String): Column? {
        println("请求字段NAME:${name}")
        return TestColumn(name, name, name, map[name] ?: DataType.STRING)
    }

}

class RestrictColumn(private val map: Map<String, DataType>) : ColumnInterface {

    override fun getColumnById(id: String): Column? {
        println("请求字段ID:${id}")
        if (!map.containsKey(id)) {
            println("未找到字段${id}")
            return null
        }
        return TestColumn(id, "", id, map[id] ?: DataType.STRING)
    }

    override fun getColumnByName(name: String): Column? {
        println("请求字段NAME:${name}")
        if (!map.containsKey(name)) {
            println("未找到字段${name}")
            return null
        }
        return TestColumn(name, name, name, map[name] ?: DataType.STRING)
    }

}

class ExactColumn(private val map: Map<String, Column?>) : ColumnInterface {
    override fun getColumnById(id: String): Column? {
        return map[id]
    }

    override fun getColumnByName(name: String): Column? {
        return map[name]
    }

}