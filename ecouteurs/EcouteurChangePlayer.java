package chocobar.ecouteurs;

import chocobar.model.Partie;
import chocobar.view.VueEatencarre;
import chocobar.view.VueJoueur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.Scanner;

public class EcouteurChangePlayer implements EventHandler<ActionEvent> {
    private Partie model;
    private VueEatencarre nb_carres_manges;
    private VueJoueur pl_view;
    public EcouteurChangePlayer(Partie game, VueEatencarre view, VueJoueur viewPl){
        this.model = game;
        this.nb_carres_manges = view;
        this.pl_view = viewPl;
    }
    @Override
    public void handle(ActionEvent evt){
        System.out.println("nom joueur 1 : ");
        Scanner sc = new Scanner(System.in);
        String prmpt = sc.nextLine();
        System.out.println("nom joueur 2 : ");
        String prmpt2 = sc.nextLine();
        model.setPlayers(new String[]{prmpt,prmpt2});
        this.nb_carres_manges.reagir();
        this.pl_view.reagir();
    }
}
