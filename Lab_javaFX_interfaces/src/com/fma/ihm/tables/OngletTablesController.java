package com.fma.ihm.tables;

import com.fma.entite.ItemDeListe3Colonnes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class OngletTablesController {

	// @formatter:off
	@FXML	private Button TablesBtn;
	@FXML	private Label TablesLabel;

	@FXML	private TableView<ItemDeListe3Colonnes> TablesTableView;
	@FXML	private TableColumn<ItemDeListe3Colonnes, String> TablesTableViewColonneTitre;
	@FXML	private TableColumn<ItemDeListe3Colonnes, String> TablesTableViewColonneValeur;
	@FXML	private TableColumn<ItemDeListe3Colonnes, String> TablesTableViewColonneCalcul;
	// @formatter:on

	// ----------------------------------------------------------------------------------------------------
	//
	//
	// Gestion des actions utilisateur
	//
	//
	//
	public void TablesBtnAction() {
		System.out.println(" TablesBtnAction : Remplissage de la table");
		ObservableList<ItemDeListe3Colonnes> ongletTablesListe = FXCollections.observableArrayList();
		ongletTablesListe.addAll(new ItemDeListe3Colonnes("Entrée #01", 1, 0),
				new ItemDeListe3Colonnes("Entrée #02", 2, 0), new ItemDeListe3Colonnes("Entrée #03", 3, 0),
				new ItemDeListe3Colonnes("Entrée #04", 4, 0), new ItemDeListe3Colonnes("Entrée #05", 5, 0),
				new ItemDeListe3Colonnes("Entrée #06", 6, 0), new ItemDeListe3Colonnes("Entrée #07", 7, 0),
				new ItemDeListe3Colonnes("Entrée #08", 8, 0), new ItemDeListe3Colonnes("Entrée #09", 9, 0),
				new ItemDeListe3Colonnes("Entrée #10", 10, 0));

		TablesTableViewColonneTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
		TablesTableViewColonneValeur.setCellValueFactory(new PropertyValueFactory<>("valeur"));
		TablesTableViewColonneCalcul.setCellValueFactory(new PropertyValueFactory<>("calcul"));

		TablesTableView.setItems(ongletTablesListe);
		TablesTableView.setEditable(false);

		//
		// Code pour savoir quel objet est sélectionné.
		// Schema du listener clic simple: "observable", "ancienne valeur", "nouvelle
		// valeur"
		//
		TablesTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				System.out.println(getClass().getName() + ": \n" + "Ancienne valeur:" + oldSelection + "\n"
						+ "Nouvelle valeur:" + TablesTableView.getSelectionModel().getSelectedItem().toString());
				TablesLabel.setText(TablesTableView.getSelectionModel().getSelectedItem().toString());
			}
		});

		//
		// Code pour intercepter le double clic sur une ligne(row) et passer à la vue
		// suivante
		//
		TablesTableView.setRowFactory(dc -> {
			TableRow<ItemDeListe3Colonnes> row = new TableRow<>();
			row.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					if (e.getClickCount() == 2 && (!row.isEmpty())) {
						System.out.println(getClass().getName() + ": " + row.getItem().getTitre() + " | "
								+ row.getItem().getValeur() + " | " + row.getItem().getCalcul());
						TablesLabel.setText(
								TablesTableView.getSelectionModel().getSelectedItem().toString() + " (2 clics)");
					}
				}
			});
			return row;
		});
	}

	public void initialize() {
		System.out.println(getClass().getName() + " Initialisation...");

	}
}
