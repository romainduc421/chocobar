package chocobar.ecouteurs;

import chocobar.model.Partie;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class EcouteurBouton implements EventHandler<MouseEvent> {

    private final Partie partie;
    private final int row, col;
    public EcouteurBouton(int k, int l, Partie model){
        this.row = k;
        this.col = l;
        this.partie = model;
    }
    @Override
    public void handle(MouseEvent evt){
        partie.croquer(this.row,this.col);
    }
}
