package com.fma.ihm.editeurhtml;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.web.HTMLEditor;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Font;

public class OngletEditeurHTMLController {
	@FXML
	private Hyperlink HTMLLien01;
	@FXML
	private Hyperlink HTMLLien02;
	@FXML
	private HTMLEditor EditeurHTMLEditeur;
	
	// ----------------------------------------------------------------------------------------------------
	//
	//
	// Gestion des actions utilisateur
	//
	//
	//
	public void initialize() {
		System.out.println(getClass().getName() + " Initialisation...");
		
//		List lf = Font.getFamilies();
//		EditeurHTMLEditeur.get
		
		EditeurHTMLEditeur.setHtmlText("<html><head></head><body contenteditable=\"true\"><font face=\"DejaVu Sans Mono\"></font></body></html>");
	}
}
