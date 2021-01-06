package fr.esteban.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.esteban.main.entities.Departement;

public interface DepartementRepository extends JpaRepository<Departement, Integer> {

}
