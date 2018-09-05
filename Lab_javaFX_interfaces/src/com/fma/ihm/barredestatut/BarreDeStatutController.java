package com.fma.ihm.barredestatut;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Classe dédiée à la Barre de status<br>
 * <br>
 * 
 * @author Faust MARIA DE ARAVALO - Tous droits réservés. Projet protfolio sur
 *         JavaFX2<br>
 *         <br>
 */
public class BarreDeStatutController {

	// @formatter:off
	@FXML	private Label BarreDeStatutMessage;
	// @formatter:on

	private static BarreDeStatutController instance = null;

	public static BarreDeStatutController getInstance() {
		return instance;
	}

	public void setBarreDeStatutMessage(String msg) {
		BarreDeStatutMessage.setText(msg);
	}

	public void initialize() {
		System.out.println(getClass().getName() + " Initialisation...");
		setBarreDeStatutMessage("Demarrage");
		instance = this; // Permet de faire communiquer les controller sur le principe du singleton.

	}

}
