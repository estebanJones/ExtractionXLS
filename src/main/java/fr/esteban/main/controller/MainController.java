package fr.esteban.main.controller;

import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.esteban.main.entities.Region;
import fr.esteban.main.manager.ExtractManager;

@RestController
public class MainController {
	private ExtractManager extractManager;
	
	public MainController(ExtractManager extractManager) {
		this.extractManager = extractManager;
	}
	
	@GetMapping("/extraction")
	public void extraction() {
		try {
			this.extractManager.extract();
		} catch (Exception e) {
			System.out.println("FIN");
		}
	}
}
