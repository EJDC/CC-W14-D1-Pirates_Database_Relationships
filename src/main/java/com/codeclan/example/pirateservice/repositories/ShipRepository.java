package com.codeclan.example.pirateservice.repositories;


import com.codeclan.example.pirateservice.models.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {

    List<Ship> findShipsByPiratesFirstName (String first_name);
    List<Ship> findShipsByPiratesFirstNameAndPiratesLastName (String first_name, String last_name);
}
