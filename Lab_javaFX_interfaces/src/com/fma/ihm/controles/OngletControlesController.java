package com.fma.ihm.controles;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

//public class OngletControlesController implements Initializable {
public class OngletControlesController {

	// @formatter:off
	@FXML	private Slider ControlesCurseur01;
	@FXML	private Label ControlesCurseurValeur01;
	@FXML	private ProgressBar ControleBarreProgression;
	@FXML	private Slider ControlesCurseur02;

	// @formatter:on

	// ----------------------------------------------------------------------------------------------------
	//
	//
	// Gestion des actions utilisateur
	//
	//
	//

	// --------------------------------------------------------------------------------

	public void ControlesCurseur02Action() {
		float progressBarValue = (float) (ControlesCurseur02.getValue() / 100);
		ControleBarreProgression.setProgress(progressBarValue);
		MajControlesCurseurValeur01(0f, progressBarValue);
		
		
		// voir aussi:
		// https://stackoverflow.com/questions/51343759/how-to-change-fill-color-of-slider-in-javafx
		StackPane trackPane = (StackPane) ControlesCurseur02.lookup(".track");
        String style = "-fx-background-color: linear-gradient(to right, #0000ff "+ControlesCurseur02.getValue()+"%, #ff000040 "+ControlesCurseur02.getValue()+"%);";
        trackPane.setStyle(style);
	}

	private void MajControlesCurseurValeur01(float ancienneVal, float nouvelleVal) {
		ControlesCurseurValeur01.setText("Valeur : " + nouvelleVal + "\rAncienne valeur : "
				+ String.format("%.2f", ancienneVal) + "\rNouvelle valeur : " + String.format("%.2f", nouvelleVal));
	}

	public void initialize() {
		System.out.println(getClass().getName() + " Initialisation...");

		ControlesCurseur01.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				float progressBarValue = new_val.floatValue() / 100;
				ControleBarreProgression.setProgress(progressBarValue);
				MajControlesCurseurValeur01(old_val.floatValue(), progressBarValue);
			}
		});
	}
}
