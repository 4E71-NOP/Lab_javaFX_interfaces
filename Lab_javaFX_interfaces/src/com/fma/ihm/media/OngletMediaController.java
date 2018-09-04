package com.fma.ihm.media;

import java.io.File;
import java.text.DecimalFormat;

import com.fma.ihm.barredestatut.BarreDeStatutController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

public class OngletMediaController {

	// @formatter:off
    @FXML	private Button MediaBoutonSelectionFichier;
    
    @FXML	private AnchorPane MediaAnchorPane;
    @FXML	private MediaView MediaMediaView;

    @FXML	private Slider MediaSliderLecture;
    @FXML	private Button MediaBoutonStop;
    @FXML	private Button MediaBoutonLecture;
    @FXML	private Label MediaLabelLecture;

    @FXML	private Slider MediaSliderSon;
	@FXML	private Label MediaLabel;

    @FXML	private Slider MediaSliderRate;

	// @formatter:on

	private Duration mpDuree;
	private MediaPlayer mp = null;
	ImageView iconeLecture = new ImageView(new Image(
			getClass().getResourceAsStream("/com/fma/ressources/media/image/icone/icone_media_player.lecture.png")));
	ImageView iconePause = new ImageView(new Image(
			getClass().getResourceAsStream("/com/fma/ressources/media/image/icone/icone_media_player.pause.png")));

	DecimalFormat compteurFormat = new DecimalFormat("00");

	// https://docs.oracle.com/javafx/2/media/playercontrol.htm
	// ----------------------------------------------------------------------------------------------------
	//
	//
	// Gestion des actions utilisateur
	//
	//
	//
	public void MediaBoutonSelectionFichierAction() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters()
				.addAll(new FileChooser.ExtensionFilter("Video Files", "*.avi", "*.mpeg", "*.mp4", "*.mpg", "*.mkv"));

		File fichierSelection = fileChooser.showOpenDialog(null);
		if (fichierSelection != null) {
			String fichierCible = fichierSelection.getAbsoluteFile().toURI().toString();
			System.out
					.println(getClass().getName() + ".MediaBoutonSelectionFichierAction fichierCible=" + fichierCible);
			try {
				if (mp != null) {
					ActivationProcessusStop();
					mp.dispose();
				}

				Media m = new Media(fichierCible);
				mp = new MediaPlayer(m);
				ActivationProcessusLectureFichierLu();

			} catch (MediaException me) {
				System.out.println(me.getMessage());
				BarreDeStatutController.getInstance().setBarreDeStatutMessage(me.getMessage());
			}
		}
	}

	public void MediaBoutonLectureAction() {
		ActivationProcessusLecture();
	}

	public void MediaBoutonStopAction() {
		ActivationProcessusStop();
	}

	public void MediaMediaViewAction() {
		// ActivationProcessusLecture ();

	}

	public void MediaSliderSonAction() {
		System.out.println("MediaSliderSonAction" + MediaSliderSon.getValue());
		if (mp != null) {
			mp.setVolume((MediaSliderSon.getValue() / 100));
		}
	}

	public void MediaSliderLectureAction() {
		System.out.println("MediaSliderLectureAction :" + MediaSliderLecture.getValue());
		SliderMaj(MediaSliderLecture, MediaSliderLecture.getValue());
		if (mp != null) {
			mpDuree = mp.getMedia().getDuration();
			mp.seek(mpDuree.multiply(MediaSliderLecture.getValue() / 100.0));
		}
	}

	public void MediaSliderRateAction() {
		System.out.println("MediaSliderRateAction :" + MediaSliderRate.getValue());
		if (mp != null) {
			mp.setRate(MediaSliderRate.getValue());
		}
	}

	private void SliderMaj(Slider s, double pos) {
		StackPane trackPane = (StackPane) s.lookup(".track");
		String style = "-fx-background-color: linear-gradient(to right, #204080 " + MediaSliderLecture.getValue()
				+ "%, #80402040 " + MediaSliderLecture.getValue() + "%);";
		trackPane.setStyle(style);
		s.setValue(pos);
	}

	// --------------------------------------------------------------------------------

	private void ActivationProcessusLectureFichierLu() {
		MediaBoutonLecture.setGraphic(iconePause);
		MediaSliderRate.setValue(1);
		mp.setRate(1);

		MediaSliderSon.setValue(100);
		mp.setVolume(1);

		mp.setAutoPlay(true);
		mpDuree = mp.getMedia().getDuration();

		MediaMediaView.setMediaPlayer(mp);
		MediaMediaView.setPreserveRatio(true);
		MediaMediaView.setFitWidth(MediaAnchorPane.getWidth());
		MediaMediaView.setFitHeight(MediaAnchorPane.getHeight());

		mp.currentTimeProperty().addListener((obs, oldValue, newValue) -> {
			int nTotalMinutes = (int) newValue.toMinutes();
			int nTotalSeconds = (int) newValue.toSeconds();
			int nSecond = nTotalSeconds - (nTotalMinutes * 60);
			SliderMaj(MediaSliderLecture, (newValue.toSeconds() / mpDuree.toSeconds()) * 100);
			MediaLabelLecture.setText(compteurFormat.format(nTotalMinutes) + ":" + compteurFormat.format(nSecond));
		});
		SliderMaj(MediaSliderLecture, 0);
		MediaSliderLecture.setVisible(true);

		// mp.setMute(true);
		// mp.setBalance(0.5);
		// mp.getCurrentTime().toSeconds() // donne la position de la lecture
		// mp.getStatus().equals(Status.PLAYING)
		// mp.setRate(1); // 1 vitesse normal

	}

	private void ActivationProcessusLecture() {
		if (mp != null) {
			Status s = mp.getStatus();
			if (s == Status.UNKNOWN || s == Status.HALTED) {
				return;
			}
			if (s == Status.PLAYING) {
				mp.pause();
				MediaBoutonLecture.setGraphic(iconeLecture);
			}
			if (s == Status.PAUSED) {
				mp.play();
				MediaBoutonLecture.setGraphic(iconePause);
			}
			if (s == Status.STOPPED) {
				mp.play();
				MediaBoutonLecture.setGraphic(iconePause);
				MediaSliderSon.setValue(100);
				mp.setVolume(1);
			}
		}
	}

	private void ActivationProcessusStop() {
		if (mp != null) {
			mp.stop();
			SliderMaj(MediaSliderLecture, 0);
			MediaLabelLecture.setText("--:--");
			MediaBoutonLecture.setGraphic(iconeLecture);

			MediaSliderSon.setValue(100);
			mp.setVolume(1);

			MediaSliderRate.setValue(1);
			mp.setRate(1);

		}
	}

	private void FitMediaMediaView() {
		MediaMediaView.setFitWidth(MediaAnchorPane.getWidth() - 8);
		MediaMediaView.setFitHeight(MediaAnchorPane.getHeight() - 8);
	}

	// --------------------------------------------------------------------------------

	public void initialize() {
		System.out.println(getClass().getName() + " Initialisation...");

		iconeLecture.setFitWidth(32);
		iconeLecture.setFitHeight(32);
		iconePause.setFitWidth(32);
		iconePause.setFitHeight(32);

		MediaAnchorPane.widthProperty().addListener((obs, oldValue, newValue) -> {
			if (mp != null) {
				FitMediaMediaView();
			}
		});

		MediaAnchorPane.heightProperty().addListener((obs, oldValue, newValue) -> {
			if (mp != null) {
				FitMediaMediaView();
			}
		});

	}

}
