package chocobar.ecouteurs;

import chocobar.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import java.util.Scanner;

public class EcouteurReset implements EventHandler<ActionEvent> {

    private int length, width;
    private Stage primaryStage;

    public EcouteurReset(Stage sc){
        this.primaryStage = sc;
    }

    @Override
    public void handle(ActionEvent evt){
        System.out.println("longueur : ");
        Scanner sc = new Scanner(System.in);
        length = sc.nextInt();
        if(length<0 || length >= 15)
            System.err.println("dimension négative ou trop grande (> 15)");
        System.out.println("largeur : ");
        width = sc.nextInt();
        if (width<0 || width>=15)
            System.err.println("dimension négative ou trop grande (> 15)");
        try{
            Thread.sleep(975);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("restart chocobar's game");
        primaryStage.close();
        Main restart = new Main();
        Platform.runLater( () -> {
            restart.setDim(new int[]{this.length, this.width});
            restart.start(new Stage());
        });
    }

}
