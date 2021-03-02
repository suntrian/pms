package function

import org.sunt.formula.function.parser.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

typealias Date = LocalDate
typealias Time = LocalTime
typealias DateTime = LocalDateTime
typealias None = Unit

interface Common {

    @Description(
        """
LOWER

   1. 用法：LOWER(string)
   2. 说明：把文本中所有英文字母转化为小写
   3. 示例：LOWER("ABCDE") = "abcde"
   4. 参数：string: 文本类型字段
    """
    )
    @Alias("LOW", "LOWCASE")
    @Translate("LOWER($1)")
    @Category("文本函数")
    fun LOWER(text: String): String

    @Description(
        """
UPPER

   1. 用法：UPPER(string)
   2. 说明：把文本中所有英文字母转化为大写
   3. 示例：UPPER("abcde") = "ABCDE"
   4. 参数：string：文本类型字段
    """
    )
    @Alias("UP", "UPCASE")
    @Translate("UPPER($1)")
    @Category("文本函数")
    fun UPPER(text: String): String

    @Description(
        """
REVERSE

   1. 用法：REVERSE(string)
   2. 说明：把文本中所有字符倒序排列
   3. 示例：REVERSE("abcde") = "edcba"
   4. 参数：string：文本类型字段    
    """
    )
    @Category("文本函数")
    @Translate("REVERSE($1)")
    fun REVERSE(text: String): String

    @Description(
        """
CONCAT

   1. 用法：CONCAT(str1,str2, ... strN)
   2. 说明：返回字符串拼接的结果
   3. 示例：CONCAT("Hello", " ", "World") = "Hello World"
   4. 参数：string: 文本类型
    """
    )
    @Translate("CONCAT($0)")
    @Category("文本函数")
    fun CONCAT(one: String, vararg remain: String): String

    @Description(
        """
CONCAT_WS

   1. 用法：CONCAT_WS(split, str1,str2, ... strN)
   2. 说明：返回字符串拼接的结果
   3. 示例：CONCAT_WS("+", "Hello", " ", "World") = "Hello+ +World"
   4. 参数：string: 文本类型
    """
    )
    @Translate("CONCAT_WS($1, $0)")
    @Category("文本函数")
    fun CONCAT_WS(split: String, one: String, vararg remain: String): String

    @Description(
        """
SUBSTRING

    1. 用法： SUBSTRING(field, start, length)
    2. 说明： 返回field从start开始，长度为length的子字符串
    3. 示例： SUBSTRING("abcdef", 2, 3) = "bcd"
    4. 参数： String类型参数
    """
    )
    @Translate("SUBSTRING($1, $2, $3)")
    @Category("文本函数")
    @Alias("SUBSTR")
    fun SUBSTRING(text: String, start: Int, length: Int): String

    @Description(
        """
LEFT

    1. 用法： LEFT(field, length)
    2. 说明： 返回field从最左开始，长度为length的子字符串
    3. 示例： LEFT("abcdef", 2) = "ab"
    4. 参数： String类型参数, Int类型参数
    """
    )
    @Translate("LEFT($1, $2)")
    @Category("文本函数")
    fun LEFT(text: String, length: Int): String

    @Description(
        """
RIGHT

    1. 用法： RIGHT(field, length)
    2. 说明： 返回field从最右开始，长度为length的子字符串
    3. 示例： RIGHT("abcdef", 2) = "ef"
    4. 参数： String类型参数, Int类型参数
    """
    )
    @Translate("RIGHT($1, $2)")
    @Category("文本函数")
    fun RIGHT(text: String, length: Int): String

    @Description(
        """
CONTAINS
    1. 用法: CONTAINS(text, substr)
    2. 说明: 返回text中是否包含substr
    3. 示例: CONTAINS('abcde', 'abc') = true
    4. 参数: String类型参数
    """
    )
    @Translate("$1 LIKE '%$2%'")
    @Category("文本函数", "布尔函数")
    fun CONTAINS(text: String, subStr: String): Boolean

    @Description(
        """
TO_STRING

    1. 用法： TO_STRING(field)
    2. 说明： 返回字段转为字符串
    3. 示例： TO_STRING(a.b.c)
    4. 参数： 任意类型字段
    """
    )
    @Translate("CAST($1 AS CHAR)")
    @Category("文本函数", "转换函数")
    @Alias("TOSTR", "STRING")
    fun TO_STRING(field: Any): String

