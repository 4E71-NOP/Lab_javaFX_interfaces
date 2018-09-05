package com.fma.ihm.menus;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;

/**
 * Classe dédiée à l'onglet Menus<br>
 * <br>
 * 
 * @author Faust MARIA DE ARAVALO - Tous droits réservés. Projet protfolio sur
 *         JavaFX2<br>
 *         <br>
 */
public class OngletMenusController {
	// @formatter:off
	@FXML	private TabPane MenusTabA1;
	@FXML	private MenuBar MenusMenuBarB1;
	@FXML	private MenuItem MenusMenuBarB1FileClose;
	@FXML	private MenuItem MenusMenuBarB1FilePerso;
	@FXML	private MenuItem MenusMenuBarB1EditDelete;
	@FXML	private MenuItem MenusMenuBarB1HelpAbout;
	
	@FXML	private TitledPane WebViewTitledPane;
	@FXML	private ToolBar MenusToolbar;
	@FXML	private Button MenusToolbarBtn01;
	@FXML	private Button MenusToolbarBtn02;

	@FXML	private Label MenusLabelActionRealisee;
	// @formatter:on
	
	// ----------------------------------------------------------------------------------------------------
	//
	//
	// Gestion des actions utilisateur
	//
	//
	//
	@FXML
	private void MenusToolbarBtn01Action() {
		System.out.println(getClass().getName() + " Bouton 01 pressé");
		MenusLabelActionRealisee.setText("Bouton 01");
	}

	@FXML
	private void MenusToolbarBtn02Action() {
		System.out.println(getClass().getName() + " Bouton 02 pressé");
		MenusLabelActionRealisee.setText("Bouton 02");
	}

	@FXML
	private void MenusTabA1Action() {
		int tabSelection = MenusTabA1.getSelectionModel().getSelectedIndex();
		Tab tabNom = MenusTabA1.getSelectionModel().getSelectedItem();
		MenusLabelActionRealisee.setText("Onglet sélectionné : " + tabSelection + "; " + tabNom.textProperty().getValue());

	}

	// ----------------------------------------------------------------------------------------------------
	//
	//	Menu
	@FXML
	private void MenusMenuBarB1FileCloseAction() {
		MenusMenuBarB1("Close");
	}

	@FXML
	private void MenusMenuBarB1FilePersoAction() {
		MenusMenuBarB1("File/Perso");
	}

	@FXML
	private void MenusMenuBarB1EditDeleteAction() {
		MenusMenuBarB1("Edit/Delete");
	}

	@FXML
	private void MenusMenuBarB1HelpAboutAction() {
		MenusMenuBarB1("Help/About");
	}

	@FXML
	private void MenusMenuBarB1Action() {
		MenusMenuBarB1("Menu");
		
	}

	private void MenusMenuBarB1(String str) {
		MenusLabelActionRealisee.setText("Menu sélectionné : " + str);
		System.out.println(getClass().getName() + ": Menu sélectionné : " + str);

	}

	// ----------------------------------------------------------------------------------------------------
	//
	//	Initialisation
	@FXML
	public void initialize() {
		System.out.println(getClass().getName() + " Initialisation...");

	}

}
