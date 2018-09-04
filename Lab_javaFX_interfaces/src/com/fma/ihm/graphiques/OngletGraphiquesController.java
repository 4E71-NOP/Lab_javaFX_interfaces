package com.fma.ihm.graphiques;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

public class OngletGraphiquesController {
	@FXML
	private LineChart<String, Number> GaphiquesLineChart;
	@FXML
	private Button GaphiquesBtn;

	private XYChart.Series<String, Number> courbe01 = new XYChart.Series<String, Number>();
	private XYChart.Series<String, Number> courbe02 = new XYChart.Series<String, Number>();

	// ----------------------------------------------------------------------------------------------------
	//
	//
	// Gestion des actions utilisateur
	//
	//
	//
	public void GaphiquesBtnAction() {
		GaphiquesLineChart.getData().remove(courbe01);
		GaphiquesLineChart.getData().remove(courbe02);
//		courbe01.getData().removeAll();
//		courbe02.getData().removeAll();
		courbe01 = new XYChart.Series<String, Number>();
		courbe02 = new XYChart.Series<String, Number>();
		CreationDeSeries();
	}

	public void CreationDeSeries() {
		for (int i = 1; i <= 25; i++) {
			courbe01.getData().add(new XYChart.Data<String, Number>(Integer.toString(i), Math.random() * 50));
			courbe02.getData().add(new XYChart.Data<String, Number>(Integer.toString(i), Math.random() * 25));
		}
		courbe01.setName("Courbe 01");
		courbe02.setName("Courbe 02");
		GaphiquesLineChart.getData().add(courbe01);
		GaphiquesLineChart.getData().add(courbe02);
	}

	public void initialize() {
		System.out.println(getClass().getName() + " Initialisation...");
		CreationDeSeries();
	}
}