    @Description(
        """
TO_DOUBLE

    1. 用法： TO_DOUBLE(field, precision)
    2. 说明： 强制转换字段field为Double,保留precision位小数
    3. 示例： TO_DOUBLE("134.343", 2) = 134.34
             TO_DOUBLE(1234, 2) = 1234.00
    4. 参数： String, Integer 类型字段
    """
    )
    @Translate("CAST($1 AS DECIMAL(38, $2))")
    @Category("数值函数", "转换函数")
    fun TO_DOUBLE(text: String, precision: Int = 2): Double


    @Translate("CAST($1 AS DECIMAL(38, 0))")
    @Overload
    fun TO_DOUBLE(int: Int): Double

    @Description(
        """
TO_INTEGER

    1. 用法： TO_INTEGER(field)
    2. 说明： 强制转换字段四舍五入为Integer
    3. 示例： TO_INTEGER("134.235") = 134
    4. 参数： String, Double 类型字段
    """
    )
    @Translate("CAST($1 AS DECIMAL)")
    @Category("数值函数", "转换函数")
    fun TO_INTEGER(text: String): Int

    @Overload
    fun TO_INTEGER(double: Double): Int

    @Description(
        """
ABS

   1. 用法：ABS(number)
   2. 说明：返回number的绝对值
   3. 示例：ABS(利润)
   4. 参数类型：number : 数值类型字段
    """
    )
    @Translate("ABS($1)")
    @Category("数值函数")
    fun ABS(num: Double): Double

    @Overload
    fun ABS(num: Int): Int

    @Description(
        """
CEILING

   1. 用法：CEILING(number)
   2. 说明：返回大于等于指定数字的最小整数
   3. 示例：CEILING(体重)
   4. 参数：number：数值类型字段
    """
    )
    @Translate("CEIL($1)")
    @Category("数值函数")
    @Alias("CEIL")
    fun CEILING(num: Double): Int

    @Description(
        """
FLOOR

   1. 用法：FLOOR(number)
   2. 说明：返回小于等于指定数字的最大整数
   3. 示例：FLOOR(体重)
   4. 参数：number：数值类型字段
    """
    )
    @Category("数值函数")
    @Translate("FLOOR($1)")
    fun FLOOR(num: Double): Int

    @Description(
        """
ROUND

   1. 用法：ROUND(num1, num2)
   2. 说明：按照四舍五入的规则保留num1的num2结果
   3. 示例：ROUND(销售收入,2)
   4. 参数类型：num1: 数值类型字段
              num2: 正整数常量
    """
    )
    @Category("数值函数")
    @Translate("ROUND($1, $2)")
    fun ROUND(num: Double, precision: Int): Double

    @Description(
        """
GREATEST

       1. 用法: GREATEST(val1, val2...valN)
       2. 说明: 返回val1...valN中的最大值
       3. 示例: GREATEST('abc', 'bbc', "cbc") = 'cbc'
       4. 参数: 任意类型参数
    """
    )
    @Translate("GREATEST($0)")
    @Category("数值函数", "文本函数")
    fun <T> GREATEST(one: T, vararg remain: T): T

    @Description(
        """
LEAST

       1. 用法: LEAST(val1, val2...valN)
       2. 说明: 返回val1...valN中的最小值
       3. 示例: LEAST('abc', 'bbc', "cbc") = 'abc'
       4. 参数: 任意类型参数
    """
    )
    @Translate("LEAST($0)")
    @Category("数值函数", "文本函数")
    fun <T> LEAST(one: T, vararg remain: T): T

    @Description(
        """
YEAR
    1. 用法： YEAR(date)
    2. 说明： 获取时间的年份
    3. 示例： YEAR('2019-10-20') = 2019
    4. 参数类型： Date日期类型，
                DateTime时间类型,
                String 字符串类型, 要求配置时间格式
                Integer 数字类型, 要求配置时间格式
    """
    )
    @Category("时间函数")
    @Translate("YEAR($1)")
    fun YEAR(date: Any): Int

    @Description(
        """
QUARTER
    1. 用法： QUARTER(date)
    2. 说明： 获取时间的季度
    3. 示例： QUARTER('2019-10-20') = 4
    4. 参数类型： Date日期类型，
                DateTime时间类型,
                String 字符串类型, 要求配置时间格式
                Integer 数字类型, 要求配置时间格式
    """
    )
    @Category("时间函数")
    @Translate("QUARTER($1)")
    fun QUARTER(date: Any): Int

    @Description(
        """
MONTH
    1. 用法： MONTH(date)
    2. 说明： 获取时间的月份
    3. 示例： MONTH('2019-10-20') = 10
    4. 参数类型： Date日期类型，
                DateTime时间类型,
                String 字符串类型, 要求配置时间格式
                Integer 数字类型, 要求配置时间格式
    """
    )
    @Category("时间函数")
    @Translate("MONTH($1)")
    fun MONTH(date: Any): Int

