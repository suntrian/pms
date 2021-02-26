package org.sunt.formula.function;

public enum TokenScope {
    NONE,           //不推荐，输入常量或者数字或者完整匹配的时候
    RESERVED,         //保留字
    FUNCTION,       //函数
    COLUMN,         //字段
    CONSTANT,       //常量
    CAL_OPERATOR,   //计算运算符
    CMP_OPERATOR,   //比较运算符
    LOG_OPERATOR,   //逻辑运算符
    PARENTHESES,    //括号
    BRACE,        //大括号
    COMMA;          //逗号
}