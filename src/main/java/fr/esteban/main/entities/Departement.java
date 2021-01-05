package fr.esteban.main.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Departement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String codeDepartement;
	@ManyToOne
	@JoinColumn(name = "region_id")
	private Region region;
	
	@OneToMany(mappedBy = "departement")
	private Set<Commune> communes = new HashSet<>();
	
	public Departement() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Departement(String codeDepartement) {
		this.codeDepartement = codeDepartement;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodeDepartement() {
		return codeDepartement;
	}

	public void setCodeDepartement(String codeDepartement) {
		this.codeDepartement = codeDepartement;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}
}