    @Description(
        """
WEEK
    1. 用法： WEEK(date)
    2. 说明： 获取时间在年内的第几周, WEEK_OF_YEAR的别名
    3. 示例： WEEK('2019-10-20') = 42
    4. 参数类型： Date日期类型，
                DateTime时间类型,
                String 字符串类型, 要求配置时间格式，且至少包含天
                Integer 数字类型, 要求配置时间格式，且至少包含天
    """
    )
    @Category("时间函数")
    @Translate("WEEKOFYEAR($1)")
    @Alias("WEEKOFYEAR")
    fun WEEK(date: Any): Int

    @Description(
        """
DAY
    1. 用法： DAY(date)
    2. 说明： 获取日期在当前月内的第几天, DAY_OF_MONTH的别名
    3. 示例： DAY('2019-10-20') = 20
    4. 参数类型： Date日期类型，
                DateTime时间类型,
                String 字符串类型, 要求配置时间格式
                Integer 数字类型, 要求配置时间格式
    """
    )
    @Category("时间函数")
    @Translate("DAY($1)")
    @Alias("DAYOFMONTH")
    fun DAY(date: Any): Int

    @Description(
        """
DATEADD

   1. 用法：DATEADD (datetime,delta,datepart)
   2. 说明：返回间隔delta个日期单位的日期
   3. 示例：DATEADD(交易日期,7,'day')，交易日期往后推7天
   4. 参数类型：date: 日期时间类型字段
              delta: 整数常量
              datepart: 日期粒度常量, 年 - ‘year', 月-‘month', 日 - ‘day'
    """
    )
    @Category("时间函数")
    @Translate("""DATE_ADD($1, interval $2 {"$3".substring(1, "$3".length()-1)})""")
    fun DATEADD(date: Date, delta: Int, @Option("'year'", "'month'", "'day'") datepart: String): Date

    @Description(
        """
DATEADD

   1. 用法：DATEADD (datetime,delta,datepart)
   2. 说明：返回间隔delta个日期单位的日期
   3. 示例：DATEADD(交易日期,7,'day')，交易日期往后推7天
   4. 参数类型：date: 日期时间类型字段
              delta: 整数常量
              datepart: 日期粒度常量, 年 - ‘year', 月-‘month', 日 - ‘day'
    """
    )
    @Category("时间函数")
    @Translate("""DATE_ADD($1, interval $2 {"$3".substring(1, "$3".length()-1)})""")
    fun DATEADD(
        date: DateTime,
        delta: Int,
        @Option("'year'", "'month'", "'day'", "'hour'", "'minute'", "'second'") datepart: String
    ): DateTime

    @Description(
        """
TO_DATE

   1. 用法：TO_DATE(string, format)
   2. 说明：把文本转化成为给定格式的时间格式
   3. 示例：TO_DATE (销售日期, 'yyyy')
   4. 参数类型：string: 文本类型字段
              format:  日期格式，例如'yyyy-MM-dd'
    """
    )
    @Category("时间函数")
    @Translate("FROM_TIMESTAMP(CAST($1 as timestamp), $2)")
    fun TO_DATE(text: String, format: String): Date

    @Description(
        """
CURRENT_DATE

    1. 用法：CURRENT_DATE( )
    2. 说明：返回当前日期
    3. 示例：CURRENT_DATE( )
    4. 参数：无
    """
    )
    @Category("时间函数")
    @Translate("CURRENT_DATE()")
    fun CURRENT_DATE(): Date

    @Description(
        """
CURRENT_DATETIME

    1. 用法：CURRENT_DATETIME( )
    2. 说明：返回当前时间
    3. 示例：CURRENT_DATETIME( )
    4. 参数：无
    """
    )
    @Category("时间函数")
    @Translate("CURRENT_TIMESTAMP()")
    fun CURRENT_DATETIME(): DateTime

    @Description(
        """
DATEDIFF

    1. 用法：DATEDIFF(TIMESTAMP / DATE startdate, TIMESTAMP / DATE enddate, String unit)
    2. 说明：计算两个时间相隔unit的数量, enddate - startdate
            unit：'day','week', 'month', 'quarter', 'year'
    3. 示例：DATEDIFF('2010-11-30 23:59:59','2010-12-31', 'day') = -31
    4. 参数：enddate, startdate
    """
    )
    @Category("时间函数")
    abstract fun DATEDIFF(
        fromDate: Date,
        toDate: Date,
        @Option("'year'", "'quarter'", "'month'", "'week'", "'day'") unit: String = "'day'"
    ): Int

