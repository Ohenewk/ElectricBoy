package app.rest;

import app.exceptions.PreConditionFailed;
import app.exceptions.ResourceNotFound;
import app.models.Scooter;
import app.models.ViewClasses;
import app.repositories.ScootersRepository;
import app.utils.RandomDataHelper;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@JsonView(ViewClasses.Summary.class)
@RestController
@RequestMapping(value = "/scooters")
public class ScooterController {

    @Autowired
    private ScootersRepository scootersRepository;

    @GetMapping(path="")
    public List<Scooter> getTestScooters() {
        return scootersRepository.findAll();
    }


    @JsonView(ViewClasses.Summary.class)
    @GetMapping(path = "/summary")
    public List<Scooter> getScootersSummary() {
        return scootersRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Scooter> getScooterById(@PathVariable("id") long id) throws ResourceNotFound {
        final Scooter scooter = scootersRepository.findById(id);
        if (scooter == null) {
            throw new ResourceNotFound(id);
        }

        return ResponseEntity.ok().body(scooter);
    }

    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<Scooter> addScooter(@RequestBody Scooter scooter) throws Exception {
        if (scootersRepository.findById(scooter.getId()) != null) {
            throw new Exception("add");
        } else if (scooter.getId() == 0) {
            scooter.setId(RandomDataHelper.getRandom().nextInt(36_000, 37_000));
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(scooter.getId())
                .toUri();
        return ResponseEntity.created(location).body(scootersRepository.save(scooter));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Scooter> updateScooter(@PathVariable("id") long id, @RequestBody Scooter scooter) throws ResourceNotFound {
        if (scootersRepository.findById(id) == null) {
            throw new ResourceNotFound(id);
        } else if (id != scooter.getId()) {
            throw new PreConditionFailed(id, scooter.getId());
        }

        return ResponseEntity.ok().body(scootersRepository.save(new Scooter(
                id,
                scooter.getTag(),
                scooter.getStatus(),
                scooter.getGpsLocation(),
                scooter.getMileage(),
                scooter.getBatteryCharge()
        )));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Scooter> deleteScooter(@PathVariable("id") long id) throws ResourceNotFound {
        final Scooter deleted = scootersRepository.deleteById(id);
        if (deleted == null) {
            throw new ResourceNotFound(id);
        }
        return ResponseEntity.ok().body(deleted);
    }
}
