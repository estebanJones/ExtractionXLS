package fr.esteban.main.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import fr.esteban.main.entities.Departement;
import fr.esteban.main.entities.Region;
import fr.esteban.main.repository.RegionRepository;
import fr.esteban.main.utils.XlsUtils;

@Service
public class ExtractManager {
	private XlsUtils xlsUtils;
	private RegionRepository regionRepository;
	
	public ExtractManager(XlsUtils xlsUtils, RegionRepository regionRepository) {
		this.xlsUtils = xlsUtils;
		this.regionRepository = regionRepository;
	}
	
	
	
	
	public void extract() throws IOException {
		 Set<Departement> departements = new HashSet<>();
		 HSSFWorkbook file = this.xlsUtils.getXlsFile();
		 this.recupererRegionsCommune(file, departements);
	}
	
	public void recupererRegionsCommune(HSSFWorkbook file, Set<Departement> departements) throws IOException {
		List<Region> regions = new ArrayList<Region>();
	   HSSFSheet feuilleCommune = this.recupererOngletCommune(file);
	   int starRow = 8;
	   int endRow = feuilleCommune.getLastRowNum();
		 
       for(int indexLigne = starRow; indexLigne < endRow; indexLigne++) { 
    	   
    	   	Cell nomRegion = this.getValueCell(file, indexLigne, 1);
	   	    Cell codeRegion = this.getValueCell(file, indexLigne, 0);
	   	    Region region = new Region(nomRegion.toString(), codeRegion.toString());
	   	 
	   	    Cell codeDepartement = this.getValueCell(file, indexLigne, 2);
	   	    Departement departement = new Departement(codeDepartement.toString());
	   	    departements.add(departement);
	   	     
        	regions.add(region);
           
        }
      List<Region> trie = regions.stream().filter(distinctByKey(Region::getNom)).collect(Collectors.toList());
      trie.forEach(e -> this.regionRepository.save(e));
	}
	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
	    Set<Object> seen = ConcurrentHashMap.newKeySet();
	    return t -> seen.add(keyExtractor.apply(t));
	}
	
	public Set<Region> getRegion(HSSFWorkbook file, Integer indexLigne,  Set<Region> regions, Set<Departement> departements) {
		Cell nomRegion = this.getValueCell(file, indexLigne, 1);
	    Cell codeRegion = this.getValueCell(file, indexLigne, 0);
	    Region region = new Region(nomRegion.toString(), codeRegion.toString());
	    Cell codeDepartement = this.getValueCell(file, indexLigne, 2);
	    Departement departement = new Departement(codeDepartement.toString());
	    departements.add(departement);
	     
	    region.setDepartements(departements);
     	regions.add(region);
     	return regions;
	}
	public Cell getValueCell(HSSFWorkbook file, Integer indexLigne, Integer indexColonne) {
		return file.getSheetAt(4).getRow(indexLigne).getCell(indexColonne);
	}
	public HSSFSheet recupererOngletCommune(HSSFWorkbook file) throws IOException {
		return this.xlsUtils.getXlsSheet(file, 4);
	}
}