    @Description(
        """
DATEDIFF

    1. 用法：DATEDIFF(TIMESTAMP / DATE startdate, TIMESTAMP / DATE enddate, String unit)
    2. 说明：计算两个时间相隔unit的数量, enddate - startdate
            unit：'day','week', 'month', 'quarter', 'year'
    3. 示例：DATEDIFF('2010-11-30 23:59:59','2010-12-31', 'day') = -31
    4. 参数：enddate, startdate
    """
    )
    @Category("时间函数")
    abstract fun DATEDIFF(
        fromDate: DateTime,
        toDate: DateTime,
        @Option("'year'", "'quarter'", "'month'", "'week'", "'day'") unit: String = "'day'"
    ): Int

    @Description(
        """
IFNULL
       1. 用法：IFNULL(var1, var2)
       2. 说明：如果var1为null,则返回var2，否则返回var1
       3. 示例：IFNULL(null,2) = 2, IFNULL(1, 0) = 1
       4. 参数类型：var1: 任意类型字段或常量
                  var2:  任意类型字段或常量
    """
    )
    @Category("其他函数")
    @Translate("IFNULL($1, $2)")
    fun <T> IFNULL(`val`: T, ifNullVal: T): T

    @Description(
        """
IF
       1. 用法：IF(var1:Bool, var2:Any, var3: Any)
       2. 说明：如果var1为true,则返回var2，否则返回var3
       3. 示例：IF(4/2==2,'yes', 'no') = 'yes'
       4. 参数类型：var1: Bool类型字段或表达式
                   var2:  任意类型字段或常量
                   var3:  任意类型字段或常量
    """
    )
    @Category("其他函数")
    @Translate("IF($1, $2, $3)")
    fun <T> IF(condition: Boolean, ifTrueVal: T, ifFalseOrNullVal: T): T

    @Description(
        """
COALESCE

       1. 用法：COALESCE(var1:T, var2:T... varN: T)
       2. 说明：返回var1, var2...varN中第一个不为null的值
       3. 示例：COALESCE(null, null, 1, 2) = 1
       4. 参数类型：任意类型
    """
    )
    @Category("其他函数")
    @Translate("COALESCE($0)")
    fun <T> COALESCE(one: T, vararg remain: T): T

    @Description(
        """
RAW_SQL
       1. 用法： RAW_SQL("raw_sql_funcion({1}, {2}, {3}, {0})", field1, field2, field3, field4, field5)
                受限于使用的数据库函数参数，本函数不保证能正确推荐和使用
       2. 说明： 返回第一个参数代表的数据库函数， {x}表示第x个参数(从1开始,0代表剩余的参数-用于变长参数情形)
       3. 示例:  RAW_SQL("CONCAT({1}, {2}, {3}, {0})", field1, field2, field3, field4, field5) 
                        = CONCAT(field1, field2, field3, field4, field5) 
       4. 参数类型： param1: 字符串类型
                   paramN: 任意类型
    """
    )
    @Category("其他函数")
    @Translate("", RawSqlTranslator::class)
    fun RAW_SQL(sql: String, vararg args: Any): None


    @Description(
        """
GROUP_COUNT

       1. 用法：GROUP_COUNT(agg:Any, group:Any... varN: Any)
       2. 说明：返回agg按group字段聚合后的值个数
       3. 示例：GROUP_COUNT(customer, branch_no), 各个营业部的客户个数
       4. 参数类型：任意字段类型
    """
    )
    @Category("聚合函数")
    @Translate("COUNT($1)")
    fun GROUP_COUNT(agg: Any, vararg group: Any): Int

    @Description(
        """
GROUP_COUNT

       1. 用法：GROUP_COUNT([DISTINCT|ALL] agg:Any, group:Any... varN: Any)
       2. 说明：返回agg按group字段聚合后的唯一值个数
       3. 示例：GROUP_COUNT([DISTINCT|ALL] customer, branch_no), 各个营业部的客户唯一数
       4. 参数类型：任意字段类型
    """
    )
    @Category("聚合函数")
    @Translate("COUNT($1 $2)")
    fun GROUP_COUNT(@Reserved("DISTINCT", "ALL") dist: Unit, agg: Any, vararg group: Any): Int

    @Description(
        """
GROUP_MAX

       1. 用法：GROUP_MAX(agg: Double/Integer, group:Any... varN: Any)
       2. 说明：返回agg按group字段聚合后的最大值
       3. 示例：GROUP_MAX(sale, branch_no), 各个营业部的最大销售额
       4. 参数类型：agg 数值类型
                  group 任意类型
    """
    )
    @Category("聚合函数")
    @Translate("MAX($1)")
    fun GROUP_MAX(agg: Double, vararg group: Any): Double

    @Overload
    fun GROUP_MAX(agg: Int, vararg group: Any): Int

