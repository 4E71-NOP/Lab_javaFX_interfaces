package com.fma.ihm.listes;

import com.fma.entite.ItemDeListe3Colonnes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;

/**
 * Classe dédiée à l'onglet Listes<br>
 * <br>
 * 
 * @author Faust MARIA DE ARAVALO - Tous droits réservés. Projet
 *         protfolio sur JavaFX2<br>
 *         <br>
 */public class OngletListesController {
	// @formatter:off
	@FXML	private Button ListesBtn;
	@FXML	private ListView<ItemDeListe3Colonnes> ListesListView;
	@FXML	private Label ListesLabel;
	// @formatter:on

	// ----------------------------------------------------------------------------------------------------
	//
	//
	// Gestion des actions utilisateur
	//
	//
	//	
	public void ListesBtnAction() {
		System.out.println(" TablesBtnAction : Remplissage de la table");
		ObservableList<ItemDeListe3Colonnes> ongletTablesListe = FXCollections.observableArrayList();
		ongletTablesListe.addAll(new ItemDeListe3Colonnes("Entrée #01", 1, 0),
				new ItemDeListe3Colonnes("Entrée #02", 2, 0), new ItemDeListe3Colonnes("Entrée #03", 3, 0),
				new ItemDeListe3Colonnes("Entrée #04", 4, 0), new ItemDeListe3Colonnes("Entrée #05", 5, 0),
				new ItemDeListe3Colonnes("Entrée #06", 6, 0), new ItemDeListe3Colonnes("Entrée #07", 7, 0),
				new ItemDeListe3Colonnes("Entrée #08", 8, 0), new ItemDeListe3Colonnes("Entrée #09", 9, 0),
				new ItemDeListe3Colonnes("Entrée #10", 10, 0));

		ListesListView.setItems(ongletTablesListe);
		ListesListView.setEditable(false);

		//
		// Code pour savoir quel objet est sélectionné.
		// Schema du listener clic simple: "observable", "ancienne valeur", "nouvelle
		// valeur"
		//
		ListesListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				System.out.println(getClass().getName() + ": \n" + "Ancienne valeur:" + oldSelection + "\n"
						+ "Nouvelle valeur:" + ListesListView.getSelectionModel().getSelectedItem().toString());
				ListesLabel.setText(ListesListView.getSelectionModel().getSelectedItem().toString());
			}
		});

		//
		// Code pour intercepter le double clic sur une ligne(row) 
		//
		ListesListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
    				ListesLabel.setText(ListesListView.getSelectionModel().getSelectedItem().toString() + " (2 clics)");
                }
            }
        });

	}
	
	
	
	public void initialize() {
		System.out.println(getClass().getName() + " Initialisation...");
		
	}
}
