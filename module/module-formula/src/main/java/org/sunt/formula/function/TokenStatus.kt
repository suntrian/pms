package org.sunt.formula.function

enum class TokenStatus(val privilege: Int) : Comparable<TokenStatus> {

    //错误(语法错误）
    ERROR(5),

    //未识别
    UNKNOWN(4),

    //期望的(当前没有）
    EXPECTED(3),

    //提示
    INFO(2),

    //正常
    NORMAL(1),

    //无
    NONE(0);

}