package org.sunt.formula

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.sunt.formula.define.DataType
import org.sunt.formula.define.SqlDialect
import org.sunt.formula.function.TokenItem
import org.sunt.formula.function.TokenStatus
import org.sunt.formula.suggestion.FormulaSuggestion
import org.sunt.formula.support.AllMatchColumn
import org.sunt.formula.support.RestrictColumn

class TestFormulaSuggest {

    private val log = LoggerFactory.getLogger(TestFormulaSuggest::class.java)

    @Test
    fun testSingleArgFunction() {
        val suggestion = FormulaHelper.of(AllMatchColumn(emptyMap())).suggest("CEIL", "CE".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertTrue(suggestion.suggestions.any { it.scopes.contains(TokenItem.COLUMN()) } && suggestion.suggestions.any { it.leftPart == "CE" })
        Assertions.assertTrue(suggestion.suggestions.any { it.scopes.contains(TokenItem.FUNCTION()) })

        val suggestion2 = FormulaHelper.of(AllMatchColumn(emptyMap())).suggest("CEIL", "CEIL".length, SqlDialect.HIVE)
        log.info("{}", suggestion2)
        Assertions.assertTrue(suggestion2.suggestions.any { it.scopes.contains(TokenItem.PARENTHESES("(")) })

        val suggestion3 =
            FormulaHelper.of(AllMatchColumn(emptyMap())).suggest("CEILing", "ceiling".length, SqlDialect.HIVE)
        log.info("{}", suggestion3)
        Assertions.assertTrue(suggestion3.suggestions.any { it.scopes.contains(TokenItem.PARENTHESES("(")) })
        Assertions.assertTrue(suggestion3.suggestions.any { it.leftPart == "CEILing" })

        val suggestion4 = FormulaHelper.of(AllMatchColumn(emptyMap())).suggest("CEil(", "ceil(".length, SqlDialect.HIVE)
        log.info("{}", suggestion4)
        Assertions.assertTrue(suggestion4.suggestions.any { it.dataTypes.contains(DataType.DECIMAL) })
        Assertions.assertTrue(suggestion4.suggestions.size == 1 && suggestion4.suggestions[0].dataTypes.size == 1)

        val suggestion5 =
            FormulaHelper.of(AllMatchColumn(emptyMap())).suggest("CEiL()", "ceil(".length, SqlDialect.HIVE)
        log.info("{}", suggestion5)
        Assertions.assertTrue(suggestion4.suggestions.any { it.dataTypes.contains(DataType.DECIMAL) })

        val suggestion6 = FormulaHelper.of(AllMatchColumn(mapOf("abcd" to DataType.DECIMAL)))
            .suggest("CEiL(abcd", "CEiL(abcd".length, SqlDialect.HIVE)
        log.info("{}", suggestion6)
        Assertions.assertTrue(suggestion6.suggestions.size == 2)
        Assertions.assertTrue(suggestion6.suggestions.any { it.dataTypes.size == 1 && it.dataTypes.contains(DataType.DECIMAL) && it.leftPart == "abcd" })
        Assertions.assertTrue(suggestion6.suggestions.any { it.scopes.contains(TokenItem.PARENTHESES(")")) })

        val suggestion7 = FormulaHelper.of(AllMatchColumn(mapOf("abcd" to DataType.DECIMAL)))
            .suggest("CEiL(abcd)", "CEiL(abcd".length, SqlDialect.HIVE)
        log.info("suggestion7: {}", suggestion7)
        Assertions.assertTrue(suggestion7.suggestions.size == 1)
        Assertions.assertTrue(suggestion7.suggestions.any { it.dataTypes.size == 1 && it.dataTypes.contains(DataType.DECIMAL) && it.leftPart == "abcd" })

        val suggestion8 = FormulaHelper.of(AllMatchColumn(mapOf("abcd" to DataType.DECIMAL)))
            .suggest("CEiL(abcd", "CEi".length, SqlDialect.HIVE)
        log.info("suggestion8: {}", suggestion8)
        Assertions.assertEquals(2, suggestion8.suggestions.size)
        Assertions.assertTrue(suggestion8.suggestions[0].scopes.contains(TokenItem.PARENTHESES(")")))
        Assertions.assertTrue(suggestion8.suggestions[1].scopes.contains(TokenItem.FUNCTION()) && suggestion8.suggestions[1].leftPart == "CEi")

        val suggestion9 =
            FormulaHelper.of(RestrictColumn(emptyMap())).suggest("CEiL(abcd", "CEiL".length, SqlDialect.HIVE)
        log.info("suggestion9: {}", suggestion9)
        Assertions.assertEquals(2, suggestion9.suggestions.size)
        Assertions.assertTrue(suggestion9.suggestions[0].status == TokenStatus.UNKNOWN)
        Assertions.assertTrue(suggestion9.suggestions[0].dataTypes.contains(DataType.DECIMAL))
        Assertions.assertTrue(suggestion9.suggestions[1].scopes.contains(TokenItem.FUNCTION()) && suggestion9.suggestions[1].leftPart == "CEiL")

        val suggestion10 = FormulaHelper.of(RestrictColumn(mapOf("dddd" to DataType.STRING)))
            .suggest("abcd(dddd)", "abcd".length, SqlDialect.HIVE)
        log.info("{}", suggestion10)
        Assertions.assertEquals(1, suggestion10.suggestions.size)
        Assertions.assertTrue(suggestion10.suggestions[0].status == TokenStatus.UNKNOWN)
        Assertions.assertTrue(suggestion10.suggestions[0].scopes == setOf(TokenItem.FUNCTION()))

        val suggestion11 = FormulaHelper.of(AllMatchColumn(mapOf("abcd" to DataType.DECIMAL)))
            .suggest("CEIL(abcd, )", "CEIL(abcd,".length, SqlDialect.HIVE)
        log.info("{}", suggestion11)
        Assertions.assertEquals(1, suggestion11.suggestions.size)
        Assertions.assertTrue(suggestion11.suggestions[0].status == TokenStatus.ERROR)
        Assertions.assertTrue(suggestion11.suggestions[0].comment == "多余的,")

        val suggestion12 = FormulaHelper.of(RestrictColumn(mapOf("abcd" to DataType.STRING)))
            .suggest("TO_INEGER(abcd", "TO_IN".length, SqlDialect.HIVE)
        log.info("{}", suggestion12)
        Assertions.assertEquals(1, suggestion12.suggestions.size)
        Assertions.assertEquals(TokenStatus.UNKNOWN, suggestion12.suggestions[0].status)
        Assertions.assertTrue(suggestion12.suggestions[0].scopes == setOf(TokenItem.FUNCTION()))

    }

    @Test
    fun testOperator() {
        var suggestion = FormulaHelper.of(RestrictColumn(mapOf("abcd" to DataType.DECIMAL)))
            .suggest("abcd - ", "abcd".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(2, suggestion.suggestions.size)
        Assertions.assertTrue(
            suggestion.suggestions[0].status == TokenStatus.EXPECTED && suggestion.suggestions[0].dataTypes.contains(
                DataType.DECIMAL
            )
        )
        Assertions.assertTrue(suggestion.suggestions[1].scopes.contains(TokenItem.FUNCTION()) && suggestion.suggestions[1].leftPart == "abcd")

        suggestion = FormulaHelper.of(RestrictColumn(mapOf("abcd" to DataType.STRING)))
            .suggest("abcd + ", "abcd".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(2, suggestion.suggestions.size)
        Assertions.assertTrue(
            suggestion.suggestions[0].status == TokenStatus.EXPECTED && suggestion.suggestions[0].dataTypes.contains(
                DataType.STRING
            )
        )
        Assertions.assertTrue(suggestion.suggestions[1].scopes.contains(TokenItem.FUNCTION()) && suggestion.suggestions[1].leftPart == "abcd")

        suggestion = FormulaHelper.of(RestrictColumn(mapOf("abcd" to DataType.STRING, "bcde" to DataType.DECIMAL)))
            .suggest("(abcd + )", "(abcd".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(2, suggestion.suggestions.size)
        Assertions.assertTrue(
            suggestion.suggestions[0].status == TokenStatus.EXPECTED && suggestion.suggestions[0].dataTypes.contains(
                DataType.STRING
            )
        )
        Assertions.assertTrue(suggestion.suggestions[1].scopes.contains(TokenItem.FUNCTION()) && suggestion.suggestions[1].leftPart == "abcd")

        suggestion = FormulaHelper.of(RestrictColumn(mapOf("abcd" to DataType.STRING, "bcde" to DataType.DECIMAL)))
            .suggest("(abcd + bcde", "(abc".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(2, suggestion.suggestions.size)
        Assertions.assertTrue(
            suggestion.suggestions[0].status == TokenStatus.EXPECTED && suggestion.suggestions[0].scopes.contains(
                TokenItem.PARENTHESES(")")
            )
        )
        Assertions.assertTrue(suggestion.suggestions[1].scopes.contains(TokenItem.FUNCTION()) && suggestion.suggestions[1].leftPart == "abc")

    }

    @Test
    fun testBinaryArgFunction() {
        var suggestion: FormulaSuggestion
        suggestion = FormulaHelper.of(RestrictColumn(mapOf("abcd" to DataType.STRING, "bcde" to DataType.INTEGER)))
            .suggest("left(abcd", "left(ab".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(2, suggestion.suggestions.size)
        Assertions.assertTrue(suggestion.suggestions[0].scopes.contains(TokenItem.COMMA()))
        Assertions.assertTrue(suggestion.suggestions[1].scopes.contains(TokenItem.COLUMN()) && suggestion.suggestions[1].leftPart == "ab")

        suggestion = FormulaHelper.of(RestrictColumn(mapOf("abcd" to DataType.STRING, "bcde" to DataType.INTEGER)))
            .suggest("left(), ", "left(".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(1, suggestion.suggestions.size)
        Assertions.assertTrue(
            suggestion.suggestions[0].status == TokenStatus.EXPECTED && suggestion.suggestions[0].dataTypes.contains(
                DataType.STRING
            )
        )

        suggestion = FormulaHelper.of(RestrictColumn(mapOf("abcd" to DataType.STRING, "bcde" to DataType.INTEGER)))
            .suggest("left(abcd, ", "left(ab".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(2, suggestion.suggestions.size)
        Assertions.assertTrue(
            suggestion.suggestions[0].status == TokenStatus.EXPECTED && suggestion.suggestions[0].dataTypes.contains(
                DataType.INTEGER
            )
        )
        Assertions.assertTrue(suggestion.suggestions[1].scopes.contains(TokenItem.COLUMN()) && suggestion.suggestions[1].leftPart == "ab")

        suggestion = FormulaHelper.of(RestrictColumn(mapOf("abcd" to DataType.STRING, "bcde" to DataType.INTEGER)))
            .suggest("left(abcd, )", "left(abcd,".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(1, suggestion.suggestions.size)
//        Assertions.assertTrue(suggestion.suggestions[0].status == TokenStatus.EXPECTED && suggestion.suggestions[0].dataTypes.contains( DataType.INTEGER ))

        suggestion = FormulaHelper.of(RestrictColumn(mapOf("abcd" to DataType.STRING, "bcde" to DataType.INTEGER)))
            .suggest("left(, bcde)", "left(,".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(1, suggestion.suggestions.size)
        Assertions.assertTrue(
            suggestion.suggestions[0].status == TokenStatus.EXPECTED && suggestion.suggestions[0].dataTypes.contains(
                DataType.STRING
            )
        )

        suggestion = FormulaHelper.of(RestrictColumn(mapOf("abcd" to DataType.STRING, "bcde" to DataType.INTEGER)))
            .suggest("SUBSTR(abcd, , bcde)", "SUBSTR(abcd,".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(1, suggestion.suggestions.size)
        Assertions.assertTrue(
            suggestion.suggestions[0].status == TokenStatus.EXPECTED && suggestion.suggestions[0].dataTypes.contains(
                DataType.INTEGER
            )
        )
    }


    @Test
    fun testVarargFunction() {
        var suggestion: FormulaSuggestion
        suggestion = FormulaHelper.of(AllMatchColumn(emptyMap())).suggest("CONCAT(", "CONCAT(".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(1, suggestion.suggestions.size)
        Assertions.assertTrue(suggestion.suggestions[0].status == TokenStatus.EXPECTED)
        Assertions.assertTrue(suggestion.suggestions[0].dataTypes.contains(DataType.STRING))

        suggestion = FormulaHelper.of(AllMatchColumn(emptyMap())).suggest("CONCAT(", "CONCA".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(2, suggestion.suggestions.size)
        Assertions.assertTrue(suggestion.suggestions[0].status == TokenStatus.EXPECTED)
        Assertions.assertTrue(suggestion.suggestions[0].dataTypes.contains(DataType.STRING))
        Assertions.assertTrue(suggestion.suggestions[1].status == TokenStatus.NORMAL)

        suggestion = FormulaHelper.of(RestrictColumn(mapOf("abcd" to DataType.STRING)))
            .suggest("CONCAT(abcd", "CONCAT(abcd".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(3, suggestion.suggestions.size)
        Assertions.assertTrue(suggestion.suggestions[0].status == TokenStatus.EXPECTED)
        Assertions.assertTrue(suggestion.suggestions.any { it.scopes.contains(TokenItem.COMMA()) })
        Assertions.assertTrue(suggestion.suggestions.any { it.scopes.contains(TokenItem.PARENTHESES(")")) })

        suggestion = FormulaHelper.of(RestrictColumn(mapOf("abcd" to DataType.STRING)))
            .suggest("CONCAT(abcd,", "CONCAT(abcd,".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(1, suggestion.suggestions.size)
        Assertions.assertTrue(suggestion.suggestions[0].status == TokenStatus.EXPECTED)
        Assertions.assertTrue(suggestion.suggestions[0].dataTypes.contains(DataType.STRING))

        suggestion = FormulaHelper.of(RestrictColumn(mapOf("abcd" to DataType.STRING)))
            .suggest("CONCAT(abcd,)", "CONCAT(abcd,".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(1, suggestion.suggestions.size)
        Assertions.assertTrue(suggestion.suggestions[0].status == TokenStatus.EXPECTED)
        Assertions.assertTrue(suggestion.suggestions[0].dataTypes.contains(DataType.STRING))

        suggestion = FormulaHelper.of(RestrictColumn(mapOf("abcd" to DataType.STRING)))
            .suggest("CONCAT(abcd)", "CONCAT(abcd".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(2, suggestion.suggestions.size)
        Assertions.assertTrue(suggestion.suggestions[0].status == TokenStatus.EXPECTED)
        Assertions.assertTrue(suggestion.suggestions[0].scopes.contains(TokenItem.COMMA()))
        Assertions.assertTrue(suggestion.suggestions[1].leftPart == "abcd")

        suggestion = FormulaHelper.of(RestrictColumn(mapOf("abcd" to DataType.STRING, "bcde" to DataType.STRING)))
            .suggest("CONCAT(abcd, bcde", "CONCAT(abcd, bcde".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(3, suggestion.suggestions.size)
        Assertions.assertTrue(suggestion.suggestions[0].status == TokenStatus.EXPECTED)
        Assertions.assertTrue(suggestion.suggestions[0].scopes.contains(TokenItem.COMMA()))
        Assertions.assertTrue(suggestion.suggestions[1].scopes.contains(TokenItem.PARENTHESES(")")))
        Assertions.assertTrue(suggestion.suggestions[2].leftPart == "bcde")

    }

    @Test
    fun testGenericsTypeFunctions() {
        var suggestion: FormulaSuggestion
        suggestion = FormulaHelper.of(RestrictColumn(mapOf("abcd" to DataType.STRING, "bcde" to DataType.STRING)))
            .suggest("GREATEST(abcd, bcde", "GREATEST(abc".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(2, suggestion.suggestions.size)
        Assertions.assertTrue(suggestion.suggestions[0].status == TokenStatus.EXPECTED)
        Assertions.assertTrue(suggestion.suggestions[0].scopes.contains(TokenItem.PARENTHESES(")")))
        Assertions.assertTrue(suggestion.suggestions[1].leftPart == "abc")

        suggestion = FormulaHelper.of(RestrictColumn(mapOf("abcd" to DataType.STRING, "bcde" to DataType.STRING)))
            .suggest("GREATEST(abcd, bcde", "GREATEST(abcd, bcde".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(3, suggestion.suggestions.size)
        Assertions.assertTrue(suggestion.suggestions[0].status == TokenStatus.EXPECTED)
        Assertions.assertTrue(suggestion.suggestions[0].scopes.contains(TokenItem.COMMA()))
        Assertions.assertTrue(suggestion.suggestions[1].scopes.contains(TokenItem.PARENTHESES(")")))
        Assertions.assertTrue(suggestion.suggestions[2].leftPart == "bcde")

        suggestion = FormulaHelper.of(RestrictColumn(mapOf("abcd" to DataType.STRING, "bcde" to DataType.DECIMAL)))
            .suggest("GREATEST(abcd, bcde", "GREATEST(abc".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(2, suggestion.suggestions.size)
        Assertions.assertTrue(suggestion.suggestions[0].status == TokenStatus.ERROR)
        Assertions.assertTrue(suggestion.suggestions[0].dataTypes.contains(DataType.STRING))
        Assertions.assertTrue(suggestion.suggestions[1].leftPart == "abc")

        suggestion = FormulaHelper.of(RestrictColumn(mapOf("abcd" to DataType.STRING, "bcde" to DataType.STRING)))
            .suggest("GREATEST(abcd, bcde, , )", "GREATEST(abcd, bcde, ".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(1, suggestion.suggestions.size)
        Assertions.assertTrue(suggestion.suggestions[0].status == TokenStatus.EXPECTED)
        Assertions.assertTrue(suggestion.suggestions[0].dataTypes.contains(DataType.STRING))

        suggestion = FormulaHelper.of(
            RestrictColumn(
                mapOf(
                    "abcd" to DataType.STRING,
                    "bcde" to DataType.STRING,
                    "cdef" to DataType.STRING
                )
            )
        )
            .suggest("GREATEST(abcd, bcde,, cdef)", "GREATEST(abcd, bcde, ".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(1, suggestion.suggestions.size)
        Assertions.assertTrue(suggestion.suggestions[0].status == TokenStatus.EXPECTED)
        Assertions.assertTrue(suggestion.suggestions[0].dataTypes.contains(DataType.STRING))

    }

    @Test
    fun testArgsExceed() {
        var suggestion: FormulaSuggestion
        suggestion = FormulaHelper.of(AllMatchColumn(emptyMap()))
            .suggest("IFNULL(abcd, cdef, 0", "IFNULL(abcd, c".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(2, suggestion.suggestions.size)
        Assertions.assertTrue(suggestion.suggestions[0].status == TokenStatus.ERROR)

        suggestion = FormulaHelper.of(AllMatchColumn(emptyMap()))
            .suggest("IFNULL(abcd, cdef, 0, '1234')", "IFNULL(abcd, cdef, 0".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(2, suggestion.suggestions.size)
        Assertions.assertTrue(suggestion.suggestions.all { it.status == TokenStatus.ERROR })

        suggestion = FormulaHelper.of(AllMatchColumn(emptyMap()))
            .suggest("CURRENT_DATETIME(abcd, cdef, 0)", "CURRENT_DATETIME(abcd, c".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(3, suggestion.suggestions.size)
        Assertions.assertTrue(suggestion.suggestions.all { it.status == TokenStatus.ERROR })


    }

    @Test
    fun testAdvancedTypeFunction() {
        var suggestion = FormulaHelper.of(AllMatchColumn(emptyMap()))
            .suggest(
                "RANK_OVER(PARTITION_BY(),ORDER_BY())",
                "RANK_OVER(PARTITION_BY(),ORDER_BY(".length,
                SqlDialect.HIVE
            )
        log.info("{}", suggestion)
        Assertions.assertEquals(1, suggestion.suggestions.size)
        Assertions.assertTrue(suggestion.suggestions[0].scopes.contains(TokenItem.FUNCTION("ORDER_ITEM")))

    }

    @Test
    fun testOptionValuesFunction() {
        var suggestion: FormulaSuggestion
        suggestion = FormulaHelper.of(RestrictColumn(mapOf("abcd" to DataType.DATE)))
            .suggest("DATEADD(abcd, 1, ", "DATEADD(abcd, 1,".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(1, suggestion.suggestions.size)
        Assertions.assertTrue(suggestion.suggestions[0].dataTypes.contains(DataType.STRING))
        Assertions.assertTrue(suggestion.suggestions[0].scopes.contains(TokenItem.CONSTANT("'year'")))
        Assertions.assertTrue(suggestion.suggestions[0].scopes.contains(TokenItem.CONSTANT("'month'")))

        suggestion = FormulaHelper.of(RestrictColumn(mapOf("abcd" to DataType.DATETIME)))
            .suggest("DATEADD(abcd, 1, ", "DATEADD(abcd, 1,".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(1, suggestion.suggestions.size)
        Assertions.assertTrue(suggestion.suggestions[0].dataTypes.contains(DataType.STRING))
        Assertions.assertTrue(suggestion.suggestions[0].scopes.contains(TokenItem.CONSTANT("'hour'")))
        Assertions.assertTrue(suggestion.suggestions[0].scopes.contains(TokenItem.CONSTANT("'second'")))

        suggestion = FormulaHelper.of(RestrictColumn(mapOf("abcd" to DataType.DATETIME, "bcde" to DataType.DATETIME)))
            .suggest("DATEDIFF(abcd, bcde, ", "DATEDIFF(abcd, bcde,".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(1, suggestion.suggestions.size)
        Assertions.assertTrue(suggestion.suggestions[0].dataTypes.contains(DataType.STRING))
        Assertions.assertTrue(suggestion.suggestions[0].scopes.contains(TokenItem.CONSTANT("'day'")))
        Assertions.assertTrue(suggestion.suggestions[0].scopes.contains(TokenItem.CONSTANT("'year'")))

        suggestion = FormulaHelper.of(RestrictColumn(mapOf("abcd" to DataType.DATETIME, "bcde" to DataType.DATETIME)))
            .suggest("DATEDIFF(abcd, bcde) ", "DATEDIFF(abcd, bcde".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(2, suggestion.suggestions.size)
        Assertions.assertTrue(suggestion.suggestions[0].scopes.contains(TokenItem.COMMA()))
        Assertions.assertEquals("DATEDIFF(abcd, bcde".length, suggestion.suggestions[0].start)
        Assertions.assertEquals("DATEDIFF(abcd, bcde".length, suggestion.suggestions[0].stop)

        suggestion = FormulaHelper.of(RestrictColumn(mapOf("abcd" to DataType.DATETIME, "bcde" to DataType.DATETIME)))
            .suggest("DATEDIFF(abcd, bcde, 'year') ", "DATEDIFF(abcd, bcde, 'yea".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(1, suggestion.suggestions.size)
        Assertions.assertTrue(suggestion.suggestions[0].scopes.contains(TokenItem.CONSTANT("'year'")))
        Assertions.assertEquals("DATEDIFF(abcd, bcde， ".length, suggestion.suggestions[0].start)
        Assertions.assertEquals("DATEDIFF(abcd, bcde， 'year'".length, suggestion.suggestions[0].stop)

        //todo with suggest string
        suggestion = FormulaHelper.of(RestrictColumn(mapOf("abcd" to DataType.DATETIME, "bcde" to DataType.DATETIME)))
            .suggest("DATEDIFF(abcd, bcde, 'yea) ", "DATEDIFF(abcd, bcde, 'yea".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(1, suggestion.suggestions.size)
        Assertions.assertTrue(suggestion.suggestions[0].scopes.contains(TokenItem.CONSTANT("'year'")))
        Assertions.assertEquals("DATEDIFF(abcd, bcde, ".length, suggestion.suggestions[0].start)
        Assertions.assertEquals("DATEDIFF(abcd, bcde, 'yea".length, suggestion.suggestions[0].stop)
    }

    @Test
    fun testReservedValueFunction() {
        var suggestion: FormulaSuggestion
        suggestion = FormulaHelper.of(RestrictColumn(mapOf("abcd" to DataType.DATE)))
            .suggest("GROUP_COUNT(", "GROUP_COUNT(".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(2, suggestion.suggestions.size)
        Assertions.assertTrue(suggestion.suggestions[0].dataTypes.contains(DataType.ANY)) { "非Any" }
        Assertions.assertTrue(suggestion.suggestions.any { it.scopes.contains(TokenItem.RESERVED("DISTINCT")) }) { "无DISTINCT" }
        Assertions.assertTrue(suggestion.suggestions.any { it.scopes.contains(TokenItem.RESERVED("ALL")) }) { "无ALL" }
        Assertions.assertTrue(suggestion.suggestions.any { it.scopes.contains(TokenItem.COLUMN()) }) { "无COLUMN" }

        suggestion = FormulaHelper.of(RestrictColumn(mapOf("abcd" to DataType.DATE, "bcde" to DataType.STRING)))
            .suggest("GROUP_COUNT(abcd, bcde)", "GROUP_COUNT(abcd".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(1, suggestion.suggestions.size)
        Assertions.assertTrue(suggestion.suggestions[0].dataTypes.contains(DataType.ANY))
        Assertions.assertTrue(suggestion.suggestions[0].status == TokenStatus.NORMAL)

        suggestion = FormulaHelper.of(RestrictColumn(mapOf("abcd" to DataType.DATE, "bcde" to DataType.STRING)))
            .suggest("GROUP_COUNT(abcd, bcde)", "GROUP_COUNT(abcd, bcde".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(2, suggestion.suggestions.size)
        Assertions.assertTrue(suggestion.suggestions[0].scopes.contains(TokenItem.COMMA()))
        Assertions.assertTrue(suggestion.suggestions[0].status == TokenStatus.EXPECTED)

        suggestion = FormulaHelper.of(RestrictColumn(mapOf("abcd" to DataType.DATE, "bcde" to DataType.STRING)))
            .suggest("GROUP_COUNT(abcd, bcde,)", "GROUP_COUNT(abcd, bcde,".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(1, suggestion.suggestions.size)
        Assertions.assertTrue(suggestion.suggestions[0].scopes.contains(TokenItem.COLUMN()))
        Assertions.assertTrue(suggestion.suggestions[0].status == TokenStatus.EXPECTED)

        suggestion = FormulaHelper.of(RestrictColumn(mapOf("abcd" to DataType.DATE, "bcde" to DataType.STRING)))
            .suggest("GROUP_COUNT(DISTINCT, abcd, bcde)", "GROUP_COUNT(DISTINCT".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(1, suggestion.suggestions.size)
        Assertions.assertTrue(suggestion.suggestions[0].scopes.contains(TokenItem.RESERVED("DISTINCT")))
        Assertions.assertTrue(suggestion.suggestions[0].status == TokenStatus.NORMAL)

        suggestion = FormulaHelper.of(RestrictColumn(mapOf("abcd" to DataType.DATE, "bcde" to DataType.STRING)))
            .suggest("GROUP_COUNT(DISTINCT, abcd, bcde)", "GROUP_COUNT(DISTI".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(1, suggestion.suggestions.size)
        Assertions.assertTrue(suggestion.suggestions[0].scopes.contains(TokenItem.RESERVED("ALL")))
        Assertions.assertTrue(suggestion.suggestions[0].status == TokenStatus.NORMAL)


    }

    @Test
    fun TestSuggestedArgFunction() {
        var suggest: FormulaSuggestion
        suggest = FormulaHelper.of(RestrictColumn(mapOf())).suggest("RANK_OVER(", "RANK_OVER(".length, SqlDialect.HIVE)
        log.info("{}", suggest)
        Assertions.assertEquals(1, suggest.suggestions.size)
        Assertions.assertTrue(suggest.suggestions[0].scopes.contains(TokenItem.FUNCTION("PARTITION_BY")))

        suggest = FormulaHelper.of(RestrictColumn(mapOf())).suggest("RANK_OVER(PARTITION_BY(", "RANK_OVER(PARTITION_BY(".length, SqlDialect.HIVE)
        log.info("{}", suggest)
        Assertions.assertEquals(2, suggest.suggestions.size)
        Assertions.assertTrue(suggest.suggestions.any { it.scopes.contains(TokenItem.COLUMN()) })
        Assertions.assertTrue(suggest.suggestions.any { it.scopes.contains(TokenItem.PARENTHESES(")")) })

        suggest = FormulaHelper.of(RestrictColumn(mapOf())).suggest("RANK_OVER(PARTITION_BY()", "RANK_OVER(PARTITION_BY()".length, SqlDialect.HIVE)
        log.info("{}", suggest)
        Assertions.assertEquals(1, suggest.suggestions.size)
        Assertions.assertTrue(suggest.suggestions[0].scopes.contains(TokenItem.COMMA()))

        suggest = FormulaHelper.of(RestrictColumn(mapOf())).suggest("RANK_OVER(PARTITION_BY(),", "RANK_OVER(PARTITION_BY(),".length, SqlDialect.HIVE)
        log.info("{}", suggest)
        Assertions.assertEquals(1, suggest.suggestions.size)
        Assertions.assertTrue(suggest.suggestions[0].scopes.contains(TokenItem.FUNCTION("ORDER_BY")))
    }

    @Test
    fun testDefaultArgFunction() {
        var suggestion: FormulaSuggestion
        suggestion = FormulaHelper.of(AllMatchColumn(emptyMap()))
            .suggest("TO_DOUBLE(abcd", "TO_DOUBLE(abcd".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(3, suggestion.suggestions.size)
        Assertions.assertTrue(suggestion.suggestions[0].scopes.contains(TokenItem.COMMA()))
        Assertions.assertTrue(suggestion.suggestions[1].scopes.contains(TokenItem.PARENTHESES(")")))
        Assertions.assertTrue(suggestion.suggestions[2].leftPart == "abcd")

        suggestion = FormulaHelper.of(AllMatchColumn(mapOf("abcd" to DataType.INTEGER)))
            .suggest("STDDEV_OVER(abcd, [], [bcde]", "STDDEV_OVER(abcd, [], [bcde]".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(2, suggestion.suggestions.size)
        Assertions.assertTrue(suggestion.suggestions[0].scopes.contains(TokenItem.COMMA()))
        Assertions.assertTrue(suggestion.suggestions[1].scopes.contains(TokenItem.PARENTHESES(")")))

        suggestion = FormulaHelper.of(AllMatchColumn(mapOf("abcd" to DataType.INTEGER)))
            .suggest("STDDEV_OVER(abcd, [], [bcde],", "STDDEV_OVER(abcd, [], [bcde],".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(1, suggestion.suggestions.size)
        Assertions.assertTrue(suggestion.suggestions[0].scopes.contains(TokenItem.RESERVED("ROWS")))
        Assertions.assertTrue(suggestion.suggestions[0].scopes.contains(TokenItem.RESERVED("RANGE")))
    }

    @Test
    fun testEmbedFunction() {
        var suggestion: FormulaSuggestion
        suggestion = FormulaHelper.of(AllMatchColumn(emptyMap()))
            .suggest("max_over(abcd, partition_by(bcde)", "max_over(abcd, partition_by(bcde".length, SqlDialect.HIVE)
        log.info("{}", suggestion)
        Assertions.assertEquals(3, suggestion.suggestions.size)
        Assertions.assertTrue(suggestion.suggestions[0].scopes.contains(TokenItem.COMMA()))
        Assertions.assertTrue(suggestion.suggestions[0].start == "max_over(abcd, partition_by(bcde".length)
        Assertions.assertTrue(suggestion.suggestions[1].scopes.contains(TokenItem.COMMA()))
        Assertions.assertTrue(suggestion.suggestions[1].start == "max_over(abcd, partition_by(bcde)".length)

        suggestion = FormulaHelper.of(AllMatchColumn(emptyMap()))
            .suggest(
                "max_over(abcd, partition_by(bcde),order_by())",
                "max_over(abcd, partition_by(bcde),order_by())".length,
                SqlDialect.HIVE
            )
        log.info("{}", suggestion)
        Assertions.assertEquals(0, suggestion.suggestions.size)

        suggestion = FormulaHelper.of(AllMatchColumn(emptyMap()))
            .suggest(
                "max_over(abcd, partition_by(bcde),order_by(),)",
                "max_over(abcd, partition_by(bcde),order_by(),".length,
                SqlDialect.HIVE
            )
        log.info("{}", suggestion)
        Assertions.assertEquals(1, suggestion.suggestions.size)
        Assertions.assertTrue(suggestion.suggestions[0].scopes.contains(TokenItem.RESERVED("ROWS")))
        Assertions.assertTrue(suggestion.suggestions[0].scopes.contains(TokenItem.RESERVED("RANGE")))

        suggestion = FormulaHelper.of(AllMatchColumn(mapOf("abcd" to DataType.DECIMAL)))
            .suggest(
                "max_over(abcd, partition_by(bcde),order_by(),ROWS)",
                "max_over(abcd, partition_by(bcde),order_by(),ROWS".length,
                SqlDialect.HIVE
            )
        log.info("{}", suggestion)
        Assertions.assertEquals(1, suggestion.suggestions.size)
        Assertions.assertTrue(suggestion.suggestions[0].scopes.contains(TokenItem.COMMA()))

    }

}