    @Description(
        """
GROUP_MIN

       1. 用法：GROUP_MIN(agg:Double/Integer, group:Any... varN: Any)
       2. 说明：返回agg按group字段聚合后的最小值
       3. 示例：GROUP_MIN(sale, branch_no), 各个营业部的最小销售额
       4. 参数类型：agg 数值类型
                  group 任意类型
    """
    )
    @Category("聚合函数")
    @Translate("MIN($1)")
    fun GROUP_MIN(agg: Double, vararg group: Any): Double

    @Overload
    fun GROUP_MIN(agg: Int, vararg group: Any): Int

    @Description(
        """
GROUP_AVG

       1. 用法：GROUP_AVG(agg:Double/Integer, group:Any... varN: Any)
       2. 说明：返回agg按group字段聚合后的平均值
       3. 示例：GROUP_AVG(sale, branch_no), 各个营业部的平均销售额
       4. 参数类型：agg 数值类型
                  group 任意类型
    """
    )
    @Category("聚合函数")
    @Translate("AVG($1)")
    fun GROUP_AVG(agg: Double, vararg group: Any): Double

    @Overload
    fun GROUP_AVG(agg: Int, vararg group: Any): Double

    @Description(
        """
GROUP_SUM

       1. 用法：GROUP_SUM(agg:Double/Integer, group:Any... varN: Any)
       2. 说明：返回agg按group字段聚合后的合计值
       3. 示例：GROUP_SUM(sale, branch_no), 各个营业部的总销售额
       4. 参数类型：agg 数值类型
                  group 任意类型
    """
    )
    @Category("聚合函数")
    @Translate("SUM($1)")
    fun GROUP_SUM(agg: Double, vararg group: Any): Double

    @Overload
    fun GROUP_SUM(agg: Int, vararg group: Any): Int

    @Description("")
    @Category("聚合函数")
    @Translate("GROUP_CONCAT($1, $2)")
    fun GROUP_CONCAT(agg: String, @Constant split: String, vararg group: Any): String

    @Description("")
    @Category("聚合函数")
    @Translate("GROUP_CONCAT($1)")
    fun GROUP_CONCAT(agg: String, vararg group: Any): String

    @Description("")
    @Category("聚合函数")
    @Translate("STDDEV($1)")
    fun GROUP_STDDEV(agg: Double, vararg group: Any): Double

    @Description("")
    @Category("聚合函数")
    @Translate("VARIANCE($1)")
    fun GROUP_VARIANCE(agg: Double, vararg group: Any): Double


    @Description(
        """
RANK_OVER
   
    """
    )
    @Category("分析函数")
    @Translate("", PartitionOrderTranslator::class, "RANK")
    fun RANK_OVER(
        @Suggest("PARTITION_BY", "FUNCTION") partitionBy: List<Any>?,
        @Suggest("ORDER_BY", "FUNCTION") orderBy: List<Any>
    ): Int

    @Description(
        """
        
    """
    )
    @Category("分析函数")
    @Translate("", PartitionOrderTranslator::class, "DENSE_RANK")
    fun DENSE_RANK_OVER(
        @Suggest("PARTITION_BY", "FUNCTION") partitionBy: List<Any>?,
        @Suggest("ORDER_BY", "FUNCTION") orderBy: List<Any>
    ): Int

    @Description(
        """

    """
    )
    @Category("分析函数")
    @Translate("", PartitionOrderTranslator::class, "PERCENT_RANK")
    fun PERCENT_RANK_OVER(
        @Suggest("PARTITION_BY", "FUNCTION") partitionBy: List<Any>?,
        @Suggest("ORDER_BY", "FUNCTION") orderBy: List<Any>
    ): Int

    @Description("")
    @Category("分析函数")
    @Translate("", PreDefinedPartitionOrderTranslator::class, "NTILE($1)", "1", "2")
    fun NTILE_OVER(
        bucket: Int,
        @Suggest("PARTITION_BY", "FUNCTION") partitionBy: List<Any>?,
        @Suggest("ORDER_BY", "FUNCTION") orderBy: List<Any>
    ): Int

    @Description("")
    @Category("分析函数")
    @Translate("", PartitionOrderTranslator::class, "ROW_NUMBER")
    fun ROW_NUM_OVER(
        @Suggest("PARTITION_BY", "FUNCTION") partitionBy: List<Any>?,
        @Suggest("ORDER_BY", "FUNCTION") orderBy: List<Any>
    ): Int

