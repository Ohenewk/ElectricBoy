package app.repositories;

import app.models.Scooter;
import app.models.Trip;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository
@Transactional
public class TripsRepositoryJpa implements EntityRepository<Trip> {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public List<Trip> findAll() {
        return em.createQuery("SELECT trip FROM Trip trip", Trip.class).getResultList();
    }

    @Transactional
    @Override
    public Trip findById(long id) {
        return em.find(Trip.class, id);
    }

    @Transactional
    @Override
    public Trip save(Trip trip) {
        if (trip.getId() == 0) {
            em.persist(trip);
        } else {
            trip = em.merge(trip);
        }
        return trip;
    }

    @Transactional
    @Override
    public Trip deleteById(long id) {
        Trip trip = findById(id);
        em.remove(trip);
        return trip;
    }

    @Override
    public List<Trip> findByQuery(String jpqlName, Object... params) {
        TypedQuery<Trip> query = em.createNamedQuery(jpqlName, Trip.class);

        // apply parameters to query
        for (int paramPosition = 0; paramPosition < params.length; paramPosition++) {
            query.setParameter(paramPosition + 1, params[paramPosition]);
        }

        return query.getResultList();
    }
}
