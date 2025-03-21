package util;

import exception.HibernateSessionFactoryInitializationException;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    @Getter
    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            throw new HibernateSessionFactoryInitializationException("Failed to initialize session factory");
        }
    }
    public static void shutdown() {
        sessionFactory.close();
    }
}
