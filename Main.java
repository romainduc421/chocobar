package chocobar;

import chocobar.ecouteurs.EcouteurChangePlayer;
import chocobar.ecouteurs.EcouteurReset;
import chocobar.model.Partie;
import chocobar.model.Player;
import chocobar.view.*;
import javafx.application.Application;
import javafx.application.Platform;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



public class Main extends Application {

    private Partie game;
    private int Length, width, nb_limits;
    private Menu menuOption, Menu_difficulty;
    private MenuItem item1, item2, item3, item4, item5, item6;

    public MenuBar menu_opt() {
        this.menuOption = new Menu("Actions");
        this.item1 = new MenuItem("restartOtherDim");
        this.item2 = new MenuItem("renamePlayers");
        this.item6 = new MenuItem("restart");
        this.item3 = new MenuItem("Exit");
        this.Menu_difficulty = new Menu("setDifficulty");
        this.menuOption.getItems().addAll(Menu_difficulty, item1, item2, item3, item6);
        this.item4 = new MenuItem("easy");
        this.item5 = new MenuItem("sticky");
        this.Menu_difficulty.getItems().addAll(item4, item5);
        return new MenuBar(this.menuOption);
    }

    public void init_partie() {

        this.game = (this.Length == 0 && this.width == 0) ?
        new Partie(10, 10) : new Partie(this.Length, this.width);
    }


    @Override
    public void start(Stage primaryStage) {
        MenuBar mB = menu_opt();
        this.item3.setOnAction(evt -> Platform.exit());
        this.item4.setOnAction(evt ->
                System.out.println("jeu facile : nb de chocos à croquer quelconque."));

        this.item6.setOnAction(evt->{
            System.out.println("Recommencer partie");
            Platform.runLater(() -> {
                Main restart = new Main();
                primaryStage.close();
                restart.start(new Stage());
            });
        }
        );
        mB.setTranslateX(6);

        BorderPane root = new BorderPane();
        item1.setOnAction(new EcouteurReset(primaryStage));
        /*item5.setOnAction(evt ->{
            System.out.println("difficulte : nb de chocos à croquer limité");
            Platform.runLater(() -> {
                Main restart = new Main();
                primaryStage.close();
                restart.setNb_limits(6);
                restart.start(new Stage());
            });
        });*/
        this.init_partie();

        VueJoueur viewPlayer = new VueJoueur(game);
        viewPlayer.getChildren().add(mB);

        root.setTop(viewPlayer);
        root.setCenter(new PlateauDeJeu(game));
        root.setBottom(new VueFinDePartie(game));
        VueEatencarre nb_carres_Manges = new VueEatencarre(game);
        root.setRight(nb_carres_Manges);
        item2.setOnAction(new EcouteurChangePlayer(game, nb_carres_Manges, viewPlayer));

        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 3;" +
                "-fx-border-radius: 3;" +
                "-fx-border-color: DARKBLUE;");

        primaryStage.setTitle("Chocobar's clone");
        primaryStage.setScene(new Scene(root, 930, 847));
        primaryStage.sizeToScene();
        primaryStage.setResizable(true);
        primaryStage.show();

    }

    public static void main(String[] args) {

        launch(args);
    }

    public void setDim(int[] dim) {
        this.width = dim[1];
        this.Length = dim[0];
    }
}
