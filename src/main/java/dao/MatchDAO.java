package dao;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import model.entity.Match;
import util.PersistenceUtil;

import java.util.List;

public class MatchDAO {
    private final static MatchDAO INSTANCE = new MatchDAO();
    public static MatchDAO getInstance() {
        return INSTANCE;
    }

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

    public List<Match> findMatches(int page) {
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
            TypedQuery<Match> typedQuery = entityManager.createQuery(select);
            typedQuery.setFirstResult(page * pageSize - pageSize);
            typedQuery.setMaxResults(page * pageSize);
            matches = typedQuery.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        entityManager.close();
        return matches;
    }
}
