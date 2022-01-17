package chocobar.view;

import chocobar.model.Partie;
//import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


//import java.io.File;


public class VueFinDePartie extends VBox implements Observateur {

    private Label etat_fin;
    private Partie partie;
    private MediaPlayer soundConnexion;
    private Slider vol = new Slider();
    private Label volume;

    public VueFinDePartie(Partie model){
        super();
        this.partie = model;

        this.etat_fin = new Label("Choco-bar game in progress !!");
        this.etat_fin.setMaxWidth(Double.MAX_VALUE);
        this.etat_fin.setAlignment(Pos.CENTER);
        this.etat_fin.setStyle("-fx-font: normal bold 18px 'serif'; -fx-background-color:BEIGE");
        this.volume = new Label("Volume : ");
        this.getChildren().add(this.etat_fin);
        this.partie.ajouterObservateur(this);
    }
    @Override
    public void reagir() {
        if(this.partie.isEndGame()) {
            //Media onConnexion = new Media(new File("src/chocobar/ressources/AkalF.wav").toURI().toString());
            // ne fonctionne pas dans le .jar mais bien dans l'arborescence du projet
            Media onConnexion = new Media(getClass().getResource("/chocobar/ressources/AkalF.wav").toString());
            this.soundConnexion = new MediaPlayer(onConnexion);
            this.vol.setPrefWidth(70.0);
            this.vol.setMinWidth(30);
            this.vol.setValue(100.0);
            this.vol.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable obsv) {
                    if(vol.isPressed())
                        soundConnexion.setVolume(vol.getValue() / 100);
                }
            });
            this.getChildren().addAll(this.volume,this.vol);

            this.etat_fin.setText(this.partie.getVainqueur() + " has won ... \n");

            this.soundConnexion.setCycleCount(3);
            this.soundConnexion.play();

        }
    }
}
