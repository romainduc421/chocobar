package chocobar.view;

import chocobar.ecouteurs.EcouteurBouton;
import chocobar.model.Partie;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


public class PlateauDeJeu extends GridPane implements Observateur
{
    private Button[][] grid;
    private Partie partie;

    public PlateauDeJeu(Partie model){
        super();
        this.grid = new Button[model.getDim()[0]][model.getDim()[1]];
        this.partie = model;
        this.setAlignment(Pos.TOP_LEFT);

        this.setStyle("-fx-background-color: #FFFFCF");

        Button burger = new Button
        ("",
            new ImageView
            (
                new Image
                (
                    getClass().getResourceAsStream("/chocobar/ressources/burger.png"),57,57,false,false
                )
            )
        );
        burger.setStyle(
                "-fx-effect:dropshadow(one-pass-box,darkred,8,0.0,2,0);" +
                        "-fx-border-radius:20"
        );
        this.grid[0][0] = burger;
        burger.setOnMouseClicked(new EcouteurBouton(0,0,this.partie));
        this.add(burger,0,0);
        this.grid_fill();
        this.partie.ajouterObservateur(this);
    }

    public void grid_fill(){
        int k,l;
        for(k = 0; k < this.partie.getDim()[0]; k++){
            for ( l = 0; l < this.partie.getDim()[1]; l++) {
                if (l != 0 || k != 0){
                    Button choco = new Button
                    ("",
                            new ImageView
                            (
                                    new Image
                                    (
                                        getClass().getResourceAsStream("/chocobar/ressources/chocolate.png"), 54.5, 54.5, true, true
                                    )
                            )
                    );
                    choco.setStyle("-fx-effect:dropshadow(one-pass-box,black,8,0.0,2,0);-fx-border-radius:20");
                    this.grid[k][l] = choco;
                    choco.setOnMouseClicked(new EcouteurBouton(k,l,this.partie));
                    this.add(choco,k,l);
                }
            }
        }
    }

    @Override
    public void reagir() {

        int k, l;

        for (k = 0; k < this.partie.getDim()[0]; k++) {
            for (l = 0; l < this.partie.getDim()[1]; l++) {
                if (partie.getCarre(k, l)) {
                    //this.getChildren().remove(this.grid[k][l]);       //à enlever pour l'extension (on ne delete plus le bouton)
                    this.grid[k][l].setGraphic(new ImageView((Image) null));

                    if (this.grid[k][l].getText().equals("")) {
                    /*condition pour reconnaitre ceux qui ont deja ete colores
                    et ne pas les colorer une seconde fois*/
                        this.grid[k][l] = new Button(" ");
                        this.grid[k][l].setPrefSize(67, 67);
                        if (!this.partie.getGameTurn()) {
                            this.grid[k][l].setStyle("-fx-background-color: LIGHTCYAN");
                            this.add(this.grid[k][l], k, l);
                        } else {
                            this.grid[k][l].setStyle("-fx-background-color: LIGHTGREY");
                            this.add(this.grid[k][l], k, l);
                        }
                    }
                }
            }
        }
    }
}
