package org.sunt.formula;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sunt.formula.define.SqlProduct;
import org.sunt.formula.function.FunctionDefine;
import org.sunt.formula.function.FunctionDefineParser;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TestFunctionParser {

    private static final Logger logger = LoggerFactory.getLogger(TestFunctionParser.class);

    @Test
    public void testParseFunctionFile() {
        FunctionDefineParser defineParser = FunctionDefineParser.INSTANCE;
        List<FunctionDefine> functions = defineParser.getFunctionMapByProduct(SqlProduct.IMPALA).values().stream().flatMap(Collection::stream).collect(Collectors.toList());
        for (FunctionDefine function : functions) {
            logger.debug("{}", function);
        }
    }

    @Test
    public void testParseFunctionDialectFile() {
        FunctionDefineParser defineParser = FunctionDefineParser.INSTANCE;
        List<FunctionDefine> impalaFunctions = defineParser.getFunctionMapByProduct(SqlProduct.IMPALA).values().stream().flatMap(Collection::stream).collect(Collectors.toList());
        logger.info("IMPALA");
        for (FunctionDefine function : impalaFunctions) {
            logger.debug("{}", function);
        }

        logger.info("MYSQL");
        List<FunctionDefine> mysqlFunctions = defineParser.getFunctionMapByProduct(SqlProduct.MYSQL).values().stream().flatMap(Collection::stream).collect(Collectors.toList());
        for (FunctionDefine mysqlFunction : mysqlFunctions) {
            logger.debug("{}", mysqlFunction);
        }
    }
}
