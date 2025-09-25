package dao;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import model.entity.Match;
import org.apache.logging.log4j.Logger;
import util.PersistenceUtil;

import java.util.List;

public class MatchDAO {
    private final static MatchDAO INSTANCE = new MatchDAO();
    public static MatchDAO getInstance() {
        return INSTANCE;
    }

    Logger logger = util.LoggingUtil.getLogger();
    public void saveMatch(Match match) {
        EntityManager entityManager = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(match);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        entityManager.close();
    }

    public List<Match> findMatches(int page, String name) {
        EntityManager entityManager = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        List<Match> matches = null;
        int pageSize = 2;
        try {
            transaction.begin();
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Match> criteriaQuery = builder.createQuery(Match.class);
            Root<Match> root = criteriaQuery.from(Match.class);
            CriteriaQuery<Match> select = criteriaQuery.select(root);
            if (name != null) {
                Predicate predicate = builder.or(builder.equal(root.get("player1").get("name"), name)
                        , builder.equal(root.get("player2").get("name"), name));
                criteriaQuery.where(predicate);
            }
            TypedQuery<Match> typedQuery = entityManager.createQuery(select);
            typedQuery.setFirstResult(page * pageSize - pageSize);
            typedQuery.setMaxResults(page * pageSize);
            matches = typedQuery.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            entityManager.close();
        }
        logger.debug(matches);
        logger.debug(matches.isEmpty());
        return matches;
    }
    public Long countEntries(String name) {
        EntityManager entityManager = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Long amount = null;
        try {
            transaction.begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            Root<Match> root = criteriaQuery.from(Match.class);
            if (name != null) {
                Predicate predicate = criteriaBuilder.or(criteriaBuilder.equal(root.get("player1").get("name"), name)
                        , criteriaBuilder.equal(root.get("player2").get("name"), name));
                criteriaQuery.where(predicate);
            }
            criteriaQuery.select(criteriaBuilder.count(root));
            TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
            amount = typedQuery.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            entityManager.close();
        }
        return amount;
    }
}
