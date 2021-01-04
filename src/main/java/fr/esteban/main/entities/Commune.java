package fr.esteban.main.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Commune {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String codeCommune;
	private Integer populationMunicipale;
	private Integer populationCompteAPart;
	private Integer populationTotale;
	private String codeInsee;
	
	private Departement departement;
}
