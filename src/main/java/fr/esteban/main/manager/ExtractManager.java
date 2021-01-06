package fr.esteban.main.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import fr.esteban.main.entities.Commune;
import fr.esteban.main.entities.Departement;
import fr.esteban.main.entities.Region;
import fr.esteban.main.indirection.XlsUtils;
import fr.esteban.main.services.CommuneService;
import fr.esteban.main.services.DepartementService;
import fr.esteban.main.services.RegionService;
import fr.esteban.main.utils.PredicateFilter;

@Service
public class ExtractManager {
	private XlsUtils xlsUtils;
	private RegionService regionService;
	private DepartementService departementService;
	private CommuneService communeService;
	
	public ExtractManager(XlsUtils xlsUtils, RegionService regionService, DepartementService departementService, CommuneService communeService) {
		this.xlsUtils = xlsUtils;
		this.regionService = regionService;
		this.departementService = departementService;
		this.communeService = communeService;
	}
	
	public void extract() throws IOException {
		 HSSFWorkbook file = this.xlsUtils.getXlsFile();
		 this.recupererRegionsCommune(file);
	}
	
	public void recupererRegionsCommune(HSSFWorkbook file) throws IOException {
	   List<Region> regions = new ArrayList<>();
	   List<Departement> departements = new ArrayList<>();
	   List<Commune> communes = new ArrayList<>();
	   
	   HSSFSheet feuilleCommune = this.recupererOngletCommune(file);
	   int startRow = 8;
	   int endRow = feuilleCommune.getLastRowNum();
		 
       for(int indexLigne = startRow; indexLigne < endRow; indexLigne++) { 
    	    this.parseFichierAndGetDatas(file, regions, departements, communes, indexLigne);
       }
       
      List<Departement> trieDepartement = departements.stream().filter(PredicateFilter.distinctByKey(Departement::getCodeDepartement)).collect(Collectors.toList());

      for(Departement d : trieDepartement) {
    	  d = this.jointureDepartementRegion(d);
    	  this.departementService.saveDepartement(d);
      }
      
      for(Commune c : communes) {
    	 this.jointureDepartementCommune(c);
      }
	}
	
	public Departement jointureDepartementRegion(Departement d) {
		Optional<Region> regionDb = this.regionService.findRegionByNom(d.getRegion().getNom());
	   	 if(regionDb.isPresent()) {
	   		 d.setRegion(regionDb.get());
	   	 } else {
	   		 Region region = this.regionService.saveRegion(d.getRegion());
	   		 d.setRegion(region);
	   	 }
	   	 return d;
	}
	
	public void jointureDepartementCommune(Commune c) {
		List<Departement> departements = this.departementService.getAllDepartement();
		for(Departement d : departements) {
			String codeInseeTemp = d.getCodeDepartement() + c.getCodeCommune();
			if(codeInseeTemp.equalsIgnoreCase(c.getCodeInsee())) {
				c.setDepartement(d);
				this.communeService.saveCommune(c);
			}
		}
	}
	
	public void parseFichierAndGetDatas(HSSFWorkbook file, List<Region> regions, List<Departement> departements, List<Commune> communes, int indexLigne) {
    	    Region region = this.regionService.createRegionFromInsee(file, indexLigne);
	   	    Departement departement = this.departementService.creeDepartement(file, indexLigne, region);
	   	    Commune commune = this.communeService.creeCommune(file, indexLigne);
	   	    departement.setRegion(region);
	   	    
	   	    regions.add(region);
	   	    departements.add(departement);
	   	    communes.add(commune);     
	}
	
	public HSSFSheet recupererOngletCommune(HSSFWorkbook file) throws IOException {
		return this.xlsUtils.getXlsSheet(file, 4);
	}
}
