package chocobar.model;
//import java.util.Observable;
import chocobar.view.Observateur;

import java.util.ArrayList;
import java.util.List;

public class Partie {

    private List<Observateur> obs;
    private Player player1, player2;
    private int nbCarres_p1, nbCarres_p2;
    private boolean gameTurn, endGame;
    private PlaqueDeChocolat milka;
    private int difficulte;


    public Partie(int nbLig, int nbCol) {
        this.init(nbLig, nbCol);
    }

    public Partie(int nbLig, int nbCol, int difficulte){
        this.difficulte = difficulte;
        this.init(nbLig,nbCol);
    }

    public void init(int nbLig, int nbCol){
        this.obs = new ArrayList<>(4);
        this.gameTurn = false;  //pass to the next player
        this.endGame = false;   //game's ending
        this.milka = new PlaqueDeChocolat(nbLig,nbCol);
        this.player1 = new Player("Lenôtre");
        this.player2 = new Player("Loiseau");
    }

    public void croquer(int k, int l) {
        int nb_croque = 0;
        int row, col;
        if (k == 0 && l == 0){
            nb_croque--;        //le burger n'est pas considere comme un choco croqué..
            this.endGame = !this.endGame;
        }
        for (row = k; row <= this.milka.getDims()[0] - 1; row++) {
            for (col = l; col <= this.milka.getDims()[1] - 1; col++) {
                if (this.milka.getCarreChoco(row, col))
                    nb_croque--;
                this.milka.setCarreChoco(row, col, true);
                nb_croque++;
            }
        }

        if (getCurrentJoueur().getName().equals(player1.getName()))
            this.nbCarres_p1 += nb_croque;
        else        //getCurrentJoueur().getName().equals(player2.getName())
            this.nbCarres_p2 += nb_croque;
        this.prevenirObservateurs();
        if (!this.endGame)
            this.changeGameTurn();
    }


    public void ajouterObservateur(Observateur obsv) {
        if(obsv == null)
            throw new NullPointerException();
        else if(!obs.contains(obsv))
            this.obs.add(obsv);
        else
            return;
    }

    public void prevenirObservateurs() {
        for (Observateur obsv : this.obs)
            obsv.reagir();
    }

    public boolean getCarre(int k, int l) {
        return this.milka.getCarreChoco(k,l);
    }

    public Player getCurrentJoueur() {
        Player res ;
        if (!this.gameTurn)
            res = this.player1;
        else
            res = this.player2;
        return res;
    }

    public boolean isEndGame() {
        return endGame;
    }

    public Player getVainqueur(){
        if(!getGameTurn())
            return player2;
        else
            return player1;
    }

    /**
     * changer de joueur
     */
    public void changeGameTurn() {
        this.gameTurn = !this.gameTurn;
    }

    public boolean getGameTurn() {
        return gameTurn;
    }

    public int[] getEatenSquare(){
        return new int[]{nbCarres_p1,nbCarres_p2};
    }

    public String[] getPlayers(){
        return new String[]{this.player1.getName(),this.player2.getName()};
    }

    public int[] getDim() {
        return this.milka.getDims();
    }

    public void setPlayers(String... players){
        this.player2.setNom(players[1]);
        this.player1.setNom(players[0]);
    }

}
