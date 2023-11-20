package app.rest;

import app.models.Scooter;
import app.repositories.ScootersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "scooters")
public class ScooterController {

    @Autowired
    private ScootersRepository scootersRepository;

    @GetMapping(path="/test")
    public List<Scooter> getTestScooters() {
        return scootersRepository.findAll();
    }
}
