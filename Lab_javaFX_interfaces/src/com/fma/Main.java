package com.fma;

import java.io.IOException;

//import org.omg.Messaging.SyncScopeHelper;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
//import uk.co.caprica.vlcj.binding.LibVlc;
//import uk.co.caprica.vlcj.discovery.NativeDiscovery;

/**
 * @author faust
 * 
 *         Auteur : Faust MARIA DE ARAVALO - Tous droits réservés. Projet
 *         protfolio sur JavaFX2
 * 
 * 
 *         L'installation de openjfx sur linux peut être nécessaire pour faire
 *         fonctionner le programme en jar executable. sudo apt-get install
 *         openjfx
 * 
 *         Sur environnement Linux il se peut que le lancement via l'icone soit
 *         mal pris en charge Dans ce cas il faut créer un fichier qui permet de
 *         définir un mode opératoire
 * 
 *         Dans /usr/share/applications créer "java.desktop"
 * 
 *         [Desktop Entry] Name=Java JRE Comment=Java GenericName=Java
 *         Keywords=java Exec=java -jar %f Terminal=false X-MultipleArgs=false
 *         Type=Application MimeType=application/x-java-archive
 *         StartupNotify=true
 * 
 *         Egalement : l'initialisation du controlleur est lancée s'il n'y a pas
 *         d'argument fournis S'ils sont fournis il faut implémenter
 *         "initializable"
 * 
 */
public class Main extends Application {

	// https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html
	private static String[] listeCle = { "file.separator", "java.class.path", "java.home", "java.vendor",
			"java.vendor.url", "java.version", "line.separator", "os.arch", "os.name", "os.version", "path.separator",
			"user.dir", "user.home", "user.name" };

	@Override
	public void start(Stage primaryStage) {

		// boolean found = new NativeDiscovery().discover();
		// System.out.println(found);
		// System.out.println(LibVlc.INSTANCE.libvlc_get_version());

		Parent vueLancementAutonome;
		try {
			vueLancementAutonome = FXMLLoader.load(getClass().getResource("ihm/principal/Principal.fxml"));
			Scene sceneLancementAutonome = new Scene(vueLancementAutonome);

			primaryStage.setScene(sceneLancementAutonome);
			primaryStage.setTitle("Lab JavaFX Interfaces");
			primaryStage.getIcons().add(new Image(
					getClass().getResourceAsStream("ressources/media/image/icone/Lab_javaFX_interfaces_icone.png")));
			primaryStage.show();

			sceneLancementAutonome.setOnKeyPressed(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent ke) {
					System.out.println("Clavier:" + ke.getText() + "/" + ke.getCode());
				}
			});

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		for (String l : listeCle) {
			System.out.println(l + " : " + System.getProperties().get(l));
		}
		launch(args);
	}

}