    @Category("分析函数")
    @Translate("", LagLeadTranslator::class, "LAG")
    fun <T> LAG_OVER(
        field: T,
        offset: Int?,
        defaultVal: T?,
        @Suggest("PARTITION_BY", "FUNCTION") partitionBy: List<Any>?,
        @Suggest("ORDER_BY", "FUNCTION") orderBy: List<Any>
    ): T

    @Overload
    fun <T> LAG_OVER(
        field: T,
        offset: Int?,
        @Suggest("PARTITION_BY", "FUNCTION") partitionBy: List<Any>?,
        @Suggest("ORDER_BY", "FUNCTION") orderBy: List<Any>
    ): T

    @Overload
    fun <T> LAG_OVER(
        field: T,
        @Suggest("PARTITION_BY", "FUNCTION") partitionBy: List<Any>?,
        @Suggest("ORDER_BY", "FUNCTION") orderBy: List<Any>
    ): T

    @Category("分析函数")
    @Translate("", LagLeadTranslator::class, "LEAD")
    fun <T> LEAD_OVER(
        field: T,
        offset: Int?,
        defaultVal: T?,
        @Suggest("PARTITION_BY", "FUNCTION") partitionBy: List<Any>?,
        @Suggest("ORDER_BY", "FUNCTION") orderBy: List<Any>
    ): T

    @Overload
    fun <T> LEAD_OVER(
        field: T,
        offset: Int?,
        @Suggest("PARTITION_BY", "FUNCTION") partitionBy: List<Any>?,
        @Suggest("ORDER_BY", "FUNCTION") orderBy: List<Any>
    ): T

    @Overload
    fun <T> LEAD_OVER(
        field: T,
        @Suggest("PARTITION_BY", "FUNCTION") partitionBy: List<Any>?,
        @Suggest("ORDER_BY", "FUNCTION") orderBy: List<Any>
    ): T

    @Category("分析函数")
    @Translate("", PreDefinedPartitionOrderFrameTranslator::class, "FIRST_VALUE($1)", "1", "2", "3", "4", "5")
    fun <T> FIRST_VALUE_OVER(
        field: T,
        @Suggest("PARTITION_BY", "FUNCTION") partitionBy: List<Any>?,
        @Suggest("ORDER_BY", "FUNCTION") orderBy: List<Any>,
        @Reserved("ROWS", "RANGE") unit: String = "ROWS",
        @Constant from: Int? = 0,
        @Constant end: Int? = 0
    ): T

    @Category("分析函数")
    @Translate("", PreDefinedPartitionOrderFrameTranslator::class, "LAST_VALUE($1)", "1", "2", "3", "4", "5")
    fun <T> LAST_VALUE_OVER(
        field: T,
        @Suggest("PARTITION_BY", "FUNCTION") partitionBy: List<Any>?,
        @Suggest("ORDER_BY", "FUNCTION") orderBy: List<Any>,
        @Reserved("ROWS", "RANGE") unit: String = "ROWS",
        @Constant from: Int? = 0,
        @Constant end: Int? = 0
    ): T

//    @Category("分析函数")
//    fun <T> NTH_VALUE_OVER(field: T, offset: Int, @Suggest("PARTITION_BY", "FUNCTION") partitionBy: List<Any>?, @Suggest("ORDER_BY", "FUNCTION") orderBy: List<Any>, @Reserved("ROWS", "RANGE") unit: String="ROWS",  @Constant from: Int?=0, @Constant end: Int?=0): T

    @Category("分析函数")
    @Translate("", PreDefinedPartitionOrderFrameTranslator::class, "COUNT($1)", "1", "2", "3", "4", "5")
    fun COUNT_OVER(
        field: Any,
        @Suggest("PARTITION_BY", "FUNCTION") partitionBy: List<Any>?,
        @Suggest("ORDER_BY", "FUNCTION") orderBy: List<Any>?,
        @Reserved("ROWS", "RANGE") unit: String = "ROWS",
        @Constant from: Int? = 0,
        @Constant end: Int? = 0
    ): Int

    @Category("分析函数")
    @Translate("", PreDefinedPartitionOrderFrameTranslator::class, "COUNT($1 $2)", "2", "3", "4", "5", "6")
    fun COUNT_OVER(
        @Reserved("DISTINCT", "ALL") dist: Unit,
        field: Any,
        @Suggest("PARTITION_BY", "FUNCTION") partitionBy: List<Any>?,
        @Suggest("ORDER_BY", "FUNCTION") orderBy: List<Any>?,
        @Reserved("ROWS", "RANGE") unit: String = "ROWS",
        @Constant from: Int? = 0,
        @Constant end: Int? = 0
    ): Int

