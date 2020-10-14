package org.sunt.formula.suggestion;

public enum TokenStatus {

    //正常
    NORMAL(1),
    //错误(语法错误）
    ERROR(5),
    //未识别
    UNKNOWN(4),
    //期望的(当前没有）
    EXPECTED(3),
    //提示
    INFO(2),
    //无
    NONE(0);

    private final int privilege;

    TokenStatus(int privilege) {
        this.privilege = privilege;
    }

    public int getPrivilege() {
        return privilege;
    }

}
