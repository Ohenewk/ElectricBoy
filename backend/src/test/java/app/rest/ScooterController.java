package app.rest;

import app.models.Scooter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class ScooterController {

    public List<Scooter> getTestScooters() {
        return List.of(
                new Scooter("Test-scooter-A"),
                new Scooter("Test-scooter-B"));
    }
}
