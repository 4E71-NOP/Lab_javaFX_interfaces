package com.fma.ihm.barredestatut;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BarreDeStatutController {

	@FXML
	private Label BarreDeStatutMessage;

	private static BarreDeStatutController instance = null;

	public static BarreDeStatutController getInstance () {
		return instance;
	}
	
	public void setBarreDeStatutMessage(String msg) {
		BarreDeStatutMessage.setText(msg);
	}

	public void initialize() {
		System.out.println(getClass().getName() + " Initialisation...");
		setBarreDeStatutMessage("Demarrage");
		instance = this;			//Permet de faire communiquer les controller sur le principe du singleton.
		
	}


}
