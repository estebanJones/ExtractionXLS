package fr.esteban.main.services;

import java.util.Optional;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import fr.esteban.main.entities.Region;
import fr.esteban.main.indirection.XlsUtils;
import fr.esteban.main.repository.RegionRepository;

@Service
public class RegionService {
	private XlsUtils xlsUtils;
	private RegionRepository regionRepository;
	
	public RegionService(XlsUtils xlsUtils, RegionRepository regionRepository) {
		this.xlsUtils = xlsUtils;
		this.regionRepository = regionRepository;
	}
	
	public Region saveRegion(Region region) {
		return this.regionRepository.save(region);
	}
	
	public Region createRegionFromInsee(HSSFWorkbook file, Integer indexLigne) {
		Cell nomRegion = this.xlsUtils.getValueCell(file, indexLigne, 1);
   	    Cell codeRegion = this.xlsUtils.getValueCell(file, indexLigne, 0);
   	    return new Region(nomRegion.toString(), codeRegion.toString());
	}
	
	public Optional<Region> findRegionByNom(String nom) {
		return this.regionRepository.findByNom(nom);
	}
}