    @Category("分析函数")
    @Translate("", PreDefinedPartitionOrderFrameTranslator::class, "MAX($1)", "1", "2", "3", "4", "5")
    fun <T> MAX_OVER(
        field: T,
        @Suggest("PARTITION_BY", "FUNCTION") partitionBy: List<Any>?,
        @Suggest("ORDER_BY", "FUNCTION") orderBy: List<Any>?,
        @Reserved("ROWS", "RANGE") unit: String = "ROWS",
        @Constant from: Int? = 0,
        @Constant end: Int? = 0
    ): T

    @Category("分析函数")
    @Translate("", PreDefinedPartitionOrderFrameTranslator::class, "MIN($1)", "1", "2", "3", "4", "5")
    fun <T> MIN_OVER(
        field: T,
        @Suggest("PARTITION_BY", "FUNCTION") partitionBy: List<Any>?,
        @Suggest("ORDER_BY", "FUNCTION") orderBy: List<Any>?,
        @Reserved("ROWS", "RANGE") unit: String = "ROWS",
        @Constant from: Int? = 0,
        @Constant end: Int? = 0
    ): T

    @Category("分析函数")
    @Translate("", PreDefinedPartitionOrderFrameTranslator::class, "AVG($1)", "1", "2", "3", "4", "5")
    fun AVG_OVER(
        field: Double,
        @Suggest("PARTITION_BY", "FUNCTION") partitionBy: List<Any>?,
        @Suggest("ORDER_BY", "FUNCTION") orderBy: List<Any>?,
        @Reserved("ROWS", "RANGE") unit: String = "ROWS",
        @Constant from: Int? = 0,
        @Constant end: Int? = 0
    ): Double

    @Category("分析函数")
    @Translate("", PreDefinedPartitionOrderFrameTranslator::class, "AVG($1)", "1", "2", "3", "4", "5")
    fun AVG_OVER(
        field: Int,
        @Suggest("PARTITION_BY", "FUNCTION") partitionBy: List<Any>?,
        @Suggest("ORDER_BY", "FUNCTION") orderBy: List<Any>?,
        @Reserved("ROWS", "RANGE") unit: String = "ROWS",
        @Constant from: Int? = 0,
        @Constant end: Int? = 0
    ): Double

    @Category("分析函数")
    @Translate("", PreDefinedPartitionOrderFrameTranslator::class, "SUM($1)", "1", "2", "3", "4", "5")
    fun SUM_OVER(
        field: Double,
        @Suggest("PARTITION_BY", "FUNCTION") partitionBy: List<Any>?,
        @Suggest("ORDER_BY", "FUNCTION") orderBy: List<Any>?,
        @Reserved("ROWS", "RANGE") unit: String = "ROWS",
        @Constant from: Int? = 0,
        @Constant end: Int? = 0
    ): Double

    @Category("分析函数")
    @Translate("", PreDefinedPartitionOrderFrameTranslator::class, "SUM($1)", "1", "2", "3", "4", "5")
    fun SUM_OVER(
        field: Int,
        @Suggest("PARTITION_BY", "FUNCTION") partitionBy: List<Any>?,
        @Suggest("ORDER_BY", "FUNCTION") orderBy: List<Any>?,
        @Reserved("ROWS", "RANGE") unit: String = "ROWS",
        @Constant from: Int? = 0,
        @Constant end: Int? = 0
    ): Int

    @Category("分析函数")
    @Translate("", PreDefinedPartitionOrderFrameTranslator::class, "STDDEV($1)", "1", "2", "3", "4", "5")
    fun STDDEV_OVER(
        field: Double,
        @Suggest("PARTITION_BY", "FUNCTION") partitionBy: List<Any>?,
        @Suggest("ORDER_BY", "FUNCTION") orderBy: List<Any>?,
        @Reserved("ROWS", "RANGE") unit: String = "ROWS",
        @Constant from: Int? = 0,
        @Constant end: Int? = 0
    ): Double

    @Category("分析函数")
    @Translate("", PreDefinedPartitionOrderFrameTranslator::class, "STDDEV($1)", "1", "2", "3", "4", "5")
    fun STDDEV_OVER(
        field: Int,
        @Suggest("PARTITION_BY", "FUNCTION") partitionBy: List<Any>?,
        @Suggest("ORDER_BY", "FUNCTION") orderBy: List<Any>?,
        @Reserved("ROWS", "RANGE") unit: String = "ROWS",
        @Constant from: Int? = 0,
        @Constant end: Int? = 0
    ): Double

    @Category("分析函数")
    @Description(
        """
CUME_DIST_OVER

        
    """
    )
    @Translate("", PreDefinedPartitionOrderFrameTranslator::class, "CUME_DIST()", "0", "1", "2", "3", "4")
    fun CUME_DIST_OVER(
        @Suggest("PARTITION_BY", "FUNCTION") partitionBy: List<Any>?,
        @Suggest("ORDER_BY", "FUNCTION") orderBy: List<Any>?,
        @Reserved("ROWS", "RANGE") unit: String = "ROWS",
        @Constant from: Int? = 0,
        @Constant end: Int? = 0
    ): Double

