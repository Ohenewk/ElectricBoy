package app.repositories;

import app.models.Scooter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScootersRepositoryMock implements ScootersRepository {

    private List<Scooter> scooterList;

    public ScootersRepositoryMock() {
        this.scooterList = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            scooterList.add(Scooter.createSampleScooter(35000 + i));
        }
    }

    @Override
    public List<Scooter> findAll() {
        return scooterList;
    }
}
