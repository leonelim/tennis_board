package util;

import exception.EntityManagerFactoryInitializationException;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Getter;

public class PersistenceUtil {
    @Getter
    private static final EntityManagerFactory entityManagerFactory = buildEntityManagerFactory();
    private static EntityManagerFactory buildEntityManagerFactory() {
        try {
            return Persistence.createEntityManagerFactory("tajik");
        } catch (Exception e) {
            e.printStackTrace();
            throw new EntityManagerFactoryInitializationException("Failed to initialize entity manager factory");
        }
    }
    public static void shutdown() {
        entityManagerFactory.close();
    }
}
