package com.codeclan.example.pirateservice.repositories;

import com.codeclan.example.pirateservice.models.Pirate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PirateRepository extends JpaRepository<Pirate, Long> {

    //find all pirates over age 30

    //operation<object>By<property><operator>

    List<Pirate> findPiratesByAgeGreaterThan(int age);

    List<Pirate> findPiratesByFirstName(String name);

    Pirate findPirateByLastName(String last_name);

    List<Pirate> findByRaidsId(Long raid_id);
}
