package com.fma.ihm.images;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import com.fma.entite.ImageObservable;
import com.fma.ihm.barredestatut.BarreDeStatutController;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.effect.MotionBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

/**
 * Classe dédiée à l'onglet Image<br>
 * <br>
 * Controlleur dédié aux images et aux filtrages sur celles-ci. Plusieurs filtre
 * sont utilisés. Même s'il sont cumulés il y peu de chance que cela soit
 * intéressant de trop cumuler les effets. Les rendu reste instantané, mais
 * plutot illisible si on abuse.* <br>
 * <br>
 * 
 * @author Faust MARIA DE ARAVALO - Tous droits réservés. Projet protfolio sur
 *         JavaFX2<br>
 *         <br>
 */
public class OngletImagesController implements Observer {
	// @formatter:off
	@FXML	private Button ImagesBtnCharger;
	@FXML	private Label ImagesLabelOpacite;
	@FXML	private Slider ImagesSliderOpacite;
	@FXML	private Label ImagesLabelZoom;
	@FXML	private Slider ImagesSliderZoom;
	@FXML	private Label ImagesLabelRotation;
	@FXML	private Slider ImagesSliderRotation;
	@FXML	private Button ImagesBtnAjuster;
	@FXML	private Label ImagesInfos;
	@FXML	private Slider ImagesSliderRouge;
	@FXML	private Label ImagesLabelRouge;
	@FXML	private Slider ImagesSliderVert;
	@FXML	private Label ImagesLabelVert;
	@FXML	private Slider ImagesSliderBleu;
	@FXML	private Label ImagesLabelBleu;

	@FXML	private Label ImagesLabelHUE;
	@FXML	private Slider ImagesSliderHUE;
	@FXML	private Label ImagesLabelSaturation;
	@FXML	private Slider ImagesSliderSaturation;
	@FXML	private Label ImagesLabelLuminosite;
	@FXML	private Slider ImagesSliderLuminosite;
	@FXML	private Label ImagesLabelContraste;
	@FXML	private Slider ImagesSliderContraste;

	@FXML	private Label ImagesLabelBloom1;
	@FXML	private Slider ImagesSliderBloom1;
	@FXML	private Label ImagesLabelBloom2;
	@FXML	private Slider ImagesSliderBloom2;
	@FXML	private Label ImagesLabelGlow;
	@FXML	private Slider ImagesSliderGlow;
	@FXML	private Label ImagesLabelBlur1;
	@FXML	private Slider ImagesSliderBlur1;
	@FXML	private Label ImagesLabelBlur2;
	@FXML	private Slider ImagesSliderBlur2;
	@FXML	private Label ImagesLabelBlur3;
	@FXML	private Slider ImagesSliderBlur3;
	@FXML	private Label ImagesLabelGblur1;
	@FXML	private Slider ImagesSliderGblur1;
	@FXML	private Label ImagesLabelMblur1;
	@FXML	private Slider ImagesSliderMblur1;
	@FXML	private Label ImagesLabelMblur2;
	@FXML	private Slider ImagesSliderMblur2;

	@FXML	private ScrollPane ImagesEncadrement;
	@FXML	private ImageView ImagesContenu;
	// @formatter:on

	private double imageZoom;
	private double imageRotation;
	private double imageOpacite;
	private String imageDimenssions;

	private int imageCanalRouge = 255;
	private int imageCanalVert = 255;
	private int imageCanalBleu = 255;

	private double imageColorAdjustHUEVal = 0;
	private double imageColorAdjustSaturationVal = 0;
	private double imageColorAdjustLuminositeVal = 0;
	private double imageColorAdjustContrasteVal = 0;

	private double imageEffectBloom1Val = 0;
	private double imageEffectBloom2Val = 1;
	private double imageEffectGlowVal = 0;
	private double imageEffectBlur1Val = 0;
	private double imageEffectBlur2Val = 0;
	private double imageEffectBlur3Val = 0;
	private double imageEffectGblur1Val = 0;
	private double imageEffectMblur1Val = 0;
	private double imageEffectMblur2Val = 0;

