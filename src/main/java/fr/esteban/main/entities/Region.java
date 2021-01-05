package fr.esteban.main.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Region {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nom;
	private String codeRegion;
	
	@OneToMany(mappedBy = "region")
	Set<Departement> departements = new HashSet<>();
	
	
	public Region() {
		// TODO Auto-generated constructor stub
	}
	
	public Region(String nom) {
		this.nom = nom;
	}
	
	public Region(String nom, String codeRegion) {
		this.nom = nom;
		this.codeRegion = codeRegion;
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCodeRegion() {
		return codeRegion;
	}

	public void setCodeRegion(String codeRegion) {
		this.codeRegion = codeRegion;
	}

	public Set<Departement> getDepartements() {
		return departements;
	}

	public void setDepartements(Set<Departement> departements) {
		this.departements = departements;
	}

	@Override
	public String toString() {
		return "Region [id=" + id + ", nom=" + nom + ", codeRegion=" + codeRegion + ", departements=" + departements
				+ "]";
	}

	
}
