package com.fma.ihm.vlc;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
//import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
//import uk.co.caprica.vlcj.player.MediaPlayerFactory;
//import uk.co.caprica.vlcj.player.direct.BufferFormat;
//import uk.co.caprica.vlcj.player.direct.DirectMediaPlayer;
//import uk.co.caprica.vlcj.player.direct.RenderCallback;

public class OngletVlcController {
	// @formatter:off
	@FXML	private AnchorPane vlcAnchorPane;
	@FXML	private Pane vlcPane;
	@FXML	private Button vlcButton01;
	@FXML	private MediaView vlcMediaView;
	// @formatter:on

	// private EmbeddedMediaPlayerComponent mpc = new
	// EmbeddedMediaPlayerComponent();

	private OngletVlcController instance = null;

	// ----------------------------------------------------------------------------------------------------
	//
	//
	// Gestion des actions utilisateur
	//
	//
	//

	public void vlcButton01Action() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters()
				.addAll(new FileChooser.ExtensionFilter("Movie Files", "*.avi", "*.mkv", "*.ogg", "*.mpg", "*.mpeg"));

		File fichierSelection = fileChooser.showOpenDialog(null);
		if (fichierSelection != null) {
			String fichierCible = fichierSelection.getAbsoluteFile().toURI().toString();
			System.out.println(getClass().getName() + ".vlcButton01Action fichierCible=" + fichierCible);

		}
	}

	public void initialize() {
		System.out.println(getClass().getName() + " Initialisation...");
		instance = this;

		// https://github.com/caprica/vlcj-javafx/tree/master/src/test/java/uk/co/caprica/vlcj/javafx/test
		// https://www.programcreek.com/java-api-examples/?code=apertus-open-source-cinema/elphelvision_eclipse/elphelvision_eclipse-master/src/VLCPlayer.java
		// https://www.programcreek.com/java-api-examples/?code=sk89q/playblock/playblock-master/src/main/java/com/skcraft/playblock/player/MediaRenderer.java#

	}
}
