package org.sunt.query.formula;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sunt.query.define.SqlDialect;
import org.sunt.query.formula.function.FunctionDefinition;
import org.sunt.query.formula.function.FunctionDefinitionParser;

import java.util.List;
import java.util.Map;

public class TestFunctionParser {

    private static final Logger logger = LoggerFactory.getLogger(TestFunctionParser.class);

    @Test
    public void testParseFunctionFile() {
        List<FunctionDefinition> functions = FunctionDefinitionParser.listFunctions(SqlDialect.IMPALA);
        for (FunctionDefinition function : functions) {
            logger.debug("{}", function);
        }
    }

    @Test
    public void testParseFunctionDialectFile() {
        List<FunctionDefinition> impalaFunctions = FunctionDefinitionParser.listFunctions(SqlDialect.IMPALA);
        logger.info("IMPALA");
        for (FunctionDefinition function : impalaFunctions) {
            logger.debug("{}", function);
        }

        logger.info("MYSQL");
        List<FunctionDefinition> mysqlFunctions = FunctionDefinitionParser.listFunctions(SqlDialect.MYSQL);
        for (FunctionDefinition mysqlFunction : mysqlFunctions) {
            logger.debug("{}", mysqlFunction);
        }
    }

    @Test
    public void testParseFunctionDialectCategoryFile() {
        Map<String, List<FunctionDefinition>> funDefs = FunctionDefinitionParser.loadFunctions(SqlDialect.IMPALA, "分析函数");
        for (Map.Entry<String, List<FunctionDefinition>> stringListEntry : funDefs.entrySet()) {
            for (FunctionDefinition functionDefinition : stringListEntry.getValue()) {
                logger.debug("{}", functionDefinition);
            }
        }

    }
}
