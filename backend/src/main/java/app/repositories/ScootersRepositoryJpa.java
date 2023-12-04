package app.repositories;

import app.models.Scooter;
import jakarta.persistence.*;
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

    @Override
    public List<Scooter> findByQuery(String jpqlName, Object... params) {
        TypedQuery<Scooter> query = em.createNamedQuery(jpqlName, Scooter.class);

        // apply parameters to query
        for (int paramPosition = 0; paramPosition < params.length; paramPosition++) {
            query.setParameter(paramPosition + 1, params[paramPosition]);
        }

        return query.getResultList();
    }
}
