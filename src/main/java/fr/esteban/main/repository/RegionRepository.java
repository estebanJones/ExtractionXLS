package fr.esteban.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.esteban.main.entities.Region;

public interface RegionRepository extends JpaRepository<Region, Integer> {

}