package fr.esteban.main.services;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import fr.esteban.main.entities.Commune;
import fr.esteban.main.indirection.XlsUtils;
import fr.esteban.main.repository.CommuneRepository;

@Service
public class CommuneService {
	private XlsUtils xlsUtils;
	private CommuneRepository communeRepository;
	
	public CommuneService(XlsUtils xlsUtils, CommuneRepository communeRepository) {
		this.xlsUtils = xlsUtils;
		this.communeRepository = communeRepository;
	}
	
	public Commune saveCommune(Commune c) {
		return this.communeRepository.save(c);
	}
	
	public Commune creeCommune(HSSFWorkbook file, Integer indexLigne) {
		Cell codeCommune = this.xlsUtils.getValueCell(file, indexLigne, 5);
		Cell nomCommune = this.xlsUtils.getValueCell(file, indexLigne, 6);
		double populationMunicipale = this.xlsUtils.getValueCell(file, indexLigne, 7).getNumericCellValue();
		double populationCompteAPart = this.xlsUtils.getValueCell(file, indexLigne, 8).getNumericCellValue();
		String codeInsee = this.constructCodeINSEE(file, indexLigne, codeCommune.toString());
		
		Commune commune = new Commune(codeCommune.toString(), nomCommune.toString(),(int) populationMunicipale, 
				(int)populationCompteAPart, codeInsee);
   	    return commune;
	}
	
	private String constructCodeINSEE(HSSFWorkbook file, Integer indexLigne, String codeCommune) {
		StringBuilder builder = new StringBuilder();
		Cell codeDepartement = this.xlsUtils.getValueCell(file, indexLigne, 2);
		return builder.append(codeDepartement.toString()).append(codeCommune).toString();
	}

}
