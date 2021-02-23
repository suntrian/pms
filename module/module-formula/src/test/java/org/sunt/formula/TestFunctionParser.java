package org.sunt.formula;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sunt.formula.define.SqlDialect;
import org.sunt.formula.function.FunctionDefinition;
import org.sunt.formula.function.FunctionDefinitionParser;

import java.util.List;

public class TestFunctionParser {

    private static final Logger logger = LoggerFactory.getLogger(TestFunctionParser.class);

    @Test
    public void testParseFunctionFile() {
        FunctionDefinitionParser defineParser = FunctionDefinitionParser.INSTANCE;
        List<FunctionDefinition> functions = defineParser.listFunctions(SqlDialect.IMPALA);
        for (FunctionDefinition function : functions) {
            logger.debug("{}", function);
        }
    }

    @Test
    public void testParseFunctionDialectFile() {
        FunctionDefinitionParser defineParser = FunctionDefinitionParser.INSTANCE;
        List<FunctionDefinition> impalaFunctions = defineParser.listFunctions(SqlDialect.IMPALA);
        logger.info("IMPALA");
        for (FunctionDefinition function : impalaFunctions) {
            logger.debug("{}", function);
        }

        logger.info("MYSQL");
        List<FunctionDefinition> mysqlFunctions = defineParser.listFunctions(SqlDialect.MYSQL);
        for (FunctionDefinition mysqlFunction : mysqlFunctions) {
            logger.debug("{}", mysqlFunction);
        }
    }
}
