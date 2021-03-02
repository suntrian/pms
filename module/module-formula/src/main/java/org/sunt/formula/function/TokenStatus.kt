package org.sunt.formula.function

enum class TokenStatus(val privilege: Int) : Comparable<TokenStatus> {


    //无
    NONE(0),

    //正常
    NORMAL(1),


    //提示
    INFO(2),


    //期望的(当前没有）
    EXPECTED(3),

    //未识别
    UNKNOWN(4),

    //错误(语法错误）
    ERROR(5);


}