package app;

import app.models.Scooter;
import app.repositories.EntityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ElectricBoyApplication implements CommandLineRunner {

	@Autowired
	private EntityRepository<Scooter> scooterRepository;

	public static void main(String[] args) {
		SpringApplication.run(ElectricBoyApplication.class, args);
	}

	private void createInitialScooters() {
		List<Scooter> scooters = scooterRepository.findAll();
		if (scooters.size() > 0) return;
		System.out.println("Couldn't find scooters. Creating initial scooter data...");

		for (int i=0; i<11; i++) {
			Scooter scooter = Scooter.createSampleScooter(0);
			Scooter savedScooter = scooterRepository.save(scooter);
			System.out.printf("Saved scooter: %s\n", scooter);
		}
	}

	@Transactional
	@Override
	public void run(String... args) throws Exception {
		createInitialScooters();
	}
}
