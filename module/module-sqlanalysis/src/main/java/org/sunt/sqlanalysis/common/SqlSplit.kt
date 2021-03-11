package org.sunt.sqlanalysis.common

import java.util.*


object SqlSplit {

    private const val MODE_OUT_SQL = 0
    private const val MODE_S_COMMENT = 1 //单行注释
    private const val MODE_M_COMMENT = 2 //多行注释
    private const val MODE_IN_SQL = 3
    private const val MODE_STRING = 4
    private const val SQL_TYPE_CREATE = 10
    private const val SQL_TYPE_INSERT = 11
    private const val SQL_TYPE_DELETE = 12
    private const val SQL_TYPE_SELECT = 13
    private const val SQL_TYPE_OTHERS = 14
    private const val STMT_CREATE_TABLE_UNCERTAIN = 100
    private const val STMT_CREATE_TABLE_AS_SELECT = 101
    private const val STMT_CREATE_PROCEDURE_FUNCTION = 102
    private const val STMT_INSERT_INTO_UNCERTAIN = 110
    private const val STMT_INSERT_INTO_TABLE_SELECT = 111
    private const val STMT_INSERT_INTO_TABLE_VALUES = 112
    private const val STMT_DELETE_FROM_TABLE_UNCERTAIN = 120
    private const val STMT_DELETE_FROM_JOIN_OR_SELECT = 121
    private const val STMT_DELETE_FROM_TABLE_PLAIN = 122
    private const val STMT_SELECT_FROM_TABLE = 130
    private const val STMT_OTHER_STATEMENT = 109
    private const val DELIMITER = ";"

