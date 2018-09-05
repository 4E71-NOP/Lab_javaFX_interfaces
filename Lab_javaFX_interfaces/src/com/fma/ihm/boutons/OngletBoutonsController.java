package com.fma.ihm.boutons;

import com.fma.ihm.barredestatut.BarreDeStatutController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Classe dédiée à l'onglet Boutons<br>
 * <br>
 * 
 * @author Faust MARIA DE ARAVALO - Tous droits réservés. Projet protfolio sur
 *         JavaFX2<br>
 *         <br>
 */
public class OngletBoutonsController {
	// @formatter:off
	@FXML	private Button BoutonsBtn;
	@FXML	private Label BoutonsLabel;
	// @formatter:on

	private boolean etat = true;
	// ----------------------------------------------------------------------------------------------------
	//
	//
	// Gestion des actions utilisateur
	//
	//
	//
	public void btnAction() {
		String text = (etat) ? "Texte pour valeur 'vrai'" : "Texte pour valeur 'faux'";
		this.etat = (etat) ? false : true;
		BoutonsLabel.setText(text);
		BarreDeStatutController.getInstance().setBarreDeStatutMessage(text);
	}

	public void initialize() {
		System.out.println(getClass().getName() + " Initialisation...");
		BoutonsBtn.setText("Bouton de test");
	}
}
