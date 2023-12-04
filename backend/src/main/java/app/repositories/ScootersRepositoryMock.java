package app.repositories;

import app.models.Scooter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScootersRepositoryMock implements EntityRepository<Scooter> {

    private final List<Scooter> scooterList;

    public ScootersRepositoryMock() {
        this.scooterList = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            scooterList.add(Scooter.createSampleScooter(35000 + i + 1));
        }
    }

    @Override
    public List<Scooter> findAll() {
        return scooterList;
    }

    @Override
    public Scooter findById(long id) {
        for (Scooter scooter : scooterList) {
            if (scooter.getId() == id)
                return scooter;
        }
        return null;
    }

    @Override
    public Scooter save(Scooter scooter) {
        if (scooter.getId() == 0) {
            // if id is zero, generate new id based on the last one
            scooter.setId(scooterList.get(scooterList.size() - 1).getId() + 1);
        }

        // look for an existing scooter to replace/update
        for (int index = 0; index < scooterList.size(); index++) {
            Scooter scooterInList = scooterList.get(index);
            if (scooterInList.getId() == scooter.getId()) {
                // if existing scooter we replace
                scooterList.set(index, scooter);
                return scooter;
            }
        }

        // add scooter if not found in list
        scooterList.add(scooter);
        return scooter;
    }

    @Override
    public Scooter deleteById(long id) {
        Scooter scooter = findById(id);
        boolean removed = scooterList.remove(scooter);
        return removed ? scooter : null;
    }

    @Override
    public List<Scooter> findByQuery(String jpqlName, Object... params) {
        return null;
    }
}
