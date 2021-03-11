package org.sunt.sqlanalysis.common

import org.slf4j.LoggerFactory
import java.util.regex.Matcher
import java.util.regex.Pattern

class PlaceholderPreHandler(private val placeHolders: List<Pair<Pattern, String>>) : PreHandler {

    constructor(placeHolderSerial: String): this(PlaceHolderResolver.resolvePlaceHolder(placeHolderSerial))

    override fun handle(sql: String): String {
        var vSql = sql
        for ((key, value) in placeHolders) {
            vSql = key.matcher(vSql).replaceAll(value)
        }
        return vSql
    }

    override val ord: Int
        get() = 1

    private object PlaceHolderResolver {

        private val logger = LoggerFactory.getLogger(PlaceholderPreHandler::class.java)
        private const val placeHolderItemSplit = "//"
        private const val placeHolderKvSplit = "->"
        private val regexPlaceholderPattern = Pattern.compile("r'(.*?)'")

        @JvmStatic
        fun resolvePlaceHolder(placeHolderSerial: String): List<Pair<Pattern, String>> {
            val placeHolders = mutableListOf<Pair<Pattern, String>>()
            if (placeHolderSerial.isNotBlank()) {
                var i = '1'
                //             将无法解析的占位符替换成可以数字，使SQL能够成功解析，替换为字符串不行，因为字符串作为值时需要加引号，需要根据占位符出现的位置进行替换
//             如果要做反向替换，让替换后的表名、字段名和SQL中保持一致，则会存在一些问题
//             1、替换的数字，在原SQL中也存在，使用直接替换的方式可能会错误地替换一些原本不是占位符，而是原本数字的情形。
//             2、在表名或者字段名中出现的占位符，可能存在一些特殊字符如%_等, 如果保存表名字段名时使用这些特殊字符，在后续的SQL查询这些表或字段时，都需要做转义处理或者使用PreparedStatement进行查询,
//                否则%_等会被作为SQL的模糊匹配字段
//             3、为了让解析时能够解析出表和字段的精确的位置，替换的数字长度必须和占位符的长度一致，占位符越短，问题1出现的机率会显著增加
//            for (String s : sqlParserPlaceHolderStr.split(placeHolderSplit)) {
//                String replacement;
//                if (i <= 9) {
//                    replacement = StringUtils.repeat(String.valueOf(i++), s.length());
//                } else {
//                    replacement = StringUtils.repeat(String.valueOf(i++), s.length()).substring(0, s.length());
//                }
//                logger.info(DB_MARKER, "占位符{}被替换为{}", s.trim(), replacement);
//                placeHolders.add(new AbstractMap.SimpleEntry<>(Pattern.compile(s.trim(), Pattern.LITERAL), Pattern.compile(replacement, Pattern.LITERAL)));
//            }
                for (s in placeHolderSerial.split(placeHolderItemSplit.toRegex())) {
                    val placeholderAndReplacement = s.split(placeHolderKvSplit.toRegex())
                    val placeholder = placeholderAndReplacement[0].trim()
                    if (placeholder.isBlank()) continue
                    val replacement = if (placeholderAndReplacement.size == 1)
                        i++.toString().repeat(8)
                    else placeholderAndReplacement[1]
                    var placeholderPattern: Pattern
                    var matcher: Matcher
                    // 正则的替换规则放在最后
                    if (regexPlaceholderPattern.matcher(placeholder).also { matcher = it }.matches()) {
                        placeholderPattern = Pattern.compile(matcher.group(1))
                        placeHolders.add(placeholderPattern to replacement)
                    } else {
                        placeholderPattern = Pattern.compile(placeholder, Pattern.LITERAL)
                        placeHolders.add(0, placeholderPattern to replacement)
                    }
                    logger.info("占位符{}被替换为{}", placeholder.trim(), replacement)
                }
            }
            return placeHolders
        }

    }

}