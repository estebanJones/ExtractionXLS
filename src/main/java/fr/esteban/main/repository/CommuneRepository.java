package fr.esteban.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.esteban.main.entities.Commune;

public interface CommuneRepository extends JpaRepository<Commune, Integer> {

}
