package org.sunt.formula.support

import org.sunt.formula.ColumnInterface
import org.sunt.formula.define.DataType
import org.sunt.formula.define.IColumn

class AllMatchColumn(private val map: Map<String, DataType>) : ColumnInterface {

    override fun getColumnById(id: String): IColumn? {
        println("请求字段ID:${id}")
        return TestColumn(id, "", id, map[id] ?: DataType.STRING)
    }

    override fun getColumnByName(name: String): IColumn? {
        println("请求字段NAME:${name}")
        return TestColumn("", name, name, map[name] ?: DataType.STRING)
    }

}

class RestrictColumn(private val map: Map<String, DataType>) : ColumnInterface {

    override fun getColumnById(id: String): IColumn? {
        println("请求字段ID:${id}")
        if (!map.containsKey(id)) {
            println("未找到字段${id}")
            return null
        }
        return TestColumn(id, "", id, map[id] ?: DataType.STRING)
    }

    override fun getColumnByName(name: String): IColumn? {
        println("请求字段NAME:${name}")
        if (!map.containsKey(name)) {
            println("未找到字段${name}")
            return null
        }
        return TestColumn("", name, name, map[name] ?: DataType.STRING)
    }

}

class ExactColumn(private val map: Map<String, IColumn?>) : ColumnInterface {
    override fun getColumnById(id: String): IColumn? {
        return map[id]
    }

    override fun getColumnByName(name: String): IColumn? {
        return map[name]
    }

}