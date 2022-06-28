package org;

import org.db.RepositoryService;
import org.db.dao.TestObjectDaoImpl;
import org.model.TestObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class DBTest {

    private static final RepositoryService DB = new RepositoryService();
    private static final TestObjectDaoImpl TEST_OBJECT_DAO = DB.getTestObjectDao();

    @Test
    public void testObjectCanBeStored() {
        // when: store a test object in DB
        long objectId = TEST_OBJECT_DAO.save(TestObject.getTypicalTestObject()).getId();

        // then: the test object is saved in DB properly
        TestObject savedObject = TEST_OBJECT_DAO.findById(objectId);
        Assert.assertNotNull(savedObject);
        Assert.assertEquals(savedObject.getParam1(), TestObject.getTypicalTestObject().getParam1(),
                "param1 value aren't equal");
        Assert.assertEquals(savedObject.getParam2(), TestObject.getTypicalTestObject().getParam2(),
                "param2 value aren't equal");
    }

    @AfterClass
    public void clearTestData() {
        DB.removeAllTestDataFromDB();
    }
}
