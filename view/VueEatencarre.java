package chocobar.view;

import chocobar.model.Partie;
import javafx.geometry.Insets;
//import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class VueEatencarre extends VBox implements Observateur{

    private Partie partie;
    private Label lbl_nb;

    public VueEatencarre(Partie model){
        super();
        this.partie = model;
        this.lbl_nb=new Label("");
        this.lbl_nb.setPadding(new Insets(5));
        this.lbl_nb.setText((this.partie.getPlayers())[0]+" : 0 chocos"+"\n"
                +(this.partie.getPlayers())[1]+ " : 0 chocos");
        this.lbl_nb.setStyle("-fx-font: normal bold 13px 'serif'");
        this.partie.ajouterObservateur(this);
        this.getChildren().add(this.lbl_nb);
    }
    @Override
    public void reagir(){
        this.lbl_nb.setText((this.partie.getPlayers())[0]+" : "+(this.partie.getEatenSquare())[0]+ " chocos"+"\n"
                +(this.partie.getPlayers())[1]+ " : "+(this.partie.getEatenSquare())[1]+ " chocos");
    }
}
