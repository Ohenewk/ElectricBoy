package app;

import app.models.Scooter;
import app.models.Trip;
import app.repositories.EntityRepository;
import app.utils.RandomDataHelper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ElectricBoyApplication implements CommandLineRunner {

	@Autowired
	private EntityRepository<Scooter> scooterRepository;

	@Autowired
	private EntityRepository<Trip> tripsRepository;

	public static void main(String[] args) {
		SpringApplication.run(ElectricBoyApplication.class, args);
	}

	@Transactional
	private List<Scooter> createInitialScooters() {
		List<Scooter> scooters = scooterRepository.findAll();
		if (scooters.size() > 0) return null;
		System.out.println("Couldn't find scooters. Creating initial scooter data...");

		for (int i = 0; i < 11; i++) {
			Scooter savedScooter = scooterRepository.save(Scooter.createSampleScooter(0));

			scooters.add(savedScooter);
			System.out.printf("Saved scooter: %s\n", savedScooter);
		}

		return scooters;
	}

	@Transactional
	private void createInitialTripsForScooters(List<Scooter> scooters) {
		List<Trip> trips = tripsRepository.findAll();
		if (scooters == null) return;
		System.out.println("Creating initial trip data...");

		scooters.forEach(scooter -> {
			// add random amount of trips
			for (int index = 0; index < RandomDataHelper.getRandom().nextInt(scooters.size()); index++) {
				Trip trip = Trip.createSampleTripForScooter(scooter);

				boolean success = scooter.associateTrip(trip);
				if (success) {
					Trip savedTrip = tripsRepository.save(trip);
					System.out.printf("Saved trip: %s for scooter: %s\n", savedTrip, scooter);
				} else {
					System.err.printf("Failed to save trip: %s for scooters: %s\n", trip, scooter);
				}
			}
		});
	}

	@Transactional
	@Override
	public void run(String... args) throws Exception {
		List<Scooter> scooters = createInitialScooters();
		createInitialTripsForScooters(scooters);
	}
}
