package org.sunt.query.keyword

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.platform.commons.util.ReflectionUtils
import org.sunt.query.parser.KeywordSegment
import org.sunt.query.util.CharTrie

class TestKeywordSegment {

    @ParameterizedTest
    @CsvSource(
        "按用户存款排序 > 按 用户存款 排序",
        "按揭贷款额度按用户年龄升序排序用户性别是男 > 按 揭贷款额度 按 用户年龄 升序排序 用户性别 是 男",
        "用户名/*+id=123,name=用户名*/是张三 CEIL(积分) > 用户名/*+id=123,name=用户名*/ 是 张三   CEIL (积分)",
        "用户排名/*+id=123,name=用户排名*/是10 用户倒数排名/*+id=123,name=数排名*/是10 > 用户排名/*+id=123,name=用户排名*/ 是 10   用户 倒 数排名/*+id=123,name=数排名*/ 是 10",
        delimiter = '>',
    )
    fun testKeywordSegment(keywords: String, expected: String){
        println("测试： $keywords -> $expected")
        val segments = KeywordSegment.tokenize(keywords)
        println(segments)
        Assertions.assertEquals(expected, segments)
    }

    @ParameterizedTest
    @CsvSource(
        "注册时间是当前日期 > 注册时间 是 当前日期/*+type=function,name=CURRENT_DATE()*/",
        "当前时间 > 当前时间/*+type=function,name=CURRENT_TIME()*/",
        delimiter = '>'
    )
    fun testReservedFunction(keywords: String, expected: String) {
        println("测试： $keywords -> $expected")
        val segments = KeywordSegment.tokenize(keywords)
        println(segments)
        Assertions.assertEquals(expected, segments)
    }

    @Test
    fun testEmptySegment() {
        val segments = KeywordSegment.tokenize("  ")
        println(segments)
        Assertions.assertEquals("   ", segments)
    }

    @Test
    fun testKeywordReservedTrie() {
        val segClass = Class.forName("org.sunt.query.parser.KeywordSegment")
        val segFields = ReflectionUtils.findFields(segClass, {true}, ReflectionUtils.HierarchyTraversalMode.TOP_DOWN)
        val segMethods = ReflectionUtils.findMethods(segClass) { true }
        val segInnerClasses = ReflectionUtils.findNestedClasses(segClass){ true }


        val trieClass = Class.forName("org.sunt.query.parser.KeywordSegment\$KeywordReservedTrie")
        val trieFields = ReflectionUtils.findFields(trieClass, {true}, ReflectionUtils.HierarchyTraversalMode.TOP_DOWN)
        val trieMethods = ReflectionUtils.findMethods(trieClass) { true }
        val trieInstanceFields = ReflectionUtils.findFields(trieClass, {f->f.name=="INSTANCE"}, ReflectionUtils.HierarchyTraversalMode.TOP_DOWN)
        val trieInstances = ReflectionUtils.readFieldValues(trieInstanceFields, null)

        val instance: CharTrie<*> = trieInstances[0] as CharTrie<*>
        instance.keys.forEach { print(" $it ") }
        println()
        instance.values.forEach{ print(" $it ") }
        println()
        instance.entries.forEach { print(" ${it.key} -> ${it.value} ") }
        println()
        println(instance.getValue("当前日期"))
        println(instance.containsKey("介于"))
        println(instance.prefixed("在"))
        println(instance.walk(charArrayOf('在', '上')))


    }

}