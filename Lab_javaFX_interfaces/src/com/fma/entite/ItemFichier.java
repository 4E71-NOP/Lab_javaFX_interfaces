package com.fma.entite;

/**
 * Classe permettant de faciliter la gestion des fichiers.<br>
 * <br>
 * 
 * @author Faust MARIA DE ARAVALO - Tous droits réservés. Projet protfolio sur
 *         JavaFX2<br>
 *         <br>
 */
public class ItemFichier {
	// 0 fichier; 1 repertoire; 2 lien
	private String nom;
	private String chemin;
	private String nomComplet;
	private int type = 0;
	private int taille = 0;

	public ItemFichier() {
	}

	public ItemFichier(String nom, String chemin, int type, int taille) {
		this.nom = nom;
		this.chemin = chemin;
		this.nomComplet = chemin + "/" + nom;
		this.type = type;
		this.taille = taille;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getChemin() {
		return chemin;
	}

	public void setChemin(String chemin) {
		this.chemin = chemin;
	}

	public String getNomComplet() {
		return nomComplet;
	}

	public void setNomComplet(String nomComplet) {
		this.nomComplet = nomComplet;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public String toStringDetail() {
		return "ItemFichier [nom=" + nom + ", chemin=" + chemin + ", type=" + type + ", taille=" + taille
				+ ",\n nomComplet=" + nomComplet + "]";
	}

	@Override
	public String toString() {
		return nom;
	}

}
