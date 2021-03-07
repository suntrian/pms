package org.sunt.query.define

enum class SqlDialect(val driver: List<String>, val port: Int, vararg val protocols: String) {

    DEFALUT(emptyList(), 0, "default"),
    MYSQL(listOf("com.mysql.cj.jdbc.Driver"), 3306, "mysql"),
    MARIADB(listOf("org.mariadb.jdbc.Driver"), 3306, "mariadb"),
    ORACLE(listOf("oracle.jdbc.driver.OracleDriver"), 1521, "oracle:thin"),
    SQLSERVER(listOf("com.microsoft.sqlserver.jdbc.SQLServerDriver"), 1433, "sqlserver"),
    DB2(listOf("com.ibm.db2.jcc.DB2Driver"), 50000, "db2"),
    POSTGRESQL(listOf("org.postgresql.Driver"), 5432, "postgresql"),
    SYBASE(listOf("com.sybase.jdbc.SybDriver"), 5000, "sybase:Tds"),
    HSQL(listOf(""), 0, ""),
    H2(listOf("org.h2.Driver"), 0, "h2"),
    DERBY(listOf("org.apache.derby.jdbc.EmbeddedDriver"), 0, ""),
    SQLITE(listOf(""), 0, ""),
    HIVE(listOf("org.apache.hive.jdbc.HiveDriver"), 10000, "hive2"),
    TERADATA(listOf(""), 0, ""),
    IMPALA(listOf("com.cloudera.impala.jdbc41.Driver"), 21050, "impala"),
    ACCESS(listOf(""), 0, ""),
    CASSANDRA(listOf(""), 0, ""),
    PRESTO(listOf("com.facebook.presto.jdbc.PrestoDriver"), 9090, "presto"),
    GREENPLUM(listOf(""), 0, ""),
    CLICKHOUSH(listOf(""), 0, ""),
    REDSHIFT(listOf(""), 0, ""),
    SPARKSQL(listOf(""), 0, ""),
    TIDB(listOf(""), 0, ""),
    INFORMIX(listOf(""), 0, ""),
    VERTICA(listOf(""), 0, ""),
    FIREBIRD(listOf(""), 0, ""),

    ODBC(listOf("sun.jdbc.odbc.JdbcOdbcDriver"), 0, "odbc");

    companion object {

        @JvmStatic
        fun of(product: String): SqlDialect {
            return try {
                valueOf(product.toUpperCase());
            } catch (e: Exception) {
                values().first { it.protocols.any { it.equals(product.toLowerCase()) } }
            }
        }

        @JvmStatic
        fun validPort(port: Int): Boolean {
            return port in 1024..49151
        }

    }
}