package fr.esteban.main.manager;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;

import fr.esteban.main.entities.Region;
import fr.esteban.main.utils.XlsUtils;

@Service
public class ExtractManager {
	private XlsUtils xlsUtils;
	
	public ExtractManager(XlsUtils xlsUtils) {
		this.xlsUtils = xlsUtils;
	}
	
	
	
	
	public void extract() throws IOException {
		 Set<Region> regions = new HashSet<>();
		 HSSFWorkbook file = this.xlsUtils.getXlsFile();
		 
		
		
	}
	
	public Set<Region> recupererRegionsCommune(HSSFWorkbook file, Set<Region> regions) throws IOException {
	   HSSFSheet feuilleCommune = this.recupererOngletCommune(file);
	   int starRow = feuilleCommune.getFirstRowNum();
	   int endRow = feuilleCommune.getLastRowNum();
		 
       for(int i = starRow; i < endRow; i++ ) { 
    	   
            Cell nomRegion = this.getValueCell(file, i, 1);
            Cell codeRegion = this.getValueCell(file, i, 0);
            Region region = new Region(nomRegion.toString(), codeRegion.toString());
        	regions.add(region);
      
        }
       return regions;
	}
	
	public Cell getValueCell(HSSFWorkbook file, Integer indexLigne, Integer indexColonne) {
		return file.getSheetAt(4).getRow(indexLigne).getCell(indexColonne);
	}
	public HSSFSheet recupererOngletCommune(HSSFWorkbook file) throws IOException {
		return this.xlsUtils.getXlsSheet(file, 4);
	}
}
