package fr.esteban.main.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Commune {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String codeCommune;
	private String nomCommune;
	private Integer populationMunicipale;
	private Integer populationCompteAPart;
	private Integer populationTotale;
	private String codeInsee;
	@ManyToOne
	@JoinColumn(name = "departement_id")
	private Departement departement;
	
	public Commune() {
		// TODO Auto-generated constructor stub
	}
	
	public Commune(String codeCommune, String nomCommune, Integer populationMunicipale, Integer populationCompteAPart, String codeInsee) {
		super();
		this.codeCommune = codeCommune;
		this.nomCommune = nomCommune;
		this.populationMunicipale = populationMunicipale;
		this.populationCompteAPart = populationCompteAPart;
		this.populationTotale = this.populationCompteAPart + this.populationMunicipale;
		this.codeInsee = codeInsee;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodeCommune() {
		return codeCommune;
	}

	public void setCodeCommune(String codeCommune) {
		this.codeCommune = codeCommune;
	}


	public String getNomCommune() {
		return nomCommune;
	}

	public void setNomCommune(String nomCommune) {
		this.nomCommune = nomCommune;
	}

	public Integer getPopulationMunicipale() {
		return populationMunicipale;
	}

	public void setPopulationMunicipale(Integer populationMunicipale) {
		this.populationMunicipale = populationMunicipale;
	}

	public Integer getPopulationCompteAPart() {
		return populationCompteAPart;
	}

	public void setPopulationCompteAPart(Integer populationCompteAPart) {
		this.populationCompteAPart = populationCompteAPart;
	}

	public Integer getPopulationTotale() {
		return this.populationCompteAPart + this.populationMunicipale;
	}

	public String getCodeInsee() {
		return codeInsee;
	}

	public void setCodeInsee(String codeInsee) {
		this.codeInsee = codeInsee;
	}

	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	
	
}
