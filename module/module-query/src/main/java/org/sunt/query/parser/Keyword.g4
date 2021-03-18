grammar Keyword;

import Formula;

@lexer::header {
package org.sunt.query.parser;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
}

@parser::header {
package org.sunt.query.parser;
}

@lexer::members {
public static Set<String> reservedWords = new HashSet<>();
static {
    String reservedWordStr = "COUNT: '计数' | '数目' | '数量' | '个数'" +
                             "COUNT_DISTINCT: '唯一数' | '去重计数'" +
                             "SUM: '合计' | '总计' | '求和' | '加总' | '共计' | '总量'" +
                             "AVG: '均值' | '平均值' | '平均'" +
                             "GREATEST: '最大的' | '最多的'" +
                             "LEATEST: '最小的' |'最少的'" +
                             "MAX: '最大值' | '最大'" +
                             "MIN: '最小值' | '最小'" +
                             "GROUP_YEAR: '每年' | '按年统计' | '趋势按年' | '年趋势'" +
                             "GROUP_QUARTER: '每季' | '按季统计' | '趋势按季' | '季趋势'" +
                             "GROUP_MONTH: '每月' | '按月统计' | '趋势按月' | '月趋势'" +
                             "GROUP_WEEK: '每周' | '按周统计' | '趋势按周' | '周趋势'" +
                             "GROUP_DAY: '每日' | '按日统计' | '趋势按日' | '日趋势' " +
                             "GREATER: '大于' | '多于' | '高于' | '超过'" +
                             "LESS: '小于' | '少于' | '低于'" +
                             "GREATER_EQUAL: '大于等于' | '不少于' | '不低于' | '不小于'" +
                             "LESS_EQUAL: '小于等于' | '不多于' | '不高于' | '不大于'" +
                             "NOT_EQUAL: '不等于' | '不是'" +
                             "START_WITH: '开头是' | '开始是'" +
                             "START: '开头' | '开始'" +
                             "END_WITH: '结尾是' | '结束是'" +
                             "END: '结尾' | '结束'" +
                             "EQUAL: '等于' | '是' | '为'" +
                             "CONTAINS: '包含'" +
                             "LAST_YEAR: '在去年' | '在上年'" +
                             "LAST_QUARTER: '在上季度'" +
                             "LAST_MONTH: '在上月' | '在上个月'" +
                             "LAST_WEEK: '在上周' | '在上星期'" +
                             "LAST_DAY: '在昨天' | '在上一天'" +
                             "ORDER: '排名'|'名次'|'排'" +
                             "ASC: '升序' | '升序排序' | '排序'" +
                             "DESC: '降序' | '倒序' | '降序排序' | '倒序排序'" +
                             "REVERSE: '倒数'" +
                             "INSIDE: '之间' | '之内'" +
                             "BEFORE: '之前' | '以前' | '前'" +
                             "AFTER: '之后' | '以后' | '后'" +
                             "AUX_ZAI: '在'" +
                             "AUX_YI: '按' | '以'" +
                             "AUX_HE: '和' | '与'" +
                             "AUX_DE: '的'" +
                             "AUX_MIN: '名的' | '名'" +
                             "LOGIC_OR:  '或者' | '或' " +
                             "LOGIC_AND: '并且' | '而且' | '且'" +
                             "'介于'"
        ;
    Pattern patern = Pattern.compile("'([^']+?)'");
    Matcher matcher = patern.matcher(reservedWordStr);
    while (matcher.find()) {
        reservedWords.add(matcher.group(1));
    }
}

}

keywords
    : (keyword SEMICOLON?)+ EOF
    ;

keyword
    : sort
    | aggregate
    | filter
    | group
    | limit
    | segment
    ;

segment
    : (statement | nonReserved )+ HINT?
    ;

aggregate
    : segment (COUNT | COUNT_DISTINCT | SUM | AVG | MAX | MIN)
    | GREATEST segment
    | LEATEST segment
    ;

group
    : segment (GROUP_YEAR | GROUP_QUARTER | GROUP_MONTH | GROUP_WEEK | GROUP_DAY)
    ;

