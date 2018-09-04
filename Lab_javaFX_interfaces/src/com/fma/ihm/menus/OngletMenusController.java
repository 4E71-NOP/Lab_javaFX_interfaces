package com.fma.ihm.menus;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;

public class OngletMenusController {
	@FXML
	private TitledPane WebViewTitledPane;
	@FXML
	private ToolBar MenusToolbar;
	@FXML
	private Button MenusToolbarBtn01;
	@FXML
	private Button MenusToolbarBtn02;
    @FXML
    private Label MenusLabelActionRealisee;
	
	// ----------------------------------------------------------------------------------------------------
	//
	//
	// Gestion des actions utilisateur
	//
	//
	//
    public void MenusToolbarBtn01Action() {
		System.out.println(getClass().getName() + " Bouton 01 pressé");
		MenusLabelActionRealisee.setText("Bouton 01");
	}
	public void MenusToolbarBtn02Action() {
		System.out.println(getClass().getName() + " Bouton 02 pressé");
		MenusLabelActionRealisee.setText("Bouton 02");
	}
	
	public void initialize() {
		System.out.println(getClass().getName() + " Initialisation...");

	}
}
