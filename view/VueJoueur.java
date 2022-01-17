package chocobar.view;

import chocobar.model.Partie;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class VueJoueur extends VBox implements Observateur{

    private Partie partie;
    private Label player;
    public VueJoueur(Partie model){
        super();
        this.player = new Label("It's Lenôtre who begin");
        this.player.setMaxWidth(Double.MAX_VALUE);
        this.player.setAlignment(Pos.CENTER);
        this.player.setPadding(new Insets(9));
        this.player.setTextFill(Color.web("#8B048B"));
        this.player.setStyle("-fx-font-size: 20px ; -fx-text-fill: rgb(250,241,205); " +
                "-fx-border-radius: 8; -fx-font-weight: bold; -fx-background-color: DARKSLATEBLUE;" );
        this.getChildren().add(player);
        this.partie = model;
        this.partie.ajouterObservateur(this);
    }

    @Override
    public void reagir() {

        if(this.partie.isEndGame())
            this.player.setText("no more active players, end of the game");
        else
            this.player.setText("It's "+this.partie.getCurrentJoueur().getName()+"'s turn to play");
    }
}
