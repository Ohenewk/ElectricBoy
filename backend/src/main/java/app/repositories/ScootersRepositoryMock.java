package app.repositories;

import app.models.Scooter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScootersRepositoryMock implements ScootersRepository {

    private final List<Scooter> scooterList;

    public ScootersRepositoryMock() {
        this.scooterList = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            scooterList.add(Scooter.createSampleScooter(35000 + i +1));
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
        for (Scooter s : scooterList) {
            if (s.getId() == scooter.getId()) {
                scooterList.set(scooterList.indexOf(s), scooter);
                return scooter;
            }
        }

        scooterList.add(scooter);
        return scooter;
    }

    @Override
    public Scooter deleteById(long id) {
        Scooter scooter = findById(id);
        boolean removed = scooterList.remove(scooter);
        return removed ? scooter : null;
    }
}