    @Translate("$0")
    private fun LIST(vararg item: Any): List<Any> = emptyList()

    @Translate("$0")
    private fun PARTITION_BY(vararg item: Any): List<Any> = emptyList()

    @Translate("$0")
    private fun ORDER_BY(@Suggest("ORDER_ITEM", "FUNCTION") vararg item: Any): List<Any> = emptyList()

    @Translate("$1 $2")
    private fun <T> ORDER_ITEM(item: T, @Reserved("ASC", "DESC") order: Unit): T? = null
}

interface Hive : Common {

    @Translate("CAST($1 AS STRING)")
    override fun TO_STRING(field: Any): String

    @Translate("STRLEFT($1, $2)")
    override fun LEFT(text: String, length: Int): String

    @Translate("STRRIGHT($1, $2)")
    override fun RIGHT(text: String, length: Int): String

    @Translate("CEILING(MONTH($1)/3)")
    override fun QUARTER(date: Any): Int

    @Translate("""TO_DATE( DATE_ADD(cast($1 as timestamp), interval $2 {"$3".substring(1, "$3".length()-1)}))""")
    override fun DATEADD(date: Date, delta: Int, datepart: String): Date

    @Translate("""TO_DATE( DATE_ADD(cast($1 as timestamp), interval $2 {"$3".substring(1, "$3".length()-1)}))""")
    override fun DATEADD(date: DateTime, delta: Int, datepart: String): DateTime

    @Translate("TO_TIMESTAMP($1, $2)")
    override fun TO_DATE(text: String, format: String): Date

    @Translate("TO_DATE(CURRENT_TIMESTAMP())")
    override fun CURRENT_DATE(): Date

    @Translate("FROM_TIMESTAMP(CURRENT_TIMESTAMP(), 'yyyy-MM-dd HH:mm:ss')")
    override fun CURRENT_DATETIME(): DateTime

    @Translate(
        """{
        if ("'day'".equalsIgnoreCase("$3")) {
            //天差
            return "DATEDIFF($1, $2)";
        } else if ("'week'".equalsIgnoreCase("$3")) {
            //周差
            return "FLOOR(DATEDIFF($1, $2)/7)";
        } else if ("'month'".equalsIgnoreCase("$3")) {
            //月差
            return "INT_MONTHS_BETWEEN($1, $2)";
        } else if ("'quarter'".equalsIgnoreCase("$3")) {
            //季差
            return "FLOOR(INT_MONTHS_BETWEEN($1, $2)/3)";
        } else if("'year'".equalsIgnoreCase("$3")){
            //年差
            return "YEAR($1)-YEAR($2)";
        } else {
            return "DATEDIFF($1, $2)";
        }
    }"""
    )
    override fun DATEDIFF(fromDate: Date, toDate: Date, unit: String): Int

    @Translate(
        """{
        if ("'day'".equalsIgnoreCase("$3")) {
            //天差
            return "DATEDIFF($1, $2)";
        } else if ("'week'".equalsIgnoreCase("$3")) {
            //周差
            return "FLOOR(DATEDIFF($1, $2)/7)";
        } else if ("'month'".equalsIgnoreCase("$3")) {
            //月差
            return "INT_MONTHS_BETWEEN($1, $2)";
        } else if ("'quarter'".equalsIgnoreCase("$3")) {
            //季差
            return "FLOOR(INT_MONTHS_BETWEEN($1, $2)/3)";
        } else if("'year'".equalsIgnoreCase("$3")){
            //年差
            return "YEAR($1)-YEAR($2)";
        } else {
            return "DATEDIFF($1, $2)";
        }
    }"""
    )
    override fun DATEDIFF(fromDate: DateTime, toDate: DateTime, unit: String): Int
}

interface Impala : Hive

interface Mysql : Common {

    @Translate("STR_TO_DATE($1,$2)")
    override fun TO_DATE(text: String, format: String): Date

    @Translate("""TIMESTAMPDIFF({"$3".substring(1, "$3".length()-1)},  $1, $2)""")
    override fun DATEDIFF(fromDate: Date, toDate: Date, unit: String): Int

    @Translate("""TIMESTAMPDIFF({"$3".substring(1, "$3".length()-1)},  $1, $2)""")
    override fun DATEDIFF(fromDate: DateTime, toDate: DateTime, unit: String): Int


}

interface MariaDB : Mysql

