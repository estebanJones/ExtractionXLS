package fr.esteban.main.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;


public class MainController {
	public static void main(String[] args) {
		try {
			FileInputStream inputStream = new FileInputStream(new File("C:/Users/Jordan/Documents/eclipse-workspace/recensement-insee/src/main/resources/ensemble.xls"));
			 HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
			 HSSFSheet sheet = workbook.getSheetAt(4);
		        // Get iterator to all the rows in current sheet
		        Iterator<Row> rowIterator = sheet.iterator();
		 
		        while (rowIterator.hasNext()) {
		            Row row = rowIterator.next();
		            // Get iterator to all cells of current row
		            Iterator<Cell> cellIterator = row.cellIterator();
		 
		            while (cellIterator.hasNext()) {
		                Cell cell = cellIterator.next();
		 
		                // Change to getCellType() if using POI 4.x
		                CellType cellType = cell.getCellTypeEnum();
		 
		                switch (cellType) {
		                case _NONE:
		                    System.out.print("");
		                    System.out.print("\t");
		                    break;
		                case BOOLEAN:
		                    System.out.print(cell.getBooleanCellValue());
		                    System.out.print("\t");
		                    break;
		                case BLANK:
		                    System.out.print("");
		                    System.out.print("\t");
		                    break;
		                case FORMULA:
		                    // Formula
		                    System.out.print(cell.getCellFormula());
		                    System.out.print("\t");
		                     
		                    FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
		                    // Print out value evaluated by formula
		                    System.out.print(evaluator.evaluate(cell).getNumberValue());
		                    break;
		                case NUMERIC:
		                    System.out.print(cell.getNumericCellValue());
		                    System.out.print("\t");
		                    break;
		                case STRING:
		                    System.out.print(cell.getStringCellValue());
		                    System.out.print("\t");
		                    break;
		                case ERROR:
		                    System.out.print("!");
		                    System.out.print("\t");
		                    break;
		                }
		 
		            }
		            System.out.println("");
		        }
		 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
