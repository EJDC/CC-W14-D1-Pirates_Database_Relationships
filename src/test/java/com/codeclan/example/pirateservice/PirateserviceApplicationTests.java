package com.codeclan.example.pirateservice;

import com.codeclan.example.pirateservice.models.Pirate;
import com.codeclan.example.pirateservice.models.Raid;
import com.codeclan.example.pirateservice.models.Ship;
import com.codeclan.example.pirateservice.repositories.PirateRepository;
import com.codeclan.example.pirateservice.repositories.RaidRepository;
import com.codeclan.example.pirateservice.repositories.ShipRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@ActiveProfiles("test")  //indicates test profile
class PirateserviceApplicationTests {

	@Autowired
	PirateRepository pirateRepository;


	@Autowired
	ShipRepository shipRepository;

	@Autowired
	RaidRepository raidRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void createPirateOnShip() {

		Ship ship = new Ship("The Flying Dutchman");
		shipRepository.save(ship);

		Pirate jack = new Pirate("Andy", "Sparrow", 32, ship);
		pirateRepository.save(jack);
	}

	@Test
	public void addPiratesAndRaids(){
		Ship ship = new Ship("The Flying Dutchman");
		shipRepository.save(ship);

		Pirate pirate1 = new Pirate("Jack", "Sparrow", 32, ship);
		pirateRepository.save(pirate1);

		Raid raid1 = new Raid("Tortuga", 100);
		raidRepository.save(raid1);

		raid1.addPirate(pirate1);
		raidRepository.save(raid1);

	}

	@Test
	public void canFindPiratesOver30(){
		List<Pirate> found = pirateRepository.findPiratesByAgeGreaterThan(30);
		assertTrue(found.size()>0);
	}

	@Test
	public void canFindPirateByFirstName() {
		List<Pirate> found = pirateRepository.findPiratesByFirstName("Jack");
		assertTrue(found.size()>0);
		assertEquals(found.get(0).getFirstName(), "Jack");
	}

	@Test
	public void canFindPirateByLastName(){
		Pirate found = pirateRepository.findPirateByLastName("Silver");
		assertEquals(found.getLastName(), "Silver");
	}

	@Test
	public void canFindRaidsByLocation() {
		List<Raid> foundRaids = raidRepository.findRaidsByLocation("Tortuga");
		assertTrue(foundRaids.size()>0);
		assertEquals(foundRaids.get(0).getLocation(), "Tortuga");
	}

	@Test
	public void canFindPiratesByRaidId() {
		List<Pirate> foundPirates = pirateRepository.findByRaidsId(1L);
		assertEquals(1, foundPirates.size());
		assertEquals("Jack", foundPirates.get(0).getFirstName());
	}

	@Test
	public void canFindShipsByPirateFirstName() {
		List<Ship> foundShips = shipRepository.findShipsByPiratesFirstName("Jack");
		assertEquals("The Black Pearl", foundShips.get(0).getName());
	}

	@Test
	public void canFindShipsByPirateFirstAndLastName() {
		List<Ship> foundShips = shipRepository.findShipsByPiratesFirstNameAndPiratesLastName("Jack", "Sparrow");
		assertEquals("The Black Pearl", foundShips.get(0).getName());
	}

	@Test
	public void canFindRaidsByShipName() {
		List<Raid> foundRaids = raidRepository.findRaidsByPiratesShipName("The Black Pearl");
		assertEquals(26, foundRaids.size());
	}
}
