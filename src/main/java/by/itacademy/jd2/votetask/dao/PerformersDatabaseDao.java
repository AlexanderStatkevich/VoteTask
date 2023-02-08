package by.itacademy.jd2.votetask.dao;

import by.itacademy.jd2.votetask.dao.api.IPerformersDao;
import by.itacademy.jd2.votetask.domain.Performer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class PerformersDatabaseDao implements IPerformersDao {
    private final EntityManagerFactory factory;

    public PerformersDatabaseDao(EntityManagerFactory factory) {
        this.factory = factory;
    }
    private static final String CHECK_VOTES_FOR_PERFORMER = "SELECT EXISTS (SELECT * FROM data.vote_performer WHERE id_performer = ?);";

    @Override
    public void create(Performer performer) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(performer);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Performer> readAll() {
        EntityManager entityManager = factory.createEntityManager();
        List<Performer> resultList = null;
        try {
            entityManager.getTransaction().begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Performer> query = criteriaBuilder.createQuery(Performer.class);
            Root<Performer> root = query.from(Performer.class);
            CriteriaQuery<Performer> select = query.select(root);
            resultList = entityManager.createQuery(select).getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
        return resultList;
    }

    @Override
    public boolean delete(Long id) {
        EntityManager entityManager = factory.createEntityManager();
        boolean isVoted = isVotedForPerformer(id);
        if (isVoted) {
            return false;
        }
        try {
            entityManager.getTransaction().begin();
            Performer performerToRemove = entityManager.find(Performer.class, id);
            entityManager.remove(performerToRemove);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
        return true;
    }

    @Override
    public void update(Performer performer) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(performer);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean exist(Long id) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Performer performer = entityManager.find(Performer.class, id);
            entityManager.getTransaction().commit();
            return performer != null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    private boolean isVotedForPerformer(Long id) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createNativeQuery(CHECK_VOTES_FOR_PERFORMER);
            query.setParameter(1, id);
            boolean isVoted =(boolean) query.getSingleResult();
            entityManager.getTransaction().commit();
            return isVoted;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }
}
