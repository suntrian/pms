package org.sunt.query.keyword

import org.sunt.query.model.keyword.KeywordFactory
import org.sunt.query.model.keyword.KeywordNode
import org.sunt.query.model.sql.Query
import org.sunt.query.model.sql.QueryTable
import java.util.*

class KeywordQueryBuilder {

    private val keywordFactory: KeywordFactory = KeywordFactory

    private val keywordNodes: List<KeywordNode> = LinkedList()

    fun build(): Query {
        return Query(QueryTable())
    }

}