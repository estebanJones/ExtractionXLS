package fr.esteban.main.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.esteban.main.manager.ExtractManager;

@RestController
public class MainController {
	private ExtractManager extractManager;
	
	public MainController(ExtractManager extractManager) {
		this.extractManager = extractManager;
	}
	
	@GetMapping("extraction")
	public void extraction() {
		try {
			this.extractManager.extract();
		} catch (Exception e) {
			System.out.println("FIN");
		}
	}
}
