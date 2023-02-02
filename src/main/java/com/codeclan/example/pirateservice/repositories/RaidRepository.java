package com.codeclan.example.pirateservice.repositories;

import com.codeclan.example.pirateservice.models.Raid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RaidRepository extends JpaRepository<Raid, Long> {

    List<Raid> findRaidsByLocation (String location);

    List<Raid> findRaidsByPiratesShipName (String name);
}

