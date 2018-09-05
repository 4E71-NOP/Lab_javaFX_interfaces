package com.fma.ihm.contenus;

import com.fma.ihm.barredestatut.BarreDeStatutController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;

/**
 * Classe dédiée à l'onglet Contenus<br>
 * <br>
 * 
 * @author Faust MARIA DE ARAVALO - Tous droits réservés. Projet protfolio sur
 *         JavaFX2<br>
 *         <br>
 */
public class OngletContenusController {
	// @formatter:off
	@FXML	private TitledPane WebViewTitledPane;
	@FXML	private Label ContenusToolbarLabel;
	
	@FXML	private TitledPane ContenusTitledPaneA1;
	@FXML	private TitledPane ContenusTitledPaneB1;
	@FXML	private TitledPane ContenusTitledPaneB2;
	@FXML	private TitledPane ContenusTitledPaneB3;
	// @formatter:on

	// ----------------------------------------------------------------------------------------------------
	//
	//
	// Gestion des actions utilisateur
	//
	//
	//
	@FXML
	private void ContenusTitledPaneA1Action() {
		miseAJourAffichage("Click sur ContenusTitledPaneA1Action");
	}

	@FXML
	private void ContenusTitledPaneB1Action() {
		miseAJourAffichage("Click sur ContenusTitledPaneB1Action");
	}

	@FXML
	private void ContenusTitledPaneB2Action() {
		miseAJourAffichage("Click sur ContenusTitledPaneB2Action");
	}

	@FXML
	private void ContenusTitledPaneB3Action() {
		miseAJourAffichage("Click sur ContenusTitledPaneB3Action");
	}

	private void miseAJourAffichage(String str) {
		BarreDeStatutController.getInstance().setBarreDeStatutMessage(str);
		ContenusToolbarLabel.setText(str);
	}

	public void initialize() {
		System.out.println(getClass().getName() + " Initialisation...");
	}
}
