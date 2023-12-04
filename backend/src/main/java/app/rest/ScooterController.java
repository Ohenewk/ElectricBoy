package app.rest;

import app.exceptions.PreConditionFailed;
import app.exceptions.ResourceNotFound;
import app.models.Scooter;
import app.models.Trip;
import app.models.ViewClasses;
import app.repositories.EntityRepository;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Enumerated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@JsonView(ViewClasses.Summary.class)
@RestController
@RequestMapping(value = "/scooters")
public class ScooterController {

    @Autowired
    private EntityRepository<Scooter> scootersRepository;

    @Autowired
    private EntityRepository<Trip> tripsRepository;

    private Scooter.Status stringToStatus(String value) {
        return Scooter.Status.valueOf(value.toUpperCase());
    }


    @GetMapping(path = "")
    public ResponseEntity<?> getAll(@RequestParam Optional<Integer> battery,
                                    @RequestParam Optional<String> status) {
        if (battery.isPresent() && status.isPresent()) {
            return (ResponseEntity<?>) ResponseEntity.badRequest();
        }

        List<Scooter> queriedScooters;
        if (battery.isPresent()) {
            queriedScooters = scootersRepository.findByQuery("Scooter.find_by_battery", battery.get());
        } else if (status.isPresent()) {
            queriedScooters = scootersRepository.findByQuery("Scooter.find_by_status", stringToStatus(status.get()));
        } else {
            queriedScooters = scootersRepository.findAll();
        }

        return ResponseEntity.ok(queriedScooters);
    }

    @JsonView(ViewClasses.Summary.class)
    @GetMapping(path = "/summary")
    public List<Scooter> getScootersSummary() {
        return scootersRepository.findAll();
    }

    // scooters
    @GetMapping(path = "/{id}")
    public ResponseEntity<Scooter> getScooterById(@PathVariable("id") long id) throws ResourceNotFound {
        final Scooter scooter = scootersRepository.findById(id);
        if (scooter == null) {
            throw new ResourceNotFound(id);
        }

        return ResponseEntity.ok().body(scooter);
    }

    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<Scooter> addScooter(@RequestBody Scooter scooter) throws PreConditionFailed {
        if (scootersRepository.findById(scooter.getId()) != null) {
            throw new PreConditionFailed(String.format("Scooter with id %d already exists.", scooter.getId()));
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

    // trips
    @GetMapping(path = "/{id}/trips")
    public List<Trip> getScooterTripsById(@PathVariable("id") long id,
                                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> from,
                                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> to
    ) throws ResourceNotFound {
        final Scooter scooter = scootersRepository.findById(id);
        if (scooter == null) {
            throw new ResourceNotFound(id);
        }


        if (from.isPresent() && to.isPresent()) {
            return tripsRepository.findByQuery("Trip.find_by_scooterId_between_periods", id, from.get(), to.get());
        }
        return scooter.getTrips();
    }

    @PostMapping(path = "/{id}/trips", produces = "application/json")
    public ResponseEntity<Scooter> addTripToScooter(@PathVariable("id") long id, @RequestBody Trip trip) throws PreConditionFailed {
        Scooter scooter = scootersRepository.findById(id);
        if (scooter == null) {
            throw new PreConditionFailed(String.format("Scooter with id %d doesn't exist!", id));
        } else if (scooter.getStatus() != Scooter.Status.IDLE) {
            throw new PreConditionFailed(String.format("Scooter with id %d is not IDLE!", id));
        } else if (scooter.getBatteryCharge() < 10) {
            throw new PreConditionFailed(String.format("Scooter with id %d doesn't have enough battery charge (%d < 10).", id, scooter.getBatteryCharge()));
        }

        trip.setStartLocation(scooter.getGpsLocation());
        scooter.setStatus(Scooter.Status.INUSE);
        boolean success = scooter.associateTrip(trip);
        if (success) {
            tripsRepository.save(trip);
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}/trips")
                .buildAndExpand(scooter.getId())
                .toUri();
        return ResponseEntity.created(location).body(scootersRepository.save(scooter));
    }

    @GetMapping(path = "/{scooterId}/trips/{period}")
    public List<Trip> getScooterByIdAndPeriod(@PathVariable("scooterId") long scooterId,
                                              @PathVariable("period") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                              LocalDateTime period) {
        return tripsRepository.findByQuery("Trip.find_by_scooterId_and_period", scooterId, period);
    }
}
