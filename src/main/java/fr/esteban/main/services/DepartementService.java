package fr.esteban.main.services;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import fr.esteban.main.entities.Departement;
import fr.esteban.main.entities.Region;
import fr.esteban.main.indirection.XlsUtils;
import fr.esteban.main.repository.DepartementRepository;

@Service
public class DepartementService {
	private XlsUtils xlsUtils;
	private DepartementRepository departementRepository;
	
	public DepartementService(XlsUtils xlsUtils, DepartementRepository departementRepository) {
		this.xlsUtils = xlsUtils;
		this.departementRepository = departementRepository;
		
	}
	
	public List<Departement> getAllDepartement() {
		return this.departementRepository.findAll();
	}
	public Departement saveDepartement(Departement departement) {
		return this.departementRepository.save(departement);
	}
	
	public Departement creeDepartement(HSSFWorkbook file, Integer indexLigne, Region region) {
		Cell codeDepartement = this.xlsUtils.getValueCell(file, indexLigne, 2);
   	    Departement departement = new Departement(codeDepartement.toString());
   	    departement.setRegion(region);
   	    return departement;
	}
	
	
}
