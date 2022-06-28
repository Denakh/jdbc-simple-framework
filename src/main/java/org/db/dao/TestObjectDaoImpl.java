package org.db.dao;

import org.db.EntityManagerUtil;
import org.model.TestObject;

public class TestObjectDaoImpl extends EntityDaoImpl<TestObject> {

    public TestObjectDaoImpl(EntityManagerUtil entityManagerUtil) {
        super(entityManagerUtil, TestObject.class);
    }
}
