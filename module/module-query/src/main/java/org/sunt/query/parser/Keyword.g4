grammar Keyword;

import Formula;

keywords
    : (keyword SEMICOLON?)+ EOF
    ;

keyword
    : statement
    | aggregate
    | filter
    | group
    | sort
    | limit
    ;

aggregate
    : statement ('计数' | '数目' | '数量' | '个数')                     # COUNT
    | statement ('唯一数' | '去重计数')                                 # COUNT_DISTINCT
    | statement ('合计' | '总计' | '求和' | '加总' | '共计' | '总量')    # SUM
    | statement ('均值' | '平均值' | '平均')                            # AVG
    | statement ('最大值' | '最大' )                                    # MAX
    | '最大的' statement                                               # MAX
    | statement ('最小值' | '最小' )                                    # MIN
    | '最小的' statement                                               # MIN
    ;

group
    : statement ('每年' | '按年统计' | '趋势按年' | '年趋势')            # GROUP_YEAR
    | statement ('每季度' | '按季度统计' | '趋势按季度' | '季度趋势')     # GROUP_QUARTER
    | statement ('每月' | '按月统计' | '趋势按月' | '月趋势')            # GROUP_MONTH
    | statement ('每周' | '按周统计' | '趋势按周' | '周趋势')            # GROUP_MONTH
    | statement ('每日' | '按日统计' | '趋势按日' | '日趋势')            # GROUP_DAY
    ;

filter
    : statement '最大的'                                                               # GREATEST
    | statement '最少的'                                                               # LEATEST
    | statement ('大于' | '多于' | '高于' | '超过') statement                           # GREATER
    | statement ('小于' | '少于' | '低于' ) statement                                  # LESS
    | statement ('大于等于' | '不少于' | '不低于' | '不小于') statement                  # GREATER_EQUAL
    | statement ('小于等于' | '不多于' | '不高于' | '不大于') statement                  # LESS_EQUAL
    | statement ('等于' | '是') statement                                              # EQUAL
    | statement ('不等于' | '不是') statement                                          # NOT_EQUAL
    | statement ('在' | '介于') statement ('和' | '与') statement ('之间' | '之内')     # BETWEEN
    | statement ('开头是' | '开始是') statement                                        # START_WITH
    | statement '以' statement ('开头' | '开始')                                       # START_WITH
    | statement ('结尾是' | '结束是') statement                                        # END_WITH
    | statement '以' statement ('结尾' | '结束')                                       # END_WITH
    | statement ('包含') statement                                                    # CONTAINS
    | statement ('在去年' | '在上年' )                                                 # LAST_YEAR
    | statement ('在上季度')                                                          # LAST_QUARTER
    | statement ('在上月' | '在上个月')                                                # LAST_MONTH
    | statement ('在上周' | '在上星期')                                                # LAST_WEEK
    | statement ('在昨天' | '在上一天')                                                # LAST_DAY
    | statement '在' statement ('之前' | '以前' )                                     # BEFORE
    | statement '在' statement ('之后' | '以后' )                                     # AFTER
    ;



sort
    : ('按'|'以')? statement '升序' '排序'?                                            # ASC
    | ('按'|'以')? statement '降序' '排序'?                                            # DESC
    ;

limit
    : statement ('排名'|'名次'|'排')? '在'? '前' INTEGER '名'? '的'?                  # ASC_LIMIT
    | statement ('排名'|'名次'|'排')? '在'? '后' INTEGER '名'? '的'?                  # DESC_LIMIT
    | statement '倒数' INTEGER '名'? '的'?                                           # DESC_LIMIT
    ;

SEMICOLON: ';';