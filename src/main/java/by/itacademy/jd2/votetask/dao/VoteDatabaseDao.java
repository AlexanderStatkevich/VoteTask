package by.itacademy.jd2.votetask.dao;

import by.itacademy.jd2.votetask.dao.api.IVoteDao;
import by.itacademy.jd2.votetask.domain.SavedVote;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class VoteDatabaseDao implements IVoteDao {
    private final EntityManagerFactory factory;

    public VoteDatabaseDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(SavedVote savedVote) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(savedVote);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<SavedVote> readAll() {
        EntityManager entityManager = factory.createEntityManager();
        List<SavedVote> resultList = null;
        try {
            entityManager.getTransaction().begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<SavedVote> query = criteriaBuilder.createQuery(SavedVote.class);
            Root<SavedVote> root = query.from(SavedVote.class);
            CriteriaQuery<SavedVote> select = query.select(root);
            EntityGraph<SavedVote> entityGraph = entityManager.createEntityGraph(SavedVote.class);
            entityGraph.addAttributeNodes("voiceForPerformer","voicesForGenres");
            resultList = entityManager
                    .createQuery(select)
                    .setHint("javax.persistence.fetchgraph",entityGraph)
                    .getResultList();
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
        try {
            entityManager.getTransaction().begin();
            SavedVote voteToRemove = entityManager.find(SavedVote.class, id);
            entityManager.remove(voteToRemove);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
        return true;
    }

    @Override
    public boolean isVotedGenre(Long id) {
        return false;
    }

    @Override
    public boolean isVotedPerformer(Long id) {
        return false;
    }
}
