package app.rest;

import app.models.Scooter;
import app.models.Trip;
import app.models.ViewClasses;
import app.repositories.EntityRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@JsonView(ViewClasses.Summary.class)
@RestController
@RequestMapping(value = "/trips")
public class TripsController {

    @Autowired
    private EntityRepository<Trip> tripsRepository;

    @GetMapping(path = "")
    public List<Trip> getAll() {
        return tripsRepository.findAll();
    }
}
