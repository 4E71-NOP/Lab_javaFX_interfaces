package com.fma.ihm.editeurhtml;

import javafx.fxml.FXML;
import javafx.scene.web.HTMLEditor;
import javafx.scene.control.Hyperlink;

/**
 * Classe dédiée à l'onglet EditeurHTML<br>
 * <br>
 * 
 * @author Faust MARIA DE ARAVALO - Tous droits réservés. Projet protfolio sur
 *         JavaFX2<br>
 *         <br>
 */
public class OngletEditeurHTMLController {
	// @formatter:off
	@FXML	private Hyperlink HTMLLien01;
	@FXML	private Hyperlink HTMLLien02;
	@FXML	private HTMLEditor EditeurHTMLEditeur;
	// @formatter:on

	// ----------------------------------------------------------------------------------------------------
	//
	//
	// Gestion des actions utilisateur
	//
	//
	//
	public void initialize() {
		System.out.println(getClass().getName() + " Initialisation...");

		// List lf = Font.getFamilies();
		// EditeurHTMLEditeur.get

		EditeurHTMLEditeur.setHtmlText(
				"<html><head></head><body contenteditable=\"true\"><font face=\"DejaVu Sans Mono\"></font></body></html>");
	}
}
