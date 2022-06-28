package org.db;

import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import java.util.function.Consumer;
import java.util.function.Function;

public class EntityManagerUtil {

    private final SessionFactory sessionFactory;

    public EntityManagerUtil(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void performTransaction(Consumer<EntityManager> entityManagerConsumer) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            entityManagerConsumer.accept(entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("A DAO operation error. Transaction is rolled back!", e);
        } finally {
            entityManager.close();
        }
    }

    public <T> T performTransactionAndGetResult(Function<EntityManager, T> entityManagerFunction) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            T result = entityManagerFunction.apply(entityManager);
            entityManager.getTransaction().commit();
            return result;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("A DAO operation error. Transaction is rolled back!", e);
        } finally {
            entityManager.close();
        }
    }

}
