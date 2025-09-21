package dao;

import model.entity.Player;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.NoArgsConstructor;
import util.PersistenceUtil;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class PlayerDAO {
    private static final PlayerDAO INSTANCE = new PlayerDAO();
    public static PlayerDAO getInstance() {
        return INSTANCE;
    }

    public void savePlayerIfUnique(Player player) {
        EntityManager entityManager = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(player);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        entityManager.close();
    }

    public Optional<Player> findPlayer(int id) {
        EntityManager entityManager = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Player player;
        try {
            transaction.begin();
            player = entityManager.find(Player.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return Optional.empty();
        }
        entityManager.close();
        return Optional.of(player);
    }

    public Optional<Player> findPlayer(String name) {
        EntityManager entityManager = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Player player = null;
        try {
            transaction.begin();
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Player> criteriaQuery = builder.createQuery(Player.class);
            Root<Player> root = criteriaQuery.from(Player.class);
            criteriaQuery.select(root).where(builder.equal(root.get("name"), name));
            TypedQuery<Player> typedQuery = entityManager.createQuery(criteriaQuery);
            player = typedQuery.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return Optional.empty();
        }
        entityManager.close();
        return Optional.ofNullable(player);
    }
}