	// ----------------------------------------------------------------------------------------------------
	//
	//
	// Gestion des actions utilisateur
	//
	//
	//

	/**
	 * Lance le chargement d'un fichier via un FileChooser.
	 * 
	 */
	public void ImagesBtnChargerAction() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.jpeg", "*.bmp", "*.tiff"));

		File fichierSelection = fileChooser.showOpenDialog(null);
		if (fichierSelection != null) {
			String fichierCible = fichierSelection.getAbsoluteFile().toURI().toString();
			System.out.println(getClass().getName() + ".ImagesBtnAction fichierCible=" + fichierCible);
			chargeFichierVisionneuse(fichierCible);
		}
	}

	public void chargeFichierVisionneuse(String chemin) {
		ImageObservable.getInstance().setNouvelleImage(chemin);
		// Inutile de lancer affichageVisionneuse() car c'est l'observateur qui prend le
		// relais.
	}

	// Observateur qui recoit une mise à jour de l'objet "ImageObservable"
	@Override
	public void update(Observable o, Object arg) {
		System.out.println(getClass().getName() + ".Update : reçu de ImageObservable");
		affichageVisionneuse();
	}

	// La visionneuse prend le contenu de l'objet "ImageObservable" et l'assigne au
	// contenu
	public void affichageVisionneuse() {
		ImageObservable imgObs = ImageObservable.getInstance();
		ImagesContenu.setImage(imgObs.getImageBuffer());
		System.out.println(getClass().getName() + ".affichageVisionneuse : " + imgObs.getChemin());

		ImagesBtnAjusterAction();
	}

	public void ImagesBtnAjusterAction() {
		ImageObservable img = ImageObservable.getInstance();

		double ratio = ImagesEncadrement.getWidth() / img.getWidth() * 100;
		double imageCoef = ImagesEncadrement.getWidth() / img.getWidth();

		ImagesContenu.setOpacity(0);

		ImagesSliderRotation.setValue(0);
		ImagesContenu.setRotate(0);

		ImagesSliderZoom.setValue((double) Integer.parseInt(String.format("%.0f", ratio)));
		ImagesContenu.setFitWidth(img.getWidth() * imageCoef);
		ImagesContenu.setFitHeight(img.getHeight() * imageCoef);

		ImagesSliderRouge.setValue(255);
		ImagesSliderVert.setValue(255);
		ImagesSliderBleu.setValue(255);

		ImagesSliderHUE.setValue(0);
		ImagesSliderSaturation.setValue(0);
		ImagesSliderLuminosite.setValue(0);
		ImagesSliderContraste.setValue(0);

		ImagesSliderBloom1.setValue(0);
		ImagesSliderBloom2.setValue(1);
		ImagesSliderGlow.setValue(0);
		ImagesSliderBlur1.setValue(0);
		ImagesSliderBlur2.setValue(0);
		ImagesSliderBlur3.setValue(0);
		ImagesSliderGblur1.setValue(0);
		ImagesSliderMblur1.setValue(0);
		ImagesSliderMblur2.setValue(0);

		ImagesContenu.setEffect(null);
		ImagesSliderOpacite.setValue(100);
		ImagesContenu.setOpacity(1);
		BarreDeStatutController.getInstance().setBarreDeStatutMessage("Ajustement de l'image");

	}

	// ----------------------------------------------------------------------------------------------------
	//
	//
	//

	public void ActualisationImagesInfos() {
		ImagesInfos.setText("Information:\n" + "Zoom : " + imageZoom + "\n" + "Rotation : " + imageRotation + "\n"
				+ "Opacité : " + imageOpacite + "\n" + "Dimenssions :" + imageDimenssions);
	}

	public void ActualisationFiltreRendu() {

		// https://stackoverflow.com/questions/41287585/add-multiple-effects-on-imageview
		ColorAdjust fxColorAdjust = new ColorAdjust();
		Bloom fxBloom = new Bloom();
		Glow fxGlow = new Glow();
		BoxBlur fxBBlur = new BoxBlur();
		MotionBlur fxMblur = new MotionBlur();
		GaussianBlur fxGblur = new GaussianBlur();

		fxColorAdjust.setInput(fxBloom);
		fxBloom.setInput(fxGlow);
		fxGlow.setInput(fxBBlur);
		fxBBlur.setInput(fxMblur);
		fxMblur.setInput(fxGblur);

		fxColorAdjust.setHue(imageColorAdjustHUEVal);
		fxColorAdjust.setBrightness(imageColorAdjustLuminositeVal);
		fxColorAdjust.setContrast(imageColorAdjustContrasteVal);
		fxColorAdjust.setSaturation(imageColorAdjustSaturationVal);
		fxBloom.setThreshold((float) imageEffectBloom1Val);
		fxBloom.thresholdProperty().set(imageEffectBloom2Val);
		fxGlow.setLevel(imageEffectGlowVal);
		fxBBlur.setWidth(imageEffectBlur1Val);
		fxBBlur.setHeight(imageEffectBlur2Val);
		fxBBlur.setIterations((int) imageEffectBlur3Val);
		fxMblur.setRadius(imageEffectMblur1Val);
		fxMblur.setAngle(imageEffectMblur2Val);
		fxGblur.setRadius(imageEffectGblur1Val);

		Blend myBlend = new Blend(BlendMode.MULTIPLY, null, new ColorInput(0, 0, ImagesContenu.getFitWidth(),
				ImagesContenu.getFitHeight(), Color.rgb(imageCanalRouge, imageCanalVert, imageCanalBleu)));
		myBlend.setBottomInput(fxColorAdjust);

		ImagesContenu.setEffect(myBlend);
	}

	// ----------------------------------------------------------------------------------------------------
	//
	//
	//

	public void initialize() {
		System.out.println(getClass().getName() + " Initialisation...");

		// Définition de la relation observé/observable
		ImageObservable imgObs = ImageObservable.getInstance();
		OngletImagesController onImcon = this; // Reference à cette classe spécifiquement et non un new !!!
		imgObs.addObserver(onImcon);

		ImagesSliderOpacite.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				imageOpacite = (double) Integer.parseInt(String.format("%.0f", new_val));
				ImagesLabelOpacite.setText("" + imageOpacite);
				ImagesContenu.setOpacity(imageOpacite / 100);
				ActualisationImagesInfos();
			}
		});

		ImagesSliderZoom.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				Image ImagesContenuTampon = ImageObservable.getInstance().getImageBuffer();
				if (ImagesContenuTampon != null) {
					imageZoom = (double) Integer.parseInt(String.format("%.0f", new_val));
					if (imageZoom == 0) {
						imageZoom++;
					}
					double imageContenuTailleX = ImagesContenuTampon.getWidth();
					double imageContenuTailleY = ImagesContenuTampon.getHeight();
					double imageZoomTailleX = Math.floor(imageContenuTailleX * (imageZoom / 100));
					double imageZoomTailleY = Math.floor(imageContenuTailleY * (imageZoom / 100));
//					double encadrementTailleX = ImagesEncadrement.getWidth();
//					double encadrementTailleY = ImagesEncadrement.getHeight();

					ImagesEncadrement.setHvalue(0.5);
					ImagesEncadrement.setVvalue(0.5);
					// ImagesContenu.setTranslateX(Math.floor(encadrementTailleX - imageZoomTailleX)
					// / 2);
					// ImagesEncadrement.setTranslateY(Math.floor(encadrementTailleY -
					// imageZoomTailleY) / 2);

					ImagesLabelZoom.setText(imageZoom + "%");
					imageDimenssions = imageZoomTailleX + "x" + imageZoomTailleY;
					ImagesContenu.setFitWidth(imageZoomTailleX);
					ImagesContenu.setFitHeight(imageZoomTailleY);
					ActualisationImagesInfos();
				}
			}
		});

		ImagesSliderRotation.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				imageRotation = (double) Integer.parseInt(String.format("%.0f", new_val));
				ImagesLabelRotation.setText(imageRotation + "°");
				ImagesContenu.setRotate(imageRotation);
				ActualisationImagesInfos();
			}
		});
		ImagesSliderRouge.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				imageCanalRouge = (int) Integer.parseInt(String.format("%.0f", new_val));
				ImagesLabelRouge.setText("" + imageCanalRouge);
				ActualisationFiltreRendu();
			}
		});
		ImagesSliderVert.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				imageCanalVert = (int) Integer.parseInt(String.format("%.0f", new_val));
				ImagesLabelVert.setText("" + imageCanalVert);
				ActualisationFiltreRendu();
			}
		});
		ImagesSliderBleu.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				imageCanalBleu = (int) Integer.parseInt(String.format("%.0f", new_val));
				ImagesLabelBleu.setText("" + imageCanalBleu);
				ActualisationFiltreRendu();
			}
		});

		ImagesSliderHUE.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				imageColorAdjustHUEVal = (double) new_val;
				ImagesLabelHUE.setText(String.format("%.2f", new_val));
				ActualisationFiltreRendu();
			}
		});
		ImagesSliderSaturation.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				imageColorAdjustSaturationVal = (double) new_val;
				ImagesLabelSaturation.setText(String.format("%.2f", new_val));
				ActualisationFiltreRendu();
			}
		});
		ImagesSliderLuminosite.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				imageColorAdjustLuminositeVal = (double) new_val;
				ImagesLabelLuminosite.setText(String.format("%.2f", new_val));
				ActualisationFiltreRendu();
			}
		});
		ImagesSliderContraste.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				imageColorAdjustContrasteVal = (double) new_val;
				ImagesLabelContraste.setText(String.format("%.2f", new_val));
				ActualisationFiltreRendu();
			}
		});

		// ----------------------------------------------------------------------------------------------------
		//
		ImagesSliderBloom1.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				ImagesLabelBloom1.setText("" + String.format("%.2f", new_val));
				imageEffectBloom1Val = (double) new_val;
				ActualisationFiltreRendu();
			}
		});
		ImagesSliderBloom2.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				ImagesLabelBloom2.setText("" + String.format("%.2f", new_val));
				imageEffectBloom2Val = (double) new_val;
				ActualisationFiltreRendu();
			}
		});

		ImagesSliderGlow.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				ImagesLabelGlow.setText("" + String.format("%.2f", new_val));
				imageEffectGlowVal = (double) new_val;
				ActualisationFiltreRendu();
			}
		});

		ImagesSliderBlur1.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				ImagesLabelBlur1.setText("" + String.format("%.2f", new_val));
				imageEffectBlur1Val = (double) new_val;
				ActualisationFiltreRendu();
			}
		});
		ImagesSliderBlur2.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				ImagesLabelBlur2.setText("" + String.format("%.2f", new_val));
				imageEffectBlur2Val = (double) new_val;
				ActualisationFiltreRendu();
			}
		});
		ImagesSliderBlur3.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				ImagesLabelBlur3.setText("" + String.format("%.2f", new_val));
				imageEffectBlur3Val = (double) new_val;
				ActualisationFiltreRendu();
			}
		});

		ImagesSliderGblur1.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				ImagesLabelGblur1.setText("" + String.format("%.2f", new_val));
				imageEffectGblur1Val = (double) new_val;
				ActualisationFiltreRendu();
			}
		});

		ImagesSliderMblur1.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				ImagesLabelMblur1.setText("" + String.format("%.2f", new_val));
				imageEffectMblur1Val = (double) new_val;
				ActualisationFiltreRendu();
			}
		});
		ImagesSliderMblur2.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				ImagesLabelMblur2.setText("" + String.format("%.2f", new_val));
				imageEffectMblur2Val = (double) new_val;
				ActualisationFiltreRendu();
			}
		});

	}

}