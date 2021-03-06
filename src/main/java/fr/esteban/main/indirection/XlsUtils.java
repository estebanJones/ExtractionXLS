package fr.esteban.main.indirection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Component;

@Component
public class XlsUtils {
	private static final String PATH = "C:/Users/Jordan/Documents/eclipse-workspace/recensement-insee/src/main/resources/ensemble.xls";
	public XlsUtils() {
		// TODO Auto-generated constructor stub
	}
	
	public Cell getValueCell(HSSFWorkbook file, Integer indexLigne, Integer indexColonne) {
		return file.getSheetAt(4).getRow(indexLigne).getCell(indexColonne);
	}
	
	public HSSFWorkbook getXlsFile() throws IOException {
		FileInputStream inputStream = new FileInputStream(new File(PATH));
		return new HSSFWorkbook(inputStream);
	}
	
	public HSSFSheet getXlsSheet(HSSFWorkbook file, Integer indexSheet) throws IOException {
		return file.getSheetAt(indexSheet);
	}
}
