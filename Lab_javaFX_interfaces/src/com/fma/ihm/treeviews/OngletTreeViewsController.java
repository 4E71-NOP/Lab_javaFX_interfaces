package com.fma.ihm.treeviews;

import java.io.File;
import java.util.regex.Pattern;

import com.fma.agents.GestionMemoire;
import com.fma.entite.ImageObservable;
import com.fma.entite.ItemFichier;
import com.fma.ihm.barredestatut.BarreDeStatutController;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class OngletTreeViewsController {
	@FXML
	private AnchorPane OngletTreeViewsTabId;
	@FXML
	private Button TreeViewsBtn;
	@FXML
	private TreeView<ItemFichier> TreeViewsContenu;
	@FXML
	private TreeTableView<ItemFichier> TreeViewsTreeTableView;
	@FXML
	private Label TreeViewsLabel;

	// private final Node icone01 = new ImageView(new
	// Image(getClass().getResourceAsStream("animale-staroffice-summerbird-icone-6155-128.png")));
	// revoir le chemin pour arriver dans le vrai répertoire des ressources

	private TreeItem<ItemFichier> selectionElementTreeView;
	private ItemFichier selectionFichier;
	private String comportementTreeView = "SurDemande"; // SurDemande ou ChargementComplet
	// ----------------------------------------------------------------------------------------------------
	//
	//
	// Gestion des actions utilisateur
	//
	//
	//
	public void TreeViewsBtnAction() {
		Stage stage = (Stage) OngletTreeViewsTabId.getScene().getWindow();
		DirectoryChooser dc = new DirectoryChooser();
		dc.setTitle("Open Resource File");
		File repertoireSelection = dc.showDialog(stage);
		// treeView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		if (repertoireSelection != null) {
			System.out.println("Repertoire : " + repertoireSelection.getPath());

			String filtreFichier = ".*(\\.bmp|\\.gif|\\.jpg|\\.jpeg|\\.png)$";

			TreeItem<ItemFichier> arborescence = new TreeItem<ItemFichier>(
					new ItemFichier(repertoireSelection.getName(), repertoireSelection.getPath(), -1, -1));
			arborescence.setExpanded(true);

			switch (comportementTreeView) {
			case "SurDemande":
				assignationListener(arborescence, filtreFichier);
				ajouteElementsSurDemande(repertoireSelection.getPath(), repertoireSelection.getName(), filtreFichier,
						arborescence);
				break;
			case "ChargementComplet":
				ConstructionArborescence(repertoireSelection.getPath(), "/", 0, 15, 0, filtreFichier, arborescence);

				break;
			}

			if (arborescence != null) {
				TreeViewsContenu.setRoot(arborescence);
				BarreDeStatutController.getInstance().setBarreDeStatutMessage("Creation de l'arborescence");

				TreeViewsContenu.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
					selectionElementTreeView = newValue;
					selectionFichier = TreeViewsContenu.getSelectionModel().getSelectedItem().getValue();
					TreeViewsLabel
							.setText("de: " + oldValue + "; à: " + newValue + "\n" + selectionFichier.toStringDetail());
				});

				TreeViewsContenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent click) {
						if (click.getClickCount() == 2) {
							ItemFichier tif = TreeViewsContenu.getSelectionModel().getSelectedItem().getValue();
							if (tif.getType() == 0) {
								File f = new File(selectionFichier.getNomComplet());
								System.out.println(
										getClass().getName() + " Fichier=" + f.getAbsoluteFile().toURI().toString());
								TreeViewsLabel.setText(selectionFichier.toStringDetail() + "\n (2 clics)");
								ImageObservable.getInstance().setNouvelleImage(f.getAbsoluteFile().toURI().toString());
							}
						}
					}
				});
			}
		}
	}

	// ----------------------------------------------------------------------------------------------------
	//
	// Parcours un répertoire et peuple l'objet (ici TreeItem<ItemFichier>) avec les
	// candidats trouvés (ici des images)
	// fichiers trouvées. Mode Lecture à la demande
	// ".*(\\.bmp|\\.gif|\\.jpg|\\.jpeg|\\.png)$"
	//
	private void ajouteElementsSurDemande(String chemin, String nom, String typeFichier,
			TreeItem<ItemFichier> objEnCours) {

		objEnCours.getChildren().clear();
		GestionMemoire.getInstance().executeGc();

		File repertoireCible = new File(chemin);
		File[] contenu = repertoireCible.listFiles();

		// Traitement des répertoires en premier
		for (File f : contenu) {
			if (f.isDirectory()) {
				TreeItem<ItemFichier> nouveauRep = new TreeItem<ItemFichier>(
						new ItemFichier(f.getName(), f.getPath(), 1, 0));
				assignationListener(nouveauRep, typeFichier);
				objEnCours.getChildren().add(nouveauRep);
			}
		}

		// Les fichiers ensuite...
		for (File f : contenu) {
			if (f.isFile()) {
				if (Pattern.matches(typeFichier, f.getName())) {
					objEnCours.getChildren()
							.add(new TreeItem<ItemFichier>(new ItemFichier(f.getName(), f.getParent(), 0, 0)));
				}
			}
		}

		for (TreeItem<ItemFichier> elm : objEnCours.getChildren()) {
			if (elm.valueProperty().get().getType() == 1) {
				// Ajoute un "..." en guise d'attente du chargement des données
				elm.getChildren().add(new TreeItem<ItemFichier>(new ItemFichier("...", "...", -1, -1)));
				elm.setExpanded(false);
			}
		}
	}

	private void assignationListener(TreeItem<ItemFichier> objCourant, String typeFichier) {
		objCourant.expandedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				BooleanProperty booleanPropertyEnCours = (BooleanProperty) observable;
				TreeItem<ItemFichier> treeItemEnCours = (TreeItem<ItemFichier>) booleanPropertyEnCours.getBean();
				ItemFichier tif = treeItemEnCours.getValue();
				ajouteElementsSurDemande(tif.getChemin(), tif.getNom(), typeFichier, treeItemEnCours);
				TreeViewsLabel.setText("de: " + oldValue + "; à: " + newValue + "\n" + tif.getNom() + "\n"
						+ tif.getChemin() + "\n" + tif.getType());
			}
		});
	}

	// ----------------------------------------------------------------------------------------------------
	//
	// Parcours un répertoire et peuple l'objet (ici TreeItem<ItemFichier>) avec
	// TOUS les éléments et sous-éléments trouvés.
	// Mode ChargementComplet
	//
	private void ConstructionArborescence(String chemin, String nom, int niveauEnCours, int niveauMax,
			int arretDepliage, String typeFichier, TreeItem<ItemFichier> objEnCours) {

		objEnCours.getChildren().clear();

		TreeItem<ItemFichier> arborescence = new TreeItem<ItemFichier>(new ItemFichier(nom, chemin, 1, 0)); // , icone01
		if (niveauEnCours <= arretDepliage) {
			arborescence.setExpanded(true);
		}

		if (niveauEnCours <= niveauMax) {
			File repertoireCible = new File(chemin);
			File[] listeFichiers = repertoireCible.listFiles();

			for (File f : listeFichiers) {
				if (f.isFile()) {
					if (Pattern.matches(typeFichier, f.getName())) {
						objEnCours.getChildren()
								.add(new TreeItem<ItemFichier>(new ItemFichier(f.getName(), f.getParent(), 0, 0)));
					}
				} else if (f.isDirectory()) {
					TreeItem<ItemFichier> nouveauRep = new TreeItem<ItemFichier>(
							new ItemFichier(f.getName(), f.getPath(), 1, 0));
					objEnCours.getChildren().add(nouveauRep);
					ConstructionArborescence(f.getPath(), f.getName(), niveauEnCours + 1, niveauMax, arretDepliage,
							typeFichier, nouveauRep);
				}
			}
		}
	}

	// ----------------------------------------------------------------------------------------------------
	//
	//
	//

	public void initialize() {
		System.out.println(getClass().getName() + " Initialisation...");

	}

}
