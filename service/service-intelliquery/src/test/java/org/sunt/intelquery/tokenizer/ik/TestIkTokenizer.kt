package org.sunt.intelquery.tokenizer.ik

import org.apache.lucene.analysis.tokenattributes.CharTermAttribute
import org.apache.lucene.analysis.tokenattributes.FlagsAttribute
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute
import org.apache.lucene.analysis.tokenattributes.TypeAttribute
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.wltea.analyzer.cfg.DefaultConfig
import org.wltea.analyzer.dic.Dictionary
import org.wltea.analyzer.lucene.IKAnalyzer

class TestIkTokenizer {

    companion object {
        @BeforeAll
        @JvmStatic
        fun init() {
            Dictionary.initial(DefaultConfig.getInstance())
            Dictionary.getSingleton().addWords(listOf("自工切", "切肝灶"))
        }
    }

    @ParameterizedTest
    @CsvSource(
        "小明 最大的年纪 性别是男 工作单位",
        """按用户性别分组 以年龄排序 to_date(生日, 'yyyy-MM-dd'）晚于10年前""",
        "group_AVG(收入, 毕业学校, YEAR(毕业日期))",
        "自工切切肝灶会跳舞吗？有没有一首歌会让你想起我 ",
        delimiter = '|')
    fun testIkTokenizer(sentence: String) {
        println("SENTENCE:$sentence")

        println("use smart")

        val smartAnalyzer = IKAnalyzer(true)
        val tokenStream = smartAnalyzer.tokenStream("content", sentence)
        tokenStream.reset()
        val chars = tokenStream.getAttribute(CharTermAttribute::class.java)
        val offset = tokenStream.getAttribute(OffsetAttribute::class.java)
        val type = tokenStream.getAttribute(TypeAttribute::class.java)
        val flag = tokenStream.addAttribute(FlagsAttribute::class.java)
        //val token = tokenStream.addAttribute(org.apache.lucene.analysis.Token::class.java)
        while (tokenStream.incrementToken()) {
            //print(token)
            print("$chars/${flag.flags}/${type.type()}(${offset.startOffset()}) ")
        }
        println()
        tokenStream.end()
        tokenStream.close()


        println("use standard")
        val standardAnalyzer = IKAnalyzer()
        val tokenStream2 = standardAnalyzer.tokenStream("content", sentence)
        tokenStream2.reset()
        val chars2 = tokenStream2.getAttribute(CharTermAttribute::class.java)
        val offset2 = tokenStream2.getAttribute(OffsetAttribute::class.java)
        val type2 = tokenStream2.getAttribute(TypeAttribute::class.java)
        val flag2 = tokenStream2.addAttribute(FlagsAttribute::class.java)
        //val token = tokenStream2.addAttribute(org.apache.lucene.analysis.Token::class.java)
        while (tokenStream2.incrementToken()) {
            //print(token)
            print("$chars2/${flag2.flags}/${type2.type()}(${offset2.startOffset()}) ")
        }
        println()
        tokenStream2.end()
        tokenStream2.close()
    }

}