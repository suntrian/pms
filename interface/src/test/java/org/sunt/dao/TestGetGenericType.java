package org.sunt.dao;

import org.junit.jupiter.api.Test;
import org.sunt.common.dao.AbstractDao;

public class TestGetGenericType {

    @Test
    public void testGetGenericTypeWithinParent() {
        TestDao dao = new TestDao();
    }

    private class TestDao extends AbstractDao<TestGetGenericType, Integer> {

    }

}
