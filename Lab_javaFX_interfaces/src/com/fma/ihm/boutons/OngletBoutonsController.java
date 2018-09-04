package com.fma.ihm.boutons;

import com.fma.ihm.barredestatut.BarreDeStatutController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class OngletBoutonsController {
	private boolean etat = true;

	@FXML
	private Button BoutonsBtn;
	@FXML
	private Label BoutonsLabel;

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
