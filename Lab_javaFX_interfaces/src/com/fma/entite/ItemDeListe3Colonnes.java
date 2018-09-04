package com.fma.entite;

public class ItemDeListe3Colonnes {

	private String titre;
	private int valeur;
	private int calcul;

	public ItemDeListe3Colonnes() {
	}

	public ItemDeListe3Colonnes(String titre, int valeur, int calcul) {
		super();
		this.titre = titre;
		this.valeur = valeur;
		this.calcul = calcul;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	public int getCalcul() {
		return calcul;
	}

	public void setCalcul(int calcul) {
		this.calcul = calcul;
	}

	@Override
	public String toString() {
		return titre;
	}
	
	

}
