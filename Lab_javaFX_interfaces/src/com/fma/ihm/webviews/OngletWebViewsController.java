package com.fma.ihm.webviews;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * Classe dédiée à l'onglet WebView<br>
 * <br>
 * 
 * @author Faust MARIA DE ARAVALO - Tous droits réservés. Projet protfolio sur
 *         JavaFX2<br>
 *         <br>
 */
public class OngletWebViewsController {
	// @formatter:off
	@FXML	private Button WebViewBtn;
	@FXML	private WebView WebViewContenu;
	@FXML	private TextField WebViewTextField;
	// @formatter:on

	// ----------------------------------------------------------------------------------------------------
	//
	//
	// Gestion des actions utilisateur
	//
	//
	//
	public void WebViewBtnAction() {
		System.out.println(getClass().getName() + " Action ");
		WebEngine we = WebViewContenu.getEngine();
		we.load(WebViewTextField.getText());

	}

	public void initialize() {
		System.out.println(getClass().getName() + " Initialisation...");

	}
}
