package com.fma.ihm.formulaires;

import java.util.ArrayList;
import java.util.Observable;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TextArea;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;

public class OngletFormulairesController {

	// @formatter:off
	@FXML	private TextField FormulaireChampTexte;
	@FXML	private Label FormulaireChampTexteValeur;
	
	@FXML	private PasswordField FormulaireChampMDP;
	@FXML	private Label FormulaireChampMDPValeur;
	
	@FXML	private TextField FormulaireChampRegEx;
	@FXML	private Label FormulaireChampRegExValeur;
	
	@FXML	private CheckBox FormulaireCheckbox01;
	@FXML	private Label FormulaireCheckbox01Valeur;

	@FXML	private CheckBox FormulaireCheckbox02;
	@FXML	private Label FormulaireCheckbox02Valeur;
	
	@FXML	private ChoiceBox<String> FormulaireMenuSelection;
	@FXML	private Label FormulaireMenuSelectionValeur;
	
	@FXML	private ToggleButton FormulaireBoutonPosition;
	@FXML	private Label FormulaireBoutonPositionValeur;
	
	@FXML	private DatePicker FormulaireSelecteurDate;
	@FXML	private Label FormulaireSelecteurDateValeur;
	
	@FXML	private ColorPicker FormulaireSelecteurCouleur;
	@FXML	private Label FormulaireSelecteurCouleurValeur;
	
	@FXML	private TextArea FormulaireChampTextArea;
	@FXML	private Label FormulaireChampTextAreaValeur;
	
	@FXML	private ToggleGroup BoutonRadioGroup;
	@FXML	private RadioButton FormulaireBoutonRadio01;
	@FXML	private RadioButton FormulaireBoutonRadio02;
	@FXML	private RadioButton FormulaireBoutonRadio03;
	@FXML	private Label FormulaireBoutonRadioValeur;
	
	@FXML	private ComboBox<String> FormulaireComboBox;
	@FXML	private Label FormulaireComboBoxValeur;
	
	// @formatter:on

	private String[] strList = { "Vide", "Check", "Intermédiaire" };

	// ----------------------------------------------------------------------------------------------------
	//
	//
	// Gestion des actions utilisateur
	//
	//
	//
	public void FormulaireCheckbox01Action() {
		int isSelected = (FormulaireCheckbox01.isSelected()) ? 1 : 0;
		String str = strList[isSelected];
		FormulaireCheckbox01Valeur.setText("Etat : " + str);
		System.out.println(getClass().getName() + " FormulaireCheckbox01 état : " + FormulaireCheckbox01.isSelected());
	}

	public void FormulaireCheckbox02Action() {
		int isSelected = (FormulaireCheckbox02.isSelected()) ? 1 : 0;
		int isIntermediate = (FormulaireCheckbox02.isIndeterminate()) ? 2 : 0;
		int CheckboxState = isSelected + isIntermediate;
		String str = strList[CheckboxState];

		FormulaireCheckbox02Valeur.setText("Etat : " + str);
		System.out.println(getClass().getName() + " FormulaireCheckbox02 état : " + FormulaireCheckbox02.isSelected() + " /intermédiaire" + FormulaireCheckbox02.isIndeterminate());
		// FormulaireCheckbox02.is
	}

	public void FormulaireBoutonPositionAction() {
		FormulaireBoutonPositionValeur.setText("Position : " + FormulaireBoutonPosition.selectedProperty().getValue());
		System.out.println(getClass().getName() + " FormulaireBoutonPosition état : "
				+ FormulaireBoutonPosition.selectedProperty().getValue());
	}

	public void FormulaireSelecteurDateAction() {
		FormulaireSelecteurDateValeur.setText("Date : " + FormulaireSelecteurDate.getValue());
		System.out.println(getClass().getName() + " FormulaireSelecteurDate : " + FormulaireSelecteurDate.getValue());
	}

	public void FormulaireSelecteurCouleurAction() {
		FormulaireSelecteurCouleurValeur.setText("Couleur : " + FormulaireSelecteurCouleur.getValue());
		System.out.println(
				getClass().getName() + " FormulaireSelecteurCouleur : " + FormulaireSelecteurCouleur.getValue());
	}

	public void FormulaireBoutonRadio() {
		RadioButton selectedRadioButton = (RadioButton) BoutonRadioGroup.getSelectedToggle();
		FormulaireBoutonRadioValeur.setText(selectedRadioButton.getText());
	}

	// --------------------------------------------------------------------------------
	public void initialize() {
		System.out.println(getClass().getName() + " Initialisation...");

		FormulaireCheckbox02.setAllowIndeterminate(true);

		// Méthode 1
		// FormulaireMenuSelection.getItems().addAll("Selection #01", "Selection #02",
		// "Selection #03", "Selection #04", "Selection #05");
		// Méthode 2
		ObservableList<String> lms = FXCollections.observableArrayList("Selection #01", "Selection #02",
				"Selection #03", "Selection #04", "Selection #05");
		FormulaireMenuSelection.setItems(lms);
		FormulaireMenuSelection.getSelectionModel().select(1);

		FormulaireComboBox.setItems(lms);
		FormulaireComboBox.setVisibleRowCount(3);
		FormulaireComboBox.getSelectionModel().select(1);

		FormulaireChampTexte.setText("Test");
		FormulaireChampMDP.setText("Test");
		FormulaireChampRegEx.setText("123");
		FormulaireChampTextArea.setText("Test");

		FormulaireChampTexte.textProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println("FormulaireChampTexte passe de :\n" + oldValue + "\n" + newValue);
			FormulaireChampTexteValeur.setText(newValue);
		});

		FormulaireChampMDP.textProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println("FormulaireChampTexte passe de :\n" + oldValue + "\n" + newValue);
			FormulaireChampMDPValeur.setText(newValue);
		});

		// Intercepte le changement et le confronte a une expression régulière
		// Le principe de l'expression régulière en Java est que toute la chaine doit
		// correspondre.
		// Exemple si on veut tous les fichier.gif & jpg on doit produire le pattern
		// suivant : .*(\.gif|\.jpg)$ : tout caractere et ensuite la capture via un
		// groupe pour les extenssions. Dans un context RegExp pur on pourrait se passer
		// de ".*".
		FormulaireChampRegEx.textProperty().addListener((observable, oldValue, newValue) -> {
			if (Pattern.matches("\\d*\\.?\\d*", newValue) == false) {
				System.out.println(getClass().getName() + " : " + newValue);
				FormulaireChampRegEx.setText(oldValue);
			}
		});

		FormulaireMenuSelection.getSelectionModel().selectedIndexProperty()
				.addListener((observable, oldValue, newValue) -> {
					System.out.println(getClass().getName() + " FormulaireMenuSelection passe de : " + oldValue + " à "
							+ newValue);
					FormulaireMenuSelectionValeur.setText(FormulaireMenuSelection.getItems().get((int) newValue));
				});

		FormulaireComboBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println(getClass().getName() + " FormulaireComboBox passe de : " + oldValue + " à " + newValue);
			FormulaireComboBoxValeur.setText(FormulaireComboBox.getItems().get((int) newValue));
		});

		FormulaireChampTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println("FormulaireChampTexte passe de :\n" + oldValue + "\n" + newValue);
			FormulaireChampTextAreaValeur.setText(newValue);
		});

	}

}