filter
    : segment op=(GREATEST | LEATEST)                                                                      # mostFilter
    | segment op=(GREATER | LESS | GREATER_EQUAL | LESS_EQUAL | EQUAL | NOT_EQUAL | CONTAINS) segment      # compareFilter
    | segment op=(AUX_ZAI | '介于') segment AUX_HE segment INSIDE                                           # betweenFilter
    | segment op=(START_WITH | END_WITH) segment                                                           # stringFilter
    | segment op=AUX_YI segment (START | END)                                                              # stringFilter
    | segment op=(LAST_YEAR | LAST_QUARTER | LAST_MONTH | LAST_WEEK | LAST_DAY)                            # lastDateFilter
    | segment AUX_ZAI segment op=(BEFORE | AFTER)                                                          # compareDateFilter
    | filter op=LOGIC_AND filter                                                                           # logicAndFilter
    | filter op=LOGIC_OR filter                                                                            # logicOrFilter
    ;



sort
    : AUX_YI? segment (ASC | DESC)
    ;

limit
    : segment ORDER? AUX_ZAI? (BEFORE | AFTER) INTEGER (AUX_MIN | AUX_DE)
    | segment REVERSE INTEGER (AUX_MIN | AUX_DE)
    ;

nonReserved
    : COUNT | COUNT_DISTINCT
    | SUM | AVG
    | GREATEST | LEATEST
    | MAX | MIN
    | GROUP_YEAR | GROUP_QUARTER | GROUP_MONTH | GROUP_WEEK | GROUP_DAY
    | EQUAL
    | ORDER
    | AUX_ZAI | AUX_YI | AUX_HE | AUX_DE | AUX_MIN
    ;

SEMICOLON: ';';
COUNT: '计数' | '数目' | '数量' | '个数';
COUNT_DISTINCT: '唯一数' | '去重计数';
SUM: '合计' | '总计' | '求和' | '加总' | '共计' | '总量';
AVG: '均值' | '平均值' | '平均';
GREATEST: '最大的' | '最多的';
LEATEST: '最小的' |'最少的';
MAX: '最大值' | '最大';
MIN: '最小值' | '最小';
GROUP_YEAR: '每年' | '按年统计' | '趋势按年' | '年趋势';
GROUP_QUARTER: '每季' | '按季统计' | '趋势按季' | '季趋势';
GROUP_MONTH: '每月' | '按月统计' | '趋势按月' | '月趋势';
GROUP_WEEK: '每周' | '按周统计' | '趋势按周' | '周趋势';
GROUP_DAY: '每日' | '按日统计' | '趋势按日' | '日趋势' ;
GREATER: '大于' | '多于' | '高于' | '超过';
LESS: '小于' | '少于' | '低于';
GREATER_EQUAL: '大于等于' | '不少于' | '不低于' | '不小于';
LESS_EQUAL: '小于等于' | '不多于' | '不高于' | '不大于';
NOT_EQUAL: '不等于' | '不是';
START_WITH: '开头是' | '开始是';
START: '开头' | '开始';
END_WITH: '结尾是' | '结束是';
END: '结尾' | '结束';
EQUAL: '等于' | '是' | '为';
CONTAINS: '包含';
LAST_YEAR: '在去年' | '在上年';
LAST_QUARTER: '在上季度';
LAST_MONTH: '在上月' | '在上个月';
LAST_WEEK: '在上周' | '在上星期';
LAST_DAY: '在昨天' | '在上一天';
ORDER: '排名'|'名次'|'排';
ASC: '升序' | '升序排序' | '排序';
DESC: '降序' | '倒序' | '降序排序' | '倒序排序';
REVERSE: '倒数';
INSIDE: '之间' | '之内';
BEFORE: '之前' | '以前' | '前';
AFTER: '之后' | '以后' | '后';
AUX_ZAI: '在';
AUX_YI: '按'|'以';
AUX_HE: '和' | '与';
AUX_DE: '的';
AUX_MIN: '名的' | '名';
LOGIC_OR:  '或者' | '或' ;
LOGIC_AND: '并且' | '而且' | '且' ;