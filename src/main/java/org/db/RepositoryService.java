package org.db;

import org.db.dao.TestObjectDaoImpl;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.model.TestObject;

import java.util.Properties;

public class RepositoryService {

    private final TestObjectDaoImpl testObjectDao;

    public TestObjectDaoImpl getTestObjectDao() {
        return testObjectDao;
    }

    public RepositoryService() {
        String jdbcUrl = System.getenv("DBUrl");
        String user = System.getenv("DBUser");
        String pass = System.getenv("DBPassword");
        String driver = "com.mysql.jdbc.Driver";
        SessionFactory sessionFactory = configureHibernate(jdbcUrl, user, pass, driver);
        EntityManagerUtil entityManagerUtil = new EntityManagerUtil(sessionFactory);
        testObjectDao = new TestObjectDaoImpl(entityManagerUtil);
    }

    private static SessionFactory configureHibernate(String jdbcUrl, String user, String pass, String driver) {
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.url", jdbcUrl);
        properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.connection.username", user);
        properties.setProperty("hibernate.connection.password", pass);
        properties.setProperty("hibernate.connection.driver_class", driver);
        properties.setProperty("hibernate.show_sql", "false");
        properties.setProperty("hibernate.format_sql", "false");

        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(TestObject.class);

        return configuration.addProperties(properties).buildSessionFactory();
    }

    public void removeAllTestDataFromDB() {
        testObjectDao.removeAll();
    }
}
