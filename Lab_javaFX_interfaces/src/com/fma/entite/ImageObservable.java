package com.fma.entite;

import java.util.Observable;
import com.fma.ihm.barredestatut.BarreDeStatutController;
import javafx.scene.image.Image;

public class ImageObservable extends Observable {

	private static ImageObservable instance;
	private String chemin;
	private Image imageBuffer;

	private ImageObservable() {
	}

	public static ImageObservable getInstance() {
		if (instance == null) {
			instance = new ImageObservable();
		}
		return instance;
	}

	//	Charge une image et stock le contenu dans imagebuffer.
	//	Est un observable qui previent ses abonnés.
	public void setNouvelleImage(String chemin) {
		this.chemin = chemin;
		this.imageBuffer = new Image(chemin);
		BarreDeStatutController.getInstance().setBarreDeStatutMessage("Chargement image : "+ chemin);
		setChanged();
		notifyObservers(this.getClass());
	}

	public double getWidth() {
		return imageBuffer.getWidth();
	}

	public double getHeight() {
		return imageBuffer.getHeight();
	}

	public Image getImageBuffer() {
		return imageBuffer;
	}

	public void setImageBuffer(Image img) {
		imageBuffer = img;
	}

	public String getChemin() {
		return chemin;
	}

	public void setChemin(String chemin) {
		this.chemin = chemin;
	}

	@Override
	public String toString() {
		return "ImageObservable [chemin=" + chemin + "]";
	}

	
	
}
