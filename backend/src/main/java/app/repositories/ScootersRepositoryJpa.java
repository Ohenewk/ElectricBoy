package app.repositories;

import app.models.Scooter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;


@Primary
@Repository
@Transactional
public class ScootersRepositoryJpa implements EntityRepository<Scooter> {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public List<Scooter> findAll() {
        return em.createQuery("SELECT scooter FROM Scooter scooter", Scooter.class).getResultList();
    }

    @Transactional
    @Override
    public Scooter findById(long id) {
        return em.find(Scooter.class, id);
    }

    @Transactional
    @Override
    public Scooter save(Scooter scooter) {
        if (scooter.getId() == 0) {
            em.persist(scooter);
        } else {
            scooter = em.merge(scooter);
        }
        return scooter;
    }

    @Transactional
    @Override
    public Scooter deleteById(long id) {
        Scooter scooter = findById(id);
        em.remove(scooter);
        return scooter;
    }
}
