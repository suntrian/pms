package org.sunt.intelquery.tokenizer.hanlp

import com.hankcs.hanlp.HanLP
import com.hankcs.hanlp.corpus.tag.Nature
import com.hankcs.hanlp.dictionary.CustomDictionary
import com.hankcs.hanlp.seg.Dijkstra.DijkstraSegment
import com.hankcs.hanlp.seg.NShort.NShortSegment
import com.hankcs.hanlp.tokenizer.SpeedTokenizer
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class TestHanLP {

    companion object{

        @BeforeAll
        @JvmStatic
        fun addDict() {
            CustomDictionary.add("自工切")
            var word = CustomDictionary.get("自工切")
            println("WORD: $word")
            CustomDictionary.insert("切肝灶", Nature.a.toString() + " 10")
            word = CustomDictionary.get("切肝灶")
            println("WORD: $word")
            //CustomDictionary.reload()
        }
    }

    @ParameterizedTest(name = "{0}")
    @CsvSource(
        "小明 最大的年纪 性别是男 工作单位",
        """按用户性别分组 以年龄排序 to_date(生日, 'yyyy-MM-dd'）晚于10年前""",
        "group_AVG(收入, 毕业学校, YEAR(毕业日期))",
        "自工切切肝灶会跳舞吗？有没有一首歌会让你想起我 ",
        delimiter = '|'
    )
    fun testHanLP(sentence: String) {
        println("SENTENCE:$sentence")
        val firstCharSeq = HanLP.convertToPinyinFirstCharString(sentence, "-", true)
        println(firstCharSeq)
        val pinyin = HanLP.convertToPinyinString(sentence, "-", true)
        println(pinyin)

        println("\n标准分词")
        val stdTerms = HanLP.segment(sentence)
        for (term in stdTerms) {
            print(term)
        }

//        println()
//        val nlpTerms = NLPTokenizer.segment(sentence)
//        for (nlpTerm in nlpTerms) {
//            print(nlpTerm)
//        }

        println("\n词典分词")
        val dictTerms = SpeedTokenizer.segment(sentence)
        for (dictTerm in dictTerms) {
            print(dictTerm)
        }

        println("\n维特比")
        val vitTerms = HanLP.newSegment().enableCustomDictionary(true).seg(sentence)
        for (vitTerm in vitTerms) {
            print(vitTerm)
        }

        println("\nnShort")
        val nShortTerms = NShortSegment().enableCustomDictionary(true).seg(sentence)
        for (nShortTerm in nShortTerms) {
            print(nShortTerm)
        }

        println("\nshortest")
        val shortestTerms = DijkstraSegment().enableCustomDictionary(true).seg(sentence)
        for (shortestTerm in shortestTerms) {
            print(shortestTerm)
        }

//        println("\nCRF")
//        val crfTerms = CRFLexicalAnalyzer().seg(sentence)
//        for (crfTerm in crfTerms) {
//            print(crfTerm)
//        }
    }

}