    @JvmStatic
    fun split(content: String): List<Pair<Int, String>> {
        val tokens = StringTokenizer(content, " \t\r\n\"';-#/*()", true)
        val sqlBuilder = StringBuilder()
        val result: MutableList<Pair<Int, String>> = LinkedList()
        var forMode = MODE_OUT_SQL
        var curMode = MODE_OUT_SQL
        var sqlType = SQL_TYPE_OTHERS
        var stmtType = STMT_OTHER_STATEMENT
        var forToken: String? = null
        var stringOpen: String? = null
        var countToken = true
        OUT@ while (tokens.hasMoreTokens()) {
            val token = tokens.nextToken()
            if (curMode == MODE_S_COMMENT && !"\r\n".contains(token)) {
                countToken = false
            } else if (curMode == MODE_M_COMMENT && "/" != token) {
                countToken = false
            } else if (curMode == MODE_STRING && stringOpen != token) {
                countToken = false
            }
            if (!countToken) {
                if (forMode == MODE_IN_SQL) {
                    sqlBuilder.append(token)
                }
                forToken = token
                countToken = true
                continue
            }

            when (token.toUpperCase()) {
                "'", "\"" -> if (curMode == MODE_STRING && token == stringOpen && "\\" != forToken) {
                    curMode = forMode
                    forMode = MODE_OUT_SQL
                } else if (curMode != MODE_STRING) {
                    forMode = curMode
                    curMode = MODE_STRING
                    stringOpen = token
                }
                "/" -> {
                    if ("*" == forToken && curMode == MODE_M_COMMENT) {
                        curMode = forMode
                        forMode = MODE_OUT_SQL
                        countToken = false
                        break
                    }
                    if (token != forToken) {
                        break
                    }
                    if (curMode != MODE_S_COMMENT && curMode != MODE_M_COMMENT && curMode != MODE_STRING) {
                        forMode = curMode
                        curMode = MODE_S_COMMENT
                    }
                }
                "-" -> {
                    if (token != forToken) {
                        break
                    }
                    if (curMode != MODE_S_COMMENT && curMode != MODE_M_COMMENT && curMode != MODE_STRING) {
                        forMode = curMode
                        curMode = MODE_S_COMMENT
                    }
                }
                "#" -> if (curMode != MODE_S_COMMENT && curMode != MODE_M_COMMENT && curMode != MODE_STRING) {
                    forMode = curMode
                    curMode = MODE_S_COMMENT
                }
                "*" -> if ("/" == forToken && curMode != MODE_S_COMMENT && curMode != MODE_M_COMMENT && curMode != MODE_STRING) {
                    forMode = curMode
                    curMode = MODE_M_COMMENT
                }
                "\\" -> if ("\\" == forToken) {
                    countToken = false
                }
                "\r", "\n" -> if (curMode == MODE_S_COMMENT) {
                    curMode = forMode
                    forMode = MODE_OUT_SQL
                }
                " ", "\t" -> {
                }
                DELIMITER -> if (curMode == MODE_IN_SQL) {
                    if (sqlBuilder.isNotEmpty()) {
                        result.add(stmtType to sqlBuilder.toString())
                        sqlBuilder.setLength(0)
                    }
                    curMode = MODE_OUT_SQL
                    forMode = MODE_OUT_SQL
                    sqlType = SQL_TYPE_OTHERS
                    stmtType = STMT_OTHER_STATEMENT
                }
                "CREATE" -> if (curMode == MODE_OUT_SQL) {
                    curMode = MODE_IN_SQL
                    sqlType = SQL_TYPE_CREATE
                    stmtType = STMT_CREATE_TABLE_UNCERTAIN
                }
                "SELECT" -> {
                    if (curMode == MODE_OUT_SQL) {
                        curMode = MODE_IN_SQL
                        sqlType = SQL_TYPE_SELECT
                        stmtType = STMT_SELECT_FROM_TABLE
                    }
                    if (sqlType == SQL_TYPE_CREATE && stmtType != STMT_CREATE_PROCEDURE_FUNCTION) {
                        stmtType = STMT_CREATE_TABLE_AS_SELECT
                    } else if (sqlType == SQL_TYPE_INSERT && stmtType != STMT_INSERT_INTO_TABLE_VALUES) {
                        stmtType = STMT_INSERT_INTO_TABLE_SELECT
                    } else if (sqlType == SQL_TYPE_DELETE) {
                        stmtType = STMT_DELETE_FROM_JOIN_OR_SELECT
                    }
                }
                "JOIN" -> if (sqlType == SQL_TYPE_DELETE) {
                    stmtType = STMT_DELETE_FROM_JOIN_OR_SELECT
                }
                "INSERT" -> if (curMode == MODE_OUT_SQL) {
                    sqlType = SQL_TYPE_INSERT
                    curMode = MODE_IN_SQL
                    stmtType = STMT_INSERT_INTO_UNCERTAIN
                }
                "VALUES" -> if (sqlType == SQL_TYPE_DELETE) {
                    stmtType = STMT_INSERT_INTO_TABLE_VALUES
                }
                "DELETE" -> if (curMode == MODE_OUT_SQL) {
                    sqlType = SQL_TYPE_DELETE
                    curMode = MODE_IN_SQL
                    stmtType = STMT_DELETE_FROM_TABLE_UNCERTAIN
                }
                "PROCEDURE", "FUNCTION" -> {
                    if (sqlType == SQL_TYPE_CREATE) {
                        stmtType = STMT_CREATE_PROCEDURE_FUNCTION
                    }
                    break@OUT
                }
                else -> if (curMode == MODE_OUT_SQL) {
                    curMode = MODE_IN_SQL
                }
            }
            if (countToken) {
                forToken = token
            } else {
                forToken = null
                countToken = true
            }
            if (curMode == MODE_IN_SQL || forMode == MODE_IN_SQL) {
                sqlBuilder.append(token)
            }
        }
        if (stmtType == STMT_CREATE_PROCEDURE_FUNCTION) {
            result.add(stmtType to content)
        } else if (sqlBuilder.isNotEmpty()) {
            result.add(stmtType to sqlBuilder.toString())
        }
        return result
    }
}
